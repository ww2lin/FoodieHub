package com.cs446.foodiehub.Factory;

import android.support.v4.app.FragmentManager;

import com.cs446.foodiehub.Fragment.AboutMeFragment;
import com.cs446.foodiehub.Fragment.FragmentType;
import com.cs446.foodiehub.Fragment.OrderHistoryFragment;
import com.cs446.foodiehub.Fragment.OrderRequestFragment;
import com.cs446.foodiehub.Fragment.RestaurantFragment;
import com.cs446.foodiehub.Fragment.base.FoodieHubFragment;

/**
 * Created by Alex on 15-06-09.
 */
public class FragmentTypeFactory {

    public static FoodieHubFragment getFramgnet(FragmentManager fragmentManager, FragmentType fragmentType) {
        FoodieHubFragment result = (FoodieHubFragment)fragmentType.getFramgnetByType(fragmentManager);

        if (result == null) {
            switch (fragmentType){
                case ABOUT_ME:
                    result = new AboutMeFragment();
                    break;
                case ORDER_HISTORY:
                    result = new OrderHistoryFragment();
                    break;
                case ORDER_REQUESTS:
                    result = new OrderRequestFragment();
                    break;
                case RESTAURANT:
                default :
                    result = new RestaurantFragment();
            }
        }
        return result;
    }

}
