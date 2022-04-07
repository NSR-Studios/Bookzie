package com.example.instagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("H1jUhrMN1sOOPd3HxFPCdAjEjXp3lrBY3pElJu8I")
                .clientKey("UBhoRf7U1saowa3ODqGLDDe5xRbHN6yKK8hjBKpC")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
