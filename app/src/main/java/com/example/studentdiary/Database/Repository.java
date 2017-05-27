package com.example.studentdiary.Database;

import com.activeandroid.query.Select;
import com.example.studentdiary.Entities.Homework;
import com.example.studentdiary.Entities.Mark;
import com.example.studentdiary.Entities.Subject;

import java.util.List;

/**
 * Created by Виталина on 09.05.2017.
 */

public class Repository {
    public static List<Subject> getSchedule(String day){
        return new Select().from(Subject.class).where("day=?",day).execute();
    }
    public static void saveOrUpdate(Subject subject){
        Subject s = new Select().from(Subject.class).where("day=?",subject.getDay())
                .where("subject_number=?",subject.getSubject_number()).executeSingle();
         if (s!=null) {
             s.delete();
         }
         subject.save();
    }
    public static List<Homework> getHomework(long subjectId){
        return new Select().from(Homework.class).where("subject=?",subjectId).execute();
    }
    public static List<Mark> getMarks(long subjectId){
        return new Select().from(Mark.class).where("subject=?",subjectId).execute();
    }
    public static Subject getSubject(long subjectId){
        return new Select().from(Subject.class).where("id=?",subjectId).executeSingle();
    }
}
