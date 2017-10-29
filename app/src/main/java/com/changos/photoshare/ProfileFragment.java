package com.changos.photoshare;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.changos.photoshare.customListView.CustomAdapter;


public class ProfileFragment extends Fragment {

    private ListView profilePics;

    private String[] titles, descriptions;
    private int[] imgs;
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profilePics = view.findViewById(R.id.lv_profile_pics);

        titles = new String[]{"Gato", "Primer Beso", "Una Tarde en mi Casa", "Una Fría Mañana", "Casa Redonda"};
        descriptions = new String[]{
                "Peludo y adorable, gatito apestocito que le dan de comer",
                "Y aquí comenzó nuestra historia",
                "Pensaba salir de mi casa, pero tomé un tiempo para admirar esta belleza",
                "Apesar de llegar temprano, es curioso ver estas pequeñas maravillas",
                "Pasando una navidad increible con mis familiares cercanos"
        };
        imgs = new int[] {
                R.drawable.img_gato,
                R.drawable.img_primer_beso,
                R.drawable.img_casa,
                R.drawable.img_manana,
                R.drawable.img_casa_chihuahua
        };

        CustomAdapter customAdapter = new CustomAdapter(getContext(), titles, descriptions, imgs);
        profilePics.setAdapter(customAdapter);

        profilePics.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), "" + i, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
