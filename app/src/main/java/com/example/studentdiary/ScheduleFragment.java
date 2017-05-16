package com.example.studentdiary;



import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.studentdiary.Adapter.SubjectAdapter;
import com.example.studentdiary.Database.Repository;
import com.example.studentdiary.Entities.Subject;

import org.androidannotations.annotations.AfterExtras;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * Created by Виталина on 09.05.2017.
 */
@EFragment(R.layout.schedule)
public class ScheduleFragment extends Fragment {
    @FragmentArg
    String day;
    @ViewById
    RecyclerView schedule;
    @Bean
    SubjectAdapter adapter;
    @AfterViews
    public void getSchedule(){
        List<Subject> daySchedule = Repository.getSchedule(day);
        schedule.setLayoutManager(new LinearLayoutManager(getActivity()));
        schedule.setAdapter(adapter);
        adapter.setList(daySchedule);
         }
}
