package com.sjsu.quiz1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class MultiplicationActivity extends AppCompatActivity {

    public String TAG = "MultiplicationActivity";
    boolean isPaused = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG,"on create");
        setContentView(R.layout.activity_subtraction);
        if(! isPaused) {
            Log.v(TAG,"on create no Paused");

            if (findViewById(R.id.fragment_container) != null) {
                MultiplicationFragment firstFragment = MultiplicationFragment.newInstance(1, 0);
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment, "mul").commit();
            }
        }

    }

    @Override
    public void onBackPressed() {
        onPause();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to quit ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                MultiplicationActivity.super.onBackPressed();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                onResume();
                return;
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    protected void onPause() {
        isPaused = true;
        super.onPause();
        //onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //onResume();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_subtraction);
        if (findViewById(R.id.fragment_container) != null) {

            FragmentManager fragmentManager = getSupportFragmentManager();

            MultiplicationFragment fragment = (MultiplicationFragment) fragmentManager.findFragmentByTag("mul");
            Log.v(TAG, fragment.nTrueQuestions + "");
            MultiplicationFragment multiplicationFragment = MultiplicationFragment.newInstance(fragment.presentQuestion, fragment.nTrueQuestions);
            if (fragment != null) {
                multiplicationFragment.getArguments().putInt("n1", fragment.n1);
                multiplicationFragment.getArguments().putInt("n2", fragment.n2);
                multiplicationFragment.getArguments().putInt("tSec", (int) fragment.timerClock.resume);
                multiplicationFragment.getArguments().putString("answerString",fragment.answerString);
                fragmentManager.beginTransaction().replace(R.id.fragment_container, multiplicationFragment, "mul").commit();
            }
        }

    }
}
