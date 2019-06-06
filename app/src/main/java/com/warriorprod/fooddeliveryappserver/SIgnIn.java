package com.warriorprod.fooddeliveryappserver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.warriorprod.fooddeliveryappserver.Common.Common;
import com.warriorprod.fooddeliveryappserver.model.User;

public class SIgnIn extends AppCompatActivity {
    Button btnSignIn;
    EditText editPhone,editPwd;

    DatabaseReference t_user;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        editPhone = (EditText)findViewById(R.id.editPhone);
        editPwd = (EditText)findViewById(R.id.editPassword);
        database = FirebaseDatabase.getInstance();
        t_user = database.getReference("User");


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser(editPhone.getText().toString(),editPwd.getText().toString());

            }
        });
    }
    private void signInUser(String phn,String pwd){
        final ProgressDialog mDialog = new ProgressDialog(SIgnIn.this);
        mDialog.setMessage("Waiting....");
        mDialog.show();

        t_user.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(editPhone.getText().toString()).exists())
                {
                    mDialog.dismiss();
                    User user = dataSnapshot.child(editPhone.getText().toString()).getValue(User.class);
                    user.setPhone(editPhone.getText().toString());
                    if(user.getPassword().equals(editPwd.getText().toString()))
                    {
                        Intent homeIntent = new Intent(SIgnIn.this, Home.class);
                        Common.currentUser = user;
                        startActivity(homeIntent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(SIgnIn.this,"Wrong Password",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    mDialog.dismiss();
                    Toast.makeText(SIgnIn.this,"User not valid",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    };
}
