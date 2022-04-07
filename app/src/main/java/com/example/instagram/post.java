package com.example.instagram;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
//import java.util.Date;

@ParseClassName("Post")

public class Post extends ParseObject {

        public static final String BOOK_NUMBER = "ISBN";
        //public static final String MAJOR = "Major";
        //public static final String CLASS_OF = "ClassOf";
        //public static final String RATING = "Rating";
        public static final String CONDITION = "Condition";
        public static final String PRICE = "Price";
        public static final String KEY_FRONT_IMAGE = "FrontCover";
        public static final String KEY_BACK_IMAGE = "BackCover";
        public static final String KEY_USER = "user";
        //public static final String KEY_CREATED_KEY = "createdAt";

        public String getISBN() {
            return getString(BOOK_NUMBER);
        }

   //     public String getMajor() { return getString(MAJOR); }

   //     public String getClassOf() { return getString(CLASS_OF); }

   //     public String getRating() { return getString(RATING); }

   //     public String getCondition() { return getString(CONDITION); }

   //     public String getPrice() { return getString(PRICE); }

        public void setISBN(String description){
            put(BOOK_NUMBER,description);
        }

        public void setCondition(String description){
        put(CONDITION,description);
    }

        public void setPrice(String description){ put(PRICE,description); }

  //      public void setMajor(String description){ put(MAJOR,description); }

  //      public void setClassOf(String description){ put(CLASS_OF,description); }

//       public void setRating(String description){ put(RATING,description); }
//
//        public ParseFile getFrontImage(){
//            return getParseFile(KEY_FRONT_IMAGE);
//        }
//
//        public ParseFile getBackImage(){
//            return getParseFile(KEY_BACK_IMAGE);
//        }

        public void setFrontImage(ParseFile parseFile){
            put(KEY_FRONT_IMAGE,parseFile);
        }

        public void setBackImage(ParseFile parseFile){
            put(KEY_BACK_IMAGE,parseFile);
        }

        public ParseUser getUser(){
            return getParseUser(KEY_USER);
        }

        public void setUser(ParseUser user){
            put(KEY_USER,user);
        }

   //     public Date getCreated(){return getCreatedAt();}

}

