package com.changos.photoshare.database;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Hector on 28/10/2017.
 */

public class Post {

    private final static String db_nodeName_Post = "posts";

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private String postID;

    private String userID;

    private String postTitle;
    private String postDescription;
    private String postEncodedImage;

    public Post(){}

    public Post(String postID){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(db_nodeName_Post);
        this.postID = postID;
    }

    public Post(String userID, String postTitle, String postDescription, Bitmap postImage){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(db_nodeName_Post);
        this.userID = userID;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        postImage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        this.postEncodedImage = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
    }

    public void uploadPostToDatabase(){
        if(TextUtils.isEmpty(postID)){
            postID = databaseReference.push().getKey();
        }

        databaseReference.child(postID).setValue(this);
    }

    public String getUserID(){ return this.userID; }

    public String getPostTitle() { return this.postTitle; }

    public String getPostDescription(){ return this.postDescription; }

    public Bitmap getPostImage(){
        Bitmap imageBitmap = null;
        try{
            imageBitmap = decodeFromFirebaseBase64(postEncodedImage);
        } catch (IOException e){
            e.printStackTrace();
        }
        return imageBitmap;
    }

    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }

    public void setUserID(String userID){ this.userID = userID; }

    public void setPostTitle(String postTitle){ this.postTitle = postTitle; }

    public void setPostDescription(String postDescription){ this.postDescription = postDescription; }

    public void setPostImage(Bitmap postImage){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        postImage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        this.postEncodedImage = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
    }



}
