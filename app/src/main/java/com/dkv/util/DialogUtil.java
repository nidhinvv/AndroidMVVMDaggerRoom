package com.dkv.util;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;

import com.dkv.androidmvvmdaggerroom.R;
import com.dkv.taskcallback.ConfirmationDialogCallback;


public class DialogUtil {

    /**
     * Method for creating ProgressDialog
     *
     * @param context current context object
     * @return AlertDialog
     */
    public android.support.v7.app.AlertDialog createProgressDialog(Context context) {

        android.support.v7.app.AlertDialog.Builder builder =
                new android.support.v7.app.AlertDialog.Builder(context);

        final View dialogView = View.inflate(context, R.layout.progress, null);

        builder.setView(dialogView);
        android.support.v7.app.AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);

        if (alertDialog.getWindow() != null) {

            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        }


        return alertDialog;
    }

    public void showDoubleButtonAlert(final Context context, final int dialogId, String tittle,
                                      String message, String buttonPos, String buttonNeg,
                                      final ConfirmationDialogCallback
                                              confirmationDialogCallback) {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setTitle(tittle);
        builder.setMessage(message);

        builder.setPositiveButton(buttonPos, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                confirmationDialogCallback.onConfirmationDialogPositiveButtonClicked(dialogId);
            }
        });

        builder.setNegativeButton(buttonNeg, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                confirmationDialogCallback.onConfirmationDialogNegativeButtonClicked(dialogId);
            }
        });


        android.app.AlertDialog dialog = builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                        .setTextColor(context.getResources().getColor(R.color.red));

                dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                        .setTextColor(context.getResources().getColor(R.color.header_bg_color));

            }
        });


        dialog.show();
    }


}
