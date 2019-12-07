package com.application.matihaw17.licznikodleglosci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    Context context = this;
    final String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                ///premission granted
            }
            else{
                Toast.makeText(this,"Uprawnienia nie zostały nadane",Toast.LENGTH_LONG).show();
            }
        }
        if(requestCode==2){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                ///premission granted
            }else{
                Toast.makeText(this,"Uprawnienia nie zostały nadane",Toast.LENGTH_LONG).show();
            }
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        File appDirectory = new File(Environment.getExternalStorageDirectory()+File.separator+"LicznikPrzebiegu");
        if(!appDirectory.exists()){
            appDirectory.mkdir();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)  != PackageManager.PERMISSION_GRANTED){ ///Check is permissions are not granted
            requestPermissions(permission,1);   ///ask for permission
        }
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){ ///Check is permissions are not granted
            requestPermissions(permission,2);      ///ask for permission
        }

        Button buttonCount = findViewById(R.id.button2);
        buttonCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent countActivity = new Intent(context,CountActivity.class);
                startActivity(countActivity);
            }
        });
        Button buttonHistory = findViewById(R.id.button3);
        buttonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyActivity = new Intent(context, History_activity.class);
                startActivity(historyActivity);
            }
        });
        Button buttomAmountOfKilometers = findViewById(R.id.button4);
        buttomAmountOfKilometers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent amountOfKilometers = new Intent(context,AmountOfKilometers.class);
                startActivity(amountOfKilometers);
            }
        });
    }

}






