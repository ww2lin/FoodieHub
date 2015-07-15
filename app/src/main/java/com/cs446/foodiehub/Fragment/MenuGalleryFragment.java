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
import com.cs446.foodiehub.Factory.DescriptionDialogFactory;
import com.cs446.foodiehub.Fragment.base.FoodieHubFragment;
import com.cs446.foodiehub.Interface.ServerResponse;
import com.cs446.foodiehub.R;
import com.cs446.foodiehub.Util.Util;
import com.cs446.foodiehub.model.server.FoodOrder;
import com.cs446.foodiehub.model.server.MenuItem;
import com.fasterxml.jackson.core.type.TypeReference;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Alex on 15-06-10.
 */
public class MenuGalleryFragment extends FoodieHubFragment {

    private GridView gridView;
    private ImageAdapter mImageAdapter;
    private String mResturantId;
    private double mTotalPrice = 0;

    private static final String EXTRA_FOOD_ORDER = "extra_food_order";
    private static final String EXTRA_TOTAL_PRICE = "extra_total_price";
    private static final String EXTRA_TABLE_ID = "extra_table_id";
    private static final String EXTRA_RESTURANT_ID = "extra table";

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
        final BootstrapButton mRecentButton = (BootstrapButton) rootView.findViewById(R.id.btn_recent_orders);

        loadMenu();
        gridView = (GridView) rootView.findViewById(R.id.gv_menu_gallery);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tableId = tableNumber.getEditableText().toString();

                ArrayList<FoodOrder> selectedFood = new ArrayList<FoodOrder>();
                for (MenuItem menuItem : mImageAdapter.getMenu()) {
                    if (menuItem.isChecked()) {
                        selectedFood.add(new FoodOrder(menuItem.getName(), menuItem.getServerId(), menuItem.getImage(), menuItem.getPrice(), menuItem.getDescription()));
                        mTotalPrice += Double.parseDouble(menuItem.getPrice());
                    }
                }
                if (tableId != null && tableId.trim().length() > 0 && selectedFood.size() > 0) {
                    // Since mMenu was passed by reference into the adapter
                    // thus we just fetch the "MenuItem.checked" field inside mMenu
                    // for all the photos;
                    // Open the checkout page
                    Fragment mFragment = new CheckoutFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList(EXTRA_FOOD_ORDER, selectedFood);
                    bundle.putDouble(EXTRA_TOTAL_PRICE, mTotalPrice);
                    bundle.putString(EXTRA_RESTURANT_ID, mResturantId);
                    bundle.putString(EXTRA_TABLE_ID, tableId);
                    // set Fragmentclass Arguments
                    mFragment.setArguments(bundle);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.container, mFragment);
                    ft.addToBackStack(null);
                    ft.commit();
                } else {
                    DescriptionDialogFactory.buildNoteDialog(getActivity(), R.string.missing_information, R.string.pick_table_food).show();
                }
            }
        });

        mRecentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment mFragment = new PastResturantOrderFragment();
                Bundle bundle = new Bundle();
                bundle.putString(EXTRA_RESTURANT_ID, mResturantId);
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

        mResturantId = RestaurantFragment.getMenu(getArguments());
        MenuRequest.getMenu(mResturantId, mServerResponse);
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        Fragment mFragment = new PastResturantOrderFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(EXTRA_RESTURANT_ID, mResturantId);
//        // set Fragmentclass Arguments
//        mFragment.setArguments(bundle);
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.replace(R.id.container, mFragment);
//        ft.addToBackStack(null);
//        ft.commit();
//    }

    public static ArrayList<FoodOrder> getFoodOrder(Bundle bundle) {
        return bundle.getParcelableArrayList(EXTRA_FOOD_ORDER);
    }

    public static Double getTotalPrice(Bundle bundle) {
        return bundle.getDouble(EXTRA_TOTAL_PRICE);
    }

    public static String getRestrauntId(Bundle bundle) {
        return bundle.getString(EXTRA_RESTURANT_ID);
    }

    public static String getTableId(Bundle bundle) {
        return bundle.getString(EXTRA_TABLE_ID);
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

    @Override
    protected String getTitle() {
        return FragmentType.RESTAURANT.getName();
    }
}
