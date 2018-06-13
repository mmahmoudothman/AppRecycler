package com.example.osos.apprecycler.fragment;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.osos.apprecycler.R;
import com.example.osos.apprecycler.model.Complaine;

import java.util.ArrayList;
import java.util.List;

public class LearningAdapter extends RecyclerView.Adapter<LearningAdapter.PleaceVH>{


    List<Complaine> CompList = new ArrayList<>();

    public LearningAdapter(List<Complaine> complaines) {
        this.CompList = complaines;
    }

    @NonNull
    @Override
    public PleaceVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list_item, parent,false);

        return new PleaceVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PleaceVH holder, int position) {
        holder.name.setText(CompList.get(position).getName());
        holder.phone.setText(CompList.get(position).getPhone());
        holder.desc.setText(CompList.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return CompList.size();
    }

    public class PleaceVH extends RecyclerView.ViewHolder{
        TextView name;
        TextView phone;
        TextView desc;
        public PleaceVH(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.list_post_name);
            phone= itemView.findViewById(R.id.list_post_phone);
            desc= itemView.findViewById(R.id.list_post_description);
        }
    }
}