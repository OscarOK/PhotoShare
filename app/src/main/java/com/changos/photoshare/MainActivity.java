package com.changos.photoshare;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.navigation_top:
                        pushFragment(new TopFragment());
                        return true;

                    case R.id.navigation_map:
                        pushFragment(new MapFragment());
                        return true;

                    case R.id.navigation_profile:
                        pushFragment(new ProfileFragment());
                        return true;
                }

                return true;
            }
        });

        navigation.setSelectedItemId(R.id.navigation_map);
        pushFragment(new MapFragment());
    }

    protected void pushFragment(Fragment fragment) {
        if (fragment == null)
            return;

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (ft != null) {
                ft.replace(R.id.fragment_container, fragment);
                ft.commit();
            }
        }
    }
/*
    public void LaunchCamera(){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(i.resolveActivity(getPackageManager()) != null){
            startActivityForResult(i, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();

            Bitmap imageBitmap = (Bitmap) extras.get("data");

            Intent i = new Intent();
            i.setClass(MainActivity.this, PostActivity.class);
            i.putExtra("imageData", imageBitmap);
            startActivity(i);
        }
    }*/
}
