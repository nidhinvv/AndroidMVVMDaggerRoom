package com.dkv.ui.student.view;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.dkv.androidmvvmdaggerroom.R;
import com.dkv.androidmvvmdaggerroom.databinding.ActivityStudentProfileBinding;
import com.dkv.base.BaseActivity;
import com.dkv.ui.question.view.QuestionActivity;
import com.dkv.ui.student.constant.StudentProfileConstant;
import com.dkv.ui.student.viewmodel.StudentProfileViewModel;
import com.dkv.util.CommonUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class StudentProfileActivity extends BaseActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    CommonUtil commonUtil;

    private StudentProfileViewModel viewModel;
    private ActivityStudentProfileBinding binding;

    @BindView(R.id.tool_common)
    android.support.v7.widget.Toolbar toolBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(StudentProfileViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_student_profile);
        binding.setStudentViewModel(viewModel);
        ButterKnife.bind(this);
        setToolbar(toolBarView, getResources().getString(R.string.tittle_profile), true);
        setObservers();

    }

    private void setObservers() {


        viewModel.getProfileScreenTriggerObservable().observe(this, (Integer type) -> {


            if (type != null && type == 1) {
                // New profile for the student
                createProfile();
            }
        });

        viewModel.getStudentProfileCompleteCheck().observe(this, (Integer id) -> {

            if (id != null) {

                Bundle bundle = new Bundle();
                bundle.putInt(StudentProfileConstant.STUDENT_PROFILE_ID, id);
                moveToQuestion(bundle);

            }
        });

    }


    private void createProfile() {

        if (!TextUtils.isEmpty(binding.studentName.getText().toString())) {
            viewModel.processStudentProfile(binding.studentName.getText().toString());
        } else {
            Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show();

        }

    }

    private void moveToQuestion(Bundle bundle) {

        commonUtil.moveToActivity(this, QuestionActivity.class, bundle);
        finish();

    }

}