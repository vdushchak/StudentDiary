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
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
