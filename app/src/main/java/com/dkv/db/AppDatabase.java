package com.dkv.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.dkv.dao.ExamDetailsDao;
import com.dkv.dao.ExamStatusDao;
import com.dkv.dao.OptionsDao;
import com.dkv.dao.QuestionsDao;
import com.dkv.dao.StudentDao;
import com.dkv.entity.ExamDetails;
import com.dkv.entity.ExamStatus;
import com.dkv.entity.Options;
import com.dkv.entity.Questions;
import com.dkv.entity.Student;
import com.dkv.helper.DateConverter;


@Database(entities = {
        Student.class,
        Questions.class,
        Options.class,
        ExamDetails.class,
        ExamStatus.class}, version = 1)

@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract StudentDao studentDao();
    public abstract QuestionsDao questionsDao();
    public abstract OptionsDao optionsDao();
    public abstract ExamDetailsDao examDetailsDao();
    public abstract ExamStatusDao examStatusDao();


}
