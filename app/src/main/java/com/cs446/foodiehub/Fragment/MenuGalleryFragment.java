package com.cs446.foodiehub.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.cs446.foodiehub.Adapter.ImageAdapter;
import com.cs446.foodiehub.R;
import com.cs446.foodiehub.model.FoodOrder;
import com.cs446.foodiehub.model.MenuItem;

import java.util.ArrayList;

/**
 * Created by Alex on 15-06-10.
 */
public class MenuGalleryFragment extends Fragment {

    private GridView gridView;
    private ArrayList<MenuItem> mMenu = new ArrayList<>();
    private ImageAdapter mImageAdapter;

    private static final String EXTRA_FOOD_ORDER = "extra_food_order";

    /**
     * Called when the activity is first created.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);

        final Button submit = (Button) rootView.findViewById(R.id.btn_submit);
        final EditText tableNumber = (EditText) rootView.findViewById(R.id.et_table_number);

        mImageAdapter = new ImageAdapter(getActivity(), mMenu);
        loadMenu();
        gridView = (GridView) rootView.findViewById(R.id.gv_menu_gallery);
        gridView.setAdapter(mImageAdapter);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Since mMenu was passed by reference into the adapter
                // thus we just fetch the "MenuItem.checked" field inside mMenu
                // for all the photos;
                ArrayList<FoodOrder> selectedFood = new ArrayList<FoodOrder>();
                for (MenuItem menuItem : mMenu){
                    if (menuItem.isChecked()){
                        selectedFood.add(new FoodOrder(menuItem.getServerId(), menuItem.getImage()));
                    }
                }

                // Open the checkout page
                Fragment mFragment = new CheckoutFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(EXTRA_FOOD_ORDER, selectedFood);
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
        mMenu.clear();
        for (int i = 0; i< imageURLArray.length; ++i){
            mMenu.add(new MenuItem(imageURLArray[i], Integer.toString(i)));
        }
        mImageAdapter.notifyDataSetChanged();
    }

    public static ArrayList<FoodOrder> getFoodOrder(Bundle bundle){
        return bundle.getParcelableArrayList(EXTRA_FOOD_ORDER);
    }

    private String[] imageURLArray = new String[]{
            "http://farm8.staticflickr.com/7315/9046944633_881f24c4fa_s.jpg",
            "http://farm4.staticflickr.com/3777/9049174610_bf51be8a07_s.jpg",
            "http://farm8.staticflickr.com/7324/9046946887_d96a28376c_s.jpg",
            "http://farm3.staticflickr.com/2828/9046946983_923887b17d_s.jpg",
            "http://farm4.staticflickr.com/3810/9046947167_3a51fffa0b_s.jpg",
            "http://farm4.staticflickr.com/3773/9049175264_b0ea30fa75_s.jpg",
            "http://farm4.staticflickr.com/3781/9046945893_f27db35c7e_s.jpg",
            "http://farm6.staticflickr.com/5344/9049177018_4621cb63db_s.jpg",
            "http://farm8.staticflickr.com/7307/9046947621_67e0394f7b_s.jpg",
            "http://farm6.staticflickr.com/5457/9046948185_3be564ac10_s.jpg",
            "http://farm4.staticflickr.com/3752/9046946459_a41fbfe614_s.jpg",
            "http://farm8.staticflickr.com/7403/9046946715_85f13b91e5_s.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/6/64/Foods_(cropped).jpg",
    "http://www.fitnea.com/wp-content/uploads/2013/04/11476450_s.jpg",
    "http://www.pachd.com/free-images/food-images/oranges-01.jpg",
            "http://www.pachd.com/free-images/food-images/pickles-01.jpg",
            "http://www.pachd.com/free-images/food-images/apple-01.jpg",
            "http://www.pachd.com/free-images/food-images/babanas-01.jpg",
            "http://www.pachd.com/free-images/food-images/berries-01.jpg",
            "http://www.pachd.com/free-images/food-images/berries-02.jpg",
            "http://www.pachd.com/free-images/food-images/berries-03.jpg",
            "http://www.pachd.com/free-images/food-images/blueberries-01.jpg",
            "http://www.pachd.com/free-images/food-images/cherries-01.jpg",
            "http://www.pachd.com/free-images/food-images/chinese-food-01.jpg",
            "http://www.pachd.com/free-images/food-images/grilling-01.jpg"};

}
