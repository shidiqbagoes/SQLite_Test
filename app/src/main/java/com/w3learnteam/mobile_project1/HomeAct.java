package com.w3learnteam.mobile_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class HomeAct extends AppCompatActivity {

    LinearLayout btn_bakmie,btn_lumpia,btn_bubur,btn_capcai,btn_puiling,btn_jamur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_bakmie = findViewById(R.id.btn_bakmie);
        btn_lumpia = findViewById(R.id.btn_lumpia);
        btn_bubur = findViewById(R.id.btn_Bubur);
        btn_capcai = findViewById(R.id.btn_capcai);
        btn_puiling = findViewById(R.id.btn_puiling);
        btn_jamur = findViewById(R.id.btn_jamur);

        btn_bakmie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeAct.this,FoodCheckout.class);
                intent.putExtra("jenis_sembako","Beras");
                startActivity(intent);
            }
        });
        btn_lumpia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeAct.this,FoodCheckout.class);
                intent.putExtra("jenis_sembako","Jagung");
                startActivity(intent);
            }
        });
        btn_bubur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeAct.this,FoodCheckout.class);
                intent.putExtra("jenis_sembako","Gula");
                startActivity(intent);
            }
        });
        btn_capcai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeAct.this,FoodCheckout.class);
                intent.putExtra("jenis_sembako","Minyak Goreng");
                startActivity(intent);
            }
        });
        btn_puiling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeAct.this,FoodCheckout.class);
                intent.putExtra("jenis_sembako","Daging");
                startActivity(intent);
            }
        });
        btn_jamur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeAct.this,FoodCheckout.class);
                intent.putExtra("jenis_sembako","Telur");
                startActivity(intent);
            }
        });

    }
}
