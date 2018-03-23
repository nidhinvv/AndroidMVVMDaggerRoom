package com.dkv.ui.examresult.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.dkv.model.AnswerDbModel;
import com.dkv.repository.CommonRepository;

import java.util.List;

import javax.inject.Inject;

public class ExamResultViewModel extends ViewModel {

    private CommonRepository commonRepository;
    private LiveData<List<AnswerDbModel>> examStatus = new MutableLiveData<>();


    @Inject
    public ExamResultViewModel(CommonRepository commonRepository) {
        this.commonRepository = commonRepository;
        fetchResultDb();
    }

    private void fetchResultDb() {

        examStatus = commonRepository.getAllResult();
    }

    public LiveData<List<AnswerDbModel>> getExamStatus() {
        return examStatus;
    }
}


