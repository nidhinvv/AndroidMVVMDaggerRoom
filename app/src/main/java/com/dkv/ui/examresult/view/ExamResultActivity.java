package com.dkv.ui.examresult.view;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.dkv.androidmvvmdaggerroom.R;
import com.dkv.androidmvvmdaggerroom.databinding.ActivityExamResultBinding;
import com.dkv.base.BaseActivity;
import com.dkv.model.AnswerDbModel;
import com.dkv.ui.examresult.viewmodel.ExamResultViewModel;
import com.dkv.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class ExamResultActivity extends BaseActivity {


    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    CommonUtil commonUtil;

    private ExamResultViewModel viewModel;
    private ExamResultAdapter examResultAdapter;
    private ActivityExamResultBinding binding;
    private List<AnswerDbModel> examStatuses;

    @BindView(R.id.tool_common)
    android.support.v7.widget.Toolbar toolBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ExamResultViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_exam_result);
        binding.setExamResultViewModel(viewModel);
        ButterKnife.bind(this);
        setToolbar(toolBarView, getResources().getString(R.string.tittle_exam_result), true);

        initializeView();
        setObservers();
    }


    private void initializeView() {

        binding.examResultRecyclerView.setHasFixedSize(true);
        binding.examResultRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<AnswerDbModel> examStatuses = new ArrayList<>();
        examResultAdapter = new ExamResultAdapter(examStatuses);
        binding.examResultRecyclerView.setAdapter(examResultAdapter);

    }

    private void setObservers() {

        viewModel.getExamStatus().observe(this, (List<AnswerDbModel> result) -> {

            if (result != null && result.size() > 0) {
                examStatuses = result;
                setResult();
            }

        });

    }


    private void setResult() {

        examResultAdapter.updateList(examStatuses);
    }


}
