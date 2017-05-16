package com.example.studentdiary;



import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.studentdiary.Adapter.SubjectAdapter;
import com.example.studentdiary.Database.Repository;
import com.example.studentdiary.Entities.Subject;

import org.androidannotations.annotations.AfterExtras;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * Created by Виталина on 09.05.2017.
 */
@EFragment(R.layout.schedule)
public class ScheduleFragment extends Fragment  {
    @FragmentArg
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
        adapter.setClickable(false);
        adapter.setDay(day);
        schedule.setLayoutManager(new LinearLayoutManager(getActivity()));
        schedule.setAdapter(adapter);
        adapter.setList(daySchedule);
    }

    @Click(R.id.addSubject)
    public void createSubject(){
        Snackbar.make(schedule,"New Subject",Snackbar.LENGTH_SHORT).show();
    }
}
