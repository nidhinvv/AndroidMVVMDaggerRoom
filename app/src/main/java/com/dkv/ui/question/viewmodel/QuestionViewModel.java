package com.dkv.ui.question.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.dkv.helper.SingleLiveEvent;
import com.dkv.model.QuestionDbModel;
import com.dkv.repository.CommonRepository;

import java.util.List;

import javax.inject.Inject;

public class QuestionViewModel extends ViewModel {

    private CommonRepository commonRepository;
    private LiveData<List<QuestionDbModel>> questionList = new MutableLiveData<>();
    private SingleLiveEvent<Boolean> previousClick = new SingleLiveEvent<>();
    private SingleLiveEvent<Boolean> nextClick = new SingleLiveEvent<>();
    private SingleLiveEvent<Boolean> examCompleteCheck;



    @Inject
    public QuestionViewModel(CommonRepository commonRepository) {
        this.commonRepository = commonRepository;
        examCompleteCheck = commonRepository.getExamCompleteCheck();
        fetchQuestionFromDb();
    }

    private void fetchQuestionFromDb() {

        questionList = commonRepository.getAllQuestions();
    }

    public LiveData<List<QuestionDbModel>> getQuestionList() {
        return questionList;
    }


    public SingleLiveEvent<Boolean> getPreviousClick() {

        return previousClick;
    }

    public SingleLiveEvent<Boolean> getNextClick() {

        return nextClick;
    }

    public void previousClick() {

        previousClick.setValue(true);
    }

    public void nextClick() {

        nextClick.setValue(true);
    }

    public void saveExam(int id, List<QuestionDbModel> questionDbModel) {

        commonRepository.saveExam(id, questionDbModel);
    }

    public SingleLiveEvent<Boolean> getExamCompleteCheck() {
        return examCompleteCheck;
    }
}
