package edu.neu.madcourse.numad22sp_feiergu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerHolder> {
    private ArrayList<Link> linksList;
    private OnLinkListener onLinkListener;

    public RecyclerAdapter(ArrayList<Link> linksList, OnLinkListener onLinkListener) {
        this.linksList = linksList;
        this.onLinkListener = onLinkListener;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_links, parent, false);
        return new RecyclerHolder(itemView, onLinkListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        String name = linksList.get(position).getLinkName();
        String url = linksList.get(position).getUrl();
        holder.linkName.setText(name);
        holder.linkUrl.setText(url);
    }

    @Override
    public int getItemCount() {
        return linksList.size();
    }
}
