package com.sjsu.quiz1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MultiplicationFragment extends Fragment {

    int n1;
    int n2;
    int correctAnswer = 0;
    Toast toast = null;
    Toast toastValid = null;
    int presentQuestion ;
    int nTrueQuestions;
    CustomTimer timerClock = null;
    TextView timer ;
    boolean isPaused = false;
    ImageView trueView ;
    ImageView falseView ;
    String answerString = "";
    boolean retained = false;
    static String TAG = "SubtractFragment";
    int tSec = 0;
    protected Activity mActivity;
    public MultiplicationFragment() {
        // Required empty public constructor
    }

    public static MultiplicationFragment newInstance(int param1, int param2) {

        Log.v(TAG,"new instance");

        MultiplicationFragment fragment = new MultiplicationFragment();
        Bundle args = new Bundle();
        args.putInt("presentQuestion", param1);
        args.putInt("True", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG,"on create");

        if(savedInstanceState != null ){
            Log.v(TAG,"on create with saved bundles");

            retained = true;
        }
        if (getArguments() != null) {
            presentQuestion = getArguments().getInt("presentQuestion");
            nTrueQuestions = getArguments().getInt("True");
            n1 = getArguments().getInt("n1");
            n2 = getArguments().getInt("n2");
            if( n1 == 0 && n2 == 0) {
                Random random = new Random();
                n1 = random.nextInt(10);
                n2 = random.nextInt(10);


            }
            isPaused = getArguments().getBoolean("isPaused");
            answerString = getArguments().getString("answerString");
            answerString = answerString == null ? "" : answerString;
            correctAnswer = n1*n2;
            tSec = getArguments().getInt("tSec");
            if(tSec != 0) {
                timerClock = new CustomTimer(tSec, 1000);
                //timer = (TextView) getView().findViewById(R.id.timer);
                //timerClock.setView(timer);
                //timerClock.start();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v(TAG,"on create view");
        return inflater.inflate(R.layout.add_activity, container, false);
    }

    @Override
    public void onStart() {
        Log.v(TAG,"on start");

        super.onStart();
        Log.v(TAG,"on start 2");
        /*TextView num1 = (TextView) getView().findViewById(R.id.num1);
        num1.setTextSize(30);
        num1.setText(n1 + "");
        TextView num2 = (TextView) getView().findViewById(R.id.num2);
        num2.setTextSize(30);
        num2.setText(n2 + "");*/
        init();

    }

    public void init(){

        Log.v(TAG,"on init");

        if(timerClock != null ) {
            timerClock.cancel();
            timerClock = new CustomTimer(timerClock.resume,1000);
            timer = (TextView) getView().findViewById(R.id.timer);
            timerClock.setView(timer);
        }
        else {
            timerClock = new CustomTimer(5000 + 300, 1000);
            timer = (TextView) getView().findViewById(R.id.timer);
            timerClock.setView(timer);
        }

        trueView = new ImageView(getActivity().getApplicationContext());
        trueView.setImageResource(R.drawable.green_min);
        falseView = new ImageView(getActivity().getApplicationContext());
        falseView.setImageResource(R.drawable.red_wrong);


        TextView answ = (TextView) getView().findViewById(R.id.answer);
        answ.setText("  "+answerString);


        TextView presQ = (TextView) getView().findViewById(R.id.gap);
        presQ.setText("Ques : "+presentQuestion+" of 10");



        TextView num1 = (TextView) getView().findViewById(R.id.num1);
        num1.setTextSize(30);
        num1.setText("   "+n1);
        TextView num2 = (TextView) getView().findViewById(R.id.num2);
        num2.setTextSize(30);
        num2.setText("* "+n2+"");
        if(!isPaused){
            timerClock.setView(timer);
            timerClock.start();
            onPause();
        }

        Button b1 = (Button) getView().findViewById(R.id.one);
        Button b2 = (Button) getView().findViewById(R.id.two);
        Button b3 = (Button) getView().findViewById(R.id.three);
        Button b4 = (Button) getView().findViewById(R.id.four);
        Button b5 = (Button) getView().findViewById(R.id.five);
        Button b6 = (Button) getView().findViewById(R.id.six);
        Button b7 = (Button) getView().findViewById(R.id.seven);
        Button b8 = (Button) getView().findViewById(R.id.eight);
        Button b9 = (Button) getView().findViewById(R.id.nine);
        Button b0 = (Button) getView().findViewById(R.id.zero);
        Button bclear = (Button) getView().findViewById(R.id.clear);
        Button bEnter = (Button) getView().findViewById(R.id.enter);

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
        bclear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                updateAnswer(11);
            }

        });
        bEnter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TextView num1 = (TextView) getView().findViewById(R.id.answer);
                String existing = num1.getText().toString().trim();
                String e = getIntValue(existing);
                if (e.length() > 2 || e.length() == 0) {
                    showToast("Enter valid number");
                } else {
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
        toastValid = Toast.makeText(getActivity().getApplicationContext(),msg,Toast.LENGTH_SHORT);
        toastValid.show();
    }

    public void updateAnswer(int i){
        TextView num1 = (TextView) getView().findViewById(R.id.answer);
        answerString = num1.getText().toString().trim();
        String e = getIntValue(answerString);

        if (e.length() > 2) {
            e = "";
        }
        if (i == 11)
            answerString = "";
        else
            answerString = e + i;
        if (answerString.length() > 2) {
            showToast("Number cannot exceed 2 digits");
        } else
            num1.setText("  "+answerString);

    }
    public String getIntValue(String existing) {
        return existing;
    }

    public void nextScreen(final boolean answer) {

        if(toastValid != null)
            toastValid.cancel();

        toast = new Toast(mActivity.getApplicationContext());

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
            }, 100);
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
            }, 100);
        }
    }
    public void moveToNextScreen(boolean answer) {
        //Toast.makeText(getActivity(),answer ? "Correct Answer" : "Wrong Answer",Toast.LENGTH_SHORT).show();;
        timerClock.cancel();
        nTrueQuestions = answer ? nTrueQuestions + 1 : nTrueQuestions;
        if (++presentQuestion <= 10) {
            /*Intent newIntent = new Intent(getApplicationContext(), AdditionActivity.class);
            newIntent.putExtra("True", nTrueQuestions);
            newIntent.putExtra("presentQuestion", presentQuestion);
            newIntent.putExtra("ParentClassSource", "com.sjsu.quiz1.HomeActivity");

            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(newIntent);*/

            FragmentTransaction fragmentTransaction = ((FragmentActivity)mActivity).getSupportFragmentManager().beginTransaction();
            Fragment mulFragment = MultiplicationFragment.newInstance(presentQuestion,nTrueQuestions);
            fragmentTransaction.replace(R.id.fragment_container, mulFragment, "mul");
            fragmentTransaction.commit();
        } else {
            if (toast != null)
                toast.cancel();
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Your score is "+nTrueQuestions +" / 10");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent newIntent = new Intent(mActivity.getApplicationContext(), HomeActivity.class);
                    getActivity().finish();
                    newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(newIntent);
                }
            });
            AlertDialog dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.v(TAG,"on resume");

        if (isPaused) {
            isPaused = false;
            timerClock = timerClock.resume();
            timerClock.setView(timer);
            timerClock.start();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v(TAG,"on pause");


        isPaused = true;
        timerClock.pause();
    }
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //mActivity = activity;
    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        mActivity = (Activity) context;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    class CustomTimer extends CountDownTimer {
        long resume = 0;
        TextView timer;

        public CustomTimer(long millFuture, long gap) {
            super(millFuture, gap);
            resume = millFuture;
        }

        public void setView(TextView view) {
            this.timer = view;
        }

        public void onTick(long millisUntilFinished) {
            resume = millisUntilFinished;
            timer.setTextSize(25);
            timer.setText("Timer : " + millisUntilFinished / 1000);
        }

        public void onFinish() {

            timer.setText("Timer : 0");
            nextScreen(false);
        }

        public void pause() {
            super.cancel();
        }

        public CustomTimer resume() {
            if (resume > 0)
                return new CustomTimer(resume, 1000);
            else
                return new CustomTimer(5000, 1000);
        }
    }

}
