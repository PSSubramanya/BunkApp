package com.example.bunkingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static String pushvalue = " ";
    public static String usnno1 = " ";
    public static String date1 = " ";

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;


    EditText dates,name,reason,usnnumber;
    TextView sem, usn;
    //Spinner usn;
//    String [] weekdays;

    Calendar calendar;
    DatePickerDialog datePickerDialog;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        send = (Button)findViewById(R.id.send);
        dates = (EditText)findViewById(R.id.dates);
        name = (EditText)findViewById(R.id.name);
        reason = (EditText)findViewById(R.id.reason);
        usnnumber = (EditText)findViewById(R.id.usnnumber);

        sem = (TextView)findViewById(R.id.sem);
//        usn = (Spinner)findViewById(R.id.usn);
        registerForContextMenu(sem);
        usn = (TextView) findViewById(R.id.usn);
        registerForContextMenu(usn);



        dates.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDAy) {
                        dates.setText(mDAy + "/" + mMonth + "/" + mYear);
                    }
                },day,month,year);
                datePickerDialog.show();
                return true;
            }
        });

        send.setOnClickListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == R.id.sem)
        {
            getMenuInflater().inflate(R.menu.spinner_context_menu,menu);
        }

        if (v.getId() == R.id.usn)
        {
            getMenuInflater().inflate(R.menu.weekdays_context_menu,menu);
        }

//        getMenuInflater().inflate(R.menu.spinner_context_menu,menu);
//        getMenuInflater().inflate(R.menu.weekdays_context_menu,menu);


//        switch()
//        {
//
//        }
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.full)
        {
            //Toast.makeText(this,"Absent for full day",Toast.LENGTH_LONG).show();
            sem.setText("Fullday");
        }

        //if (id == R.id.half)
        //{
        if (id == R.id.morning)
        {
            //Toast.makeText(this,"Absent in the morning",Toast.LENGTH_LONG).show();
            sem.setText("Morning");
        }

        if (id == R.id.afternoon)
        {
            //Toast.makeText(this,"Absent in the afternoon",Toast.LENGTH_LONG).show();
            sem.setText("Afternoon");
        }

        if (id == R.id.monday)
        {
            //Toast.makeText(this,"Absent for full day",Toast.LENGTH_LONG).show();
            usn.setText("Monday");
        }


        if (id == R.id.tuesday)
        {
            //Toast.makeText(this,"Absent for full day",Toast.LENGTH_LONG).show();
            usn.setText("Tuesday");
        }


        if (id == R.id.wednesday)
        {
            //Toast.makeText(this,"Absent for full day",Toast.LENGTH_LONG).show();
            usn.setText("Wednesday");
        }


        if (id == R.id.thursday)
        {
            //Toast.makeText(this,"Absent for full day",Toast.LENGTH_LONG).show();
            usn.setText("Thursday");
        }


        if (id == R.id.friday)
        {
            //Toast.makeText(this,"Absent for full day",Toast.LENGTH_LONG).show();
            usn.setText("Friday");
        }


        if (id == R.id.saturday)
        {
            //Toast.makeText(this,"Absent for full day",Toast.LENGTH_LONG).show();
            usn.setText("Saturday");
        }


        if (id == R.id.full)
        {
            //Toast.makeText(this,"Absent for full day",Toast.LENGTH_LONG).show();
            sem.setText("Fullday");
        }

        //}
        return super.onContextItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
//        return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.taskbar_icons,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
//        return super.onOptionsItemSelected(item);
        int id = item.getItemId();

        if (id == R.id.logouticoni)
        {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(getApplicationContext(),Login.class));
        }

        if (id == R.id.personalinfoi)
        {
//            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//            builder.setTitle("Enter USN and Date")
//                    .setMessage()
            openDialogue();
        }
        return super.onOptionsItemSelected(item);
    }

    private void openDialogue()
    {
        PersonalInfoDialogue personalInfoDialogue = new PersonalInfoDialogue();
        personalInfoDialogue.show(getSupportFragmentManager(),"personal info dialogue");
    }

    public void savesubjectinfo()
    {
        String name1 = name.getText().toString().trim();
        //String usnno1 = usnnumber.getText().toString().trim();
        usnno1 = usnnumber.getText().toString().trim();//declared in the beginning of the class
        date1 = dates.getText().toString().trim();
        //String [] weekdaysi;
        String day1 = usn.getText().toString().trim();
        String slot1 = sem.getText().toString().trim();
        String reason1 = reason.getText().toString().trim();




        //UserRegisteration userRegisteration = new UserRegisteration(fullname,usnnum,semesternum,sectionnum);
//        SubjectInfo subjectInfo = new SubjectInfo(subcd1,subjname1,subshortform1,
//                subcd2,subjname2,subshortform2,
//                subcd3,subjname3,subshortform3,
//                subcd4,subjname4,subshortform4,
//                subcd5,subjname5,subshortform5,
//                subcd6,subjname6,subshortform6,
//                subcd7,subjname7,subshortform7,
//                subcd8,subjname8,subshortform8);


        AbsenceInfo absenceInfo = new AbsenceInfo(name1,usnno1,date1,day1,slot1,reason1);


        FirebaseUser user = firebaseAuth.getCurrentUser();
        //databaseReference.child(user.getUid()).setValue(userRegisteration);
        //databaseReference.child("Subject Information").push().setValue(subjectInfo);


        //databaseReference.child(user.getUid()).child("Absence Information").push().setValue(absenceInfo);//1// This is proper code as of now dated 4/9/2019 for pushing and storing the data to firebase

//        databaseReference.child(user.getUid()).child("Absence Information").setValue(absenceInfo);//2

//        databaseReference.child(user.getUid()).setValue(absenceInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                Log.d("hello","done");
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.d("hello","failed");
//            }
//        });//3


        //String pushvalue = databaseReference.push().getKey();
        pushvalue = databaseReference.push().getKey();// will declare in d beginning of the class itself
        //databaseReference.push().setValue(absenceInfo);//4
//        databaseReference.child(pushvalue).child(usnno1).setValue(absenceInfo);//5
        databaseReference.child(pushvalue).setValue(absenceInfo);//6
//        databaseReference.child(pushvalue).child(usnno1).setValue(absenceInfo);//6

        //databaseReference.child("Absence Info").child(usnno1).setValue(absenceInfo);//7
//        databaseReference.child(usnno1).child(date1).setValue(absenceInfo);//8
        //databaseReference.child(usnno1).setValue(absenceInfo);//9
        //databaseReference.setValue(absenceInfo);//9
        Toast.makeText(this,"Subject Information Saved!",Toast.LENGTH_LONG).show();

    }
    @Override
    public void onClick(View view)
    {
        if (view == send)
        {
            savesubjectinfo();
        }
    }

    public static String getpushdata()
    {
        return pushvalue;
    }

    public static String getdataUSN()
    {
        return usnno1;
    }

    public static String getdatedata()
    {
        return date1;
    }

}
