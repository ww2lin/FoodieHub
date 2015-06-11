package com.cs446.foodiehub.Fragment;


import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.MenuInflater;

import com.cs446.foodiehub.Activity.FoodieHubActivity;
import com.cs446.foodiehub.model.MenuItem;

/**
 * Created by Alex on 15-06-09.
 */
public class FoodieHubFragment extends Fragment{

    public boolean handleMenuItem(MenuItem menuItem){
        return false;
    }

    public boolean inflateMenu(MenuInflater menuInflater){
        return false;
    }

    public ActionBar getActionBar(){
        return ((FoodieHubActivity) getActivity()).getSupportActionBar();
    }

}
