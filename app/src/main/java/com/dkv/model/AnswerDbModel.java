package com.dkv.model;


import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.dkv.entity.ExamStatus;
import com.dkv.entity.Student;

import java.util.List;

public class AnswerDbModel {

    @Embedded
    public ExamStatus examStatus;

    @Relation(parentColumn = "userId", entityColumn = "id", entity = Student.class)
    private List<Student> student;

    public ExamStatus getExamStatus() {
        return examStatus;
    }

    public void setExamStatus(ExamStatus examStatus) {
        this.examStatus = examStatus;
    }

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }
}
