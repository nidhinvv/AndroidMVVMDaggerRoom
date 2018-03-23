package com.dkv.base;

import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import com.dkv.util.DialogUtil;


public class BaseFragment extends Fragment {

    protected boolean isActive = false;
    public AlertDialog mProgressAlert;

    /**
     * Method to show the progress dialog
     */
    public void showProgressDialog() {

        if (mProgressAlert == null || !mProgressAlert.isShowing()) {

            mProgressAlert = new DialogUtil().createProgressDialog(getActivity());
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
