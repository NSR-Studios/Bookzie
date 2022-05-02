package com.example.book;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class singleTransaction extends AppCompatActivity {

    private TextView tvTitle2;
    private TextView tvCategory2;
    private TextView tvCondition2;
    private TextView tvPrice2;
    private TextView tvDescription2;
    private TextView checkStatus;
    private TextView listRequesters1;
    private ImageView cover2;
    private Button meetingDetails;

    private RecyclerView requester;
    protected listRequestersAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    protected List<Request> listRequesters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_single);

        tvTitle2 = findViewById(R.id.tvTitle2);;
        tvCategory2 = findViewById(R.id.tvCategory2);
        tvCondition2 = findViewById(R.id.tvCondition2);;
        tvPrice2 = findViewById(R.id.tv_Price2);
        tvDescription2 = findViewById(R.id.tvDescription2);;
        cover2 = findViewById(R.id.cover2);
        requester = findViewById(R.id.requesters);
        checkStatus = findViewById(R.id.checkStatus);
        listRequesters1 = findViewById(R.id.listRequesters);
        meetingDetails = findViewById(R.id.meetingDetails);
        meetingDetails.setVisibility(View.GONE);

        linearLayoutManager = new LinearLayoutManager(this);

        Request request = Parcels.unwrap(getIntent().getParcelableExtra("Request"));
        tvTitle2.setText(request.getPost().getBookTitle());
        tvCategory2.setText(request.getPost().getBookCategory());
        tvCondition2.setText(request.getPost().getCondition());
        tvPrice2.setText(String.valueOf(request.getPost().getPrice()));
        tvDescription2.setText(request.getPost().getBookDescription());
        ParseFile image = request.getPost().getFrontImage();
        String imageURL = request.getPost().getImageUrl();
        if (imageURL != null)
        {
            String url = request.getPost().getImageUrl();
            url = url.substring(0,4) + "s" + url.substring(4);
            Log.i("Link", "LinkToImage: "+ url);
            Glide.with(this).load(url).into(cover2);
        }
        else if (image != null) {
            Glide.with(this).load(image.getUrl()).into(cover2);
        }

        if (request.getPost().getMeetingSet() != null) {
            if (request.getRequester().getUsername().equals(ParseUser.getCurrentUser().getUsername())) {
                checkStatus.setText("Waiting for Seller Response");
            } else if (request.getPost().getMeetingSet().equals("true") && !(request.getRequester().getUsername().equals(ParseUser.getCurrentUser().getUsername()))) {
                queryCheck(request.getPost().getObjectId());
            } else {
                listRequesters1.setText("Requesters");
                listRequesters = new ArrayList<>();
                adapter = new listRequestersAdapter(this, listRequesters);
                requester.setAdapter(adapter);
                requester.setLayoutManager(linearLayoutManager);
                Log.i("singleTransaction", request.getPost().getObjectId());
                queryRequesters(request.getPost().getObjectId());
            }
        }

    }

    protected void queryCheck(String objectId){
        ParseQuery<Transaction> query = ParseQuery.getQuery(Transaction.class);
        query.include(Transaction.KEY_POST);
        query.include(Transaction.CANCELED);
        Log.i("object id", objectId);
        Log.i("key post", Transaction.KEY_POST);
        query.include(objectId);
        query.orderByDescending("createdAt");
        query.setLimit(1);
        query.findInBackground(new FindCallback<Transaction>() {
            @Override
            public void done(List<Transaction> transactions, ParseException e) {
                if (e != null) {
                    Log.e("singleTransaction","Issue with getting cancelled transaction details",e);
                }
                Log.i("transaction id", String.valueOf(transactions));
                for (Transaction transaction : transactions){
                    if (transaction.getCanceled() != "false"){
                        Log.i("check", transaction.getTime());
                        listRequesters1.setText("Meeting Confirmation");
                        meetingDetails.setVisibility(View.VISIBLE);
                        requester.setVisibility(View.GONE);
                        checkStatus.setVisibility(View.GONE);
                    }
                }
            }
        });

        meetingDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDetails(objectId);
            }
        });
    }

    protected void getDetails(String objectId){
        ParseQuery<Transaction> query = ParseQuery.getQuery(Transaction.class);
        query.include(Transaction.KEY_LOCATION);
        query.include(Transaction.KEY_BUYER);
        query.include(Transaction.KEY_DATE);
        query.include(Transaction.KEY_POST);
        query.include(Transaction.KEY_SELLER);
        query.include(Transaction.CANCELED);
        query.include(Transaction.KEY_TIME);
        query.include(objectId);
        query.orderByDescending("createdAt");
        query.setLimit(1);
        query.findInBackground(new FindCallback<Transaction>() {
            @Override
            public void done(List<Transaction> transactions, ParseException e) {
                if (e != null) {
                    Log.e("Transaction Details","Issue with getting transaction details",e);
                }
                Log.i("transaction id", String.valueOf(transactions));
                for (Transaction transaction : transactions){
                    Intent i = new Intent(singleTransaction.this, MeetingConfirmation.class);
                    i.putExtra("Transaction", Parcels.wrap(transaction));
                    startActivity(i);
                }
            }
        });
    }

    protected void queryRequesters(String objectId) {
        ParseQuery<Request> query = ParseQuery.getQuery(Request.class);
        //get all the transactions
        query.include(Request.Requester);
        query.include(Request.Seller);
        query.include(Request.Postid);
        query.include(Request.Status);
        query.whereEqualTo(Request.Requester, ParseUser.getCurrentUser());
        query.whereEqualTo(Request.Status, "false");
        query.findInBackground(new FindCallback<Request>() {
            @Override
            public void done(List<Request> requests, ParseException e) {
                if (e != null) {
                    Log.e("singleTransaction","Issue with getting pending transactions",e);
                }
                for (Request request : requests) {
                    //To do
                    Log.i("singleTransaction", "Pending Seller " + Request.getStatus());
                    addRequesters(requests);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    protected void addRequesters(List<Request> requests) {
        listRequesters.addAll(requests);
        adapter.notifyDataSetChanged();
    }
}