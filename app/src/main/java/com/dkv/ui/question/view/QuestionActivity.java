package com.dkv.ui.question.view;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.dkv.androidmvvmdaggerroom.R;
import com.dkv.androidmvvmdaggerroom.databinding.ActivityQuestionBinding;
import com.dkv.base.BaseActivity;
import com.dkv.entity.Options;
import com.dkv.model.QuestionDbModel;
import com.dkv.taskcallback.ConfirmationDialogCallback;
import com.dkv.ui.question.viewmodel.QuestionViewModel;
import com.dkv.ui.student.constant.StudentProfileConstant;
import com.dkv.util.CommonUtil;
import com.dkv.util.DialogUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class QuestionActivity extends BaseActivity implements ConfirmationDialogCallback {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    CommonUtil commonUtil;

    @Inject
    DialogUtil dialogUtil;

    private QuestionViewModel viewModel;
    private QuestionAdapter questionAdapter;
    ActivityQuestionBinding binding;
    private List<QuestionDbModel> questionDbModels;
    private int position = 0;
    private int studentId;
    private static final int DIALOG_ID_DELETE_VISIT = 1001;

    @BindView(R.id.tool_common)
    android.support.v7.widget.Toolbar toolBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(QuestionViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_question);
        binding.setQuestionViewModel(viewModel);
        ButterKnife.bind(this);
        setToolbar(toolBarView, getResources().getString(R.string.tittle_exam), true);

        initializeView();
        setObservers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_item_done:
                viewModel.saveExam(studentId, questionDbModels);
                break;
        }

        return false;


    }

    private void initializeView() {

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            studentId = bundle.getInt(StudentProfileConstant.STUDENT_PROFILE_ID);
        }

        binding.optonsRecyclerView.setHasFixedSize(true);
        binding.optonsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Options> optionsList = new ArrayList<>();
        questionAdapter = new QuestionAdapter(optionsList);
        binding.optonsRecyclerView.setAdapter(questionAdapter);

    }

    private void setObservers() {

        viewModel.getQuestionList().observe(this, (List<QuestionDbModel> result) -> {

            if (result != null && result.size() > 0) {
                questionDbModels = result;
                setQuestion(0);
            }

        });

        viewModel.getNextClick().observe(this, click -> {

            if (click != null && click && (questionDbModels.size() > position + 1)) {
                position++;
                setQuestion(position);
            }

        });

        viewModel.getPreviousClick().observe(this, click -> {

            if (click != null && click && (position - 1 >= 0)) {
                position--;
                setQuestion(position);
            }

        });

        viewModel.getExamCompleteCheck().observe(this, result -> {

            if (result != null && result) {
                Toast.makeText(this, "Complected the exam", Toast.LENGTH_SHORT).show();
                finish();
            }

        });

    }


    private void setQuestion(int position) {

        String question = questionDbModels.get(position).getQuestions().getId() + " . "+
                android.text.Html.fromHtml(questionDbModels.get(position).getQuestions().getQuestion()).toString();
        showHidPreviousNextButton();
        binding.questionText.setText(question);
        questionAdapter.updateList(questionDbModels.get(position).getOptions());
    }

    private void showHidPreviousNextButton() {

        if (isNextQuestionAvailable(position + 1)) {
            binding.bottomLayout.imageViewNext.setImageResource(R.drawable.ic_right_navarrow);
        } else {
            binding.bottomLayout.imageViewNext.setImageResource(R.drawable.ic_right_navarrow_fade);
        }

        if (isPreviousQuestionAvailable(position - 1)) {

            binding.bottomLayout.imageViewPrevious.setImageResource(R.drawable.ic_left_navarrow);

        } else {

            binding.bottomLayout.imageViewPrevious.setImageResource(
                    R.drawable.ic_left_navarrow_fade);

        }


    }

    private boolean isNextQuestionAvailable(int id) {
        boolean isQuestionAvailable = false;

        if (questionDbModels.size() > id) {

            QuestionDbModel canonicalQuestionAnswer = questionDbModels.get(id);

            if (canonicalQuestionAnswer != null) {
                isQuestionAvailable = true;
            }
        }

        return isQuestionAvailable;

    }

    private boolean isPreviousQuestionAvailable(int id) {

        boolean isQuestionAvailable = false;

        if (id > -1) {

            QuestionDbModel canonicalQuestionAnswer = questionDbModels.get(id);

            if (canonicalQuestionAnswer != null) {
                isQuestionAvailable = true;
            }
        }

        return isQuestionAvailable;
    }


    @Override
    public void onBackPressed() {
        showCancelDialog();

    }

    @Override
    public void onConfirmationDialogPositiveButtonClicked(int mDialogID) {
       finish();
    }

    @Override
    public void onConfirmationDialogNegativeButtonClicked(int mDialogID) {

    }

    private void showCancelDialog() {

        dialogUtil.showDoubleButtonAlert(this, DIALOG_ID_DELETE_VISIT, getString(R.string.alert_warning_title),
                getString(R.string.alert_warning_message),
                getString(R.string.button_text_warning_ok), getString(R.string.button_text_warning_cancel), this);
    }
}
