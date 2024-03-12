package com.Aqmal.hitungumur;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Mendapatkan data dari Intent yang dikirim dari MainActivity
        String nama = getIntent().getStringExtra("nama");
        String tanggalLahirString = getIntent().getStringExtra("tanggal_lahir");

        // Konversi tanggal lahir dari string ke objek Date
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        Date tanggalLahir = null;
        try {
            tanggalLahir = sdf.parse(tanggalLahirString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Menghitung umur
        Calendar tanggalLahirCal = Calendar.getInstance();
        tanggalLahirCal.setTime(tanggalLahir);
        Calendar now = Calendar.getInstance();
        int umur = now.get(Calendar.YEAR) - tanggalLahirCal.get(Calendar.YEAR);
        int bulan = now.get(Calendar.MONTH) - tanggalLahirCal.get(Calendar.MONTH);
        int hari = now.get(Calendar.DAY_OF_MONTH) - tanggalLahirCal.get(Calendar.DAY_OF_MONTH);
        if (bulan < 0 || (bulan == 0 && hari < 0)) {
            umur--;
        }

        // Menampilkan data di TextView
        TextView textViewNama = findViewById(R.id.tvNama);
        textViewNama.setText("Nama: " + nama);

        TextView textViewUmur = findViewById(R.id.tvUmur);
        textViewUmur.setText("Umur: " + umur + " tahun, " + Math.abs(bulan) + " bulan, " + Math.abs(hari) + " hari.");
    }
}
