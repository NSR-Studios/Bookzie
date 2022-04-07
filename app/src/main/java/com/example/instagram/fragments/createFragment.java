package com.example.instagram.fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.example.instagram.LoginActivity;
import com.example.instagram.Post;
import com.example.instagram.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.util.List;

public class createFragment extends Fragment {

    public static final String TAG = "ComposeFragment";
    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 42;
    private EditText ISBN;
    private EditText Condition;
    private EditText Price;
    private Button FrontCover;
    private ImageView ivFrontImage;
    private Button BackCover;
    private ImageView ivBackImage;
    private Button Submit;
    private File photoFile;
    private File photoFile2;
    public String photoFileName = "photo.jpg";
    public String photoFilename2 = "photo2.jpg";

    public createFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compose, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        ISBN = view.findViewById(R.id.ISBN);
        Condition = view.findViewById(R.id.Condition);
        Price = view.findViewById(R.id.Price);
        FrontCover= view.findViewById(R.id.FrontCover);
        ivFrontImage = view.findViewById(R.id.ivFrontImage);
        BackCover= view.findViewById(R.id.BackCover);
        ivBackImage = view.findViewById(R.id.ivBackImage);
        Submit = view.findViewById(R.id.Submit);

        FrontCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Item Clicked!", Toast.LENGTH_SHORT).show();
                launchCamera();
            }
        });

        BackCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Item Clicked!", Toast.LENGTH_SHORT).show();
                launchCamera2();
            }
        });


        //queryPosts();
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String BookNum= ISBN.getText().toString();
                String Con = Condition.getText().toString();
                String Pri = Price.getText().toString();

                if(BookNum.isEmpty()){
                    Toast.makeText(getContext(), "Must enter the Book ISBN number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Con.isEmpty()){
                    Toast.makeText(getContext(), "Must enter the Book Condition", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Pri.isEmpty()){
                    Toast.makeText(getContext(), "Must enter the Book Price", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(photoFile == null || photoFile2 == null || ivFrontImage.getDrawable() == null || ivBackImage.getDrawable() == null){
                    Toast.makeText(getContext(), "Must enter book images", Toast.LENGTH_SHORT).show();
                    return;
                }
                ParseUser currentUser = ParseUser.getCurrentUser();
                savePost(BookNum, currentUser, photoFile, photoFile2, Con, Pri);
            }
        });


    }

    private void gotoLogin(){
        Intent i = new Intent(getContext(), LoginActivity.class);
        startActivity(i);
    }

    private void launchCamera() {
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Create a File reference for future access
        photoFile = getPhotoFileUri(photoFileName);

        // wrap File object into a content provider
        // required for API >= 24
        // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
        Uri fileProvider = FileProvider.getUriForFile(getContext(), "com.codepath.fileprovider", photoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(getContext().getPackageManager()) != null) {
            // Start the image capture intent to take photo
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
        else{
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            Log.e(TAG, "Failed to make it here");
        }
    }

    private void launchCamera2() {
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Create a File reference for future access
        photoFile2 = getPhotoFileUri(photoFilename2);

        // wrap File object into a content provider
        // required for API >= 24
        // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
        Uri fileProvider = FileProvider.getUriForFile(getContext(), "com.codepath.fileprovider", photoFile2);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(getContext().getPackageManager()) != null) {
            // Start the image capture intent to take photo
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
        else{
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            Log.e(TAG, "Failed to make it here");
        }
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // by this point we have the camera photo on disk
                Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                // RESIZE BITMAP, see section below
                // Load the taken image into a preview
                ivFrontImage.setImageBitmap(takenImage);
            } else { // Result was a failure
                Toast.makeText(getContext(), "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }
        }
    }



    public File getPhotoFileUri(String fileName) {
        // Get safe storage directory for photos
        // Use `getExternalFilesDir` on Context to access package-specific directories.
        // This way, we don't need to request external read/write runtime permissions.
        File mediaStorageDir = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
            Log.d(TAG, "failed to create directory");
        }
        Log.i(TAG,"Directory was created successfully");
        // Return the file target for the photo based on filename
        return new File(mediaStorageDir.getPath() + File.separator + fileName);

    }

    private void savePost(String ISBN, ParseUser currentUser, File photoFile, File photoFile2, String condition,   String price){
        Post post = new Post();
        post.setISBN(ISBN);
        post.setCondition(condition);
        post.setPrice(price);
        post.setUser(currentUser);
        post.setFrontImage(new ParseFile(photoFile));
        post.setBackImage(new ParseFile(photoFile2));
        post.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if( e != null){
                    Log.e(TAG, "Error while saving", e);
                }
                Log.i(TAG, "Post was successful!!" );
            }
        });
    }

//    private void queryPosts() {
//        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
//        query.include(Post.KEY_USER);
//        query.findInBackground(new FindCallback<Post>() {
//            @Override
//            public void done(List<Post> posts, ParseException e) {
//                if (e!=null){
//                    Log.e(TAG,"Issue with getting posts", e);
//                    return;
//                }
//                for(Post post : posts){
//                    Log.i(TAG, "Post:" + post.getISBN() + ", User: " + post.getUser().getUsername());
//                }
//            }
//        });
//    }
}
