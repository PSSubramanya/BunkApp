package com.example.bunkingapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class PersonalInfoDialogue extends AppCompatDialogFragment
{
    private EditText et1, et2;
    //private PersonalInfoDialogueListener listener;
    //public static String usndata = " ";
    //public static String datedata = " ";
    //private DatePicker et2;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.list_layout,null);
        et1 = view.findViewById(R.id.textViewUsn);
        //et2 = view.findViewById(R.id.textViewDate);

        builder.setView(view)
                .setTitle("Absence Info")
                .setMessage("Enter Usn!!!")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//
                        String usndata = et1.getText().toString();

                        Intent intent = new Intent(((Dialog)dialogInterface).getContext(),ViewDatabase.class);
                        intent.putExtra("usndata",usndata);
                        startActivity(intent);

                        //startActivity(new Intent(((Dialog)dialogInterface).getContext(),ViewDatabase.class));

//                        Intent intent = new Intent(ViewDatabase.INTENT_ADD_TASK);
//                        intent.putExtra(IntentConst.PARAM_A, aInput);
//                        intent.putExtra(IntentConst.PARAM_B, bInput);
//                        activity.sendBroadcast(intent);
                    }
                });

//        et1 = view.findViewById(R.id.textViewUsn);
//        et2 = view.findViewById(R.id.textViewDate);

        return builder.create();
    }

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//
//        try {
//            listener = (PersonalInfoDialogueListener)context;
//        }
//        catch (ClassCastException e)
//        {
//            //e.printStackTrace();
//            throw new ClassCastException(context.toString()
//            +"must implement PersonalInfoDialogueListener");
////            throw new ClassCastException(context.toString());
//        }
//    }

//    public interface PersonalInfoDialogueListener
//    {
//        void applyTexts(String usndata, String datedata);
//    }

//    public static String sendUsn()
//    {
//        return usndata;
//    }

//    public static String sendDate()
//    {
//        return datedata;
//    }
}
