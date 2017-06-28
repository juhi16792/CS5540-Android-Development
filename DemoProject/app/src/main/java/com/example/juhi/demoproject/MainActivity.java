package com.example.juhi.demoproject;

import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ActionProvider;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.SimpleAdapter;



public class  MainActivity extends AppCompatActivity implements ViewManager {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button clicker = (Button) findViewById(R.id.clicker);
        clicker.setOnClickListener( this);
    }

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.d("testclick", "clicked");
            }

    @Override
    public void addView(View view, ViewGroup.LayoutParams params) {

    }

    @Override
    public void updateViewLayout(View view, ViewGroup.LayoutParams params) {

    }

    @Override
    public void removeView(View view) {

    }
}
}