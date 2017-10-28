package com.changos.photoshare.database;

import android.graphics.Bitmap;

/**
 * Created by Hector on 28/10/2017.
 */

public class Post {

    private String userID;

    private String postTitle;
    private String postDescription;
    private Bitmap postImage;

    public Post(){}

    public Post(String userID, String postTitle, String postDescription, Bitmap postImage){
        this.userID = userID;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.postImage = postImage;
    }

    public String getUserID(){ return this.userID; }

    public String getPostTitle() { return this.postTitle; }

    public String getPostDescription(){
        return "";
    }

}
