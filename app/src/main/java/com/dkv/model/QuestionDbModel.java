package com.dkv.model;


import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.dkv.entity.Options;
import com.dkv.entity.Questions;

import java.util.List;

public class QuestionDbModel {

    @Embedded
    public Questions questions;

    @Relation(parentColumn = "id", entityColumn = "questionId", entity = Options.class)
    private List<Options> options;

    public Questions getQuestions() {
        return questions;
    }

    public void setQuestions(Questions questions) {
        this.questions = questions;
    }

    public List<Options> getOptions() {
        return options;
    }

    public void setOptions(List<Options> entity) {
        this.options = entity;
    }
}
