package com.dkv.di.module;

import android.content.Context;

import com.dkv.app.FitKidsApp;
import com.dkv.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;


@Module(includes = ViewModelModule.class)
public class AppModule {

    FitKidsApp application;

    public AppModule(FitKidsApp application) {

        this.application = application;
    }

    @Provides
    @ApplicationScope
    protected Context provideContext() {

        return application.getApplicationContext();
    }

}
