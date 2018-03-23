package com.dkv.entity;

import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@android.arch.persistence.room.Entity
public class ExamStatus {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private Integer userId;
    private Integer totalQuestions;
    private Integer totalAnsweredQuestions;
    private Integer totalCorrectAnswer;


    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(Integer totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public Integer getTotalAnsweredQuestions() {
        return totalAnsweredQuestions;
    }

    public void setTotalAnsweredQuestions(Integer totalAnsweredQuestions) {
        this.totalAnsweredQuestions = totalAnsweredQuestions;
    }

    public Integer getTotalCorrectAnswer() {
        return totalCorrectAnswer;
    }

    public void setTotalCorrectAnswer(Integer totalCorrectAnswer) {
        this.totalCorrectAnswer = totalCorrectAnswer;
    }
}
