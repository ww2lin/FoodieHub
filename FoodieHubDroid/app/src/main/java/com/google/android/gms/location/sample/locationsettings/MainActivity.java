package com.google.android.gms.location.sample.locationsettings;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.location.sample.locationsettings.UI.FragmentGPSService;


public class MainActivity extends Activity {

    protected static final String TAG = "yolo";

    //Constant used in the location settings dialog.
    protected static final int REQUEST_CHECK_SETTINGS = 0x1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "Activitiy onCreate");
        setContentView(R.layout.main_activity);
        // add Form
        if (savedInstanceState == null) {
            // adding a fragment dynamically requires to ensure activity is not recreated from saved instance state
            // if 'savedInstanceState' is null - this menas activity is created for the first time, ie. app just started
            getFragmentManager().beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .add(R.id.rootView, new FragmentGPSService(), FragmentGPSService.NAME)
                    .commit();
        }
    }

    @Override
    protected void onStart() {
        Log.i(TAG, "Activitiy onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i(TAG, "Activitiy onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "Activitiy onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "Activitiy onStop");
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CHECK_SETTINGS) {
            FragmentGPSService gpsFragment = (FragmentGPSService) getFragmentManager()
                    .findFragmentByTag(FragmentGPSService.NAME);
            gpsFragment.handleLocationResponse(requestCode, resultCode, data);
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
