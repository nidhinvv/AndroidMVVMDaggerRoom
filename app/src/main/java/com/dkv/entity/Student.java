package com.dkv.entity;

import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@android.arch.persistence.room.Entity
public class Student {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private String firstName;

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}