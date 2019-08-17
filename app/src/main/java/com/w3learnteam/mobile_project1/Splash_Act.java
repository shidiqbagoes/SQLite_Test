package com.w3learnteam.mobile_project1;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash_Act extends AppCompatActivity {

    private ImageView splash_image;
    private TextView splash_txt;
    private Animation splashanim;

    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        databaseHelper = new DatabaseHelper(this);
        sqLiteDatabase = databaseHelper.getReadableDatabase();

        splash_image = findViewById(R.id.splash_img);
        splash_txt = findViewById(R.id.splash_txt);
        splashanim = AnimationUtils.loadAnimation(Splash_Act.this,R.anim.splashanim);
        splash_image.startAnimation(splashanim);

        splash_txt.setTranslationY(800);
        splash_txt.setAlpha(0);
        splash_txt.animate().alpha(1).translationY(0).setDuration(800).setStartDelay(100).start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // merubah activity ke activity lain
                Intent gogethome = new Intent(Splash_Act.this,HomeAct.class);
                startActivity(gogethome);
                finish();
            }
        }, 2000); // 2000 ms = 2s
    }
}
