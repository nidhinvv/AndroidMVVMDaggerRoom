package com.dkv.app;

import android.app.Activity;
import android.app.Application;

import com.dkv.di.AppComponent;
import com.dkv.di.DaggerAppComponent;
import com.dkv.di.module.AppModule;
import com.dkv.di.module.RepositoryModule;
import com.dkv.di.module.StorageModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;


public class FitKidsApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    private AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        initializeLibraries();
    }

    private void initializeLibraries() {
        initializeDagger();

    }

    private void initializeDagger() {

        appComponent = buildAppComponent();
        appComponent.inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    public AppComponent buildAppComponent() {

        return appComponent = DaggerAppComponent.builder().application(this).appModule(
                new AppModule(this)).repositoryModule(new RepositoryModule())
                .storageModule(new StorageModule()).build();
    }

}