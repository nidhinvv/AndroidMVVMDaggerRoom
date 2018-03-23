package com.dkv.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import com.dkv.db.AppDatabase;
import com.dkv.helper.SingleLiveEvent;
import com.dkv.model.AnswerDbModel;
import com.dkv.model.QuestionDbModel;
import com.dkv.task.CreateStudentProfileTask;
import com.dkv.task.ExamTask;
import com.dkv.task.InsertQuestionsToDbTask;
import com.dkv.util.CommonUtil;

import java.util.List;

public class CommonRepository {

    private AppDatabase appDatabase;
    private Context context;
    private SingleLiveEvent<Boolean> insertCompleteCheck;
    private SingleLiveEvent<Integer> studentProfileCompleteCheck;
    private SingleLiveEvent<Boolean> examCompleteCheck;

    public CommonRepository(Context context, CommonUtil commonUtil, AppDatabase appDatabase) {

        this.appDatabase = appDatabase;
        this.context = context;
        this.insertCompleteCheck = new SingleLiveEvent<>();
        this.studentProfileCompleteCheck = new SingleLiveEvent<>();
        this.examCompleteCheck = new SingleLiveEvent<>();

    }

    public SingleLiveEvent<Boolean> getInsertCompleteCheck() {
        return insertCompleteCheck;
    }

    public void processAssetTracking() {

        new InsertQuestionsToDbTask(appDatabase, context,
                processAsset -> insertCompleteCheck.setValue(processAsset)).executeOnExecutor(
                AsyncTask.THREAD_POOL_EXECUTOR);
    }


    public LiveData<List<QuestionDbModel>> getAllQuestions() {

        return appDatabase.questionsDao().getAllQuestions();
    }

    public LiveData<List<AnswerDbModel>> getAllResult() {

        return appDatabase.examStatusDao().getAllResult();
    }


    public void createStudentProfile(String name) {

        new CreateStudentProfileTask(appDatabase, name,
                processAsset -> studentProfileCompleteCheck.setValue(processAsset)).executeOnExecutor(
                AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public SingleLiveEvent<Integer> getStudentProfileCompleteCheck() {
        return studentProfileCompleteCheck;
    }


    public void saveExam(int id, List<QuestionDbModel> questionDbModel) {

        new ExamTask(appDatabase, id,questionDbModel,
                processAsset -> examCompleteCheck.setValue(processAsset)).executeOnExecutor(
                AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public SingleLiveEvent<Boolean> getExamCompleteCheck() {
        return examCompleteCheck;
    }
}
