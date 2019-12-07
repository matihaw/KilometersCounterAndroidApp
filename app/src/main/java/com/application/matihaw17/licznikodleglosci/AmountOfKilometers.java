package com.application.matihaw17.licznikodleglosci;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class AmountOfKilometers extends AppCompatActivity {
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.amount_of_kilometers);
        try {
            TextView showAmountOfKilometers = findViewById(R.id.textView8);
            File wholeKilometers = new File(Environment.getExternalStorageDirectory()+File.separator+"LicznikPrzebiegu","wholeKilometers.txt");
            Scanner wholeKilometersScanner = new Scanner(wholeKilometers);
            showAmountOfKilometers.setText(wholeKilometersScanner.nextLine()+"km");
        }catch (Exception e){
            Toast.makeText(context,"Błąd: " + e, Toast.LENGTH_LONG).show();
        }
        Button clearHistoryButton = findViewById(R.id.button5);
        clearHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Czy na pewno chcesz usunąć całą historię?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            File wholeKilometers = new File(Environment.getExternalStorageDirectory()+File.separator+"LicznikPrzebiegu","wholeKilometers.txt");     ///cheack is filer exist
                            try {
                                if(!wholeKilometers.exists()) {
                                    wholeKilometers.createNewFile();    ///create file
                                }
                                FileWriter fileWriter = new FileWriter(wholeKilometers);
                                fileWriter.write("0");
                                fileWriter.close();
                                Toast.makeText(getApplicationContext(), "Historia została wyczyszczona", Toast.LENGTH_SHORT).show();
                                setContentView(R.layout.amount_of_kilometers);
                            }catch (Exception e){
                                Toast.makeText(context,"Błąd: " + e, Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                    builder.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "Historia nie została wyczyszczona", Toast.LENGTH_SHORT).show();
                        }
                    });

                    builder.show();

                }catch (Exception e){
                    Toast.makeText(context,"Błąd: " + e, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
