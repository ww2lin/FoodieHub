package com.cs446.foddiehub.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.cs446.foddiehub.R;

public class ImageAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private View.OnClickListener mOnClickListener;
    public ImageAdapter(Activity activity, View.OnClickListener onClickListener) {
        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mOnClickListener = onClickListener;
    }

    public int getCount() {
        return 0;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(
                    R.layout.galleryitem, null);
            holder.imageview = (ImageView) convertView.findViewById(R.id.thumbImage);
            holder.checkbox = (CheckBox) convertView.findViewById(R.id.itemCheckBox);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.checkbox.setId(position);
        holder.imageview.setId(position);
        holder.id = position;
        return convertView;
    }
}

class ViewHolder {
    ImageView imageview;
    CheckBox checkbox;
    int id;
}

