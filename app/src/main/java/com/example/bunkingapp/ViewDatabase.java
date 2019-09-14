package com.example.bunkingapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewDatabase extends AppCompatActivity {
    private static final String TAG = "ViewDatabase";

    //add Firebase Database stuffs

    private FirebaseDatabase mfirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private String userID;
    private String usnnum1;
//    public String fetcheddialogueusn= " ";
    private String fetcheddialogueusn= " ";
    //public String dayfetch = "Monday";



    private ListView mListView;

    //private RecyclerView mListView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_database_layout);


        mListView = (ListView)findViewById(R.id.mListView);

        //mListView = (RecyclerView) findViewById(R.id.mListView);


        mAuth = FirebaseAuth.getInstance();
        mfirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mfirebaseDatabase.getReference();
        final FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
        //usnnum1 = myRef.

//        Query query = myRef.orderByChild("usn").equalTo(fetcheddialogueusn).limitToFirst(2);
//        query.addListenerForSingleValueEvent(valueEventListener);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (user!= null)
                {

                    Log.d(TAG,":" +user.getUid());
                }
                else {
                    Log.d(TAG, "onAutStateChanged:signed_out");
                }
            }
        };

        //fetcheddialogueusn = PersonalInfoDialogue.sendUsn();
        fetcheddialogueusn = getIntent().getStringExtra("usndata");




        //        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
//        Query query = reference.child(pushcheck1).child(userCheck1).orderByChild("usn").equalTo(userCheck2);
        //Query query = reference.child(pushcheck1).child(userCheck1).orderByChild("usn").equalTo(userCheck2);



        //***************************************


//        Query query = myRef.orderByChild("usn").equalTo(fetcheddialogueusn).limitToFirst(20);
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists())
//                {
//                    showData(dataSnapshot);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });//2nd appropriate method to execute the fetching of data



        //**********************************************


        myRef.orderByChild("usn").startAt(fetcheddialogueusn).endAt(fetcheddialogueusn+"\uf8ff").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Log.d("Our String",String.valueOf(dataSnapshot.getValue()));
//                showData(dataSnapshot);
                if (dataSnapshot.exists())
                {
                    for (DataSnapshot ds1 : dataSnapshot.getChildren())
                    {
                        Log.d("Our String",String.valueOf(dataSnapshot.getValue()));
                        showData(dataSnapshot);
//                        Log.d("Our String",String.valueOf(ds1.getValue()));
//                        showData(ds1);

//                        Log.d("Our String",String.valueOf(dataSnapshot.getValue()));
//                        showData(dataSnapshot);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });// 3rd and ALMOST PERFECT Code to fetch


//        myRef.orderByChild("usn").startAt("4SF16CS118").endAt("4SF16CS118\uf8ff").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Log.d("Our String",String.valueOf(dataSnapshot.getValue()));
//                showData(dataSnapshot);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });



        //Query query = myRef.orderByChild("usn").equalTo(fetcheddialogueusn).limitToFirst(20);


        //1234

//        ValueEventListener valueEventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
////                for (DataSnapshot ds : dataSnapshot.getChildren())
////                {
////                    if (ds.child("day").getKey().equals(dayfetch))
////                    {
////                        showData(dataSnapshot);
////                    }
////                }
//
//                if (dataSnapshot.exists())
//                {
//                    for (DataSnapshot ds : dataSnapshot.getChildren())
//                    {
//                        AbsenceInfo sinfo = ds.getValue(AbsenceInfo.class);
//                        ArrayList<AbsenceInfo> array = new ArrayList<>();
////                        array.add(sinfo.getName());
////                        array.add(sinfo.getUsn());
////                        array.add(sinfo.getDate());
////                        array.add(sinfo.getDay());
////                        array.add(sinfo.getSlot());
////                        array.add(sinfo.getReason());
////                        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1 ,array);
////                        mListView.setAdapter(adapter);
//                        array.add(sinfo);
//                    }
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        };

        //--1234


//        Query query = myRef.orderByChild("usn").equalTo(fetcheddialogueusn).limitToFirst(20);
//        //Query query = myRef.orderByChild("usn").startAt("4SF17CS118").endAt("4SF17CS118\uf8ff");
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists())
//                {
//                    showData(dataSnapshot);
//                    //Log.d("Our String",String.valueOf(dataSnapshot.getValue()));
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });// Perfectly fetched data part 1 on the basis of USN(Draw back: fetches only the lastest record under the given USN)




