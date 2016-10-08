package com.sjsu.quiz1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by jay on 10/2/16.
 */

public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_activity);

        Intent intent = getIntent();
        int correctAnswers = intent.getIntExtra("True", 0);

        TextView tView = (TextView) findViewById(R.id.endQuiz);
        tView.setTextSize(40);
        //tView.setTextColor(2);
        tView.setText("Your score : " + correctAnswers + "/10");
    }

}
