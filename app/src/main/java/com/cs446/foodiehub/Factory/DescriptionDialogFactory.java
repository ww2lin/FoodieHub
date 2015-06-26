package com.cs446.foodiehub.Factory;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.StringRes;

import com.cs446.foodiehub.Util.Util;
import com.cs446.foodiehub.dialog.AddNoteDialog;

/**
 * Created by Alex on 15-06-26.
 */
public class DescriptionDialogFactory {

    public static AddNoteDialog build(Context context, @StringRes int stringResId, final String descprtion) {
        AddNoteDialog addNoteDialog = new AddNoteDialog(context, Util.getStringById(context, stringResId), descprtion, new AddNoteDialog.AddNoteDialogCallback() {
            @Override
            public void onDone(AddNoteDialog addNoteDialog) {
                addNoteDialog.dismiss();
            }

            @Override
            public void onClear(AddNoteDialog addNoteDialog) {
                // not interested
            }
        });
        addNoteDialog.hideClearButton();
        addNoteDialog.getDialogText().setEnabled(false);
        addNoteDialog.getDialogText().setTextColor(Color.BLACK);
        addNoteDialog.show();
        return addNoteDialog;
    }
}
