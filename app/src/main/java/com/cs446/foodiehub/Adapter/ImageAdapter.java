package com.cs446.foodiehub.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.cs446.foodiehub.R;
import com.cs446.foodiehub.Util.Util;
import com.cs446.foodiehub.model.MenuItem;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private final ArrayList<MenuItem> mUrls;
    private RequestManager mGlide;

    private static class ViewHolder {
        ImageView imageView;
        ImageView checkBox;
        TextView price;
    }

    public ImageAdapter(Context context, ArrayList<MenuItem> mUrls) {
        this.context = context;
        this.mUrls = mUrls;
        mGlide = Glide.with(context);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // get layout from panel_image.xml
            convertView = inflater.inflate(R.layout.panel_gallery_image, null);

            viewHolder.checkBox = (ImageView) convertView.findViewById(R.id.cb_food_selection);

            // set image
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_menu_image);
            convertView.setTag(viewHolder);

            //set price
            viewHolder.price = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final MenuItem menuItem = mUrls.get(position);


        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleCheckState(v.getContext(), viewHolder.checkBox, menuItem);
            }
        });

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleCheckState(v.getContext(), viewHolder.checkBox, menuItem);
            }
        });

        String url = mUrls.get(position).getImage();
        mGlide.load(url).into(viewHolder.imageView);
        updateCheckState(context, viewHolder.checkBox, menuItem);
        viewHolder.price.setText(mUrls.get(position).getPrice());

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
        return position;
    }

    private void updateCheckState(Context context, ImageView checkbox, MenuItem menuItem){
        checkbox.setImageDrawable(getCheckmarkDrawable(context, menuItem.isChecked()));
    }

    private void toggleCheckState(Context context, ImageView checkbox, MenuItem menuItem){
        menuItem.setChecked(!menuItem.isChecked());
        checkbox.setImageDrawable(getCheckmarkDrawable(context, menuItem.isChecked()));
    }

    private Drawable getCheckmarkDrawable(Context context, boolean state) {
        if (state) {
            return Util.getDrawable(context, R.drawable.checked);
        } else return Util.getDrawable(context, R.drawable.unchecked);
    }

    public ArrayList<MenuItem> getMenu(){
        return mUrls;
    }


}



