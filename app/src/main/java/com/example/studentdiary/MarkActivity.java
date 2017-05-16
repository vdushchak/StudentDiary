package com.example.studentdiary;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.studentdiary.Adapter.MarkAdapter;
import com.example.studentdiary.Entities.Mark;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivang on 16.05.2017.
 */
@EActivity(R.layout.marks_list)
public class MarkActivity extends AppCompatActivity {
    @ViewById
    RecyclerView marks_list;
    @ViewById
    FloatingActionButton vAdd;
    @Bean
    MarkAdapter adapter;
    @AfterViews
    public void init(){
        List<Mark> marks = new ArrayList();
        marks_list.setLayoutManager(new LinearLayoutManager(this));
        marks_list.setAdapter(adapter);
        adapter.setItems(marks);
    }
}
