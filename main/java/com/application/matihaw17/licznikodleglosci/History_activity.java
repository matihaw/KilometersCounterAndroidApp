package com.application.matihaw17.licznikodleglosci;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import java.io.FileWriter;
import java.util.Scanner;

public class History_activity extends AppCompatActivity {
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);



        try {
            File courseDifferenceFile = new File(Environment.getExternalStorageDirectory() + File.separator + "LicznikPrzebiegu", "courseDifference.txt");     ///cheack is filer exist
            if(!courseDifferenceFile.exists()) {
                courseDifferenceFile.createNewFile();    ///create file
            }
            Scanner fileReader = new Scanner(courseDifferenceFile);
            TextView allCoursesUpToDate = findViewById(R.id.textView6);
            while (fileReader.hasNextLine()){
                allCoursesUpToDate.append(System.lineSeparator());
                allCoursesUpToDate.append(fileReader.nextLine());

            }
            fileReader.close();
        }catch (Exception e ){
            Toast.makeText(context,"Błąd: " + e, Toast.LENGTH_LONG).show();
        }
        Button clearHistoryButton = findViewById(R.id.button6);
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
                            File courseDifferenceFile = new File(Environment.getExternalStorageDirectory()+File.separator+"LicznikPrzebiegu","courseDifference.txt");     ///cheack is filer exist
                            try {
                                if(!courseDifferenceFile.exists()) {
                                    courseDifferenceFile.createNewFile();    ///create file
                                }
                                FileWriter fileWriter = new FileWriter(courseDifferenceFile);
                                fileWriter.write("");
                                fileWriter.close();
                                Toast.makeText(getApplicationContext(), "Historia została wyczyszczona", Toast.LENGTH_SHORT).show();
                                setContentView(R.layout.history_activity);
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
