package com.cs446.foodiehub.Fragment;


import android.support.v4.app.Fragment;

/**
 * Created by Alex on 15-06-09.
 */
public class FoodieHubFragment extends Fragment {

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

}
