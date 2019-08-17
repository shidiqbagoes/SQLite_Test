package com.w3learnteam.mobile_project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class BuyList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_list);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        sqLiteDatabase = databaseHelper.getReadableDatabase();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerAdapter = new RecyclerAdapter(BuyList.this, getAllItems());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);
    }

    private Cursor getAllItems(){
        return sqLiteDatabase.query(
                DatabaseHelper.TABLE_BELI,
                null,
                null,
                null,
                null,
                null,
                DatabaseHelper.B_NAME + " ASC"
        );

    }

}

