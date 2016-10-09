package com.sjsu.quiz1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class SubtractionActivity extends AppCompatActivity {

    public String TAG = "SubtractionActivity";
    boolean isPaused = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG,"on create");
        setContentView(R.layout.activity_subtraction);
        if(! isPaused) {
            Log.v(TAG,"on create no Paused");

            if (findViewById(R.id.fragment_container) != null) {
                SubtractFragment firstFragment = SubtractFragment.newInstance(1, 0);
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment, "sub").commit();
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
            SubtractionActivity.super.onBackPressed();
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

            SubtractFragment fragment = (SubtractFragment) fragmentManager.findFragmentByTag("sub");
            Log.v(TAG, fragment.nTrueQuestions + "");
            SubtractFragment subtractFragment = SubtractFragment.newInstance(fragment.presentQuestion, fragment.nTrueQuestions);
            if (fragment != null) {
                subtractFragment.getArguments().putInt("n1", fragment.n1);
                subtractFragment.getArguments().putInt("n2", fragment.n2);
                subtractFragment.getArguments().putInt("tSec", (int) fragment.timerClock.resume);
                fragmentManager.beginTransaction().replace(R.id.fragment_container, subtractFragment, "sub").commit();
            }
        }

    }
}
