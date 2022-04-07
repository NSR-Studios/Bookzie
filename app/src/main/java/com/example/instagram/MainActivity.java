package com.example.instagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.instagram.fragments.allListingsFragment;
import com.example.instagram.fragments.createFragment;
import com.example.instagram.fragments.transactionFragment;
import com.example.instagram.fragments.userFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        actionBar.setTitle("");
        actionBar.setDisplayShowCustomEnabled(true);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.custom2, null);
//        actionBar.setCustomView(view);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.action_home:
                        fragment = new allListingsFragment();
                        break;
                    case R.id.action_create:
                        fragment = new createFragment();
                        break;
                    case R.id.action_user:
                        fragment = new userFragment();
                    case R.id.action_transaction:
                        fragment = new transactionFragment();
                    default:
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }




}