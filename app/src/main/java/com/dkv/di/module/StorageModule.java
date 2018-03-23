package com.dkv.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.dkv.db.AppDatabase;
import com.dkv.db.constant.DatabaseConstant;
import com.dkv.di.scope.ApplicationScope;
import com.dkv.preference.PreferenceManager;

import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {

    @Provides
    @ApplicationScope
    protected PreferenceManager providePreferenceManager(Context context) {

        return new PreferenceManager(context);
    }

    @Provides
    @ApplicationScope
    protected AppDatabase provideAppDatabase(Context context) {

        return Room.databaseBuilder(context, AppDatabase.class,
                DatabaseConstant.DATABASE_NAME).build();
    }

}
