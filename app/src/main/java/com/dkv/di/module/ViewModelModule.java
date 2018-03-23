package com.dkv.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.dkv.di.helper.ViewModelKey;
import com.dkv.helper.ViewModelFactory;
import com.dkv.ui.examresult.viewmodel.ExamResultViewModel;
import com.dkv.ui.main.viewmodel.MainActivityViewModel;
import com.dkv.ui.question.viewmodel.QuestionViewModel;
import com.dkv.ui.student.viewmodel.StudentProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public interface ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel.class)
    abstract ViewModel bindSplashViewModel(MainActivityViewModel mainActivityViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(QuestionViewModel.class)
    abstract ViewModel bindQuestionViewModel(QuestionViewModel questionViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(StudentProfileViewModel.class)
    abstract ViewModel bindStudentProfileViewModel(StudentProfileViewModel studentProfileViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ExamResultViewModel.class)
    abstract ViewModel bindExamResultViewModel(ExamResultViewModel examResultViewModel);



}
