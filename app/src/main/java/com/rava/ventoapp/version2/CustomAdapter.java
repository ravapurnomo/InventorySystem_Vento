package com.rava.ventoapp.version2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.rava.ventoapp.version2.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList id_barang, nama_barang, jumlah_barang;

    CustomAdapter(Activity activity, Context context, ArrayList id_barang, ArrayList nama_barang,
                  ArrayList jumlah_barang){
        this.activity = activity;
        this.context = context;
        this.id_barang = id_barang;
        this.nama_barang = nama_barang;
        this.jumlah_barang = jumlah_barang;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.id_barang_txt.setText(String.valueOf(id_barang.get(position)));
        holder.nama_barang_txt.setText(String.valueOf(nama_barang.get(position)));
        holder.jumlah_barang_txt.setText(String.valueOf(jumlah_barang.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(id_barang.get(position)));
                intent.putExtra("nama", String.valueOf(nama_barang.get(position)));
                intent.putExtra("jumlah", String.valueOf(jumlah_barang.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return id_barang.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_barang_txt, nama_barang_txt, jumlah_barang_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_barang_txt = itemView.findViewById(R.id.id_barang_txt);
            nama_barang_txt = itemView.findViewById(R.id.nama_barang_txt);
            jumlah_barang_txt = itemView.findViewById(R.id.jumlah_barang_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
