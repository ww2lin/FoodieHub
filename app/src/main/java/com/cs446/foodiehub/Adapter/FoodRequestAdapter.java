package com.cs446.foodiehub.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.cs446.foodiehub.Api.FoodOrderRequest;
import com.cs446.foodiehub.Factory.DescriptionDialogFactory;
import com.cs446.foodiehub.Interface.ServerResponse;
import com.cs446.foodiehub.R;
import com.cs446.foodiehub.Util.Util;
import com.cs446.foodiehub.dialog.AddNoteDialog;
import com.cs446.foodiehub.model.FoodItem;
import com.cs446.foodiehub.model.server.FoodOrder;
import com.cs446.foodiehub.model.server.FoodOrderWrapper;
import com.cs446.foodiehub.model.FoodSection;
import com.devspark.robototextview.widget.RobotoTextView;


/**
 * Created by Alex on 15-07-13.
 */
public class FoodRequestAdapter extends FoodHeaderAdapter {

    public FoodRequestAdapter(Context context) {
        super(context);
    }

    private static class ViewHolder {
        TextView name;
        ImageView imageView;
        RobotoTextView note;

        BootstrapButton submit;
        BootstrapButton cancel;

        TextView table;
        TextView orderNumber;
        TextView date;
    }

    public void addItem(final FoodOrder item, String orderId) {
        item.setOrderId(orderId);
        mPastOrders.add(item);
        notifyDataSetChanged();
    }

    public void addSectionHeaderItem(FoodOrderWrapper foodOrderWrapper) {
        mPastOrders.add(FoodSection.generateSectionMenu(foodOrderWrapper));
        notifyDataSetChanged();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        int rowType = getItemViewType(position);

        if (convertView == null) {
            viewHolder = new ViewHolder();
            switch (rowType) {
                case TYPE_ITEM:
                    convertView = mInflater.inflate(R.layout.panel_order_request, null);
                    viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);

                    // set image
                    viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_menu_image);
                    viewHolder.note = (RobotoTextView) convertView.findViewById(R.id.tv_note);

                    break;
                case TYPE_SEPARATOR:
                default:
                    convertView = mInflater.inflate(R.layout.panel_order_request_section, null);
                    viewHolder.table = (TextView) convertView.findViewById(R.id.tv_table_number);
                    viewHolder.date = (TextView) convertView.findViewById(R.id.tv_date);
                    viewHolder.orderNumber = (TextView) convertView.findViewById(R.id.tv_order_number);

                    //set submit / cancel
                    viewHolder.submit = (BootstrapButton) convertView.findViewById(R.id.btn_submit);
                    viewHolder.cancel = (BootstrapButton) convertView.findViewById(R.id.btn_cancel);

                    break;
            }
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        switch (rowType) {
            case TYPE_ITEM:
                final FoodOrder foodOrder = (FoodOrder) mPastOrders.get(position);
                mGlide.load(foodOrder.getImage()).centerCrop().into(viewHolder.imageView);
                viewHolder.name.setText(foodOrder.getName());
                viewHolder.note.setText(foodOrder.getNote());
                viewHolder.note.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String note = viewHolder.note.getText().toString();
                        if (note.length() > 0) {
                            DescriptionDialogFactory.buildNoteDialog(context, R.string.description, viewHolder.note.getText().toString()).show();
                        }
                    }
                });

                break;
            case TYPE_SEPARATOR:
            default:
                viewHolder.table.setText(Util.getStringById(context, R.string.table_order) + ((FoodSection) mPastOrders.get(position)).getTable());
                viewHolder.orderNumber.setText(Util.getStringById(context, R.string.order_number) + (mPastOrders.get(position)).getOrderId());
                viewHolder.date.setText(((FoodSection) mPastOrders.get(position)).getDate());
                viewHolder.submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FoodOrderRequest.orderCompleted(mPastOrders.get(position).getOrderId(), new ServerResponse() {
                            @Override
                            public void onSuccess(int statusCode, String responseString) {
                                deleteOrder(mPastOrders.get(position).getOrderId());
                                Toast.makeText(context, Util.getStringById(context, R.string.order_sent), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(int statusCode, String responseString) {
                                Toast.makeText(context, Util.getStringById(context, R.string.bad_connection), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                viewHolder.cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DescriptionDialogFactory.buildGeneralDialog(context, R.string.cancel_order, R.string.close, R.string.done, null, addNoteDialogCallback, position).show();
                    }
                });
                break;
        }
        return convertView;
    }

    private AddNoteDialog.AddNoteDialogCallback addNoteDialogCallback = new AddNoteDialog.AddNoteDialogCallback() {
        @Override
        public void onDone(AddNoteDialog addNoteDialog, Object index) {
            String orderId = mPastOrders.get((Integer) index).getOrderId();
            deleteOrder(orderId);
            FoodOrderRequest.orderCancel(orderId, new ServerResponse() {
                @Override
                public void onSuccess(int statusCode, String responseString) {
                    Toast.makeText(context, Util.getStringById(context, R.string.order_cancel), Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(int statusCode, String responseString) {
                    Toast.makeText(context, Util.getStringById(context, R.string.bad_connection), Toast.LENGTH_SHORT).show();
                }
            });
            addNoteDialog.dismiss();
        }

        @Override
        public void onClear(AddNoteDialog addNoteDialog, Object index) {
            addNoteDialog.dismiss();
        }
    };

    /**
     * Delete all the item in the adpater which has the taget orderId
     * <p/>
     * Probably a better way to solve this is using a hashmap, which maps
     * (orderId -> index), then we can access the target index at O(1) time
     * and when the OrderId no longer matches we can terminate
     */
    private void deleteOrder(String orderId) {
        for (int i = 0; i < mPastOrders.size(); i++) {
            FoodItem foodItem = mPastOrders.get(i);
            if (orderId.equalsIgnoreCase(foodItem.getOrderId())) {
                // found a matching order id..
                mPastOrders.remove(i);
                // index has been shift
                --i;
            }
        }
        notifyDataSetChanged();
    }

}
