package com.cs446.foodiehub.Fragment.base;


import android.support.v4.app.Fragment;

import com.cs446.foodiehub.Activity.FoodieHubActivity;

/**
 * Created by Alex on 15-06-09.
 */
public abstract class FoodieHubFragment extends Fragment {
    /**
     * this class should not be override, instead override the class {#link populateCustomActionBar}
     * @return
     */
    public boolean overrideActionBar() {
        return populateCustomActionBar() && isVisible();
    }

    protected boolean populateCustomActionBar(){
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Set title
        ((FoodieHubActivity)getActivity()).setActionBarTitle(getTitle());
    }


    protected abstract String getTitle();
}
