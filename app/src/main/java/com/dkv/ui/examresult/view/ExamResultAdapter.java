package com.dkv.ui.examresult.view;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dkv.androidmvvmdaggerroom.R;
import com.dkv.androidmvvmdaggerroom.databinding.ItemExamResultBinding;
import com.dkv.model.AnswerDbModel;

import java.util.List;

public class ExamResultAdapter extends RecyclerView.Adapter<ExamResultAdapter.ExamResultItemViewHolder> {

    private List<AnswerDbModel> examStatuses;

    ExamResultAdapter(List<AnswerDbModel> examStatuses) {
        this.examStatuses = examStatuses;
    }

    void updateList(List<AnswerDbModel> examStatuses) {

        this.examStatuses = examStatuses;
        notifyDataSetChanged();
    }

    @Override
    public ExamResultItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View statusContainer = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_exam_result, parent, false);
        return new ExamResultAdapter.ExamResultItemViewHolder(statusContainer);
    }

    @Override
    public void onBindViewHolder(ExamResultItemViewHolder holder, int position) {

        AnswerDbModel examStatus = examStatuses.get(position);
        holder.bindData(examStatus);

    }

    @Override
    public int getItemCount() {
        return examStatuses.size();
    }

    class ExamResultItemViewHolder extends RecyclerView.ViewHolder {

        private ItemExamResultBinding binding;

        private ExamResultItemViewHolder(View view) {

            super(view);
            binding = DataBindingUtil.bind(itemView);

        }

        private void bindData(AnswerDbModel examStatus) {

            String studentName = "Name : " + examStatus.getStudent().get(0).getFirstName();
            String totalNoOfQuestions = "Total Questions : " + String.valueOf(examStatus.getExamStatus().getTotalQuestions());
            String answeredQuestions = "Answered Questions : " + String.valueOf(examStatus.getExamStatus().getTotalAnsweredQuestions());
            String correctAnswer = "Correct Answers : " + String.valueOf(examStatus.getExamStatus().getTotalCorrectAnswer());

            binding.studentName.setText(studentName);
            binding.totalQuestion.setText(totalNoOfQuestions);
            binding.answeredQuestion.setText(answeredQuestions);
            binding.correctAnswer.setText(correctAnswer);

        }

    }

}
