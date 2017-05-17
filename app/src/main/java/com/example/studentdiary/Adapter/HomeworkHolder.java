package com.example.studentdiary.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.studentdiary.Entities.Homework;
import com.example.studentdiary.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Виталина on 17.05.2017.
 */
@EViewGroup(R.layout.item_homework)
public class HomeworkHolder extends LinearLayout {
    @ViewById
    LinearLayout item_homework_layout;
    @ViewById
    TextView homework_task;
    @ViewById
    CheckBox status;
    public HomeworkHolder(@NonNull Context context) {
        super(context);
    }
    public void bind(Homework homework, int pos, View.OnClickListener listener){
        item_homework_layout.setTag(pos);
        homework_task.setTag(pos);
        status.setTag(pos);
        item_homework_layout.setOnClickListener(listener);
        homework_task.setOnClickListener(listener);
        status.setOnClickListener(listener);

        homework_task.setText(homework.getTask());
        status.setChecked(homework.isStatus());
    }
}
