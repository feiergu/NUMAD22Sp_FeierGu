package edu.neu.madcourse.numad22sp_feiergu;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class RecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView linkName;
    public TextView linkUrl;
    public OnLinkListener onLinkListener;

    public RecyclerHolder(final View view, OnLinkListener onLinkListener) {
        super(view);
        linkName = view.findViewById(R.id.textView3);
        linkUrl = view.findViewById(R.id.textView5);
        this.onLinkListener = onLinkListener;

        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onLinkListener.onLinkClick(getAdapterPosition());
    }
}
