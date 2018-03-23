package com.dkv.di;


import com.dkv.app.FitKidsApp;
import com.dkv.di.module.AppModule;
import com.dkv.di.module.BuildersModule;
import com.dkv.di.module.RepositoryModule;
import com.dkv.di.module.StorageModule;
import com.dkv.di.scope.ApplicationScope;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;


@ApplicationScope
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        RepositoryModule.class,
        StorageModule.class,
        BuildersModule.class
     })

public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(FitKidsApp application);
        Builder appModule(AppModule module);
        Builder repositoryModule(RepositoryModule module);
        Builder storageModule(StorageModule module);

        AppComponent build();
    }

    void inject(FitKidsApp application);
}




