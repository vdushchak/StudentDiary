package com.example.studentdiary.Adapter;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentdiary.Entities.Subject;
import com.example.studentdiary.MarkActivity_;
import com.example.studentdiary.R;
import com.example.studentdiary.SheduleActivity_;


import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

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

    @Override
    protected SubjectHolder onCreateItemView(ViewGroup parent, int viewType) {
        return SubjectHolder_.build(context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<SubjectHolder> holder, int position) {
            SubjectHolder view = holder.getView();
            Subject subject = items.get(position);
            view.bind(subject, position, this);
    }

    public void setList (List<Subject> list){
        items = list;
        if(items.isEmpty()){
            items.add(new Subject("name","teacher","room","day",0));
            items.add(new Subject("name","teacher","room","day",0));
            items.add(new Subject("name","teacher","room","day",0));
            items.add(new Subject("name","teacher","room","day",0));
            items.add(new Subject("name","teacher","room","day",0));
            items.add(new Subject("name","teacher","room","day",0));
        }
        notifyDataSetChanged();
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
            int post = (int) v.getTag();
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
// ...Irrelevant code for customizing the buttons and title
            LayoutInflater inflater = LayoutInflater.from(context);
            View dialogView = inflater.inflate(R.layout.subject_editor, null);
            dialogBuilder.setView(dialogView);
            FloatingActionButton editSubject = (FloatingActionButton) dialogView.findViewById(R.id.edit_subject);
            editSubject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            FloatingActionButton editHomeworks = (FloatingActionButton) dialogView.findViewById(R.id.edit_homework);
            editHomeworks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            FloatingActionButton editMarks = (FloatingActionButton) dialogView.findViewById(R.id.edit_marks);
            editMarks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MarkActivity_.intent(context).start();
                }
            });

            AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.show();
        } else {
            SheduleActivity_.intent(context).day(day).start();
        }
    }
}
