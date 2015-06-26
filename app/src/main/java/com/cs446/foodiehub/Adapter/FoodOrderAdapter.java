package com.cs446.foodiehub.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.cs446.foodiehub.R;
import com.cs446.foodiehub.Util.Util;
import com.cs446.foodiehub.dialog.AddNoteDialog;
import com.cs446.foodiehub.Factory.DescriptionDialogFactory;
import com.cs446.foodiehub.model.FoodOrder;

import java.util.ArrayList;

/**
 * Created by Alex on 15-06-11.
 */
public class FoodOrderAdapter extends BaseAdapter {
    private Context context;
    private final ArrayList<FoodOrder> mFoodOrders;
    private RequestManager mGlide;

    private static class ViewHolder {
        TextView name;
        ImageView imageView;
        BootstrapEditText addNote;
        TextView price;
    }

    public FoodOrderAdapter(Context context, ArrayList<FoodOrder> foodOrders) {
        this.context = context;
        this.mFoodOrders = foodOrders;
        this.mGlide = Glide.with(context);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        final FoodOrder foodOrder = mFoodOrders.get(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // get layout from panel_image.xml
            convertView = inflater.inflate(R.layout.panel_checkout_order, null);

            // name of the food
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);

            viewHolder.addNote = (BootstrapEditText) convertView.findViewById(R.id.et_note);

            // set image
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_menu_image);

            // set up the price column
            viewHolder.price = (TextView) convertView.findViewById(R.id.tv_price);

            showDescription(viewHolder.imageView, foodOrder.getDescription());
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        mGlide.load(foodOrder.getUrl()).centerCrop().into(viewHolder.imageView);
        viewHolder.price.setText(foodOrder.getPrice());
        viewHolder.addNote.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    addNote(foodOrder, viewHolder.addNote);
                }
                return true;
            }
        });
        viewHolder.name.setText(foodOrder.getName());
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

    private void addNote(final FoodOrder foodOrder, final BootstrapEditText textView) {
        new AddNoteDialog(context, Util.getStringById(context, R.string.add_note), textView.getText().toString(), new AddNoteDialog.AddNoteDialogCallback() {
            @Override
            public void onDone(AddNoteDialog addNoteDialog) {
                foodOrder.setNote(addNoteDialog.getDialogText().getText().toString());
                textView.setText(foodOrder.getNote());
                addNoteDialog.dismiss();
            }

            @Override
            public void onClear(AddNoteDialog addNoteDialog) {
                addNoteDialog.getDialogText().getText().clear();
            }
        }).show();
    }

    private void showDescription(View view, final String descprtion){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DescriptionDialogFactory.build(context, R.string.description, descprtion).show();
            }
        });
    }
}
