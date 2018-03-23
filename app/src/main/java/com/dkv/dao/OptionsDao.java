package com.dkv.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import com.dkv.entity.Options;


@Dao
public interface OptionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOptions(Options options);

}
