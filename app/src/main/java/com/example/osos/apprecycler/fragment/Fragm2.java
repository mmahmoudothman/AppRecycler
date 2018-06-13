package com.example.osos.apprecycler.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.osos.apprecycler.R;
import com.example.osos.apprecycler.model.RequestModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Fragm2 extends Fragment {


    RecyclerView requestRecycler;
    RequestAdater requestAdater;
    List<RequestModel> requestModels = new ArrayList<>();
    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.activity_fragm2, container, false);
        requestRecycler = view.findViewById(R.id.rv_fragm2);
        databaseReference= FirebaseDatabase.getInstance().getReference();


        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        requestRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        requestAdater = new RequestAdater(requestModels);
        requestRecycler.setAdapter(requestAdater);


    }


    @Override
    public void onResume() {
        super.onResume();

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("request");
//        requestModels.clear();
        Query query = mDatabase;//.orderByChild("id");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot item : dataSnapshot.getChildren()) {
                        requestModels.add(item.getValue(RequestModel.class));
                    }

                    requestAdater.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}