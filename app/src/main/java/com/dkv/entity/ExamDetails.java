package com.dkv.entity;

import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@android.arch.persistence.room.Entity
public class ExamDetails {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private Integer userId;
    private Integer questionId;
    private Integer answerId;

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

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }
}
