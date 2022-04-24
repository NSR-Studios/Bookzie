package com.example.book;


import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;

@ParseClassName("Transactions")
public class Transaction extends ParseObject {

    public static final String KEY_SELLER = "Seller";
    public static final String KEY_BUYER = "Buyer";
    public static final String KEY_POST = "postId";
    public static final String KEY_DATE = "date";
    public static final String KEY_TIME = "time";
    public static final String KEY_LOCATION = "location";


    public void setSeller(ParseUser user)
    {
        put(KEY_SELLER,user);
    }

    public void setBuyer(ParseUser user)
    {
        put(KEY_BUYER,user);
    }

    public void setPost(ParseObject post)
    {
        put(KEY_POST,post);
    }

    public void setDate(String d)
    {
        put(KEY_DATE,d);
    }

    public void setTime(String t)
    {
        put(KEY_TIME,t);
    }

    public void setLocation(String location)
    {
        put(KEY_LOCATION,location);
    }


}

