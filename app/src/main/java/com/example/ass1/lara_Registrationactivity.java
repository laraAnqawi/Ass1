package com.example.ass1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class lara_Registrationactivity extends AppCompatActivity {
   EditText name ;
   EditText confirmpass;
   EditText email;
   EditText password;
   Button but_reg;
    String emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.com$";

    String namePattern = "[a-zA-Z]+";

    ProgressDialog progressDialog;
   FirebaseAuth Auth;
   FirebaseUser User;
   @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lara_registrationactivity);

        name = findViewById(R.id.name);
        email =findViewById(R.id.emaiL);
        password=findViewById(R.id.passworD);
       password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
       confirmpass =findViewById(R.id.confirmpass);
       confirmpass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

       but_reg =findViewById(R.id.but_reg);
        TextView text = findViewById(R.id.logintext);
        progressDialog =new ProgressDialog (this);
        Auth=FirebaseAuth.getInstance();
        User=Auth.getCurrentUser();
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(lara_Registrationactivity.this,lara_loginactivity.class));
            }
        });


        but_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preforAuth();
            }
        });
    }

    private void preforAuth() {
        String Name  =name.getText().toString();
        String Email= email.getText().toString();
        String Password = password.getText().toString();
        String ConfirmPass =confirmpass.getText().toString();


        if (!Email.matches(emailPattern)){
            email.setError("Enter Correct Email");
        }else if (!Name.matches(namePattern)){
            name.setError("Enter Correct Name");
        }else if (Password.isEmpty() || Password.length() < 6){
            password.setError("Enter proper Password");
        }else if (!Password.equals(ConfirmPass)){
            confirmpass.setError("Password mismatched");
        }else {
            progressDialog.setMessage("Please wait until registration ends ");
            progressDialog.setTitle("Registered successfully");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            Auth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUsertoanotheractivity();
                        Toast.makeText(lara_Registrationactivity.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(lara_Registrationactivity.this," "+task.getException(),Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }

    private void sendUsertoanotheractivity() {//the same things
    Intent intent = new Intent(lara_Registrationactivity.this, nextactivity.class);
    intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}