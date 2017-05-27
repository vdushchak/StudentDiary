package com.example.studentdiary.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.studentdiary.Entities.Mark;
import com.example.studentdiary.R;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.Calendar;
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
    public void onClick(final View v) {
       /* AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
// ...Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = LayoutInflater.from(context);
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
                mark.setSubject(subjectId);
                mark.save();
                addItem(mark);
                items.add(mark);
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();*/
    }
    public void setItems(List<Mark> marks){
        items = marks;
        notifyDataSetChanged();
    }

    public void deleteItem(int pos){
        Mark mark = items.get(pos);
        items.remove(pos);
        mark.delete();
    }
    public void addItem(Mark mark){
        items.add(mark);
        notifyDataSetChanged();
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
