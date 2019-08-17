package com.w3learnteam.mobile_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FoodCheckout extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    DatabaseHelper databaseHelper;
    Button btnmines,btnplus,btn_buy_sembako;
    LinearLayout btn_back;
    TextView txt_nama_sembako,txt_harga_satuan,textjumlahsembako,texttotalharga;

    Integer valuejumlahsembako = 1;
    Integer valuetotalharga = 0;
    Integer valuehargasembako = 0;
    Integer valuesisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_checkout);

        databaseHelper = new DatabaseHelper(this);
        sqLiteDatabase = databaseHelper.getReadableDatabase();

        txt_nama_sembako = findViewById(R.id.txt_nama_sembako);
        txt_harga_satuan = findViewById(R.id.txt_harga_satuan);
        textjumlahsembako = findViewById(R.id.textjumlahsembako);
        texttotalharga = findViewById(R.id.texttotalharga);

        btnplus = findViewById(R.id.btnplus);
        btnmines = findViewById(R.id.btnmines);
        btn_buy_sembako = findViewById(R.id.btn_buy_sembako);
        btn_back = findViewById(R.id.btn_back);

        Bundle bundle = getIntent().getExtras();
        final String getsembako = bundle.getString("jenis_sembako");

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM sembako WHERE sname = '"+getsembako+"'",null);
        cursor.moveToFirst();

        final String id_sembako = cursor.getString(0);
        final String nama_sembako = cursor.getString(1);
        final String harga_satuan = cursor.getString(2);
        final String harga_sembako = cursor.getString(3);
        final String sisa_sembako = cursor.getString(4);

        txt_nama_sembako.setText(nama_sembako);
        txt_harga_satuan.setText(harga_satuan);

        textjumlahsembako.setText(valuejumlahsembako.toString());

        valuehargasembako = Integer.valueOf(harga_sembako);
        valuesisa = Integer.valueOf(sisa_sembako);

        valuetotalharga = valuehargasembako*valuejumlahsembako;
        texttotalharga.setText("IDR "+valuetotalharga.toString());



        if(valuesisa == 0){
            btnplus.animate().alpha(0).setDuration(300).start();
            btnplus.setEnabled(false);
            textjumlahsembako.setText("0");
            texttotalharga.setTextColor(Color.parseColor("#242424"));
            texttotalharga.setText("HABIS");
            btn_buy_sembako.setEnabled(false);
            btn_buy_sembako.setBackgroundResource(R.drawable.bg_btn_color_disable);
        }
        else {
            btnplus.setEnabled(true);
            btnplus.animate().alpha(1).setDuration(300).start();
            btn_buy_sembako.setEnabled(true);
            texttotalharga.setTextColor(Color.parseColor("#1abc9c"));
            btn_buy_sembako.setBackgroundResource(R.drawable.bg_btn_color);
        }

        btnmines.animate().alpha(0).setDuration(300).start();
        btnmines.setEnabled(false);


        btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valuejumlahsembako+=1;
                textjumlahsembako.setText(valuejumlahsembako.toString());
                if (valuejumlahsembako > 1) {
                    btnmines.animate().alpha(1).setDuration(300).start();
                    btnmines.setEnabled(true);
                }
                if(valuejumlahsembako >= valuesisa){
                    btnplus.animate().alpha(0).setDuration(300).start();
                    btnplus.setEnabled(false);
                }

                valuetotalharga = valuehargasembako * valuejumlahsembako;
                texttotalharga.setText("IDR " + valuetotalharga+"");

            }
        });

        btnmines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valuejumlahsembako-=1;
                textjumlahsembako.setText(valuejumlahsembako.toString());
                if (valuejumlahsembako < 2) {
                    btnmines.animate().alpha(0).setDuration(300).start();
                    btnmines.setEnabled(false);
                }
                if(valuejumlahsembako < valuesisa){
                    btnplus.animate().alpha(1).setDuration(300).start();
                    btnplus.setEnabled(true);
                }
                valuetotalharga = valuehargasembako * valuejumlahsembako;
                texttotalharga.setText("IDR " + valuetotalharga+"");
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btn_buy_sembako.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer updatejumlah = valuesisa - valuejumlahsembako;

                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseHelper.S_JUMSEM,updatejumlah.toString());

                databaseHelper = new DatabaseHelper(FoodCheckout.this);
                sqLiteDatabase = databaseHelper.getWritableDatabase();

                sqLiteDatabase.update(DatabaseHelper.TABLE_SEMBAKO,contentValues,DatabaseHelper.S_ID+"=?",
                        new String[]{id_sembako});
                sqLiteDatabase.close();

                ContentValues contentValues1 = new ContentValues();
                contentValues1.put(DatabaseHelper.B_NAME,nama_sembako);
                contentValues1.put(DatabaseHelper.B_JUMBEL,valuejumlahsembako.toString());
                contentValues1.put(DatabaseHelper.B_PRICE,valuetotalharga.toString());

                sqLiteDatabase = databaseHelper.getWritableDatabase();
                sqLiteDatabase.insert(DatabaseHelper.TABLE_BELI,null,contentValues1);

                ContentValues contentValues2 = new ContentValues();
                contentValues2.put(DatabaseHelper.B_JUMSEM,updatejumlah.toString());

                sqLiteDatabase.update(DatabaseHelper.TABLE_BELI,contentValues2,DatabaseHelper.B_NAME+"=?",
                        new String[]{getsembako});
                sqLiteDatabase.close();

                Intent intent = new Intent(FoodCheckout.this,BuyList.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
