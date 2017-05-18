package com.example.studentdiary;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.studentdiary.Adapter.HomeworkAdapter;
import com.example.studentdiary.Database.Repository;
import com.example.studentdiary.Entities.Homework;
import com.example.studentdiary.Entities.Subject;
import com.example.studentdiary.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Виталина on 17.05.2017.
 */
@EActivity(R.layout.add_homework)
public class HomeworkActivity extends AppCompatActivity {
    @ViewById
    RecyclerView homework_list;
    @Bean
    HomeworkAdapter adapter;
    @Extra
    long subjectId;
    Subject subject;
    @AfterViews
    public void init(){
        subject = Repository.getSubject(subjectId);
        List<Homework> homeworkList =  Repository.getHomework(subjectId);
        homework_list.setLayoutManager(new LinearLayoutManager(this));
        homework_list.setAdapter(adapter);
        adapter.setItems(homeworkList);
    }
    @Click
    public void addHomework(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
// ...Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.edit_homework, null);
        dialogBuilder.setView(dialogView);
        final EditText task = (EditText) dialogView.findViewById(R.id.task);
        final CheckBox homework_status = (CheckBox) dialogView.findViewById(R.id.homework_status);
        dialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Homework homework = new Homework(task.getText().toString(),
                        homework_status.isChecked(),subject);
                homework.save();
                adapter.addItem(homework);
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }
}
