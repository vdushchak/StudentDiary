package com.example.studentdiary;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.studentdiary.Adapter.MarkAdapter;
import com.example.studentdiary.Database.Repository;
import com.example.studentdiary.Entities.Mark;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.Calendar;
import java.util.List;

/**
 * Created by ivang on 16.05.2017.
 */
@EActivity(R.layout.marks_list)
public class MarkActivity extends AppCompatActivity {
    @ViewById
    RecyclerView marks_list;
    @ViewById
    FloatingActionButton vAddMark;
    @Bean
    MarkAdapter adapter;
    @Extra
    long subjectId;
    @AfterViews
    public void init(){
        List<Mark> marks = Repository.getMarks(subjectId);
        marks_list.setLayoutManager(new LinearLayoutManager(this));
        marks_list.setAdapter(adapter);
        adapter.setItems(marks);
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
        }).attachToRecyclerView(marks_list);
    }
    @Click(R.id.vAddMark)
    public void addMark(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
// ...Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.edit_mark, null);
        dialogBuilder.setView(dialogView);
        final EditText editMark = (EditText) dialogView.findViewById(R.id.edit_mark);
        final DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.edit_mark_date);
        dialogBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Mark mark = new Mark();
                mark.setMark(editMark.getText().toString());
                mark.setDate(getDateFromDatePicker(datePicker));
                mark.setSubject(Repository.getSubject(subjectId));
                mark.save();
                adapter.addItem(mark);
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }
    public  java.util.Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }
}
