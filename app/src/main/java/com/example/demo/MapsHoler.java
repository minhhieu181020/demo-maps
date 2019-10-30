package com.example.demo;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MapsHoler extends RecyclerView.ViewHolder {
    public TextView textView1;
    public ImageView imgxoa;
    public MapsHoler(@NonNull View itemView) {

        super(itemView);
        textView1=itemView.findViewById(R.id.tvText1);
        imgxoa=itemView.findViewById(R.id.imgXoa);

    }
}
