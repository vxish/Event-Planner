package com.example.vi5h.staffseventplanner;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;

public class CameraActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler
{
    private static final int REQUEST_CAM = 1;
    private ZXingScannerView scannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if(checkPermission())
            {
                Toast.makeText(CameraActivity.this, "Permission granted.", Toast.LENGTH_LONG).show();
            }
            else
            {
                requestPermission();
            }
        }
    }

    private boolean checkPermission()
    {
        return (ContextCompat.checkSelfPermission
                (CameraActivity.this, CAMERA) == PackageManager.PERMISSION_GRANTED);
    }
    private void requestPermission()
    {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST_CAM);
    }
    public void onRequestPermissionResult(int requestCode, String permission[], int grantResults[])
    {
        switch (requestCode)
        {
            case REQUEST_CAM :
                if(grantResults.length > 0)
                {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted)
                    {
                        Toast.makeText(CameraActivity.this, "Permission Granted", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(CameraActivity.this, "Permission Denied!", Toast.LENGTH_LONG).show();
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                        {
                            if(shouldShowRequestPermissionRationale(CAMERA))
                            {
                                displayAlerMessage("You need to allow access for both permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{CAMERA}, REQUEST_CAM);
                                                }
                                            }
                                        });
                                return;
                            }
                        }
                    }
                }
                break;
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkPermission()){
                if(scannerView == null){
                    scannerView = new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            }
                else
                {
                    requestPermission();
                }
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        scannerView.stopCamera();
    }

    public void displayAlerMessage(String message, DialogInterface.OnClickListener listener)
    {
        new AlertDialog.Builder(CameraActivity.this).setMessage(message)
                .setPositiveButton("OK", listener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    //When the code scanner returns a result this method allows the user to visit or decline
    @Override
    public void handleResult(Result result)
    {
        final String scanResult = result.getText();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                scannerView.resumeCameraPreview(CameraActivity.this);
            }
        });
        builder.setNeutralButton("Vist Link", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(scanResult));
                startActivity(intent);
            }
        });
        builder.setMessage(scanResult);
        AlertDialog alert = builder.create();
        alert.show();
    }
}
