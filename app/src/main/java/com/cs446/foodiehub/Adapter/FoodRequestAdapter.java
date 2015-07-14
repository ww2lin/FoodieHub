package com.cs446.foodiehub.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.cs446.foodiehub.Factory.DescriptionDialogFactory;
import com.cs446.foodiehub.R;
import com.cs446.foodiehub.Util.Util;
import com.cs446.foodiehub.dialog.AddNoteDialog;
import com.cs446.foodiehub.model.FoodOrder;
import com.cs446.foodiehub.model.FoodSection;
import com.devspark.robototextview.widget.RobotoTextView;


/**
 * Created by Alex on 15-07-13.
 */
public class FoodRequestAdapter extends FoodHeaderAdapter {

    public FoodRequestAdapter(Context context){
        super(context);
    }

    private static class ViewHolder {
        TextView name;
        ImageView imageView;
        RobotoTextView note;

        BootstrapButton submit;
        BootstrapButton cancel;

        TextView section;
        TextView date;
    }

    public void addItem(final FoodOrder item) {
        mPastOrders.add(item);
        notifyDataSetChanged();
    }

    public void addSectionHeaderItem(final String item, String date, String table) {
        mPastOrders.add(FoodSection.generateSectionMenu(item, date, table));
        notifyDataSetChanged();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
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
                    viewHolder.section = (TextView) convertView.findViewById(R.id.textSeparator);
                    viewHolder.date = (TextView) convertView.findViewById(R.id.tv_date);

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
                viewHolder.section.setText(Util.getStringById(context, R.string.table_order) + ((FoodSection) mPastOrders.get(position)).getTable());
                viewHolder.date.setText(((FoodSection) mPastOrders.get(position)).getDate());
                viewHolder.submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "submit clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                viewHolder.cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DescriptionDialogFactory.buildGeneralDialog(context, R.string.cancel_order, R.string.close, R.string.done, addNoteDialogCallback).show();
                    }
                });
                break;
        }
        return convertView;
    }

    private AddNoteDialog.AddNoteDialogCallback addNoteDialogCallback = new AddNoteDialog.AddNoteDialogCallback() {
        @Override
        public void onDone(AddNoteDialog addNoteDialog) {
            // food canceled
            Toast.makeText(context, "done clicked", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onClear(AddNoteDialog addNoteDialog) {
            addNoteDialog.dismiss();
        }
    };

}
