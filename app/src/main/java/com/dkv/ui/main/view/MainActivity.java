package com.dkv.ui.main.view;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.dkv.androidmvvmdaggerroom.R;
import com.dkv.androidmvvmdaggerroom.databinding.ActivityMainBinding;
import com.dkv.base.BaseActivity;
import com.dkv.preference.PreferenceManager;
import com.dkv.preference.constant.PreferenceKey;
import com.dkv.ui.examresult.view.ExamResultActivity;
import com.dkv.ui.main.viewmodel.MainActivityViewModel;
import com.dkv.ui.student.view.StudentProfileActivity;
import com.dkv.util.CommonUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class MainActivity extends BaseActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    CommonUtil commonUtil;

    @Inject
    PreferenceManager preferenceManager;

    private MainActivityViewModel viewModel;

    @BindView(R.id.tool_common)
    android.support.v7.widget.Toolbar toolBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel.class);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setHomeViewModel(viewModel);
        ButterKnife.bind(this);
        setToolbar(toolBarView, getResources().getString(R.string.app_name), false);
        initializeView();
        setObservers();
    }

    private void initializeView() {

        if (!preferenceManager.getBooleanSharedPreference(PreferenceKey.KEY_USER_SYNCED)) {
            showProgressDialog();
            viewModel.processData();

        }

    }

    private void setObservers() {


        viewModel.getProfileScreenTriggerObservable().observe(this, (Integer type) -> {

            if (type != null && type == 1) {
                // Question section
                moveToQuestion();

            } else if (type != null && type == 2) {
                // Result section
                moveToResult();
            }
        });

        viewModel.getInsertCompleteCheck().observe(this, (Boolean result) -> {

            if (result != null && result) {
                dismissProgressDialog();
                preferenceManager.setSharedPreferences(PreferenceKey.KEY_USER_SYNCED, true);

            }
        });
    }


    private void moveToQuestion() {

        commonUtil.moveToActivity(this, StudentProfileActivity.class, null);

    }

    private void moveToResult() {

        commonUtil.moveToActivity(this, ExamResultActivity.class, null);

    }

}