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

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button logbtn;
    private TextView logtext;
    private EditText emaillog, passlog;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            //startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
        progressDialog = new ProgressDialog(this);


        logbtn = (Button)findViewById(R.id.logbtn);
        logtext = (TextView) findViewById(R.id.logtext);
        emaillog = (EditText) findViewById(R.id.emaillog);
        passlog = (EditText) findViewById(R.id.passlog);



        logbtn.setOnClickListener(this);
        //logtext.setOnClickListener(this);

        logtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Signup.class);
                startActivity(intent);
            }
        });


    }


    private void userLoginPage()
    {
        String email = emaillog.getText().toString().trim();
        String password = passlog.getText().toString().trim();


        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Please enter your E-mail id!!!",Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Please enter the password!!!",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Logging in...");
        progressDialog.show();


        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful())
                        {
                            //Toast.makeText(MainActivity.this,"Registered Successfully!",Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            Toast.makeText(Login.this,"Login Successful!",Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(getApplicationContext(),ProfilePage.class));
                        }
                        else
                        {
                            Toast.makeText(Login.this,"Login Failed!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view)
    {
        if (view == logbtn)
        {
            userLoginPage();
        }


//        if (view == logtext)
//        {
//            finish();
//            startActivity(new Intent(getApplicationContext(),Signup.class));
//        }
    }
}
