package com.polbins.themoviedb.app.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class HelpActivity extends AppCompatActivity {
    private Button About_us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.polbins.themoviedb.R.layout.activity_help);

        About_us = (Button) findViewById(com.polbins.themoviedb.R.id.HAboutMe);
        About_us.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(HelpActivity.this, AboutUsActivity.class));
            }
        });
    }
}
