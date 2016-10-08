package com.sjsu.quiz1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by jay on 10/1/16.
 */

public class AdditionActivity extends AppCompatActivity {

    Toast toast = null;
    Toast toastValid = null;
    int presentQuestion ;
    CustomTimer timerClock;
    TextView timer ;
    boolean isPaused = false;
    ImageView trueView ;
    ImageView falseView ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(R.layout.add_activity);
        LayoutInflater inflater = getLayoutInflater();
        trueView = new ImageView(getApplicationContext());
        trueView.setImageResource(R.drawable.green_min);
        falseView = new ImageView(getApplicationContext());
        falseView.setImageResource(R.drawable.red_wrong);
        timerClock = new CustomTimer(5000,1000);
        timer = (TextView) findViewById(R.id.timer);
        timerClock.setView(timer);

        presentQuestion = getIntent().getIntExtra("presentQuestion",1);
        TextView presQ = (TextView) findViewById(R.id.gap);
        presQ.setText(presentQuestion+" / 10");


        Random random = new Random();
        int n1 = random.nextInt(10);
        int n2 = random.nextInt(10);
        final int correctAnswer = n1+n2;
        TextView num1 = (TextView) findViewById(R.id.num1);
        num1.setTextSize(45);
        num1.setText(n1+"");
        TextView num2 = (TextView) findViewById(R.id.num2);
        num2.setTextSize(45);
        num2.setText(n2+"");

        timerClock.start();

        Button b1 = (Button) findViewById(R.id.one);
        Button b2 = (Button) findViewById(R.id.two);
        Button b3 = (Button) findViewById(R.id.three);
        Button b4 = (Button) findViewById(R.id.four);
        Button b5 = (Button) findViewById(R.id.five);
        Button b6 = (Button) findViewById(R.id.six);
        Button b7 = (Button) findViewById(R.id.seven);
        Button b8 = (Button) findViewById(R.id.eight);
        Button b9 = (Button) findViewById(R.id.nine);
        Button b0 = (Button) findViewById(R.id.zero);
        Button bclear = (Button) findViewById(R.id.clear);
        Button bEnter = (Button) findViewById(R.id.enter);

        b1.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                updateAnswer(1);
            }

        });
        b2.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                updateAnswer(2);
            }

        });
        b3.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                updateAnswer(3);
            }

        });
        b4.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                updateAnswer(4);
            }

        });
        b5.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                updateAnswer(5);
            }

        });
        b6.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                updateAnswer(6);
            }

        });
        b7.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                updateAnswer(7);
            }

        });
        b8.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                updateAnswer(8);
            }

        });
        b9.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                updateAnswer(9);
            }

        });
        b0.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                updateAnswer(0);
            }

        });
        bclear.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                updateAnswer(11);
            }

        });
        bEnter.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                TextView num1 = (TextView) findViewById(R.id.answer);
                String existing = num1.getText().toString();
                String e = getIntValue(existing);
                if(e.length() > 2 || e.length() == 0) {
                    showToast("Enter valid number");
                }
                else {
                    int givenAnswer = Integer.parseInt(e);
                    if (givenAnswer == correctAnswer) {
                        nextScreen(true);
                    } else
                        nextScreen(false);
                }
            }

        });

    }

    public void showToast(String msg) {
        if(toastValid != null){
            toastValid.cancel();
        }
        toastValid = Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT);
        toastValid.show();
    }

    public void updateAnswer(int i){
        TextView num1 = (TextView) findViewById(R.id.answer);
        String existing = num1.getText().toString();
        String e = getIntValue(existing);
        String value;

        if(e.length() > 2) {
            e="";
        }
        if(i == 11)
            value="";
        else
            value=e+i;
        if(value.length() > 2){
            showToast("Number cannot exceed 2 digits");
        }
        else
            num1.setText("Answer : "+value);
    }
    public String getIntValue(String existing) {
        return existing.split(":")[1].trim();
    }

    @Override
    public void onBackPressed() {
        timerClock.pause();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to quit ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                timerClock.cancel();
                AdditionActivity.super.onBackPressed();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                timerClock = timerClock.resume();
                timerClock.setView(timer);
                timerClock.start();
                return;
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
    public void nextScreen(final boolean answer) {

        if(toastValid != null)
            toastValid.cancel();

        toast = new Toast(getApplicationContext());

        if (answer) {
            toast.setView(trueView);

            toast.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                    moveToNextScreen(true);
                }
            }, 200);
        }
        else{
            toast.setView(falseView);

            toast.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                    moveToNextScreen(false);
                }
            }, 200);
        }
    }
    public void moveToNextScreen(boolean answer) {
            //Toast.makeText(getApplicationContext(),answer ? "Correct Answer" : "Wrong Answer",Toast.LENGTH_SHORT).show();;
            timerClock.cancel();
            Intent intent = getIntent();
            int nTrueQuestions = intent.getIntExtra("True", 0);
            nTrueQuestions = answer ? nTrueQuestions + 1 : nTrueQuestions;
            if (++presentQuestion <= 10) {
                Intent newIntent = new Intent(getApplicationContext(), AdditionActivity.class);
                newIntent.putExtra("True", nTrueQuestions);
                newIntent.putExtra("presentQuestion", presentQuestion);
                newIntent.putExtra("ParentClassSource", "com.sjsu.quiz1.HomeActivity");

                newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(newIntent);
            } else {
                if (toast != null)
                    toast.cancel();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Your score is "+nTrueQuestions +" / 10");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AdditionActivity.super.onBackPressed();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
    }

    class CustomTimer extends CountDownTimer {
        long resume = 0;
        TextView timer;

        public CustomTimer(long millFuture, long gap) {
            super(millFuture,gap);
        }

        public void setView(TextView view) {
            this.timer = view;
        }

        public void onTick(long millisUntilFinished) {
                resume = millisUntilFinished;
                timer.setText("seconds remaining: " + millisUntilFinished / 1000);
        }

        public void onFinish() {
            nextScreen(false);
        }

        public void pause(){
            super.cancel();
        }

        public CustomTimer resume(){
            if(resume > 0)
                return new CustomTimer(resume,1000);
            else
                return new CustomTimer(5000,1000);
        }

    }
    @Override
    public void onPause() {
        super.onPause();
        isPaused = true;
        timerClock.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(isPaused) {
            isPaused = false;
              timerClock = timerClock.resume();
              timerClock.setView(timer);
              timerClock.start();
        }
    }



}
