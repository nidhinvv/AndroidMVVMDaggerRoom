package com.dkv.ui.main.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.dkv.helper.SingleLiveEvent;
import com.dkv.repository.CommonRepository;

import javax.inject.Inject;


public class MainActivityViewModel extends ViewModel {

    private SingleLiveEvent<Integer> profileScreenTriggerObservable = new SingleLiveEvent<>();
    private SingleLiveEvent<Boolean> insertCompleteCheck;
    private CommonRepository commonRepository;

    @Inject
    public MainActivityViewModel(CommonRepository commonRepository) {
        this.commonRepository = commonRepository;
        insertCompleteCheck = commonRepository.getInsertCompleteCheck();
    }


    public void profileClick() {

        profileScreenTriggerObservable.setValue(1);
    }

    public void examDetailsClick() {

        profileScreenTriggerObservable.setValue(2);
    }

    public SingleLiveEvent<Integer> getProfileScreenTriggerObservable() {
        return profileScreenTriggerObservable;
    }


    public void processData() {
        commonRepository.processAssetTracking();
    }

    public SingleLiveEvent<Boolean> getInsertCompleteCheck() {
        return insertCompleteCheck;
    }
}
