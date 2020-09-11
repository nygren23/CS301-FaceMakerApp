/**
 * @author: Blake Nygren
 */

package com.example.nygren23_facemakerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find XML spinner
        Spinner spinner = findViewById(R.id.spinner);

        //set spinner listener to be this activity
        spinner.setOnItemSelectedListener(this);

        List<String> hairChoices = new ArrayList<String>();

        hairChoices.add("Long");
        hairChoices.add("Spikey");
        hairChoices.add("Curly");
        hairChoices.add("Buzzcut");

        //spinner adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hairChoices);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //set the adapter to the found spinner element
        spinner.setAdapter(adapter);
    }

    /**
     *When an item is selected, the method
     * will grab the value from it and display it
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //no use for yet
    }
}