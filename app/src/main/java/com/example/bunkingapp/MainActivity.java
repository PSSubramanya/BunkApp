package com.example.bunkingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText dates,name;
    TextView sem;

    Calendar calendar;
    DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dates = (EditText)findViewById(R.id.dates);
        name = (EditText)findViewById(R.id.name);

        sem = (TextView)findViewById(R.id.sem);
        registerForContextMenu(sem);


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
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.spinner_context_menu,menu);

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
        //}
        return super.onContextItemSelected(item);
    }
}
