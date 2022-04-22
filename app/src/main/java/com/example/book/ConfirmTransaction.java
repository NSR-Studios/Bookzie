package com.example.book;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ConfirmTransaction extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView titlePage;
    private ImageView imageCT;
    private TextView titleCT;
    private TextView categoryCT;
    private TextView priceCT;
    private TextView conditionCT;
    private TextView sellerCT;
    private TextView buyerCT;
    private ImageView imageBuyerCT;
    private ImageView imageSellerCT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_transaction);
        titlePage = findViewById(R.id.titlePage);
        titleCT = findViewById(R.id.titleCT);
        imageCT = findViewById(R.id.imageCT);
        categoryCT = findViewById(R.id.categoryCT);
        priceCT = findViewById(R.id.priceCT);
        conditionCT = findViewById(R.id.conditionCT);
        buyerCT = findViewById(R.id.buyerCT);
        sellerCT = findViewById(R.id.sellerCT);
        imageBuyerCT = findViewById(R.id.imageBuyerCT);
        imageSellerCT = findViewById(R.id.imageSellerCT);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.locations_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        List<String> list = new ArrayList<>();
        list.add("mercury");
        list.add("sun");
        list.add("moon");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ConfirmTransaction.this,android.R.layout.simple_list_item_1,list);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(arrayAdapter);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setOnItemSelectedListener(this);

        /*spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("Confirm",""+adapterView.getItemAtPosition(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/

        Request request = Parcels.unwrap(getIntent().getParcelableExtra("Request"));
        Post post = request.getPost();
        titleCT.setText(request.getPost().getBookTitle());
        ParseFile image = post.getFrontImage();
        String imageURL = post.getImageUrl();
        if (imageURL != null)
        {
            String url = post.getImageUrl();
            url = url.substring(0,4) + "s" + url.substring(4);
            Log.i("Link", "LinkToImage: "+ url);
            Glide.with(this).load(url).into(imageCT);
        }
        else if (image != null) {
            Glide.with(this).load(image.getUrl()).into(imageCT);
        }

        String url = request.getRequester().getParseFile("ProfilePic").getUrl();
        Glide.with(this).load(url).circleCrop().into(imageBuyerCT);

        String url2 = request.getSeller().getParseFile("ProfilePic").getUrl();
        Glide.with(this).load(url2).circleCrop().into(imageSellerCT);

        //NEED TO ADD VENMO TO THE DB!!!!!!!


        categoryCT.setText("Category: "+post.getBookCategory());
        priceCT.setText("Price:  $ "+post.getPrice());
        conditionCT.setText("Condition: "+post.getCondition());

        sellerCT.setText("Seller: "+request.getSeller().getUsername());
        buyerCT.setText("Buyer: "+request.getRequester().getUsername());

        titlePage.setText("Confirm Transaction");
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Log.i("Confirm",""+adapterView.getItemAtPosition(i));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}