package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    private String sourceUnitsSpinnerValue;
    private String destinationUnitsSpinnerValue;

    //Gets value of input field and converts to int
    private float getInput(EditText input){
        String inputValue = input.getText().toString();
        return (float) (Integer.parseInt(inputValue));
    }

    //Handles conversion logic. Returns -1 if input is 0 and 0 if invalid conversion. Otherwise returns float value of conversion.
    public float convert(String sourceUnitsSpinnerValue, String destinationUnitsSpinnerValue, float input){


        if(sourceUnitsSpinnerValue.equals("INCHES") && destinationUnitsSpinnerValue.equals("CENTIMETRES")){
            return (float) (input * 2.54);

        }
        else if(sourceUnitsSpinnerValue.equals("CENTIMETRES") && destinationUnitsSpinnerValue.equals("INCHES")){
            return (float) (input / 2.54);

        }
        else if(sourceUnitsSpinnerValue.equals("POUNDS") && destinationUnitsSpinnerValue.equals("KILOGRAMS")){
            return (float) (0.453592 / input);

        }
        else if(sourceUnitsSpinnerValue.equals("KILOGRAMS") && destinationUnitsSpinnerValue.equals("POUNDS")){
            return (float) (0.453592 * input);

        }
        else if(sourceUnitsSpinnerValue.equals("FAHRENHEIT") && destinationUnitsSpinnerValue.equals("CELSIUS")){
            return (float) ((input - 32)/1.8);
        }
        else if(sourceUnitsSpinnerValue.equals("CELSIUS") && destinationUnitsSpinnerValue.equals("FAHRENHEIT")){
            return (float) ((input * 1.8 + 32));

        }else{
            return 0;
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //declare application elements
        Spinner sourceUnitsSpinner = findViewById(R.id.sourceUnit);
        Spinner destinationUnitsSpinner = findViewById(R.id.destinationUnit);
        Button convertButton = findViewById(R.id.convertButton);
        EditText input = findViewById(R.id.sourceUnitAmount);
        TextView result = findViewById(R.id.result);


        //connect string-array elements to spinner
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.unitsArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        sourceUnitsSpinner.setAdapter(adapter);
        destinationUnitsSpinner.setAdapter(adapter);


        //set event listener to get value of source spinner
        sourceUnitsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sourceUnitsSpinnerValue = sourceUnitsSpinner.getSelectedItem().toString().toUpperCase();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //set event listener to get value for destinationUnits spinner
        destinationUnitsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                destinationUnitsSpinnerValue = destinationUnitsSpinner.getSelectedItem().toString().toUpperCase();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //event listener to invoke conversion once button is clicked.
        convertButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                float inputValue = getInput(input);
                String output = Float.toString(convert(sourceUnitsSpinnerValue, destinationUnitsSpinnerValue, inputValue));
                result.setText(output);
            }

        });








    }
}