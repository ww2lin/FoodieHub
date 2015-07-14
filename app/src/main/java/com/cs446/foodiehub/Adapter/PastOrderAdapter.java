package com.cs446.foodiehub.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.cs446.foodiehub.Factory.DescriptionDialogFactory;
import com.cs446.foodiehub.R;
import com.cs446.foodiehub.model.MenuItem;
import com.devspark.robototextview.widget.RobotoTextView;

import java.util.ArrayList;

/**
 * Created by Alex on 15-07-12.
 */
public class PastOrderAdapter extends BaseAdapter {
    private Context context;
    protected final ArrayList<MenuItem> menuItems;
    private RequestManager mGlide;

    private static class ViewHolder {
        TextView name;
        ImageView imageView;
        TextView price;
        RobotoTextView description;
    }

    public PastOrderAdapter(Context context, ArrayList<MenuItem> foodOrders) {
        this.context = context;
        this.menuItems = foodOrders;
        mGlide = Glide.with(context);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        final MenuItem menuItem = menuItems.get(position);

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // get layout from panel_image.xml
            convertView = inflater.inflate(R.layout.panel_past_order, null);

            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);

            // set image
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_menu_image);

            //set price
            viewHolder.price = (TextView) convertView.findViewById(R.id.tv_price);

            //set price
            viewHolder.description = (RobotoTextView) convertView.findViewById(R.id.tv_description);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        mGlide.load(menuItem.getImage()).centerCrop().into(viewHolder.imageView);
        viewHolder.price.setText(menuItem.getPrice());
        viewHolder.name.setText(menuItem.getName());
        viewHolder.description.setText(menuItem.getDescription());

        viewHolder.description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DescriptionDialogFactory.buildNoteDialog(context, R.string.description, viewHolder.description.getText().toString()).show();
            }
        });
        return convertView;
    }

    @Override
    public int getCount() {
        return menuItems.size();
    }

    @Override
    public MenuItem getItem(int position) {
        return menuItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}