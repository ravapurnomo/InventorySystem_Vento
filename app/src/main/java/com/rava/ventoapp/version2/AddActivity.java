package com.rava.ventoapp.version2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rava.ventoapp.version2.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddActivity extends AppCompatActivity {

    EditText namaBarang_input, quantity_input, keterangan_input;
    Cursor id;
    int index;
    String starterDesc, beginnningDropdownState;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        namaBarang_input = findViewById(R.id.namaBarang_input);
        quantity_input = findViewById(R.id.quantity_input);
        keterangan_input = findViewById(R.id.keterangan_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addBarang(namaBarang_input.getText().toString().trim(),
                        Integer.valueOf(quantity_input.getText().toString().trim()),
                        keterangan_input.getText().toString());
                //Tambahan 1
                /*id = myDB.selectID(namaBarang_input.getText().toString().trim());
                id.moveToFirst();
                index = id.getInt(0);
                Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                String strDate = dateFormat.format(date);
                starterDesc = "Barang "+namaBarang_input.getText().toString().trim()+" berhasil ditambahkan pada "+strDate;
                beginnningDropdownState = "New Entry";
                MyDatabaseHelper myDB2 = new MyDatabaseHelper(AddActivity.this);
                myDB2.addBarangHistory(index,Integer.valueOf(quantity_input.getText().toString().trim()),
                        starterDesc,strDate,beginnningDropdownState);*/
            }
        });
    }
}
