package com.cs446.foodiehub.Factory;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.StringRes;

import com.cs446.foodiehub.R;
import com.cs446.foodiehub.Util.Util;
import com.cs446.foodiehub.dialog.AddNoteDialog;

/**
 * Created by Alex on 15-06-26.
 */
public class DescriptionDialogFactory {

    public static AddNoteDialog buildNoteDialog(Context context, @StringRes int stringResId, final String descprtion) {
        AddNoteDialog addNoteDialog = new AddNoteDialog(context, Util.getStringById(context, stringResId), Util.getStringById(context, R.string.clear),Util.getStringById(context, R.string.done), descprtion, new AddNoteDialog.AddNoteDialogCallback() {
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
        addNoteDialog.setReadOnlyMode();
        addNoteDialog.getDialogText().setTextColor(Color.BLACK);
        addNoteDialog.show();
        return addNoteDialog;
    }

    public static AddNoteDialog buildNoteDialog(Context context, @StringRes int stringResId, @StringRes int descprtion) {
        return buildNoteDialog(context, stringResId, Util.getStringById(context, descprtion));
    }

    public static AddNoteDialog buildGeneralDialog(Context context, @StringRes int title, @StringRes int btn1, @StringRes int btn2, AddNoteDialog.AddNoteDialogCallback addNoteDialogCallback) {
        AddNoteDialog addNoteDialog = new AddNoteDialog(context, Util.getStringById(context, title), Util.getStringById(context,btn1), Util.getStringById(context,btn2), "", addNoteDialogCallback);
        addNoteDialog.getDialogText().setTextColor(Color.BLACK);
//        addNoteDialog.setScrollableText();
        addNoteDialog.show();
        return addNoteDialog;
    }
}
