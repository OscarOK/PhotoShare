package com.changos.photoshare.database;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Base64;

import com.changos.photoshare.PostActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hector on 28/10/2017.
 */

public class Post {

    private final static String db_nodeName_Post = "posts";
    private final static String db_nodeName_Users = "users";

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databasePostsReference;
    private DatabaseReference databaseUsersReference;

    private String postID;

    private String userID;

    private String postTitle;
    private String postDescription;
    private String postEncodedImage;

    public Post(){}

    public Post(String postID){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databasePostsReference = firebaseDatabase.getReference(db_nodeName_Post);
        databaseUsersReference = firebaseDatabase.getReference(db_nodeName_Users);
        this.postID = postID;
    }

    public Post(String userID, String postTitle, String postDescription, Bitmap postImage){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databasePostsReference = firebaseDatabase.getReference(db_nodeName_Post);
        this.userID = userID;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        postImage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        this.postEncodedImage = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
    }

    public void uploadPostToDatabase(){
        if(TextUtils.isEmpty(postID)){
            postID = databasePostsReference.push().getKey();
        }

        databasePostsReference.child(postID).setValue(this);

        databaseUsersReference = FirebaseDatabase.getInstance().getReference()
                .child(db_nodeName_Users).child(userID).child(db_nodeName_Post);

        Map<String, Object> map = new HashMap<>();
        map.put(postID, postID);

        databaseUsersReference.updateChildren(map);
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
