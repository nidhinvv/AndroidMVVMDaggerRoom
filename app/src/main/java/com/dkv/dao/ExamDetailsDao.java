package com.dkv.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import com.dkv.entity.ExamDetails;

import java.util.List;

@Dao
public interface ExamDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertExamDetails(List<ExamDetails> examDetails);

}
