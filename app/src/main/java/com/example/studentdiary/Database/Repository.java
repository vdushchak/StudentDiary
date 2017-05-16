package com.example.studentdiary.Database;

import com.activeandroid.query.Select;
import com.example.studentdiary.Entities.Subject;

import java.util.List;

/**
 * Created by Виталина on 09.05.2017.
 */

public class Repository {
    public static List<Subject> getSchedule(String day){
        return new Select().from(Subject.class).where("day=?",day).execute();
    }
}
