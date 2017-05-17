package com.example.studentdiary.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.studentdiary.Entities.Homework;
import com.example.studentdiary.Entities.Subject;
import com.example.studentdiary.R;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

/**
 * Created by Виталина on 17.05.2017.
 */
@EBean
public class HomeworkAdapter extends RecyclerViewAdapterBase<Homework,HomeworkHolder> implements View.OnClickListener {
    @RootContext
    Context context;
    @Override
    public void onClick(View v) {
        if (v.getId()!= R.id.status){
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
// ...Irrelevant code for customizing the buttons and title
            LayoutInflater inflater = LayoutInflater.from(context);
            View dialogView = inflater.inflate(R.layout.edit_homework, null);
            dialogBuilder.setView(dialogView);
            dialogBuilder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialogBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.show();
        }
    }

    @Override
    protected HomeworkHolder onCreateItemView(ViewGroup parent, int viewType) {
        return HomeworkHolder_.build(context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<HomeworkHolder> holder, int position) {
        HomeworkHolder view = holder.getView();
        Homework homework = items.get(position);
        view.bind(homework, position, this);
    }
    public void setItems(List<Homework> homeworks){
        items = homeworks;
        if (items.isEmpty()){
            items.add(new Homework("task", true,new Subject()));
        }
        notifyDataSetChanged();
    }

}
