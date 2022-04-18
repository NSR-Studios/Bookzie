package com.example.book;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.net.MalformedURLException;
import java.net.URL;

public class BookDetail extends AppCompatActivity {

    private TextView tvTitle;
    private TextView tvCondition;
    private TextView tvPrice;
    private TextView tvCategory;
    private TextView tvDescription;
    private ImageView Front;
    private ImageView Back;
    private ImageView Cover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        tvTitle = findViewById(R.id.tvTitle);
        tvCondition = findViewById(R.id.tvCondition);
        tvDescription = findViewById(R.id.tvDescription);
        tvCategory = findViewById(R.id.tvCategory);
        tvPrice = findViewById(R.id.tv_Price);
        Front = findViewById(R.id.Front);
        Back = findViewById(R.id.Back);
        Cover = findViewById(R.id.Cover);

        Post post = Parcels.unwrap(getIntent().getParcelableExtra("Post"));
        tvTitle.setText(post.getBookTitle());
        tvCondition.setText(post.getCondition());
        tvPrice.setText(String.valueOf(post.getPrice()) + "$");
        tvCategory.setText(post.getBookCategory());
        tvDescription.setText(post.getBookDescription());
        ParseFile FrontImage = post.getFrontImage();
        String url = post.getImageUrl();
        url = url.substring(0,4) + "s" + url.substring(4);
        Log.i("Link", "LinkToImage: "+ url);
        Glide.with(this).load(FrontImage.getUrl()).into(Front);
        ParseFile BackImage = post.getBackImage();
        Glide.with(this).load(BackImage.getUrl()).into(Back);
        Glide.with(this).load(url).into(Cover);

    }
}
