package com.cs446.foodiehub.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.cs446.foodiehub.R;

/**
 * Created by Alex on 15-06-09.
 */
public class AboutMeFragment extends FoodieHubFragment {

    private ListView mListView;

    public AboutMeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutMeFragment.this.getActivity(), "you clicked on the fragment" + AboutMeFragment.this.getClass().getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }
}
