package com.cs446.foodiehub.dialog;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by Alex on 15-06-15.
 */
public class FoodieHubDialog extends Dialog{
    public FoodieHubDialog(Context context) {
        super(context);
    }

    public FoodieHubDialog(Context context, int theme) {
        super(context, theme);
    }

    public FoodieHubDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
