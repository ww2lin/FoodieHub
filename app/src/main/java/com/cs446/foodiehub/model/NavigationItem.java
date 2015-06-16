package com.cs446.foodiehub.model;

import com.cs446.foodiehub.Fragment.FragmentType;

/**
 * Created by Alex on 15-06-16.
 */
public class NavigationItem {

    private FragmentType fragmentType;
    private int drawableId;

    public NavigationItem(FragmentType fragmentType, int drawableId) {
        this.fragmentType = fragmentType;
        this.drawableId = drawableId;
    }

    public FragmentType getFragmentType() {
        return fragmentType;
    }

    public int getDrawableId() {
        return drawableId;
    }
}
