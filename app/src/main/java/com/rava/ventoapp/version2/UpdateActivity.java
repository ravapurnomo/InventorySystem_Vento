package com.rava.ventoapp.version2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;

import com.rava.ventoapp.version2.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UpdateActivity extends AppCompatActivity {

    EditText id_barang, nama_barang, jumlah_barang, ket_barang;
    Button okay_button, detail_button;
    Spinner dropdown;

    String id, nama, jumlah, keterangan;
    MyDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        id_barang = findViewById(R.id.id_barang2);
        nama_barang = findViewById(R.id.nama_barang2);
        jumlah_barang = findViewById(R.id.jumlah_barang2);
        ket_barang = findViewById(R.id.keterangan_input2);
        okay_button = findViewById(R.id.okay_button);
        detail_button = findViewById(R.id.seedetail_button);
        dropdown = findViewById(R.id.static_spinner);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(nama);
        }
        Spinner staticSpinner = (Spinner) findViewById(R.id.static_spinner);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.brew_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);

        okay_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final int idBarang = Integer.parseInt(id);
                String namaBarang = nama_barang.getText().toString();
                String ketBarang = ket_barang.getText().toString();
                int updateJumlah = Integer.parseInt(jumlah_barang.getText().toString());
                String dropdownHistory = dropdown.getSelectedItem().toString();
                String jumlahTotal = "";
                if (dropdownHistory.equals("Barang Masuk")) {
                    jumlahTotal = String.valueOf(Integer.parseInt(jumlah) + Integer.parseInt(jumlah_barang.getText().toString()));
                } else if (dropdownHistory.equals("Barang Keluar")) {
                    jumlahTotal = String.valueOf(Integer.parseInt(jumlah) - Integer.parseInt(jumlah_barang.getText().toString()));
                }
                else if (dropdownHistory.equals("Hapus")) {
                            MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                            myDB.deleteOneRow(id);
                            finish();
                }
                else {
                    jumlahTotal = jumlah;
                }


                Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                String strDate = dateFormat.format(date);

                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.updateBarang(idBarang, namaBarang, ketBarang, strDate, updateJumlah, dropdownHistory);
               /* myDB.addBarangHistory(idBarang,updateJumlah,ketBarang,strDate,dropdownHistory);*/
                myDB.updateData(id, namaBarang, jumlahTotal, ketBarang);


            }
        });
        detail_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);

            }
        });
/*        update_button.setOnClickListener(new View.OnClickListener() {
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
        });*/
       /* myDB = new MyDatabaseHelper(UpdateActivity.this);
        Cursor cursor = myDB.readAllDataHistory();
        int a = cursor.getCount();
        while (cursor.moveToNext()){
            String idBarang = (cursor.getString(0));
            String ketBarang = (cursor.getString(1));
            String strDate = (cursor.getString(2));
            String jumlahBarang = (cursor.getString(3));
            String dropdown = (cursor.getString(4));
            String b = "";
        }*/
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("nama") && getIntent().hasExtra("jumlah")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            nama = getIntent().getStringExtra("nama");
            jumlah = getIntent().getStringExtra("jumlah");
            keterangan = getIntent().getStringExtra("keterangan");

            //Setting Intent Data
            id_barang.setText(id);
            nama_barang.setText(nama);
            jumlah_barang.setText(jumlah);
            ket_barang.setText(keterangan);
            Log.d("vento", nama+" "+jumlah+" "+keterangan+" ");
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
