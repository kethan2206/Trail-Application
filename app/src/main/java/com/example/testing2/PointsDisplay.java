package com.example.testing2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PointsDisplay extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayPoint();
    }


        public void displayPoint () {
            for (int i = 0; i < 100; i += 10){
                TextView textView = (TextView)findViewById(R.id.points);
                textView.setText(i);

            }


    }
}
