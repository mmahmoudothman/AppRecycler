package com.example.osos.apprecycler.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.osos.apprecycler.R;
import com.example.osos.apprecycler.model.RequestModel;

import java.util.ArrayList;
import java.util.List;

public class RequestAdater extends RecyclerView.Adapter<RequestAdater.PleaceVHReq>{


    List<RequestModel> modelList = new ArrayList<>();

    public RequestAdater (List<RequestModel> requestModels) {
        this.modelList = requestModels;
    }

    @NonNull
    @Override
    public RequestAdater.PleaceVHReq onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_list_item, parent,false);

        return new RequestAdater.PleaceVHReq(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestAdater.PleaceVHReq holder, int position) {
        holder.name.setText(modelList.get(position).getName());
        holder.phone.setText(modelList.get(position).getPhone());
        holder.officeName.setText(modelList.get(position).getOfficeName());
        holder.type.setText(modelList.get(position).getType());
        holder.quantity.setText(modelList.get(position).getQuantity());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class PleaceVHReq extends RecyclerView.ViewHolder{
        TextView name;
        TextView phone;
        TextView officeName;
        TextView type;
        TextView quantity;

        public PleaceVHReq(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.request_name);
            phone= itemView.findViewById(R.id.request_phone);
            officeName= itemView.findViewById(R.id.request_OfficeName);
            type= itemView.findViewById(R.id.request_type);
            quantity= itemView.findViewById(R.id.request_quantity);
        }
    }
}