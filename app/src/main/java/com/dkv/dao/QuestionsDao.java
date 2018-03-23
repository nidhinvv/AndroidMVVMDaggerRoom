package com.dkv.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.dkv.entity.Questions;
import com.dkv.model.QuestionDbModel;

import java.util.List;

@Dao
public interface QuestionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQuestions(Questions questions);

    @Query("SELECT * FROM Questions")
    LiveData<List<QuestionDbModel>> getAllQuestions();
}
