package com.cs446.foodiehub.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.cs446.foodiehub.Factory.FragmentTypeFactory;
import com.cs446.foodiehub.Fragment.FragmentType;
import com.cs446.foodiehub.Fragment.NavigationDrawerFragment;
import com.cs446.foodiehub.Fragment.RestaurantFragment;
import com.cs446.foodiehub.Fragment.base.FoodieHubFragment;
import com.cs446.foodiehub.Fragment.base.MenuFoodieHubFragment;
import com.cs446.foodiehub.R;

import java.util.List;


public class MainActivity extends FoodieHubActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = FragmentType.RESTAURANT.getName();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        FragmentManager fragmentManager = getSupportFragmentManager();
        mTitle = FragmentType.RESTAURANT.getName();
        fragmentManager.beginTransaction()
                .replace(R.id.container, new RestaurantFragment())
                .commit();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position, FragmentType menuItem) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        FoodieHubFragment displayFragment = FragmentTypeFactory.getFramgnet(fragmentManager, menuItem);

        // if the display fragment is null -- no implement has been made yet
        if (displayFragment != null) {
            mTitle = menuItem.getName();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, displayFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // check to see if any of the fragment is visible
        boolean noVisibleFragment = true;
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment instanceof FoodieHubFragment &&
                    ((FoodieHubFragment) fragment).overrideActionBar()) {
                // We found a currently visible fragment
                noVisibleFragment = false;
            }
        }

        if (!mNavigationDrawerFragment.isDrawerOpen() && noVisibleFragment) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.menu_bar, menu);
            restoreActionBar();
            return true;
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment instanceof MenuFoodieHubFragment &&
                    ((MenuFoodieHubFragment) fragment).overrideActionBar()) {
                // We found a currently visible fragment
               if (((MenuFoodieHubFragment) fragment).onMenuSelected(item)){
                    return true;
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
