package com.changos.photoshare;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.changos.photoshare.customListView.CustomAdapter;


public class ProfileFragment extends Fragment {

    private ListView profilePics;

    private String[] titles, descriptions;
    private int[] imgs;

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

        titles = new String[]{"Hola", "Hey", "adios"};
        descriptions = new String[]{"HLASDOJAOSDJOASJDAO", "FJHASKDAKJHFLKDJS", "ASDFGHJKHFGHJKDFG"};
        imgs = new int[] {R.drawable.ic_jimmy, R.drawable.ic_home_black_24dp, R.drawable.ic_launcher_background};

        CustomAdapter customAdapter = new CustomAdapter(getContext(), titles, descriptions, imgs);
        profilePics.setAdapter(customAdapter);

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
