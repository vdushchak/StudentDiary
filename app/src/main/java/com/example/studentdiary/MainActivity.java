package com.example.studentdiary;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

   @AfterViews
    public void initFragment(){
       FragmentManager fragmentManager = getSupportFragmentManager();
       fragmentManager.beginTransaction()
               .replace(R.id.Monday,ScheduleFragment_.builder().arg("day","Monday").build())
               .commit();
       fragmentManager.beginTransaction()
               .replace(R.id.Tuesday,ScheduleFragment_.builder().arg("day","Tuesday").build())
               .commit();
       fragmentManager.beginTransaction()
               .replace(R.id.Wednesday,ScheduleFragment_.builder().arg("day","Wednesday").build())
               .commit();
       fragmentManager.beginTransaction()
               .replace(R.id.Thursday,ScheduleFragment_.builder().arg("day","Thursday").build())
               .commit();
       fragmentManager.beginTransaction()
               .replace(R.id.Friday,ScheduleFragment_.builder().arg("day","Friday").build())
               .commit();
       fragmentManager.beginTransaction()
               .replace(R.id.Saturday,ScheduleFragment_.builder().arg("day","Saturday").build())
               .commit();
   }
}
