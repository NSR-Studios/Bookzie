package com.example.book;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.parceler.Parcel;

import java.util.Date;
@Parcel(analyze = Post.class)
@ParseClassName("Post")
public class Post extends ParseObject {

    public static final String BOOK_NUMBER = "ISBN";
    public static final String CONDITION = "Condition";
    public static final String PRICE = "Price";
    public static final String KEY_FRONT_IMAGE = "FrontCover";
    public static final String KEY_BACK_IMAGE = "BackCover";
    public static final String KEY_USER = "user";
    public static final String KEY_CREATED_KEY = "createdAt";
    public static final String KEY_IMAGE = "ProfilePic";
    public static final String BOOK_TITLE = "title";
    public static final String BOOK_DESCRIPTION = "description";
    public static final String BOOK_CATEGORY = "category";

    public String getBookCategory() {
        return getString(BOOK_CATEGORY);
    }

    public void setBookCategory(String bookCategory) {
        put(BOOK_CATEGORY,bookCategory);
    }

    public String getBookDescription() {
        return getString(BOOK_DESCRIPTION);
    }

    public void setBookDescription(String bookDescription)
    {
        put(BOOK_DESCRIPTION,bookDescription);
    }

    public String getBookTitle() {
        return getString(BOOK_TITLE);
    }

    public void setBookTitle(String title)
    {
        put(BOOK_TITLE,title);
    }

    public String getISBN() {return getString(BOOK_NUMBER);}

    public void setISBN(String description){put(BOOK_NUMBER,description);}

    public String getCondition() {
        return getString(CONDITION);
    }

    public void setCondition(String description){
        put(CONDITION,description);
    }

    public ParseFile getFrontImage()
    {
        return getParseFile(KEY_FRONT_IMAGE);
    }

    public int getPrice() {
        return getInt(PRICE);
    }

    public void setPrice(int description){ put(PRICE,description); }

    public void setFrontImage(ParseFile parseFile){
        put(KEY_FRONT_IMAGE,parseFile);
    }

    public void setBackImage(ParseFile parseFile){
        put(KEY_BACK_IMAGE,parseFile);
    }

    public ParseFile getBackImage()
    {
        return getParseFile(KEY_BACK_IMAGE);
    }

    public ParseFile getImage()
    {
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile parseFile)
    {
        put(KEY_IMAGE,parseFile);
    }

    public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user){
        put(KEY_USER,user);
    }

    public Date getCreated(){return getCreatedAt();}
}


