package com.dkv.task;

import android.os.AsyncTask;

import com.dkv.db.AppDatabase;
import com.dkv.entity.ExamDetails;
import com.dkv.entity.ExamStatus;
import com.dkv.entity.Options;
import com.dkv.model.QuestionDbModel;
import com.dkv.taskcallback.ExamConfirmTaskCallback;

import java.util.ArrayList;
import java.util.List;

public class ExamTask extends AsyncTask<Void, Void, Integer> {


    private AppDatabase appDatabase;
    private ExamConfirmTaskCallback examConfirmTaskCallback;
    private int studentId;
    private List<QuestionDbModel> questionDbModel;

    public ExamTask(AppDatabase appDatabase, int studentId, List<QuestionDbModel> questionDbModel,
                    ExamConfirmTaskCallback examConfirmTaskCallback) {

        this.appDatabase = appDatabase;
        this.studentId = studentId;
        this.questionDbModel = questionDbModel;
        this.examConfirmTaskCallback = examConfirmTaskCallback;
    }

    @Override
    protected Integer doInBackground(Void... voids) {

        saveExamDetails();
        return null;
    }


    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        if (examConfirmTaskCallback != null) {
            examConfirmTaskCallback.onExamConfirmTaskCallback(true);
        }
    }

    private void saveExamDetails() {


        List<ExamDetails> examDetailsList = new ArrayList<>();
        ExamStatus examStatus = new ExamStatus();
        
        int questionId;
        int noOfQuestionAnswered = 0;
        int noOfCorrectAnswers = 0;

        if (questionDbModel != null) {
            int size = questionDbModel.size();
            if (size >0 ) {
                for (int i = 0; i < size; i++) {
                    questionId = questionDbModel.get(i).getQuestions().getId();
                    
                    List<Options> optionsList = questionDbModel.get(i).getOptions();
                    
                    if(optionsList != null) {
                        int optionsSize = optionsList.size();
                        int correctAnswerId = 0;
                        int studentAnswerId = 0;
                        if (optionsSize > 0) {
                            for (int j = 0; j < optionsSize; j++) {
                                Options options = optionsList.get(j);
                                
                                if(options.isCorrectAnswer()){
                                    correctAnswerId = options.getId();
                                }
                                if(options.isAnswered()){
                                    studentAnswerId = options.getId();
                                }

                            }
                            if(studentAnswerId != 0){
                                noOfQuestionAnswered ++;

                                ExamDetails examDetails = new ExamDetails();
                                examDetails.setUserId(studentId);
                                examDetails.setQuestionId(questionId);
                                examDetails.setAnswerId(studentAnswerId);

                                examDetailsList.add(examDetails);
                            }
                            if(studentAnswerId != 0 && correctAnswerId != 0 && studentAnswerId == correctAnswerId){
                                noOfCorrectAnswers ++;
                            }

                        }
                    }

                }

                examStatus.setUserId(studentId);
                examStatus.setTotalQuestions(size);
                examStatus.setTotalAnsweredQuestions(noOfQuestionAnswered);
                examStatus.setTotalCorrectAnswer(noOfCorrectAnswers);

                appDatabase.examStatusDao().insertExamStatus(examStatus);
                appDatabase.examDetailsDao().insertExamDetails(examDetailsList);

            }
        }

    }

}