//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                showData(dataSnapshot);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });// This is first set of code to fetch data without erroe
    }

//    ValueEventListener valueEventListener = new ValueEventListener() {
//        @Override
//        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
////                for (DataSnapshot ds : dataSnapshot.getChildren())
////                {
////                    if (ds.child("day").getKey().equals(dayfetch))
////                    {
////                        showData(dataSnapshot);
////                    }
////                }
//
//            if (dataSnapshot.exists())
//            {
//                for (DataSnapshot ds : dataSnapshot.getChildren())
//                {
//                    AbsenceInfo sinfo = ds.getValue(AbsenceInfo.class);
//                    ArrayList<AbsenceInfo> array = new ArrayList<>();
////                        array.add(sinfo.getName());
////                        array.add(sinfo.getUsn());
////                        array.add(sinfo.getDate());
////                        array.add(sinfo.getDay());
////                        array.add(sinfo.getSlot());
////                        array.add(sinfo.getReason());
////                        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1 ,array);
////                        mListView.setAdapter(adapter);
//                    array.add(sinfo);
//                }
//            }
//
//        }
//
//        @Override
//        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//        }
//    };


    private void showData(DataSnapshot dataSnapshot)
    {
        for (DataSnapshot ds: dataSnapshot.getChildren())
        {
//            StudentInformation sinfo = new StudentInformation();



            //AbsenceInfo sinfo = ds.getValue(AbsenceInfo.class);





            AbsenceInfo sinfo = new AbsenceInfo();//perfectly executed code part1,2 's class

//            sinfo.setName(ds.child(userID).getValue(StudentInformation.class).getName());
//            sinfo.setUsn(ds.child(userID).getValue(StudentInformation.class).getUsn());
//            sinfo.setDate(ds.child(userID).getValue(StudentInformation.class).getDate());
//            sinfo.setDay(ds.child(userID).getValue(StudentInformation.class).getDay());
//            sinfo.setSlot(ds.child(userID).getValue(StudentInformation.class).getSlot());
//            sinfo.setReason(ds.child(userID).getValue(StudentInformation.class).getReason());


            try {
//                sinfo.setName(ds.child(userID).getValue(AbsenceInfo.class).getName());
//                sinfo.setUsn(ds.child(userID).getValue(AbsenceInfo.class).getUsn());
//                sinfo.setDate(ds.child(userID).getValue(AbsenceInfo.class).getDate());
//                sinfo.setDay(ds.child(userID).getValue(AbsenceInfo.class).getDay());
//                sinfo.setSlot(ds.child(userID).getValue(AbsenceInfo.class).getSlot());
//                sinfo.setReason(ds.child(userID).getValue(AbsenceInfo.class).getReason());

//                sinfo.setName(ds.child(userID).getValue(AbsenceInfo.class).getName());
//                sinfo.setUsn(ds.child(userID).getValue(AbsenceInfo.class).getUsn());
//                sinfo.setDate(ds.child(userID).getValue(AbsenceInfo.class).getDate());
//                sinfo.setDay(ds.child(userID).getValue(AbsenceInfo.class).getDay());
//                sinfo.setSlot(ds.child(user+-ID).getValue(AbsenceInfo.class).getSlot());
//                sinfo.setReason(ds.child(userID).getValue(AbsenceInfo.class).getReason());


                sinfo.setName(ds.getValue(AbsenceInfo.class).getName());
                sinfo.setUsn(ds.getValue(AbsenceInfo.class).getUsn());
                sinfo.setDate(ds.getValue(AbsenceInfo.class).getDate());
                sinfo.setDay(ds.getValue(AbsenceInfo.class).getDay());
                sinfo.setSlot(ds.getValue(AbsenceInfo.class).getSlot());
                sinfo.setReason(ds.getValue(AbsenceInfo.class).getReason());

                //String usnfetch = MainActivity.getdataUSN();// You cannot do this as it always requires you to constantly keep toring data every time you open the app

//                sinfo.setName(ds.child(usnfetch).getValue(AbsenceInfo.class).getName());
//                sinfo.setUsn(ds.child(usnfetch).getValue(AbsenceInfo.class).getUsn());
//                sinfo.setDate(ds.child(usnfetch).getValue(AbsenceInfo.class).getDate());
//                sinfo.setDay(ds.child(usnfetch).getValue(AbsenceInfo.class).getDay());
//                sinfo.setSlot(ds.child(usnfetch).getValue(AbsenceInfo.class).getSlot());
//                sinfo.setReason(ds.child(usnfetch).getValue(AbsenceInfo.class).getReason());


//                String fetchusn = MainActivity.getdataUSN();
//                String fetchdate = MainActivity.getdatedata();

//                sinfo.setName(ds.child(fetchusn).child(fetchdate).getValue(AbsenceInfo.class).getName());
//                sinfo.setUsn(ds.child(fetchusn).child(fetchdate).getValue(AbsenceInfo.class).getUsn());
//                sinfo.setDate(ds.child(fetchusn).child(fetchdate).getValue(AbsenceInfo.class).getDate());
//                sinfo.setDay(ds.child(fetchusn).child(fetchdate).getValue(AbsenceInfo.class).getDay());
//                sinfo.setSlot(ds.child(fetchusn).child(fetchdate).getValue(AbsenceInfo.class).getSlot());
//                sinfo.setReason(ds.child(fetchusn).child(fetchdate).getValue(AbsenceInfo.class).getReason());

//                sinfo.setName(ds.child(fetchusn).getValue(AbsenceInfo.class).getName());
//                sinfo.setUsn(ds.child(fetchusn).getValue(AbsenceInfo.class).getUsn());
//                sinfo.setDate(ds.child(fetchusn).getValue(AbsenceInfo.class).getDate());
//                sinfo.setDay(ds.child(fetchusn).getValue(AbsenceInfo.class).getDay());
//                sinfo.setSlot(ds.child(fetchusn).getValue(AbsenceInfo.class).getSlot());
//                sinfo.setReason(ds.child(fetchusn).getValue(AbsenceInfo.class).getReason());
            }
            catch (Exception e)
            {
                //Log.d()
                System.out.println("Error " + e.getMessage());
                //Log.d("Errpr" + e.getMessage());
            }





//            sinfo.setUsn(ds.child(userID).child("Absence Information").getValue(StudentInformation.class).getUsn());
//            sinfo.setUsn(ds.child(userID).getValue(StudentInformation.class).getUsn());
//            sinfo.setUsn(ds.child(userID).getValue(AbsenceInfo.class).getUsn());

//            sinfo.setDate(ds.child(userID).getValue(StudentInformation.class).getDate());
//            sinfo.setDay(ds.child(userID).getValue(StudentInformation.class).getDay());
//            sinfo.setSlot(ds.child(userID).getValue(StudentInformation.class).getSlot());
//            sinfo.setReason(ds.child(userID).getValue(StudentInformation.class).getReason());


            Log.d(TAG,"showData: name:"+sinfo.getName());
            Log.d(TAG,"showData: usn:"+sinfo.getUsn());
            Log.d(TAG,"showData: date:"+sinfo.getDate());
            Log.d(TAG,"showData: day:"+sinfo.getDay());
            Log.d(TAG,"showData: slot:"+sinfo.getSlot());
            Log.d(TAG,"showData: reason:"+sinfo.getReason());
            //Log.d(TAG,"show USN Root Value:"+MainActivity.getdataUSN());
            //Log.d(TAG, "show Push Data value:"+MainActivity.getpushdata());


            //ArrayList<AbsenceInfo> array = new ArrayList<>();
            //array.add(sinfo);
            ArrayList<String> array = new ArrayList<>();
            array.add(sinfo.getName()+"\n"+sinfo.getUsn()+"\n"+sinfo.getDate()+"\n"+sinfo.getDay()+"\n"+sinfo.getSlot()+"\n"+sinfo.getReason());

//            for (int i =0;i<3;i++)
//            {
//                array.add(sinfo.getName()+"\n"+sinfo.getUsn()+"\n"+sinfo.getDate()+"\n"+sinfo.getDay()+"\n"+sinfo.getSlot()+"\n"+sinfo.getReason());
//                ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1 ,array);
//                //adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_2 ,array);
//                mListView.setAdapter(adapter);
//
//            }
//            array.add(sinfo.getUsn());
//            array.add(sinfo.getDate());
//            array.add(sinfo.getDay());
//            array.add(sinfo.getSlot());
//            array.add(sinfo.getReason());
            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1 ,array);
            //adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_2 ,array);
            mListView.setAdapter(adapter);

        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null)
        {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
