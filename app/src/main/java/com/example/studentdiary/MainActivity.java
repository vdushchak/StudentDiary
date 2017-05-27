package com.example.studentdiary;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    private static final String DAY_KEY = "day";
    private static final String MONDAY = "Monday";
    private static final String TUESDAY = "Tuesday";
    private static final String WEDNESDAY = "Wednesday";
    private static final String THURSDAY = "Thursday";
    private static final String FRIDAY = "Friday";
    private static final String SATURDAY = "Saturday";
    @AfterViews
    public void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        // replace(id,Fragment,Tag)
        fragmentManager.beginTransaction()
                .replace(R.id.Monday, ScheduleFragment_.builder().arg(DAY_KEY, MONDAY).build(),MONDAY)
                .commit();
        fragmentManager.beginTransaction()
                .replace(R.id.Tuesday, ScheduleFragment_.builder().arg(DAY_KEY, TUESDAY).build(),TUESDAY)
                .commit();
        fragmentManager.beginTransaction()
                .replace(R.id.Wednesday, ScheduleFragment_.builder().arg(DAY_KEY, WEDNESDAY).build(),WEDNESDAY)
                .commit();
        fragmentManager.beginTransaction()
                .replace(R.id.Thursday, ScheduleFragment_.builder().arg(DAY_KEY, THURSDAY).build(),THURSDAY)
                .commit();
        fragmentManager.beginTransaction()
                .replace(R.id.Friday, ScheduleFragment_.builder().arg(DAY_KEY, FRIDAY).build(),FRIDAY)
                .commit();
        fragmentManager.beginTransaction()
                .replace(R.id.Saturday, ScheduleFragment_.builder().arg(DAY_KEY, SATURDAY).build(),SATURDAY)
                .commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
    }

    @OnActivityResult(1)
    public void updateFragment(int resultCode,Intent data){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (resultCode==RESULT_OK){
            String day = (String) data.getExtras().get(DAY_KEY);
            android.support.v4.app.Fragment f = fragmentManager.findFragmentByTag(day);
            transaction.detach(f);
            transaction.attach(f);
            transaction.commitAllowingStateLoss();
        }
    }
}
