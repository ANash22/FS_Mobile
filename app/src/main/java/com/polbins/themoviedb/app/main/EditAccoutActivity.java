package com.polbins.themoviedb.app.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


public class EditAccoutActivity extends AppCompatActivity {
    private Button Buser;
    private Button SaveEdits;
    private Button Verfi;
    private Button editPassword;

    private TextView Users;
    private TextView Emails;
    private EditText mUsernameView;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.polbins.themoviedb.R.layout.activity_edit_accout);
        Users = (TextView) findViewById(com.polbins.themoviedb.R.id.SUser);
        Emails = (TextView) findViewById(com.polbins.themoviedb.R.id.Semail);
        mUsernameView = (EditText) findViewById(com.polbins.themoviedb.R.id.editUser);
        mUsernameView.setVisibility(View.INVISIBLE);
        mAuth = FirebaseAuth.getInstance();
        Buser = (Button) findViewById(com.polbins.themoviedb.R.id. Buser);
        Buser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mUsernameView.setVisibility(View.VISIBLE);

            }
        });
        editPassword = (Button) findViewById(com.polbins.themoviedb.R.id.editPassword);
        editPassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(EditAccoutActivity.this, PasswordActivity.class));

            }
        });
        SaveEdits = (Button) findViewById(com.polbins.themoviedb.R.id.SaveEdits);
        SaveEdits.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editaccount();

            }


        });

        loadUserInformation();

    }
    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    private void loadUserInformation() {
        final FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {

            if (user.getDisplayName() != null) {
                Users.setText(user.getDisplayName());
            }

            if (user.isEmailVerified()) {
                Emails.setText(user.getEmail());
                Verfi = (Button) findViewById(com.polbins.themoviedb.R.id.Vefi);
                Verfi.setVisibility(View.INVISIBLE);
            } else {
                Emails.setText("Email Not Verified (Click to Verify)");
                Verfi = (Button) findViewById(com.polbins.themoviedb.R.id.Vefi);
                Verfi.setVisibility(View.VISIBLE);
                Verfi.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(EditAccoutActivity.this, "Verification Email Sent", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }
    }




    private void editaccount() {
        final String Username =  mUsernameView.getText().toString();
        FirebaseUser user = mAuth.getCurrentUser();

        if (!Username.isEmpty())
        {
            if (user != null) {
                UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                        .setDisplayName(Username)
                        .build();

                user.updateProfile(profile);
                startActivity(new Intent(EditAccoutActivity.this, MyAccountActivity.class));
            }
        }



    }
}