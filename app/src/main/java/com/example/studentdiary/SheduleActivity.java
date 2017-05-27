package com.example.studentdiary;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

/**
 * Created by ivang on 16.05.2017.
 */
@EActivity(R.layout.shedule_activity)
public class SheduleActivity extends AppCompatActivity{
    @Extra
    String day;
    @ViewById
    FrameLayout container;

    @AfterViews
    public void replaceFragment(){
        FragmentManager fragmentManager; fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container,ScheduleFragment_.builder().arg("day",day).arg("clickableContent",true).build())
                .commit();
        Intent intent = new Intent();
        intent.putExtra("day", day);
        setResult(RESULT_OK, intent);
    }
}
