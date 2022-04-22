package com.example.book;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    private ImageView cover2;

    private RecyclerView requester;
    protected RequestAdapter adapter;
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

        requester = findViewById(R.id.requesters);
        listRequesters = new ArrayList<>();
        adapter = new RequestAdapter(this, listRequesters);
        requester.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(this);
        requester.setLayoutManager(linearLayoutManager);
        queryRequesters(request.getPost().getObjectId());
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