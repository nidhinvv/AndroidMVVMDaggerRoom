package com.dkv.model;


import java.util.List;

public class QuestionsAnswerModel {


    private String question;
    private List<OptionsModel> options;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<OptionsModel> getOptions() {
        return options;
    }

    public void setOptions(List<OptionsModel> options) {
        this.options = options;
    }
}
