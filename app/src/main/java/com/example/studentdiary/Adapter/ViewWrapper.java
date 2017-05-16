package com.example.studentdiary.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Виталина on 09.05.2017.
 */

public class ViewWrapper<V extends View> extends RecyclerView.ViewHolder {

    private V view;

    public ViewWrapper(V itemView) {
        super(itemView);
        view = itemView;
    }

    public V getView() {
        return view;
    }
}