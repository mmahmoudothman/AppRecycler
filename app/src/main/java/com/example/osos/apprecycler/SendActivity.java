package com.example.osos.apprecycler;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SendActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    Button submit;
    TextView txtCount ,officename;
    int count=0;
    private DatabaseReference mDatabase;
    String uId;
    String name;
    String phone;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);


        String title=getIntent().getStringExtra("name");
        TextView textView=findViewById(R.id.tvTitle);
        textView.setText(title);



        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        uId = currentUser.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference();


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name=dataSnapshot.child("users").child(uId).child("name").getValue().toString();
                phone=dataSnapshot.child("users").child(uId).child("phone").getValue().toString();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



// Spinner element
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        submit =findViewById(R.id.btnSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String officeName=getIntent().getStringExtra("name");
                String type=spinner.getSelectedItem().toString();
                String quantity=String.valueOf(count);



                if (count>0) {

                    HashMap<String, String> quantityMap= new HashMap<String, String>();
                      quantityMap.put("name",name);
                      quantityMap.put("phone",phone);
                      quantityMap.put("officeName",officeName);
                      quantityMap.put("type",type);
                      quantityMap.put("quantity",quantity);
                    mDatabase.child("request").push().setValue(quantityMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {


                            Toast.makeText(SendActivity.this, " Submit success", Toast.LENGTH_LONG).show();
                            Intent mainIntent = new Intent(SendActivity.this, AboutActivity.class);
                            startActivity(mainIntent);
                            finish();

                        }
                        else {
                            Toast.makeText(SendActivity.this, " Failed", Toast.LENGTH_LONG).show();

                        }

                        }
                });

                }else {
                    Toast.makeText(SendActivity.this, " Set Quantity", Toast.LENGTH_LONG).show();

                }



            }
        });



        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Home");
        categories.add("Mall");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);







        txtCount =(TextView) findViewById(R.id.txt);
        Button buttonInc= (Button) findViewById(R.id.button1);
        Button buttonDec= (Button) findViewById(R.id.button2);

        buttonInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                txtCount.setText(String.valueOf(count));

            }
        });

        buttonDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>0){
                count--;
                txtCount.setText(String.valueOf(count));
                }
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }






}
