package com.dkv.task;

import android.content.Context;
import android.os.AsyncTask;

import com.dkv.app.AppLog;
import com.dkv.db.AppDatabase;
import com.dkv.entity.Options;
import com.dkv.entity.Questions;
import com.dkv.model.OptionsModel;
import com.dkv.model.QuestionsAnswerModel;
import com.dkv.taskcallback.InsetConfirmTaskCallback;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class InsertQuestionsToDbTask extends AsyncTask<Void, Void, Integer> {


    private AppDatabase appDatabase;
    private Context context;
    private InsetConfirmTaskCallback insetConfirmTaskCallback;

    public InsertQuestionsToDbTask(AppDatabase appDatabase, Context context,
                                   InsetConfirmTaskCallback insetConfirmTaskCallback) {

        this.appDatabase = appDatabase;
        this.context = context;
        this.insetConfirmTaskCallback = insetConfirmTaskCallback;
    }

    @Override
    protected Integer doInBackground(Void... voids) {

        try {
            InputStreamReader is = new InputStreamReader(context.getAssets().open("sample_questions.csv"));
            BufferedReader reader = new BufferedReader(is);
            reader.readLine();
            String line;
            int questionId = 1;
            while ((line = reader.readLine()) != null) {
                //Removing extra quotes in the json
                String output = line.replace("\"\"", "\"");
                String json = output.substring(1, output.length() - 1);

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                QuestionsAnswerModel questionsAnswerModel =  gson.fromJson(json, QuestionsAnswerModel.class);

                saveDataToDb(questionsAnswerModel, questionId);
                questionId ++;

            }
        } catch (IOException e) {
            AppLog.showErrorLog(e.getMessage());
        }


        return null;
    }


    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        if (insetConfirmTaskCallback != null) {
            insetConfirmTaskCallback.onInsetConfirmTaskCallback(true);
        }
    }

    private void saveDataToDb(QuestionsAnswerModel questionsAnswerModel, int id){

        Questions questions = new Questions();
        questions.setId(id);
        questions.setQuestion(questionsAnswerModel.getQuestion());
        appDatabase.questionsDao().insertQuestions(questions);


        List<OptionsModel> optionsModelList = questionsAnswerModel.getOptions();

        int optionId = 1;
        for(OptionsModel optionsModel: optionsModelList){

            Options options = new Options();
            options.setQuestionId(id);
            options.setOptionId(optionId);
            options.setCorrectAnswer(optionsModel.isCorrect());
            options.setValue(optionsModel.getValue());
            options.setAnswered(false);

            appDatabase.optionsDao().insertOptions(options);
            optionId ++;

        }








    }
}
