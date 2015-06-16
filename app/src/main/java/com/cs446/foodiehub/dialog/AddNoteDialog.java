package com.cs446.foodiehub.dialog;

import android.content.Context;

/**
 * Created by Alex on 15-06-15.
 */
public class AddNoteDialog extends FoodieHubDialog{
    public AddNoteDialog(Context context) {
        super(context);
    }

    public AddNoteDialog(Context context, int theme) {
        super(context, theme);
    }

    public AddNoteDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


}
