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
import com.cs446.foodiehub.model.FoodSection;
import com.cs446.foodiehub.model.FoodItem;
import com.cs446.foodiehub.model.MenuItem;
import com.devspark.robototextview.widget.RobotoTextView;

import java.util.ArrayList;

/**
 * Created by Alex on 15-07-13.
 */
public class OrderHistoryAdapter extends BaseAdapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;

    private ArrayList<FoodItem> mPastOrders = new ArrayList<>();
    private Context context;
    private RequestManager mGlide;

    private LayoutInflater mInflater;

    private static class ViewHolder {
        TextView name;
        ImageView imageView;
        TextView price;
        RobotoTextView description;

        TextView section;
        TextView date;
    }

    public OrderHistoryAdapter(Context context) {
        this.context = context;
        this.mPastOrders = new ArrayList<>();
        mGlide = Glide.with(context);
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(final MenuItem item) {
        mPastOrders.add(item);
        notifyDataSetChanged();
    }

    public void addSectionHeaderItem(final String item, String date) {
        mPastOrders.add(FoodSection.generateSectionMenu(item, date));
        notifyDataSetChanged();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        int rowType = getItemViewType(position);

        if (convertView == null) {
            viewHolder = new ViewHolder();
            switch (rowType) {
                case TYPE_ITEM:
                    convertView = mInflater.inflate(R.layout.panel_past_order, null);
                    viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);

                    // set image
                    viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_menu_image);

                    //set price
                    viewHolder.price = (TextView) convertView.findViewById(R.id.tv_price);

                    //set price
                    viewHolder.description = (RobotoTextView) convertView.findViewById(R.id.tv_description);

                    break;
                case TYPE_SEPARATOR:
                default:
                    convertView = mInflater.inflate(R.layout.panel_order_history_section, null);
                    viewHolder.section = (TextView) convertView.findViewById(R.id.textSeparator);
                    viewHolder.date = (TextView) convertView.findViewById(R.id.tv_date);
                    break;
            }
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        switch (rowType) {
            case TYPE_ITEM:
                final MenuItem menuItem = (MenuItem) mPastOrders.get(position);
                mGlide.load(menuItem.getImage()).centerCrop().into(viewHolder.imageView);
                viewHolder.price.setText(menuItem.getPrice());
                viewHolder.name.setText(menuItem.getName());
                viewHolder.description.setText(menuItem.getDescription());

                viewHolder.description.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DescriptionDialogFactory.build(context, R.string.description, viewHolder.description.getText().toString()).show();
                    }
                });

                break;
            case TYPE_SEPARATOR:
            default:
                viewHolder.section.setText(((FoodSection)mPastOrders.get(position)).getRestaurantTitle());
                viewHolder.date.setText(((FoodSection)mPastOrders.get(position)).getDate());
                break;
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return mPastOrders.get(position) instanceof FoodSection ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return mPastOrders.size();
    }

    @Override
    public FoodItem getItem(int position) {
        return mPastOrders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
