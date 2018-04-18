package com.polbins.themoviedb.app.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.polbins.themoviedb.R;

public class MActivity extends AppCompatActivity {

    private Button Login;
    private Button Register;
    private Button Help;
    private Button Find;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.polbins.themoviedb.R.layout.activity_m);
        mAuth = FirebaseAuth.getInstance();
        Login = (Button) findViewById(com.polbins.themoviedb.R.id.Login);
        Login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MActivity.this, LoginActivity.class));
            }
        });


        Register = (Button) findViewById(com.polbins.themoviedb.R.id.Register);
        Register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MActivity.this, RegisterActivity.class));
            }
        });

        Help = (Button) findViewById(com.polbins.themoviedb.R.id.Mhelp);
        Help.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MActivity.this, HelpActivity.class));
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(this, WelcomeActivity.class));
        }
    }
}
