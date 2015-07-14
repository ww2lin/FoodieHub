package com.cs446.foodiehub.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs446.foodiehub.Factory.DescriptionDialogFactory;
import com.cs446.foodiehub.R;
import com.cs446.foodiehub.Util.Util;
import com.cs446.foodiehub.model.FoodSection;
import com.cs446.foodiehub.model.MenuItem;
import com.cs446.foodiehub.model.RecentOrder;
import com.devspark.robototextview.widget.RobotoTextView;


/**
 * Created by Alex on 15-07-13.
 */
public class OrderHistoryAdapter extends FoodHeaderAdapter {

    public OrderHistoryAdapter(Context context){
        super(context);
    }

    private static class ViewHolder {
        TextView name;
        ImageView imageView;
        TextView price;
        RobotoTextView description;

        TextView restaurant;
        TextView orderNumber;
        TextView date;
    }

    public void addItem(final MenuItem item) {
        mPastOrders.add(item);
        notifyDataSetChanged();
    }

    public void addSectionHeaderItem(RecentOrder recentOrder) {
        mPastOrders.add(FoodSection.generateSectionMenu(recentOrder));
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
                    viewHolder.restaurant = (TextView) convertView.findViewById(R.id.tv_restaurant);
                    viewHolder.orderNumber = (TextView) convertView.findViewById(R.id.tv_order_number);
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
                        DescriptionDialogFactory.buildNoteDialog(context, R.string.description, viewHolder.description.getText().toString()).show();
                    }
                });

                break;
            case TYPE_SEPARATOR:
            default:
                viewHolder.restaurant.setText(((FoodSection) mPastOrders.get(position)).getRestaurantTitle());
                viewHolder.orderNumber.setText(Util.getStringById(context, R.string.order_number) + (mPastOrders.get(position)).getOrderId());
                viewHolder.date.setText(((FoodSection) mPastOrders.get(position)).getDate());
                break;
        }
        return convertView;
    }

}
