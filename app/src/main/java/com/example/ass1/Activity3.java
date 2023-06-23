package com.example.ass1;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Activity3 extends AppCompatActivity {
private Button Exit;
    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);

        Exit=findViewById(R.id.button2);
        CardView rectangle = findViewById(R.id.card1R);
        CardView square = findViewById(R.id.card3S);
        CardView circle = findViewById(R.id.card2C);
        CardView triangle = findViewById(R.id.card4T);





        rectangle.setOnClickListener(view -> {
            Toast.makeText(Activity3.this, "You've chosen a Rectangle ", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Activity3.this, MainActivity.class);
                    intent.putExtra("message", "Rectangle");
                    startActivity(intent);
                    finish();
                }
            }, 4000);
        });

        square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Activity3.this, "Rectangle", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Activity3.this, MainActivity.class);
                        intent.putExtra("message", "square");
                        startActivity(intent);
                        finish();
                    }
                }, 4000);
            }
        });


        triangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Activity3.this, "Triangle", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Activity3.this, MainActivity.class);
                        intent.putExtra("message", "Triangle");
                        startActivity(intent);
                        finish();
                    }
                }, 4000);
            }
        });

        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Activity3.this, "Circle", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Activity3.this, MainActivity.class);
                        intent.putExtra("message", "Circle");
                        startActivity(intent);
                        finish();
                    }
                }, 4000);
            }
        });
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }
}