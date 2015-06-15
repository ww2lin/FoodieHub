package com.cs446.foodiehub.Adapter.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import com.cs446.foodiehub.R;

/**
 * Created by Alex on 15-06-11.
 */
public class AddNoteAdapter extends BaseAdapter {
    private Context context;

    private static class ViewHolder {
        EditText note;
    }

    public AddNoteAdapter(Context context) {
        this.context = context;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // get layout from panel_image.xml
            convertView = inflater.inflate(R.layout.dialog_edittext, null);

            viewHolder.note = (EditText) convertView.findViewById(R.id.et_add_note);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
