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


    TextView data_history;
    Cursor keterangan_history;
    MyDatabaseHelper myDB;

    //CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        myDB = new MyDatabaseHelper(HistoryActivity.this);

        Cursor cursor = myDB.readAllDataHistory();
        if(cursor.getCount() == 0){
            while (cursor.moveToNext()){
                data_history.setText(cursor.getString(0));
            }
        }

       // storeDataInArrays();



       /* customAdapter = new CustomAdapter(HistoryActivity.this,this,  ket_history);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));*/

    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllDataHistory();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                //id_barang.add(cursor.getString(0));
                ket_history.add(cursor.getString(0));
                //jumlah_barang.add(cursor.getString(2));
                //ket_barang.add(cursor.getString(3));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
*/
    //@Override
    /*public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.history_all){
            data_history= findViewById(R.id.ket_history_txt);

            MyDatabaseHelper myDB = new MyDatabaseHelper(HistoryActivity.this);
            keterangan_history = myDB.readAllDataHistory();
            if(keterangan_history!=null && keterangan_history.getCount()>0)
            {
                keterangan_history.moveToFirst();
                do{
                    list_ket_history = keterangan_history.getString(0);
                    data_history.setText(list_ket_history);
                }while (keterangan_history.moveToNext());
            }
            //Refresh Activity
            Intent intent = new Intent(HistoryActivity.this, HistoryActivity.class);
            startActivity(intent);
            finish();

        }
        return super.onOptionsItemSelected(item);
    }*/

   /* void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus semua?");
        builder.setMessage("Apakah Anda yakin akan menghapus semua Data?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(HistoryActivity.this);
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(HistoryActivity.this, HistoryActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }*/
}