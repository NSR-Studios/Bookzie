package com.example.book;

import androidx.appcompat.app.AppCompatActivity;

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
    private Button changeTime;
    private Button changeLocation;

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
        cancel = findViewById(R.id.cancel);
        changeTime = findViewById(R.id.changeTime);
        changeLocation = findViewById(R.id.changeLocation);

        Transaction transaction = Parcels.unwrap(getIntent().getParcelableExtra("Transaction"));
        mc_username.setText(transaction.getSeller().getUsername());
        mc_username2.setText(transaction.getBuyer().getUsername());
        mc_location.setText(transaction.getLocation());
        mc_isbn.setText(String.valueOf(transaction.getPost().getBookTitle()));
        mc_price.setText(String.valueOf(transaction.getPost().getPrice()) + "$");
        String url = transaction.getPost().getImageUrl();
        url = url.substring(0,4) + "s" + url.substring(4);
        Glide.with(this).load(url).into(bookimage);
        String url2 = transaction.getBuyer().getParseFile("ProfilePic").getUrl();
        Glide.with(this).load(url2).circleCrop().into(buyerimage);
        String url3 = transaction.getSeller().getParseFile("ProfilePic").getUrl();
        Glide.with(this).load(url3).circleCrop().into(sellerimage);

        changeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        changeLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}