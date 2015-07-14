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
        AddNoteDialog addNoteDialog = new AddNoteDialog(context, Util.getStringById(context, stringResId), Util.getStringById(context, R.string.clear), Util.getStringById(context, R.string.done), descprtion, new AddNoteDialog.AddNoteDialogCallback() {
            @Override
            public void onDone(AddNoteDialog addNoteDialog, Object object) {
                addNoteDialog.dismiss();
            }

            @Override
            public void onClear(AddNoteDialog addNoteDialog, Object object) {
                // not interested
            }
        }, null);
        addNoteDialog.hideClearButton();
        addNoteDialog.setReadOnlyMode();
        addNoteDialog.getDialogText().setTextColor(Color.BLACK);
        addNoteDialog.show();
        return addNoteDialog;
    }

    public static AddNoteDialog buildNoteDialog(Context context, @StringRes int stringResId, @StringRes int description) {
        return buildNoteDialog(context, stringResId, Util.getStringById(context, description));
    }

    public static AddNoteDialog buildGeneralDialog(Context context, @StringRes int title, @StringRes int btn1, @StringRes int btn2, String defaultText, AddNoteDialog.AddNoteDialogCallback addNoteDialogCallback, Object object) {
        AddNoteDialog addNoteDialog = new AddNoteDialog(context, Util.getStringById(context, title), Util.getStringById(context,btn1), Util.getStringById(context,btn2), defaultText, addNoteDialogCallback, object);
        addNoteDialog.getDialogText().setTextColor(Color.BLACK);
        addNoteDialog.show();
        return addNoteDialog;
    }

    public static AddNoteDialog buildGeneralDialog(Context context, @StringRes int title, @StringRes int btn1, @StringRes int btn2, String defaultText, AddNoteDialog.AddNoteDialogCallback addNoteDialogCallback) {
        return buildGeneralDialog(context, title,btn1, btn2, defaultText, addNoteDialogCallback, null);
    }
}
