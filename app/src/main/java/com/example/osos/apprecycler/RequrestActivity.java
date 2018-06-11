package com.example.osos.apprecycler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RequrestActivity extends AppCompatActivity {


    EditText editText;
    Button btnSnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requrest);

        editText=findViewById(R.id.ed_complain);
        btnSnd=findViewById(R.id.btn_send_complaint);
        btnSnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RequrestActivity.this," Submit success",Toast.LENGTH_LONG).show();
                Intent mainIntent=new Intent(RequrestActivity.this,MainActivity.class);
                startActivity(mainIntent);
            }
        });

    }
}
