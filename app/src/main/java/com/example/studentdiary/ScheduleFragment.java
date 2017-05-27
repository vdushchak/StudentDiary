package com.example.studentdiary;


import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.example.studentdiary.Adapter.SubjectAdapter;
import com.example.studentdiary.Database.Repository;
import com.example.studentdiary.Entities.Subject;

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
public class ScheduleFragment extends Fragment {
    @FragmentArg
    String day;
    @ViewById
    RecyclerView schedule;
    @Bean
    SubjectAdapter adapter;
    @FragmentArg
    boolean clickableContent = false;

    @AfterViews
    public void getSchedule() {
        List<Subject> daySchedule = Repository.getSchedule(day);
        adapter.setClickable(clickableContent);
        adapter.setDay(day);
        schedule.setLayoutManager(new LinearLayoutManager(getActivity()));
        schedule.setAdapter(adapter);
        adapter.setList(daySchedule);
        if (clickableContent) {
            new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    //do nothing, we only care about swiping
                    return false;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                    adapter.deleteItem(viewHolder.getAdapterPosition());
                }
            }).attachToRecyclerView(schedule);
        }
    }

    @Click(R.id.schedule)
    public void onClick(View v) {
        Log.d("mytag", "click");
        Snackbar.make(schedule, "New Subject", Snackbar.LENGTH_SHORT).show();
    }
}
