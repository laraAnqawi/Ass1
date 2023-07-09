package com.example.ass1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;

import java.util.ArrayList;

public class itamActivity extends AppCompatActivity {
    CheckBox box1;

    ArrayList<CardView> favoritesList = new ArrayList<CardView>();
    CheckBox box2;
    CardView cardView1;
    CardView cardView2;
    ImageView img,img1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itam);
        box1=findViewById(R.id.checkBox);
        box2=findViewById(R.id.checkBox2);
        img=findViewById(R.id.img);
        img1=findViewById(R.id.img1);
        cardView1=findViewById(R.id.cardView3);
        cardView2=findViewById(R.id.cardView4);
        if (box1.isChecked()){
            Intent intent = new Intent(itamActivity.this,lara_favorite.class);
            intent.putExtra("image", (CharSequence) cardView1);
            favoritesList.add(cardView1);
            for (CardView cardView : favoritesList) {
                System.out.println("CardView ID: " + cardView.getId());
            }
            startActivity(intent);
        }
        if (box2.isChecked()){
            Intent intent = new Intent(itamActivity.this,lara_favorite.class);
            intent.putExtra("image", (CharSequence) cardView2);
            favoritesList.add(cardView1);
            startActivity(intent);
        }
    }
}