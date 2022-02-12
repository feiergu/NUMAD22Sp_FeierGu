package edu.neu.madcourse.numad22sp_feiergu;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class RecyclerHolder extends RecyclerView.ViewHolder {
    public TextView linkName;
    public TextView linkUrl;

    public RecyclerHolder(final View view) {
        super(view);
        linkName = view.findViewById(R.id.textView3);
        linkUrl = view.findViewById(R.id.textView5);
    }
}
