package com.example.book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;

public class MeetingConfirmation extends AppCompatActivity {

    public MeetingConfirmation() {
        // Required empty public constructor
    }

    private TextView mc_isbn;
    private TextView mc_location;
    private TextView mc_price;
    private TextView mc_username;
    private TextView mc_username2;
    private ImageView buyerimage;
    private ImageView sellerimage;
    private ImageView bookimage;
    private Button cancel;
    private Button edit;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_confirmation);

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
        edit = findViewById(R.id.edit);

        Transaction transaction = Parcels.unwrap(getIntent().getParcelableExtra("Transaction"));
        mc_username.setText(transaction.getSeller().getUsername());
        mc_username2.setText(transaction.getBuyer().getUsername());
        mc_location.setText(transaction.getLocation());
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

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction.setCanceled("true");
                Intent i = new Intent(MeetingConfirmation.this, MainActivity.class);
                startActivity(i);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction.setCanceled("true");
                Intent i = new Intent(MeetingConfirmation.this, ConfirmTransaction.class);
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


    }
}
