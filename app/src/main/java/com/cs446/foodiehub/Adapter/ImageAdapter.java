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
import com.cs446.foodiehub.Factory.DescriptionDialogFactory;
import com.cs446.foodiehub.model.MenuItem;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private final ArrayList<MenuItem> mUrls;
    private RequestManager mGlide;

    private static class ViewHolder {
        TextView name;
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
        final MenuItem menuItem = mUrls.get(position);

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // get layout from panel_image.xml
            convertView = inflater.inflate(R.layout.panel_gallery_image, null);

            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);

            viewHolder.checkBox = (ImageView) convertView.findViewById(R.id.cb_food_selection);

            // set image
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_menu_image);

            //set price
            viewHolder.price = (TextView) convertView.findViewById(R.id.tv_price);

            toggleBuyItem(viewHolder.imageView, menuItem, viewHolder);
            toggleBuyItem(viewHolder.checkBox, menuItem, viewHolder);
            toggleBuyItem(viewHolder.price, menuItem, viewHolder);
            toggleBuyItem(viewHolder.name, menuItem, viewHolder);

            showDescription(viewHolder.imageView, menuItem.getDescription());
            showDescription(viewHolder.checkBox, menuItem.getDescription());
            showDescription(viewHolder.price, menuItem.getDescription());
            showDescription(viewHolder.name, menuItem.getDescription());

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String url = mUrls.get(position).getImage();
        mGlide.load(url).centerCrop().into(viewHolder.imageView);
        updateCheckState(context, viewHolder.checkBox, menuItem);
        viewHolder.price.setText(menuItem.getPrice());
        viewHolder.name.setText(menuItem.getName());
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

    private void toggleBuyItem(View view, final MenuItem menuItem, final ViewHolder viewHolder){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleCheckState(v.getContext(), viewHolder.checkBox, menuItem);
            }
        });
    }

    private void showDescription(View view, final String description){
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DescriptionDialogFactory.buildNoteDialog(context, R.string.description, description == null ? Util.getStringById(context, R.string.no_description) : description).show();
                return true;
            }
        });
    }

    public ArrayList<MenuItem> getMenu(){
        return mUrls;
    }
}



