package com.example.ass1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class lara_loginactivity extends AppCompatActivity {
    EditText emaiL;
    EditText passworD;
    ImageView but_google;
    String emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.com$";
    ProgressDialog progressDialog;
    FirebaseAuth Auth;
    FirebaseUser User;
    GoogleSignInOptions options;
    GoogleSignInClient client;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lara_loginactivity);

        emaiL=findViewById(R.id.emaiL);
        passworD=findViewById(R.id.passworD);
       Button but_Login=findViewById(R.id.but_Login);
       but_google =findViewById(R.id.but_google);
        options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        client = GoogleSignIn.getClient(this,options);



        TextView text1 = findViewById(R.id.regtexxt);

        progressDialog =new ProgressDialog (this);

        Auth=FirebaseAuth.getInstance();
        User=Auth.getCurrentUser();

        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(lara_loginactivity.this,lara_Registrationactivity.class));
            }
        });


       but_Login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               preferLogin();
           }
       });

        but_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            LogIN();
            }
        });

    }

    private void LogIN() {
        Intent singinintent = client.getSignInIntent();
        startActivityForResult(singinintent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
               Activitynext();
            } catch (ApiException e) {
               Toast.makeText(getApplicationContext(),"something went Wrong",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void Activitynext() {
        finish();
        Intent intent =new Intent(lara_loginactivity.this, nextactivity.class);

    }

    private void preferLogin() {
        {
            String Email= emaiL.getText().toString().trim();
            String Password = passworD.getText().toString().trim();

            if (!Email.matches(emailPattern)){
                emaiL.setError("Enter Correct Email");
            }else if (Password.isEmpty() || Password.length() < 6){
                passworD.setError("Enter proper Password");
            }else {
                progressDialog.setMessage("Please wait until Login ends ");
                progressDialog.setTitle("Login successfully");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                Auth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            progressDialog.dismiss();
                            sendSuperconductivity();
                            Toast.makeText(lara_loginactivity.this,"Login successfully",Toast.LENGTH_SHORT).show();
                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(lara_loginactivity.this,""+task.getException(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        }
    }

    private void sendSuperconductivity() {//add the activity name came after the log in
        Intent intent = new Intent(lara_loginactivity.this, itamActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}