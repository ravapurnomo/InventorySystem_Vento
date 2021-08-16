package com.rava.ventoapp.version2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rava.ventoapp.version2.R;

public class UpdateActivity extends AppCompatActivity {

    EditText nama_barang, jumlah_barang;
    Button update_button, delete_button;

    String id, nama, jumlah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nama_barang = findViewById(R.id.nama_barang2);
        jumlah_barang = findViewById(R.id.jumlah_barang2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(nama);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                nama = nama_barang.getText().toString().trim();
                jumlah = jumlah_barang.getText().toString().trim();
                myDB.updateData(id, nama, jumlah);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("nama") && getIntent().hasExtra("jumlah")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            nama = getIntent().getStringExtra("nama");
            jumlah = getIntent().getStringExtra("jumlah");

            //Setting Intent Data
            nama_barang.setText(nama);
            jumlah_barang.setText(jumlah);
            Log.d("vento", nama+" "+jumlah);
        }else{
            Toast.makeText(this, "Tidak ada data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus " + nama + " ?");
        builder.setMessage("Apakah Anda yakin akan menghapus data " + nama + " ?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
