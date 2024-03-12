package com.Aqmal.hitungumur;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNama;
    private EditText editTanggalLahir;
    private Button buttonTanggalLahir;
    private Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNama = findViewById(R.id.editTextNama);
        editTanggalLahir = findViewById(R.id.editTanggalLahir);
        buttonTanggalLahir = findViewById(R.id.buttonTanggalLahir);
        buttonNext = findViewById(R.id.buttonNext);

        buttonTanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mendapatkan tanggal sekarang
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                // Membuat DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // Menetapkan tanggal yang dipilih ke EditText
                                calendar.set(year, monthOfYear, dayOfMonth);
                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                                editTanggalLahir.setText(dateFormat.format(calendar.getTime()));
                            }
                        }, year, month, dayOfMonth);

                // Menampilkan DatePickerDialog
                datePickerDialog.show();
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = editTextNama.getText().toString();
                String tanggalLahir = editTanggalLahir.getText().toString();

                // Jika input nama atau tanggal lahir kosong, tampilkan pesan peringatan
                if (nama.isEmpty() || tanggalLahir.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Harap lengkapi semua kolom", Toast.LENGTH_SHORT).show();
                } else {
                    // Buat Intent untuk memulai SecondActivity
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    // Melewatkan data ke SecondActivity
                    intent.putExtra("nama", nama);
                    intent.putExtra("tanggal_lahir", tanggalLahir);
                    // Mulai SecondActivity
                    startActivity(intent);
                }
            }
        });
    }
}
