package com.example.studentdiary.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.studentdiary.Database.Repository;
import com.example.studentdiary.Entities.Subject;
import com.example.studentdiary.HomeworkActivity_;
import com.example.studentdiary.MarkActivity_;
import com.example.studentdiary.R;
import com.example.studentdiary.SheduleActivity_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Виталина on 09.05.2017.
 */
@EBean
public class SubjectAdapter extends RecyclerViewAdapterBase<Subject,SubjectHolder> implements View.OnClickListener{
    @RootContext
    Context context;
    private boolean isClickableContent = false;
    private String day;
    int REQUEST_CODE =1;

    @Override
    protected SubjectHolder onCreateItemView(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.subject, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rootView.setLayoutParams(lp);
        SubjectHolder h =  SubjectHolder_.build(context);
        h.setLayoutParams(lp);
        return h;
    }

    @Override
    public void onBindViewHolder(ViewWrapper<SubjectHolder> holder, int position) {
            SubjectHolder view = holder.getView();
            Subject subject = items.get(position);
            view.bind(subject, position, this);
    }

    public void setList (List<Subject> list){
        items = list;
        sort();
        if (items.isEmpty()){
            items.add(new Subject());
        }
        notifyDataSetChanged();
    }
    public List<Subject> getItems(){
        return items;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setClickable(boolean canClick){
        isClickableContent = canClick;
    }
    @Override
    public void onClick(View v) {
        if (isClickableContent){
            final int pos = (int) v.getTag();
            final Subject subject = items.get(pos);
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
// ...Irrelevant code for customizing the buttons and title
            LayoutInflater inflater = LayoutInflater.from(context);
            View dialogView = inflater.inflate(R.layout.subject_editor, null);
            dialogBuilder.setView(dialogView);

            FloatingActionButton editSubject = (FloatingActionButton) dialogView.findViewById(R.id.edit_subject);
            editSubject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openSubjectEditDialog(pos);
                }
            });
            FloatingActionButton editHomeworks = (FloatingActionButton) dialogView.findViewById(R.id.edit_homework);
            editHomeworks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HomeworkActivity_.intent(context).subjectId(subject.getId()).start();
                }
            });
            FloatingActionButton editMarks = (FloatingActionButton) dialogView.findViewById(R.id.edit_marks);
            editMarks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MarkActivity_.intent(context).subjectId(subject.getId()).start();
                }
            });
            if (subject.getId()==null){
                editHomeworks.setEnabled(false);
                editMarks.setEnabled(false);
            } else {
                editHomeworks.setEnabled(true);
                editMarks.setEnabled(true);
            }
            AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.show();
        } else {
            SheduleActivity_.intent(context).day(day).startForResult(REQUEST_CODE);
        }
    }
    public  void sort(){
        Collections.sort(items, new Comparator<Subject>() {
            @Override
            public int compare(Subject o1, Subject o2) {
                return Integer.compare(o1.getSubject_number(),o2.getSubject_number());
            }
        });
    }
    public void deleteItem(int pos){
        Subject s = items.get(pos);
        items.remove(pos);
        if (s!=null && s.getId()!=null) {
            s.delete();
            notifyItemRemoved(pos);
        }
        if (items.isEmpty()){
            items.add(new Subject());
            notifyDataSetChanged();
        }
    }
    public void openSubjectEditDialog(int pos){
        Subject thisSubject = items.get(pos);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
// ...Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.edit_subject, null);
        dialogBuilder.setView(dialogView);
        final EditText vNumber = (EditText) dialogView.findViewById(R.id.subject_number);
        final EditText vName = (EditText) dialogView.findViewById(R.id.subject_name);
        final EditText vRoom = (EditText) dialogView.findViewById(R.id.subject_room);
        final EditText vTeacher = (EditText) dialogView.findViewById(R.id.subject_teacher);
        dialogBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int number = Integer.parseInt(String.valueOf(vNumber.getText()));
                String name = vName.getText().toString();
                String room = vRoom.getText().toString();
                String teacher = vTeacher.getText().toString();
                Subject subject = new Subject(name,teacher,room,day,number);
                Repository.saveOrUpdate(subject);
                setList(Repository.getSchedule(day));
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }
}
