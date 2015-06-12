package com.cs446.foodiehub.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.cs446.foodiehub.R;

import java.util.Arrays;

/**
 * Created by Alex on 15-06-09.
 */
public class RestaurantFragment extends FoodieHubFragment {

    private ListView mListView;
    private ArrayAdapter<String> mRestaurants;

    public RestaurantFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_restaurant, container, false);

        mListView = (ListView) rootView.findViewById(R.id.lv_restaurants);

        String[] values = new String[] {"slc", "mc", "dc"};
        mRestaurants = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, Arrays.asList(values));

        mListView.setAdapter(mRestaurants);
        mListView.setOnItemClickListener(onItemClickListener);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RestaurantFragment.this.getActivity(), "you clicked on the fragment" + RestaurantFragment.this.getClass().getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }


    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Fragment mFragment = new MenuGalleryFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            //Replacing using the id of the container and not the fragment itself
            ft.replace(R.id.container, mFragment);
            ft.addToBackStack(null);
            ft.commit();
        }
    };


}
