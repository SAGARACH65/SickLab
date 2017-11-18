package com.example.sagar.sicklab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sagar.database.GetDataDisease;

public class DiseaseDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_description);


        Bundle extras = getIntent().getExtras();
        String description = extras.getString("description");
        String disease_name = extras.getString("name");
        String img_url = extras.getString("img_url");


        TextView tv = (TextView) findViewById(R.id.mainToolBar);
        tv.setText(disease_name);


        TextView tv1 = (TextView) findViewById(R.id.textView11);
        tv1.setText(description);
    }
}
