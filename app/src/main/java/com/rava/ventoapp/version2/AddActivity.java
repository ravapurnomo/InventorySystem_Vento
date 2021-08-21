package com.rava.ventoapp.version2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rava.ventoapp.version2.R;

public class AddActivity extends AppCompatActivity {

    EditText namaBarang_input, quantity_input, keterangan_input;
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
            }
        });
    }
}
