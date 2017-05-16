package com.example.studentdiary.Adapter;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.studentdiary.Entities.Mark;
import com.example.studentdiary.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;

/**
 * Created by ivang on 16.05.2017.
 */
@EViewGroup(R.layout.item_marks)
public class MarkHolder extends LinearLayout {
    @ViewById
    TextView date_mark;
    @ViewById
    TextView mark;
    @ViewById
    LinearLayout item_mark_layout;
    public MarkHolder(Context context) {
        super(context);
    }
    public void bind(Mark mark, int pos, OnClickListener listener){
        item_mark_layout.setTag(pos);
        date_mark.setTag(pos);
        this.mark.setTag(pos);

        date_mark.setText(mark.getDate().toString());
        this.mark.setText(mark.getMark());

        item_mark_layout.setOnClickListener(listener);
        date_mark.setOnClickListener(listener);
        this.mark.setOnClickListener(listener);
    }
}
