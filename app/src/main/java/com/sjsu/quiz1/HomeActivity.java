package com.sjsu.quiz1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        Context context = getApplicationContext();

        Toast toast = Toast.makeText(context, "Quiz1", Toast.LENGTH_SHORT);
        toast.show();

        Button bAdd = (Button) findViewById(R.id.addButton);
        bAdd.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v) {
                Context context = getApplicationContext();
                startAddition(v);
            }
        });
        Button bSub = (Button) findViewById(R.id.subButton);
        bSub.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v) {
                //Context context = getApplicationContext();
                startSubtraction(v);
            }
        });
    }

    public void startAddition(View v){

        Intent intent = new Intent(this, AdditionActivity.class);
        intent.putExtra("origin","home");
        startActivity(intent);

    }
    public void startSubtraction(View v){

        Intent intent2 = new Intent(this, SubtractionActivity.class);
        intent2.putExtra("origin","home");
        startActivity(intent2);

    }
}
