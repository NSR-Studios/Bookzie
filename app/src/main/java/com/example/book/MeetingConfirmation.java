package com.example.book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;


import org.parceler.Parcels;

import java.util.List;

public class MeetingConfirmation extends AppCompatActivity {

    public MeetingConfirmation() {
        // Required empty public constructor
    }

    private TextView mc_title;
    private TextView mc_isbn;
    private TextView mc_location;
    private TextView mc_price;
    private TextView mc_username;
    private TextView mc_username2;
    private ImageView buyerimage;
    private ImageView sellerimage;
    private ImageView bookimage;
    private Button cancel;
    private Button button2;
    private Button edit;
    private Button confirm;
    private TextView time2;
    private TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_confirmation);

        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        actionBar.setTitle("");
        actionBar.setDisplayShowCustomEnabled(true);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.logo, null);
        actionBar.setCustomView(view);

        mc_title = findViewById(R.id.mc_title);
        mc_isbn = findViewById(R.id.mc_isbn);
        mc_price = findViewById(R.id.mc_price);
        mc_location = findViewById(R.id.mc_location);
        mc_username = findViewById(R.id.mc_username);
        mc_username2 = findViewById(R.id.mc_username2);
        buyerimage = findViewById(R.id.buyerimage);
        sellerimage = findViewById(R.id.sellerimage);
        bookimage = findViewById(R.id.bookimage);
        button2 = findViewById(R.id.button2);
        cancel = findViewById(R.id.button4);
        confirm = findViewById(R.id.confirm);
        time2 = findViewById(R.id.time2);
        date = findViewById(R.id.date);
        edit = findViewById(R.id.edit);

        Transaction transaction = Parcels.unwrap(getIntent().getParcelableExtra("Transaction"));
        mc_username.setText(transaction.getSeller().getUsername());
        mc_username2.setText(transaction.getBuyer().getUsername());
        mc_location.setText(transaction.getLocation());
        time2.setText(transaction.getTime());
        date.setText(transaction.getDate());
        mc_title.setText(transaction.getPost().getBookTitle());

        String longitude;
        String latitude;
        if(transaction.getLocation() == "Campus Center"){
            longitude = "40.7431";
            latitude = "-74.1784";
        }
        else if(transaction.getLocation() == "Library"){
            longitude = "40.7437";
            latitude = "-74.1781";
        }
        else if(transaction.getLocation() == "Gym"){
            longitude = "40.7415";
            latitude = "-74.1801";
        }
        else if(transaction.getLocation() == "Village Market"){
            longitude = "40.7414";
            latitude = "-74.1800";
        }
        else if(transaction.getLocation() == "Bookstore"){
            longitude = "40.7412";
            latitude = "-74.1781";
        }
        else{
            longitude = "40.7412";
            latitude = "-74.1781";

        }


        mc_isbn.setText(String.valueOf(transaction.getPost().getBookTitle()));
        mc_price.setText(String.valueOf(transaction.getPost().getPrice()) + "$");
        String url = transaction.getPost().getImageUrl();
        url = url.substring(0,4) + "s" + url.substring(4);
        Glide.with(this).load(url).into(bookimage);
        String url2 = transaction.getBuyer().getParseFile("ProfilePic").getUrl();
        Glide.with(this).load(url2).circleCrop().into(buyerimage);
        String url3 = transaction.getSeller().getParseFile("ProfilePic").getUrl();
        Glide.with(this).load(url3).circleCrop().into(sellerimage);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseQuery<Request> query = ParseQuery.getQuery(Request.class);
                //get all the transactions
                query.include(Request.Requester);
                query.include(Request.Seller);
                query.include(Request.Postid);
                query.include(Request.Status);

                query.whereEqualTo(Request.Requester, transaction.getBuyer());
                query.whereEqualTo(Request.Status, "false");
                query.findInBackground(new FindCallback<Request>() {
                    @Override
                    public void done(List<Request> requests, ParseException e) {
                        if (e != null) {
                            Log.e("singleTransaction","Issue with getting pending transactions",e);
                        }
                        for (Request request : requests) {
                            request.setStatus("true");
                            break;
                        }
                    }
                });
                Post post = transaction.getPost();
                post.setMarkAsCompleted();
                post.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(com.parse.ParseException e) {
                        if( e != null){
                            Log.e("TAG", "Error while saving", e);
                        }
                        Log.i("TAG", "Transaction was successful!!" );
                    }
                });
                Intent i = new Intent(MeetingConfirmation.this, MainActivity.class);
                startActivity(i);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Post post = transaction.getPost();
                post.setMeetingSet("false");
                post.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(com.parse.ParseException e) {
                        if( e != null){
                            Log.e("TAG", "Error while saving", e);
                        }
                        Log.i("TAG", "Meeting was unset" );
                    }
                });
                Log.i("check", ""+post.getMeetingSet() );
                transaction.setCanceled("true");
                transaction.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(com.parse.ParseException e) {
                        if( e != null){
                            Log.e("TAG", "Error while saving", e);
                        }
                        Log.i("TAG", "Transaction was cancelled!!" );
                    }
                });
                Intent i = new Intent(MeetingConfirmation.this, MainActivity.class);
                startActivity(i);
            }
        });



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + longitude + "," + latitude);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction.setCanceled("true");
                ParseQuery<Request> query = ParseQuery.getQuery(Request.class);
                //get all the transactions
                query.include(Request.Requester);
                query.include(Request.Seller);
                query.include(Request.Postid);
                query.include(Request.Status);

                query.whereEqualTo(Request.Requester, transaction.getBuyer());
                query.whereEqualTo(Request.Status, "false");
                query.findInBackground(new FindCallback<Request>() {
                    @Override
                    public void done(List<Request> requests, ParseException e) {
                        if (e != null) {
                            Log.e("singleTransaction","Issue with getting pending transactions",e);
                        }
                        for (Request request : requests) {
                            Intent i = new Intent(MeetingConfirmation.this, ConfirmTransaction.class);
                            i.putExtra("Request", Parcels.wrap(request));
                            startActivity(i);
                            break;
                        }
                    }
                });
            }
        });

    }

}
