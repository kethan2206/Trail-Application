package com.example.testing2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.testing2.utilities.PermissionsHelper;
import com.example.testing2.utilities.SettingsManager;


import java.io.IOException;

public class SplashScreenLauncherActivity extends AppCompatActivity implements PermissionsHelper.OnPermissionsResultListener{

    // Utility Class
    private PermissionsHelper mPermissionsHelper = null;



    TextView mReward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Request Permissions
        mPermissionsHelper = new PermissionsHelper(this, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPermissionsHelper.onActivityResult();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPermissionsHelper.onRequestPermissionsResult();
    }

    @Override
    public void onPermissionsGranted() {
        // Load Config
        try {
            App.mConfig = SettingsManager.LoadConfigFile(this);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Could not load config file", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        // Start Service
        startService(new Intent(this, BeaconMonitoringService.class));
        //finish();
    }



}
