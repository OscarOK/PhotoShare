package com.changos.photoshare.customListView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.changos.photoshare.R;

/**
 * Created by Oscar Eduardo on 28-Oct-17.
 */

public class CustomAdapter extends ArrayAdapter {

    int[] imageArray;
    String[] titleArray, descriptionArray;

    public CustomAdapter(@NonNull Context context, String[] titles, String[] descriptions, int[] images) {
        super(context, R.layout.custom_row, R.id.tv_title, titles);
        this.imageArray       = images;
        this.titleArray       = titles;
        this.descriptionArray = descriptions;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.custom_row, parent, false);

        ImageView image      = row.findViewById(R.id.imageView);
        TextView title       = row.findViewById(R.id.tv_title);
        TextView description = row.findViewById(R.id.tv_description);

        image.setImageResource(imageArray[position]);
        title.setText(titleArray[position]);
        description.setText(descriptionArray[position]);

        return row;
    }
}
