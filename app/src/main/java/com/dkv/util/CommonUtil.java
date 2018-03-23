package com.dkv.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;



import javax.inject.Inject;

public class CommonUtil {

    private Context context;

    @Inject
    public CommonUtil(Context context) {

        this.context = context;

    }

    /**
     * Method to check whether debug enabled
     *
     * @return isDebuggable
     */
    public static boolean isDebug() {

        boolean isDebug = false;

      /*  if (BuildConfig.DEBUG) {

            isDebug = true;

        }*/
        return isDebug;
    }

    public void moveToActivity(Context context, Class destinationClass, Bundle bundle) {

        Intent intent = new Intent();

        if (bundle != null) {

            intent.putExtras(bundle);
        }

        intent.setClass(context, destinationClass);
        context.startActivity(intent);
    }



}
