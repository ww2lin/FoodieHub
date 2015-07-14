package com.cs446.foodiehub.dialog;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.cs446.foodiehub.R;
import com.cs446.foodiehub.Util.Util;

/**
 * Created by Alex on 15-06-15.
 */
public class AddNoteDialog extends FoodieHubDialog{
    private BootstrapEditText dialogText;
    private BootstrapButton clear;
    private Context context;

    public AddNoteDialog(Context context, String title, String btn1, String btn2, String defaultText, final AddNoteDialogCallback addNoteDialogCallback) {
        super(context);
        this.context = context;
        setContentView(R.layout.dialog_edittext);
        setTitle(title);

        clear = (BootstrapButton) findViewById(R.id.btn_clear);
        BootstrapButton done = (BootstrapButton) findViewById(R.id.btn_done);
        dialogText = (BootstrapEditText) findViewById(R.id.et_add_note);
        dialogText.append(defaultText == null ? Util.getStringById(context, R.string.no_description) : defaultText);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addNoteDialogCallback != null)
                    addNoteDialogCallback.onClear(AddNoteDialog.this);
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addNoteDialogCallback != null) addNoteDialogCallback.onDone(AddNoteDialog.this);
            }
        });
        clear.setText(btn1);
        done.setText(btn2);
        // show the top text first
        dialogText.setSelection(0);
    }

    public AddNoteDialog(Context context, int theme) {
        super(context, theme);
    }

    public AddNoteDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public interface AddNoteDialogCallback {
        void onDone(AddNoteDialog addNoteDialog);
        void onClear(AddNoteDialog addNoteDialog);
    }

    public BootstrapEditText getDialogText() {
        return dialogText;
    }

    public AddNoteDialog hideClearButton(){
        clear.setVisibility(View.GONE);
        return this;
    }

//    public void setScrollableText(){
//        dialogText.setScroller(new Scroller(context));
//        dialogText.setVerticalScrollBarEnabled(true);
//        dialogText.setMovementMethod(new ScrollingMovementMethod());
//    }

    public void setReadOnlyMode(){
        dialogText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                return true;
            }
        });
    }
}
