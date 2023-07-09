package com.example.ass1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class LoginGoogle extends AppCompatActivity {
    GoogleSignInOptions options;
    GoogleSignInClient client;
    TextView nameG ,emailG;
    Button but_Singout;
    Button but_google;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_google);
        options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        client = GoogleSignIn.getClient(this,options);
        nameG =findViewById(R.id.nameG);
        emailG =findViewById(R.id.emailG);
        but_Singout =findViewById(R.id.but_singout);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null){
            String NAme =account.getDisplayName();
            String eMAil = account.getEmail();
            nameG.setText(NAme);
            emailG.setText(eMAil);
        }
    but_Singout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SingOut();
        }
    });

    }

    private void SingOut() {
    client.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete( Task<Void> task) {
            finish();
            startActivity(new Intent(LoginGoogle.this, lara_loginactivity.class));
        }
    });
    }
}