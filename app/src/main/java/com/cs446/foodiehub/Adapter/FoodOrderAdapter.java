package com.cs446.foodiehub.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.cs446.foodiehub.R;
import com.cs446.foodiehub.model.FoodOrder;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by Alex on 15-06-11.
 */
public class FoodOrderAdapter extends BaseAdapter {
    private Context context;
    private final ArrayList<FoodOrder> mFoodOrders;
    private RequestManager mGlide;

    private static class ViewHolder {
        ImageView imageView;
        Button button;
        TextView price;
    }

    public FoodOrderAdapter(Context context, ArrayList<FoodOrder> foodOrders) {
        this.context = context;
        this.mFoodOrders = foodOrders;
        this.mGlide = Glide.with(context);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // get layout from panel_image.xml
            convertView = inflater.inflate(R.layout.panel_checkout_order, null);

            viewHolder.button = (Button) convertView.findViewById(R.id.btn_note);

            // set image
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_menu_image);

            // set up the price column
            viewHolder.price = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        FoodOrder foodOrder = mFoodOrders.get(position);
        mGlide.load(foodOrder.getUrl()).into(viewHolder.imageView);
        viewHolder.price.setText(foodOrder.getPrice());
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });
        return convertView;
    }

    @Override
    public int getCount() {
        return mFoodOrders.size();
    }

    @Override
    public Object getItem(int position) {
        return mFoodOrders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private void addNote(){
        DialogPlus dialog = new DialogPlus.Builder(context)
                .setAdapter(this)
                .setGravity(DialogPlus.Gravity.CENTER)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                    }
                })
                .create();
        dialog.show();
    }
}
