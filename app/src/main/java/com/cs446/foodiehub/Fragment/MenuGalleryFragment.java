package com.cs446.foodiehub.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.cs446.foodiehub.Adapter.ImageAdapter;
import com.cs446.foodiehub.Api.MenuRequest;
import com.cs446.foodiehub.Interface.ServerResponse;
import com.cs446.foodiehub.R;
import com.cs446.foodiehub.Util.Util;
import com.cs446.foodiehub.model.FoodOrder;
import com.cs446.foodiehub.model.MenuItem;
import com.fasterxml.jackson.core.type.TypeReference;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Alex on 15-06-10.
 */
public class MenuGalleryFragment extends Fragment {

    private GridView gridView;
//    private ArrayList<MenuItem> mMenu;
    private ImageAdapter mImageAdapter;
    private String mSelectedRestaurantId;
    private double mTotalPrice = 0;

    private static final String EXTRA_FOOD_ORDER = "extra_food_order";
    private static final String EXTRA_TOTAL_PRICE = "extra_total_price";

    /**
     * Called when the activity is first created.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mTotalPrice = 0;
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);

        final BootstrapButton submit = (BootstrapButton) rootView.findViewById(R.id.btn_submit);
        final BootstrapEditText tableNumber = (BootstrapEditText) rootView.findViewById(R.id.et_table_number);

//        mImageAdapter = new ImageAdapter(getActivity(), mMenu);

        loadMenu();
        gridView = (GridView) rootView.findViewById(R.id.gv_menu_gallery);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Since mMenu was passed by reference into the adapter
                // thus we just fetch the "MenuItem.checked" field inside mMenu
                // for all the photos;
                ArrayList<FoodOrder> selectedFood = new ArrayList<FoodOrder>();
                for (MenuItem menuItem : mImageAdapter.getMenu()) {
                    if (menuItem.isChecked()) {
                        selectedFood.add(new FoodOrder(menuItem.getName(), menuItem.getServerId(), menuItem.getImage(), menuItem.getPrice(), menuItem.getDescription()));
                        mTotalPrice+=Double.parseDouble(menuItem.getPrice());
                    }
                }
                // Open the checkout page
                Fragment mFragment = new CheckoutFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(EXTRA_FOOD_ORDER, selectedFood);
                bundle.putDouble(EXTRA_TOTAL_PRICE, mTotalPrice);
                // set Fragmentclass Arguments
                mFragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.container, mFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return rootView;
    }

    private void loadMenu() {
        // TODO
        // need to have a check here to see if the photo has been already added
        // mMenu.clear will lose the selected food
        // and if we remove mMenu.clear then photo will be duplicated when
        // user clicks back on Checkout fragment
        // Easy way is to check against the id

        mSelectedRestaurantId = RestaurantFragment.getMenu(getArguments());
        MenuRequest.getMenu(mSelectedRestaurantId, mServerResponse);
    }

    public static ArrayList<FoodOrder> getFoodOrder(Bundle bundle) {
        return bundle.getParcelableArrayList(EXTRA_FOOD_ORDER);
    }

    public static Double getTotalPrice(Bundle bundle){
        return bundle.getDouble(EXTRA_TOTAL_PRICE);
    }

    public ServerResponse mServerResponse = new ServerResponse() {
        @Override
        public void onSuccess(int statusCode, String responseString) {
            try {
                JSONObject jsonObj = new JSONObject(responseString);
                responseString = jsonObj.getString("items");

                ArrayList<MenuItem> menuUrls = Util.getMapper().readValue(responseString, new TypeReference<ArrayList<MenuItem>>() {
                });
                mImageAdapter = new ImageAdapter(getActivity(), menuUrls);
                gridView.setAdapter(mImageAdapter);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(int statusCode, String responseString) {
            Toast.makeText(getActivity(), "error while getting menu", Toast.LENGTH_SHORT).show();
        }
    };
}
