package com.tbc.uncagedmist.cricketupdates;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tbc.uncagedmist.cricketupdates.R;

public class CustomProgressDialog {
    private Dialog mDialog;
    private boolean showingDialog = false;
    private TextView progressText;

    public CustomProgressDialog(Context context) {
        try {
            mDialog = new Dialog(context, R.style.full_screen_dialog);
            mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mDialog.setContentView(R.layout.view_custom_progress_dialog);
            if (mDialog.getWindow() != null) {
                mDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            }
            LinearLayout llView = mDialog.findViewById(R.id.llView);
            llView.setBackgroundColor(context.getResources().getColor(android.R.color.white));
            ProgressBar mProgressBar = mDialog.findViewById(R.id.progress_bar);
            progressText = mDialog.findViewById(R.id.progress_text);
            mProgressBar.setIndeterminate(true);
        } catch (Exception ignored) {
        }
    }


    public void dismiss() {
        try {
            if (mDialog != null) {
                mDialog.dismiss();
                showingDialog = false;
                mDialog = null;
            }
        } catch (Exception ignored) {
        }
    }


    public void setCancelable(boolean cancellable) {
        try {
            mDialog.setCancelable(cancellable);
            mDialog.setCanceledOnTouchOutside(cancellable);
        } catch (Exception ignored) {
        }
    }

    public void show() {
        try {
            mDialog.show();
            showingDialog = true;
        } catch (Exception ignored) {
        }
    }
}