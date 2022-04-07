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
                .applicationId("nUDh8bwuDljZEzPfOaj61gnRdU1RTFnwzMDGLhBP")
                .clientKey("WzFax4OI011GIPNUClHWKORVLGg4loTeLLXSipFe")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
