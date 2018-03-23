package com.dkv.di.module;


import com.dkv.di.scope.LocalScope;
import com.dkv.ui.examresult.view.ExamResultActivity;
import com.dkv.ui.main.view.MainActivity;
import com.dkv.ui.question.view.QuestionActivity;
import com.dkv.ui.student.view.StudentProfileActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class BuildersModule {

    @LocalScope
    @ContributesAndroidInjector(modules = UtilityModule.class)
    abstract MainActivity bindMainActivity();


    @LocalScope
    @ContributesAndroidInjector(modules = UtilityModule.class)
    abstract QuestionActivity bindQuestionActivity();

    @LocalScope
    @ContributesAndroidInjector(modules = UtilityModule.class)
    abstract StudentProfileActivity bindStudentProfileActivity();

    @LocalScope
    @ContributesAndroidInjector(modules = UtilityModule.class)
    abstract ExamResultActivity bindExamResultActivity();
}
