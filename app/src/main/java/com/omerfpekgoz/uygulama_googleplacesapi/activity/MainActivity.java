package com.omerfpekgoz.uygulama_googleplacesapi.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.omerfpekgoz.uygulama_googleplacesapi.R;
//Verileri almak için VOLLEY kütüphanesi kullandık
//Belirtiğiniz	enlem	ve	boylama	göre	çevrenizdeki	mekanları	size	veren	bir	servistir.
// • Web	servis	ile	çalışır.
// • Bunu	kullanabilmek	için	API	KEY	gerekir.


public class MainActivity extends AppCompatActivity implements LocationListener {

    private EditText txtEnlem, txtBoylam;
    private Button btnKonumAl, btnGit;

    private String konumSaglayici = "gps";
    private LocationManager locationManager;
    private int izinKontrol;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEnlem = findViewById(R.id.txtEnlem);
        txtBoylam = findViewById(R.id.txtBoylam);
        btnKonumAl = findViewById(R.id.btnKonumAl);
        btnGit = findViewById(R.id.btnGit);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        btnGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String latitude = txtEnlem.getText().toString().trim();
                String longitude = txtBoylam.getText().toString().trim();

                Intent intent = new Intent(MainActivity.this, PlacesActivity.class);
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                startActivity(intent);

            }
        });

        btnKonumAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                izinKontrol = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
                if (izinKontrol != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);

                } else {

                    Location location = locationManager.getLastKnownLocation(konumSaglayici);
                    if (location != null) {
                        onLocationChanged(location);

                    } else {
                        Toast.makeText(getApplicationContext(), "HATA", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });


    }

    @Override
    public void onLocationChanged(Location location) {

        Double latitude = location.getLatitude();  //Enlem
        Double longitude = location.getLongitude(); //Boylam

        Intent intent = new Intent(MainActivity.this, PlacesActivity.class);
        intent.putExtra("latitude", String.valueOf(latitude));
        intent.putExtra("longitude", String.valueOf(longitude));
        startActivity(intent);


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) { //Kullanıcı izin verdi ise
        if (requestCode == 100) {

            izinKontrol = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){

                Toast.makeText(getApplicationContext(), "İZİN VERİLDİ", Toast.LENGTH_SHORT).show();
                Location location = locationManager.getLastKnownLocation(konumSaglayici);
                if (location != null) {
                    onLocationChanged(location);

                } else {
                    Toast.makeText(getApplicationContext(), "HATA", Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(getApplicationContext(), "İZİN VERİLMEDİ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
