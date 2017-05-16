package com.example.studentdiary.Entities;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by Виталина on 09.05.2017.
 */
@Table(name= "Subject")
public class Subject  extends Model{

    @Column
   private String subject_name;
    @Column
   private String subject_teacher;
    @Column
   private String subject_room;
    @Column
    private String day;
    @Column
    private int subject_number;

    public Subject() {
        super();
    }



    public Subject(String subject_name, String subject_teacher, String subject_room, String day, int subject_number) {
        super();
        this.subject_name = subject_name;
        this.subject_teacher = subject_teacher;
        this.subject_room = subject_room;
        this.day = day;
        this.subject_number = subject_number;

    }

    public String getDay() {
        return day;
    }

    public int getSubject_number() {
        return subject_number;
    }

    public void setSubject_number(int subject_number) {
        this.subject_number = subject_number;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getSubject_teacher() {
        return subject_teacher;
    }

    public void setSubject_teacher(String subject_teacher) {
        this.subject_teacher = subject_teacher;
    }

    public String getSubject_room() {
        return subject_room;
    }

    public void setSubject_room(String subject_room) {
        this.subject_room = subject_room;
    }
    public List<Mark> getMarks(){
        return getMany(Mark.class,"Subject");
    }
    public List<Homework> getHomework(){
        return getMany(Homework.class,"Subject");
    }
}
