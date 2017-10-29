package com.changos.photoshare;

import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.changos.photoshare.database.Post;
import com.google.firebase.auth.FirebaseAuth;

public class PostActivity extends AppCompatActivity {

    EditText titleEditText;
    EditText descriptionEditText;

    Bitmap postImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);


        titleEditText = (EditText) findViewById(R.id.pictureTitle);
        descriptionEditText = (EditText) findViewById(R.id.pictureDescription);

        if(getIntent().getExtras() != null) {
            postImage = (Bitmap) getIntent().getExtras().getParcelable("imageData");

            ImageView postImageView = (ImageView) findViewById(R.id.picturePost);

            postImageView.setImageBitmap(postImage);
        }

        FloatingActionButton finishPostFab = (FloatingActionButton) findViewById(R.id.fab);

        finishPostFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload_post();
            }
        });

    }

    public void upload_post(){
        String postTitle = titleEditText.getText().toString();
        String postDescription = descriptionEditText.getText().toString();

        Post newPost = new Post(
                FirebaseAuth.getInstance().getCurrentUser().getUid(),
                postTitle, postDescription, postImage);

        newPost.uploadPostToDatabase();
        finish();
        Toast.makeText(this, "Publicando...", Toast.LENGTH_SHORT).show();
    }
}
