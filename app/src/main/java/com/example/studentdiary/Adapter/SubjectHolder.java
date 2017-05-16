package com.example.studentdiary.Adapter;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.studentdiary.Entities.Subject;
import com.example.studentdiary.R;


import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Виталина on 09.05.2017.
 */

@EViewGroup(R.layout.subject)
public class SubjectHolder extends LinearLayout {

    @ViewById
    TextView vLessonNumber;

    @ViewById
    TextView lesson_name;

    @ViewById
    TextView lesson_teacher;

    @ViewById
    TextView lesson_room;

    @ViewById
    LinearLayout subject_layout;

    public SubjectHolder(Context context) {
        super(context);
    }

    public void bind(Subject subject, int pos, OnClickListener listener) {
        vLessonNumber.setText(subject.getSubject_number()+"");
        lesson_name.setText(subject.getSubject_name());
        lesson_room.setText(subject.getSubject_room());
        lesson_teacher.setText(subject.getSubject_teacher());

        subject_layout.setTag(pos);
        vLessonNumber.setTag(pos);
        lesson_name.setTag(pos);
        lesson_teacher.setTag(pos);
        lesson_room.setTag(pos);

        subject_layout.setOnClickListener(listener);
        vLessonNumber.setOnClickListener(listener);
        lesson_name.setOnClickListener(listener);
        lesson_teacher.setOnClickListener(listener);
        lesson_room.setOnClickListener(listener);
    }
}
