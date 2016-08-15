package com.example.android.viewpagerinrecyvlerviewdemo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.MyViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(Model item,int position);
    }

    private List<Model> songList;
    private OnItemClickListener listener = null;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView description;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            description = (TextView) view.findViewById(R.id.description);
        }

        public void bind(final Model item, final OnItemClickListener listener, final int position) {

            name.setText(item.name);
            description.setText(item.description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {

                    listener.onItemClick(item,position);
                }
            });
        }
    }

    public ModelAdapter(List<Model> songList, OnItemClickListener listener) {
        this.songList = songList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Model model = songList.get(position);
        holder.bind(model,listener,position);
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }
}