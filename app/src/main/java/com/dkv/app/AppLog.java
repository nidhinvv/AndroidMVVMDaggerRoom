package com.dkv.app;


import com.dkv.util.CommonUtil;

import timber.log.Timber;

public class AppLog {

    /**
     * Method to log info
     */
    public static void showInfoLog(String infoMessage) {

        if (CommonUtil.isDebug()) {
            Timber.i(infoMessage);
        }
    }

    /**
     * Method to log error
     */
    public static void showErrorLog(String errorMessage) {

        if (CommonUtil.isDebug()) {
            Timber.e(errorMessage);
        }
    }

}
