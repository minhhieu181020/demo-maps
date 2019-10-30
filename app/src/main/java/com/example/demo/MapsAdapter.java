package com.example.demo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MapsAdapter extends RecyclerView.Adapter<MapsHoler> {
    private Context context;
    private List<Maps> mapsList;
    private MapsDAO mapsDAO;

    public MapsAdapter(Context context, List<Maps> mapsList) {
        this.context = context;
        this.mapsList = mapsList;

    }

    @NonNull
    @Override
    public MapsHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rvmaps, parent, false);
        MapsHoler mapsHoler = new MapsHoler(view);

        return mapsHoler;

    }

    @Override
    public void onBindViewHolder(@NonNull final MapsHoler holder,final int position) {
        mapsDAO = new MapsDAO(context);
        holder.textView1.setText("Address: "+mapsList.get(position).getTitle());

        holder.imgxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = mapsDAO.isDelete(mapsList.get(position).getTitle());
                if (result) {
                    mapsList.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "xoa that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mapsList.size();
    }
}
