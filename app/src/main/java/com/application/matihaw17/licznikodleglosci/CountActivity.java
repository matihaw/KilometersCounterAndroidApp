package com.application.matihaw17.licznikodleglosci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CountActivity extends AppCompatActivity {
    Context context=this;


    public void refreshCurrentCourseAmount(){
        setContentView(R.layout.count_activity);
        EditText initialCourseAutifill=findViewById(R.id.editText);
        try{
            File initialCourseFile = new File(Environment.getExternalStorageDirectory()+File.separator+"LicznikPrzebiegu","initialCourse.txt");     ///file link
            Scanner fileReader = new Scanner(initialCourseFile);
            String courseHint = fileReader.next();
            fileReader.close();
            initialCourseAutifill.setHint("Obecny przebieg: " + courseHint + "km");
        }catch (Exception e){
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        refreshCurrentCourseAmount();
        Button saveInitialCourseValue = findViewById(R.id.button10);
        saveInitialCourseValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    EditText initialCourseInput = findViewById(R.id.editText);
                    String initialCourseValueText = initialCourseInput.getText().toString();
                    File initialCourseFile = new File(Environment.getExternalStorageDirectory()+File.separator+"LicznikPrzebiegu","initialCourse.txt");  ///cheack is filer exist
                    if(!initialCourseFile.exists()){
                        initialCourseFile.createNewFile();  ///create file

                    }
                        FileWriter fileWriter = new FileWriter(initialCourseFile); ///writing to file Initial Course value as String
                        fileWriter.write(initialCourseValueText);
                        Toast.makeText(context,"Przebieg został zapisany pomyślnie", Toast.LENGTH_LONG).show();
                        fileWriter.close();
                        refreshCurrentCourseAmount();

                }catch (Exception e){
                    Toast.makeText(context,"Błąd: " + e,Toast.LENGTH_LONG).show();      ///exceptions
                }
            }
        });
        final Button countDifference = findViewById(R.id.button);
        countDifference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    File initialCourseFile = new File(Environment.getExternalStorageDirectory()+File.separator+"LicznikPrzebiegu","initialCourse.txt");     ///file link
                    Scanner fileReader = new Scanner(initialCourseFile);
                    double initialCourseValue = Double.valueOf(fileReader.next());
                    fileReader.close();
                    File courseDifferenceFile = new File(Environment.getExternalStorageDirectory()+File.separator+"LicznikPrzebiegu","courseDifference.txt");     ///cheack is filer exist
                    if(!courseDifferenceFile.exists()){
                        courseDifferenceFile.createNewFile();    ///create file
                    }

                    EditText finalCourseInput = findViewById(R.id.editText2);
                    Double finalCourseValue = Double.valueOf(finalCourseInput.getText().toString());        ///final course value
                    Double courseDifference = finalCourseValue - initialCourseValue;
                    FileWriter fileWriter = new FileWriter(initialCourseFile);                              ///update amount of kimometers
                    fileWriter.write(Double.toString(finalCourseValue));
                    fileWriter.close();

                    ///refreshCurrentCourseAmount();

                    TextView courseDifferenceTextView = findViewById(R.id.textView3);
                    Date date = new Date();                                                                 ///get Curr data
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");                ///format date
                    fileWriter = new FileWriter(courseDifferenceFile, true);
                    fileWriter.write("Przejechałaś: " + Double.toString(courseDifference)+" km, dnia: "+ formatter.format(date) + System.lineSeparator());      ///write to file amount fo kilometers with date
                    courseDifferenceTextView.setText("Przejechałaś teraz: " + Double.toString(courseDifference) + "km");    ///display amount of kilometers
                    fileWriter.close();

                    File wholeKilometers = new File(Environment.getExternalStorageDirectory()+File.separator+"LicznikPrzebiegu","wholeKilometers.txt");
                    if(!wholeKilometers.exists()){
                        wholeKilometers.createNewFile();
                        fileWriter = new FileWriter(wholeKilometers);
                        fileWriter.append('0');
                        fileWriter.close();
                    }
                    fileReader = new Scanner(wholeKilometers);

                    double currentKilometersAmount = Double.valueOf(fileReader.next());
                    double finnalKilometersAmount = currentKilometersAmount + courseDifference;
                    fileWriter = new FileWriter(wholeKilometers);
                    fileWriter.write(Double.toString(finnalKilometersAmount));
                    fileWriter.close();
                    fileReader.close();

                }catch (NoSuchElementException e){
                    Toast.makeText(context,"Nie podałas wszystkich danych" , Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(context,"Błąd: " + e, Toast.LENGTH_LONG).show();
                }

            }

        });
    }
}
