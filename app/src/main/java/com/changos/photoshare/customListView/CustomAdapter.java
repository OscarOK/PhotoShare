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

import java.util.List;

/**
 * Created by Oscar Eduardo on 28-Oct-17.
 */

public class CustomAdapter extends ArrayAdapter {

    int[] imageArray;
    List<String> titleArray, descriptionArray;

    public CustomAdapter(@NonNull Context context, List<String> titles, List<String> descriptions, int[] images) {
        super(context, R.layout.custom_row, R.id.tv_title, titles);
        this.imageArray       = images;
        this.titleArray       = titles;
        this.descriptionArray = descriptions;
    }

    public CustomAdapter(@NonNull Context context, List<String> titles, List<String> descriptions) {
        super(context, R.layout.custom_row, R.id.tv_title, titles);
        this.imageArray       = null;
        this.titleArray       = titles;
        this.descriptionArray = descriptions;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.custom_row, parent, false);

        ImageView image      = row.findViewById(R.id.iv_image);
        TextView title       = row.findViewById(R.id.tv_title);
        TextView description = row.findViewById(R.id.tv_description);
        if(imageArray != null)
            image.setImageResource(imageArray[position]);
        else
            image.setImageResource(R.drawable.img_casa_chihuahua);

        if(titleArray != null)
            title.setText(titleArray.get(position));
        else
            title.setText("null");
        if(descriptionArray != null)
            description.setText(descriptionArray.get(position));
        else
            description.setText("null");

        return row;
    }

    public void setNewData(List<String> titles, List<String> descriptions){
        this.titleArray       = titles;
        this.descriptionArray = descriptions;
        notifyDataSetChanged();
    }
}
