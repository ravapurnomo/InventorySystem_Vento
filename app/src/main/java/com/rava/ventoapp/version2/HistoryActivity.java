package com.rava.ventoapp.version2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rava.ventoapp.version2.R;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    MyDatabaseHelper myDB;
    ArrayList<String> id_barang, nama_barang, jumlah_barang, ket_barang;
    CustomAdapter customAdapter;
    String idBarang="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recyclerView2);

        myDB = new MyDatabaseHelper(HistoryActivity.this);
        id_barang = new ArrayList<>();
        nama_barang = new ArrayList<>();
        jumlah_barang = new ArrayList<>();
        ket_barang = new ArrayList<>();
        idBarang = getIntent().getStringExtra("id");


        if (idBarang != null){
            storeDataInArraysById();
        } else{
            storeDataInArrays();
        }
        customAdapter = new CustomAdapter(HistoryActivity.this, this, id_barang, nama_barang, jumlah_barang, ket_barang);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArraysById(){
        Cursor cursor = myDB.readDataHistoryById(Integer.parseInt(idBarang));

        int a = cursor.getCount();
        if (a > 0) {
            while (cursor.moveToNext()){
                String idBarang = (cursor.getString(0));
                String namaBarang = (cursor.getString(1));
                String ketBarang = (cursor.getString(2));
                String strDate = (cursor.getString(3));
                String jumlahBarang = (cursor.getString(4));
                String dropdown = (cursor.getString(5));

                id_barang.add(idBarang);
                nama_barang.add(namaBarang);
                jumlah_barang.add(jumlahBarang);
                ket_barang.add(dropdown);
            }
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllDataHistory();

        int a = cursor.getCount();
        if (a > 0) {
            while (cursor.moveToNext()){
                String idBarang = (cursor.getString(0));
                String namaBarang = (cursor.getString(1));
                String ketBarang = (cursor.getString(2));
                String strDate = (cursor.getString(3));
                String jumlahBarang = (cursor.getString(4));
                String dropdown = (cursor.getString(5));

                id_barang.add(idBarang);
                nama_barang.add(namaBarang);
                jumlah_barang.add(jumlahBarang);
                ket_barang.add(dropdown);
            }
        }
    }
}