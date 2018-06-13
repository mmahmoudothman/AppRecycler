package com.example.osos.apprecycler;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;

public class RequrestActivity extends AppCompatActivity {


    EditText editText;
    Button btnSnd;

    String name;
    String phone;
    String uId;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requrest);

        editText=findViewById(R.id.ed_complain);
        btnSnd=findViewById(R.id.btn_send_complaint);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        uId = currentUser.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        final String email=currentUser.getEmail();

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







        btnSnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String complain=editText.getText().toString();
                if (!TextUtils.isEmpty(complain)) {


                    HashMap<String, String> userMap = new HashMap<String, String>();
                    userMap.put("email", email);
                    userMap.put("uid", uId);
                    userMap.put("message", complain);
                    userMap.put("phone", phone);
                    userMap.put("name", name);


                    mDatabase.child("complain").push().setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){

                            Toast.makeText(RequrestActivity.this, " Submit success", Toast.LENGTH_LONG).show();
                            Intent mainIntent = new Intent(RequrestActivity.this, MainActivity.class);
                            startActivity(mainIntent);
                            finish();
                            }
                            else {
                                Toast.makeText(RequrestActivity.this, " Failed", Toast.LENGTH_LONG).show();

                            }
                        }

                    });


                }




            }
        });







    }

}
