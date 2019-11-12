package com.example.vi5h.staffseventplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnViewSU = findViewById(R.id.btnViewSU);
        Button btnViewClb = findViewById(R.id.btnViewClb);
        Button btnViewSoc = findViewById(R.id.btnViewSoc);
        Button btnScanCodes = findViewById(R.id.btnScanCode);

        btnViewSU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SUActivity.class);
                startActivity(intent);
            }
        });

        btnViewClb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ClubsActivity.class);
                startActivity(intent);
            }
        });

        btnViewSoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SocActivity.class);
                startActivity(intent);
            }
        });

        btnScanCodes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });
    }

}
