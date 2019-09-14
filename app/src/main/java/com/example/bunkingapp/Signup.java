package com.example.bunkingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity implements View.OnClickListener{

    private EditText emailsign, passsign;
    private Button signinbtn;
    private TextView backpage;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }

        //databaseReference = FirebaseDatabase.getInstance().getReference();

        progressDialog = new ProgressDialog(this);

        emailsign  = (EditText)findViewById(R.id.emailsign);
        passsign = (EditText)findViewById(R.id.passsign);
        signinbtn = (Button)findViewById(R.id.signinbtn);
        backpage = (TextView)findViewById(R.id.backpage);


        signinbtn.setOnClickListener(this);
        backpage.setOnClickListener(this);



    }

    private void regUser()
    {
        String email = emailsign.getText().toString().trim();
        String password = passsign.getText().toString().trim();

        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Please enter the email id!!!",Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Please enter the password!!!",Toast.LENGTH_SHORT).show();
            return;
        }


        progressDialog.setMessage("Registering User...");
        progressDialog.show();


        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful())
                        {
                            Toast.makeText(Signup.this,"Registered Successfully!",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(Signup.this,"Registration Failed!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public void onClick(View view)
    {
        if (view == signinbtn)
        {
            regUser();
        }

        if (view == backpage)
        {
            firebaseAuth.signOut();
            finish();
            Intent intent = new Intent(Signup.this,Login.class);
            startActivity(intent);
//            startActivity();
        }
    }
}
