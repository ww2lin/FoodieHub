package com.cs446.foddiehub.Factory;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;

import com.cs446.foddiehub.Fragment.AboutMeFragment;
import com.cs446.foddiehub.Fragment.FragmentType;
import com.cs446.foddiehub.Fragment.OrderHistoryFragment;
import com.cs446.foddiehub.Fragment.RestaurantFragment;

/**
 * Created by Alex on 15-06-09.
 */
public class FragmentTypeFactory {

    public static Fragment getFramgnet(FragmentManager fragmentManager, FragmentType fragmentType) {
        Fragment result = fragmentType.getFramgnetByType(fragmentManager);

        if (result == null) {
            switch (fragmentType){
                case ABOUT_ME:
                    result = new AboutMeFragment();
                    break;
                case ORDER_HISTORY:
                    result = new OrderHistoryFragment();
                    break;
                case RESTAURANT:
                default :
                    result = new RestaurantFragment();
            }
        }
        return result;
    }

}
