package com.dkv.base;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.dkv.util.DialogUtil;


public class BaseActivity extends AppCompatActivity {

    protected void setToolbar(Toolbar toolbar, String title, boolean isBackButtonRequired) {
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {

            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(isBackButtonRequired);

        }

    }

    public void setToolbarTitle(String title) {

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);


        }

    }

//    @Override
//    public boolean onSupportNavigateUp() {
//
//        onBackPressed();
//        return true;
//    }

    public AlertDialog mProgressAlert;

    /**
     * Method to show the progress dialog
     */
    public void showProgressDialog() {

        if (mProgressAlert == null || !mProgressAlert.isShowing()) {

            mProgressAlert = new DialogUtil().createProgressDialog(this);
            mProgressAlert.show();

        }
    }

    /**
     * Method for dismissing custom progress dialog.
     */
    public void dismissProgressDialog() {

        if (mProgressAlert != null && mProgressAlert.isShowing()) {
            mProgressAlert.dismiss();
        }
    }
}
