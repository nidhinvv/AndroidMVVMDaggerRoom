package com.dkv.ui.student.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.dkv.helper.SingleLiveEvent;
import com.dkv.repository.CommonRepository;

import javax.inject.Inject;


public class StudentProfileViewModel extends ViewModel {

    private SingleLiveEvent<Integer> profileScreenTriggerObservable = new SingleLiveEvent<>();
    private SingleLiveEvent<Integer> studentProfileCompleteCheck;
    private CommonRepository commonRepository;

    @Inject
    public StudentProfileViewModel(CommonRepository commonRepository){
        this.commonRepository = commonRepository;
        studentProfileCompleteCheck = commonRepository.getStudentProfileCompleteCheck();
    }


    public void profileClick() {

        profileScreenTriggerObservable.setValue(1);
    }

    public SingleLiveEvent<Integer> getProfileScreenTriggerObservable() {
        return profileScreenTriggerObservable;
    }


    public void processStudentProfile(String name) {

        commonRepository.createStudentProfile(name);
    }

    public SingleLiveEvent<Integer> getStudentProfileCompleteCheck() {
        return studentProfileCompleteCheck;
    }
}
