package com.example.studentdiary.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.studentdiary.Entities.Subject;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

/**
 * Created by Виталина on 09.05.2017.
 */
@EBean
public class SubjectAdapter extends RecyclerViewAdapterBase<Subject,SubjectHolder>{
    @RootContext
    Context context;

    @Override
    protected SubjectHolder onCreateItemView(ViewGroup parent, int viewType) {
        return SubjectHolder_.build(context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<SubjectHolder> holder, int position) {
            SubjectHolder view = holder.getView();
            Subject subject = items.get(position);
             view.bind(subject);
    }

    public void setList (List<Subject> list){
        items = list;
        notifyDataSetChanged();
    }
}
