package com.dkv.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.dkv.entity.ExamStatus;
import com.dkv.model.AnswerDbModel;

import java.util.List;

@Dao
public interface ExamStatusDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertExamStatus(ExamStatus examStatus);

    @Query("SELECT * FROM ExamStatus ORDER BY id DESC")
    LiveData<List<AnswerDbModel>> getAllResult();


}
