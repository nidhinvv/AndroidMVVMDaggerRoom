package com.dkv.entity;

import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@android.arch.persistence.room.Entity
public class Questions {

    @NonNull
    @PrimaryKey
    private Integer id;

    private String question;

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


}
