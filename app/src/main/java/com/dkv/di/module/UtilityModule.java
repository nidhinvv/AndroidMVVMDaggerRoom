package com.dkv.di.module;

import android.content.Context;

import com.dkv.di.scope.LocalScope;
import com.dkv.util.CommonUtil;
import com.dkv.util.DialogUtil;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilityModule {

    @Provides
    @LocalScope
    protected CommonUtil provideCommonUtil(Context context) {

        return new CommonUtil(context);
    }

    @Provides
    @LocalScope
    protected DialogUtil provideDialogUtil() {

        return new DialogUtil();
    }

}