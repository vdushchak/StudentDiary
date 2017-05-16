package com.example.studentdiary.Entities;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

/**
 * Created by Виталина on 09.05.2017.
 */
@Table(name = "Mark")
public class Mark extends Model {
    @Column
    private Date date;
    @Column
    private String mark;
    @Column(name = "subject")
    private Subject subject;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Mark() {
    }

    public Mark(Date date, String mark, Subject subject) {
        this.date = date;
        this.mark = mark;
        this.subject = subject;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
