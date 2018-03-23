package com.dkv.di.module;

import android.content.Context;

import com.dkv.db.AppDatabase;
import com.dkv.di.scope.ApplicationScope;
import com.dkv.repository.CommonRepository;
import com.dkv.util.CommonUtil;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {


    @Provides
    @ApplicationScope
    protected CommonRepository provideCommonRepository(Context context, CommonUtil commonUtil, AppDatabase database) {

        return new CommonRepository(context, commonUtil, database);
    }
}
