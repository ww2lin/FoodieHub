package com.cs446.foodiehub.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cs446.foodiehub.Adapter.RestaurantAdapter;
import com.cs446.foodiehub.Api.RestaurantRequest;
import com.cs446.foodiehub.Interface.ServerResponse;
import com.cs446.foodiehub.R;
import com.cs446.foodiehub.Util.Util;
import com.cs446.foodiehub.model.Restaurant;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;

/**
 * Created by Alex on 15-06-09.
 */
public class RestaurantFragment extends FoodieHubFragment {

    private ListView mListView;
    private RestaurantAdapter mRestaurants;
    private String mSelectedRestaurantId;

    private static final String EXTRA_SELECTED_RESTAURANT = "extra_selected_restaurant";

    public RestaurantFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_restaurant, container, false);

        // Fetch the list of restaurants
        RestaurantRequest.getRestaurants(mServerResponse);

        mListView = (ListView) rootView.findViewById(R.id.lv_restaurants);

        mListView.setOnItemClickListener(onItemClickListener);
        return rootView;
    }


    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Fragment mFragment = new MenuGalleryFragment();
            Bundle bundle = new Bundle();
            bundle.putString(EXTRA_SELECTED_RESTAURANT, mRestaurants.getItem(position).getRestaurantId());
            // set Fragmentclass Arguments
            mFragment.setArguments(bundle);
            FragmentTransaction ft = getFragmentManager().beginTransaction();

            ft.replace(R.id.container, mFragment);
            ft.addToBackStack(null);
            ft.commit();
        }
    };

    public static String getMenu(Bundle bundle) {
        return bundle.getString(EXTRA_SELECTED_RESTAURANT);
    }

    private ServerResponse mServerResponse = new ServerResponse() {
        @Override
        public void onSuccess(int statusCode, String responseString) {
            try {
                ArrayList<Restaurant> restaurants = Util.getMapper().readValue(responseString, new TypeReference<ArrayList<Restaurant>>() {
                });
                mRestaurants = new RestaurantAdapter(getActivity(), restaurants);
                mListView.setAdapter(mRestaurants);
//                    mRestaurants.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(int statusCode, String responseString) {
            Toast.makeText(RestaurantFragment.this.getActivity(), "Something unexpected happend!", Toast.LENGTH_SHORT).show();
        }
    };
}
