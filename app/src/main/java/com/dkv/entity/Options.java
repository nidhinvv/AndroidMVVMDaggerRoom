package com.dkv.entity;

import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@android.arch.persistence.room.Entity
public class Options {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private Integer questionId;
    private Integer optionId;
    private String value;
    private boolean isCorrectAnswer;
    private boolean isAnswered;

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isCorrectAnswer() {
        return isCorrectAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        isCorrectAnswer = correctAnswer;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }
}
