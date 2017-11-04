package com.changos.photoshare;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.changos.photoshare.customListView.CustomAdapter;
import com.changos.photoshare.database.Post;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ProfileFragment extends Fragment {

    private final static String db_nodeName_Post = "posts";
    private final static String db_nodeName_Users = "users";

    private ListView profilePics;

    private List<String> titles, descriptions;
    private double[] coordenadas;

    private OnFragmentInteractionListener mListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profilePics = view.findViewById(R.id.lv_profile_pics);


        ((ProgressBar) view.findViewById(R.id.profileFragment_progressBar)).setVisibility(View.VISIBLE);
        profilePics.setVisibility(View.GONE);

        titles = new ArrayList<>();
        descriptions = new ArrayList<>();

        FirebaseDatabase myDB = FirebaseDatabase.getInstance();
        DatabaseReference myRef = myDB.getReference(db_nodeName_Post);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                titles = new ArrayList<>();
                descriptions = new ArrayList<>();

                for(DataSnapshot d : dataSnapshot.getChildren()){
                    Post p = d.getValue(Post.class);

                    titles.add(p.getPostTitle());
                    descriptions.add(p.getPostDescription());
                }

                Log.d("MyLog", "Array length is " + titles.size());

                final CustomAdapter customAdapter = new CustomAdapter(getContext(), titles, descriptions);

                profilePics.setAdapter(customAdapter);
                ((ProgressBar) view.findViewById(R.id.profileFragment_progressBar)).setVisibility(View.GONE);
                profilePics.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




//        titles = new String[]{"Gato", "Primer Beso", "Una Tarde en mi Casa", "Una Fría Mañana", "Casa Redonda"};
//        descriptions = new String[]{
//                "Peludo y adorable, gatito apestocito que le dan de comer",
//                "Y aquí comenzó nuestra historia",
//                "Pensaba salir de mi casa, pero tomé un tiempo para admirar esta belleza",
//                "Apesar de llegar temprano, es curioso ver estas pequeñas maravillas",
//                "Pasando una navidad increible con mis familiares cercanos"
//        };
//        imgs = new int[] {
//                R.drawable.img_gato,
//                R.drawable.img_primer_beso,
//                R.drawable.img_casa,
//                R.drawable.img_manana,
//                R.drawable.img_casa_chihuahua
//        };

        profilePics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("MyLog", "Selected " + i);
            }
        });

//        profilePics.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getContext(), "selected " + i, Toast.LENGTH_SHORT).show();
//                Log.d("MyLog", "Selected " + i);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                Log.d("MyLog", "Selected ");
//
//            }
//        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
