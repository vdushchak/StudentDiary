package com.example.studentdiary.Entities;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Виталина on 09.05.2017.
 */
@Table(name = "Homework")
public class Homework extends Model {
    @Column
    private String task;
    @Column
    private boolean status;
    @Column(name = "subject")
    private Subject subject;

    public Homework() {
        super();
    }

    public Homework(String task, boolean status, Subject subject) {
        super();
        this.task = task;
        this.status = status;
        this.subject = subject;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
