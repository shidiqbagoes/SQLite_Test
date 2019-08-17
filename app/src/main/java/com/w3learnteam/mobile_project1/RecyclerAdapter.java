package com.w3learnteam.mobile_project1;

import android.content.Context;
import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    Context context;
    Cursor cursor;

    public RecyclerAdapter(Context context,Cursor cursor){
        this.context = context;
        this.cursor = cursor;

    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view =layoutInflater.inflate(R.layout.activity_item_beli,viewGroup,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
        if(!cursor.moveToPosition(i)){
            return;
        }

        final String getB_ID = cursor.getString(cursor.getColumnIndex(DatabaseHelper.B_ID));
        final String getB_NAME = cursor.getString(cursor.getColumnIndex(DatabaseHelper.B_NAME));
        final String getB_JUMBEL = cursor.getString(cursor.getColumnIndex(DatabaseHelper.B_JUMBEL));
        final String getB_HARGA = cursor.getString(cursor.getColumnIndex(DatabaseHelper.B_PRICE));

        recyclerViewHolder.txt_food_name.setText(getB_NAME);
        recyclerViewHolder.txt_food_qty.setText(getB_JUMBEL);
        recyclerViewHolder.txt_total_price.setText(getB_HARGA);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_food_name,txt_food_qty,txt_total_price;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_food_name = itemView.findViewById(R.id.txt_food_name);
            txt_food_qty = itemView.findViewById(R.id.txt_food_qty);
            txt_total_price = itemView.findViewById(R.id.txt_total_price);
        }
    }
}
