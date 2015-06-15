package com.cs446.foodiehub.Activity;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Alex on 15-06-11.
 */
public class FoodieHubActivity extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
