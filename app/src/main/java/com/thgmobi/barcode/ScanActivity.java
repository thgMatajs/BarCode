package com.thgmobi.barcode;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        Button btnScan = findViewById(R.id.btnscan);

        final Activity activity = this;

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamaScan(activity);
            }
        });
    }

    private void chamaScan(Activity activity) {
        IntentIntegrator integrator = new IntentIntegrator(activity);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Camera Scan");
        integrator.setCameraId(0);
        integrator.initiateScan();
        integrator.setOrientationLocked(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode,data);

        if (result != null){

            if (result.getContents() != null) {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Erro ao Escanear", Toast.LENGTH_SHORT).show();
            }

        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
