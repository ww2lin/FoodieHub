package com.cs446.foddiehub.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cs446.foddiehub.Fragment.FragmentType;

/**
 * Created by Alex on 15-06-09.
 */
public class FragmentTypeAdapter extends ArrayAdapter<FragmentType> {

    FragmentType[] mFragmentTypes;

    // View lookup cache
    private static class ViewHolder {
        TextView name;
    }

    public FragmentTypeAdapter(Context context, FragmentType[] fragmentTypes) {
        super(context, 0, fragmentTypes);
        mFragmentTypes = fragmentTypes;
    }

    public FragmentTypeAdapter(Context context, int resource, int textViewResourceId, FragmentType[] mFragmentTypes) {
        super(context, resource, textViewResourceId, mFragmentTypes);
        this.mFragmentTypes = mFragmentTypes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        FragmentType fragmentType = getItem(position);
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.name.setText(fragmentType.getName());
        // Return the completed view to render on screen
        return convertView;
    }
}
