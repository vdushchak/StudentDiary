package com.example.studentdiary.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studentdiary.Entities.Mark;
import com.example.studentdiary.Entities.Subject;
import com.example.studentdiary.R;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.Date;
import java.util.List;

/**
 * Created by ivang on 16.05.2017.
 */
@EBean
public class MarkAdapter extends RecyclerViewAdapterBase<Mark,MarkHolder> implements View.OnClickListener{
    @RootContext
    Context context;
    @Override
    protected MarkHolder onCreateItemView(ViewGroup parent, int viewType) {
        return MarkHolder_.build(context);
    }


    @Override
    public void onBindViewHolder(ViewWrapper<MarkHolder> holder, int position) {
        MarkHolder view = holder.getView();
        Mark mark = items.get(position);
        view.bind(mark, position, this);
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
// ...Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.edit_mark, null);
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
    public void setItems(List<Mark> marks){
        items = marks;
        if (items.isEmpty()){
            items.add(new Mark(new Date(),"A",new Subject()));
        }
        notifyDataSetChanged();
    }
}
