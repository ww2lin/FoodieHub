package com.cs446.foodiehub.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.cs446.foodiehub.R;
import com.cs446.foodiehub.model.MenuItem;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private final ArrayList<MenuItem> mUrls;
    private Picasso mPicasso;

    private static class ViewHolder {
        ImageView imageView;
        CheckBox checkBox;
    }

    public ImageAdapter(Context context, ArrayList<MenuItem> mUrls) {
        this.context = context;
        this.mUrls = mUrls;
        mPicasso = Picasso.with(context);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            // get layout from panel_image.xml
            convertView = inflater.inflate(R.layout.panel_image, null);

            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.cb_food_selection);

            // set image
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_menu_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final MenuItem menuItem = mUrls.get(position);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.checkBox.setChecked(!viewHolder.checkBox.isChecked());
                menuItem.setChecked(viewHolder.checkBox.isChecked());
            }
        });

        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.checkBox.setChecked(!viewHolder.checkBox.isChecked());
                menuItem.setChecked(viewHolder.checkBox.isChecked());
            }
        });
        String url = mUrls.get(position).getmImage();
        mPicasso.cancelRequest(viewHolder.imageView);
        mPicasso.load(url).into(viewHolder.imageView , new Callback() {
            @Override public void onSuccess() {
                //
            }

            @Override public void onError() {
                // Dont do anything right now
            }
        });

        return convertView;
    }

    @Override
    public int getCount() {
        return mUrls.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


}



