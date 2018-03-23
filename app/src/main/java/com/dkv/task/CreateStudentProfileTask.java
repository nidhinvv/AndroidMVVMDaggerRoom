package com.dkv.task;

import android.os.AsyncTask;

import com.dkv.db.AppDatabase;
import com.dkv.entity.Student;
import com.dkv.taskcallback.InsetStudentTaskCallback;


public class CreateStudentProfileTask extends AsyncTask<Void, Void, Integer> {


    private AppDatabase appDatabase;
    private InsetStudentTaskCallback insetStudentTaskCallback;
    private String name;
    private int studentId;

    public CreateStudentProfileTask(AppDatabase appDatabase, String name,
                                    InsetStudentTaskCallback insetStudentTaskCallback) {

        this.appDatabase = appDatabase;
        this.name = name;
        this.insetStudentTaskCallback = insetStudentTaskCallback;
    }

    @Override
    protected Integer doInBackground(Void... voids) {

        Student student = new Student();
        student.setFirstName(name);
        studentId = (int) appDatabase.studentDao().insertStudent(student);
        return null;
    }


    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        if (insetStudentTaskCallback != null) {
            insetStudentTaskCallback.onInsetStudentTaskCallback(studentId);
        }
    }

}
