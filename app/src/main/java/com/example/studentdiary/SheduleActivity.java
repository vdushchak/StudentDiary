package com.example.studentdiary;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.example.studentdiary.Adapter.SubjectAdapter;
import com.example.studentdiary.Database.Repository;
import com.example.studentdiary.Entities.Subject;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * Created by ivang on 16.05.2017.
 */
@EActivity(R.layout.schedule)
public class SheduleActivity extends AppCompatActivity{
    @Extra
    String day;
    @ViewById
    RecyclerView schedule;
    @ViewById
    FloatingActionButton addSubject;
    @Bean
    SubjectAdapter adapter;
    @AfterViews
    public void getSchedule(){
        List<Subject> daySchedule = Repository.getSchedule(day);
        adapter.setClickable(true);
        adapter.setDay(day);
        schedule.setLayoutManager(new LinearLayoutManager(this));
        schedule.setAdapter(adapter);
        adapter.setList(daySchedule);
        Snackbar.make(schedule, day ,Snackbar.LENGTH_SHORT).show();
        addSubject.setVisibility(View.VISIBLE);
    }

    @Click(R.id.addSubject)
    public void createSubject(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
// ...Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.edit_subject, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
        Snackbar.make(schedule,"New Subject",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        addSubject.setVisibility(View.GONE);
    }
}
