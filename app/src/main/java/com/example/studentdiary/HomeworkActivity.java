package com.example.studentdiary;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.studentdiary.Adapter.HomeworkAdapter;
import com.example.studentdiary.Entities.Homework;
import com.example.studentdiary.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
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
    @AfterViews
    public void init(){
        List<Homework> homeworkList = new ArrayList();
        homework_list.setLayoutManager(new LinearLayoutManager(this));
        homework_list.setAdapter(adapter);
        adapter.setItems(homeworkList);
    }
}
