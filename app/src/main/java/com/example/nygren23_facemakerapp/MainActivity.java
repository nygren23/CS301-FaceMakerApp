/**
 * @author: Blake Nygren
 */

package com.example.nygren23_facemakerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

//handling all component interactions using this class as the listener
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private Face currFace;
    private FaceView currFaceView;
    public List<String> hairChoices = new ArrayList<String>();
    private SeekBar redSB = null;
    private SeekBar greenSB = null;
    private SeekBar blueSB = null;
    private Spinner hairSpinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //add the hair styles to the spinner
        hairChoices.add("Long");
        hairChoices.add("Spikey");
        hairChoices.add("Curly");
        hairChoices.add("Buzzcut");

        //find XML components by ID and assign them to java variables for reference
        currFaceView = findViewById(R.id.faceView);
        currFace = currFaceView.getFace();

        hairSpinner = findViewById(R.id.hair_spinner);

        RadioButton hairBtn = findViewById(R.id.hair_radio);
        RadioButton eyesBtn = findViewById(R.id.eyes_radio);
        RadioButton skinBtn = findViewById(R.id.skin_radio);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Button quitBtn = findViewById(R.id.quit_button);

        Button randomBtn = findViewById(R.id.randomize);

        redSB = findViewById(R.id.red_seekbar);
        greenSB = findViewById(R.id.green_seekbar);
        blueSB = findViewById(R.id.blue_seekbar);


        //set listeners for components that need them
        //using this class as the listener for all components
        hairSpinner.setOnItemSelectedListener(this);
        hairBtn.setOnClickListener(this);
        eyesBtn.setOnClickListener(this);
        skinBtn.setOnClickListener(this);
        randomBtn.setOnClickListener(this);
        quitBtn.setOnClickListener(this);
        redSB.setOnSeekBarChangeListener(this);
        greenSB.setOnSeekBarChangeListener(this);
        blueSB.setOnSeekBarChangeListener(this);


        radioGroup.clearCheck();
        hairBtn.setChecked(true);
        currFace.hairChecked = true;

        redSB.setProgress((int) currFace.hairColor.red());
        greenSB.setProgress((int) currFace.hairColor.green());
        blueSB.setProgress((int) currFace.hairColor.blue());

        //spinner adapter
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hairChoices);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //set the adapter to the found spinner element
        hairSpinner.setAdapter(spinnerAdapter);
        hairSpinner.setSelection(currFace.hairStyle);

        radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {

                    @Override

                    // The flow will come here when
                    // any of the radio buttons in the radioGroup
                    // has been clicked

                    // Check which radio button has been clicked
                    public void onCheckedChanged(RadioGroup group, int checkedId) {

                        // Get the selected Radio Button
                        RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                        switch (checkedId) {
                            case R.id.hair_radio:
                                redSB.setProgress((int) currFace.hairColor.red());
                                greenSB.setProgress((int) currFace.hairColor.green());
                                blueSB.setProgress((int) currFace.hairColor.blue());
                                Log.i("radio button testing", "hair button checked");
                                currFace.hairChecked = true;
                                currFace.eyesChecked = false;
                                currFace.skinChecked = false;
                                currFaceView.invalidate();
                                break;
                            case R.id.eyes_radio:
                                redSB.setProgress((int) currFace.eyeColor.red());
                                greenSB.setProgress((int) currFace.eyeColor.green());
                                blueSB.setProgress((int) currFace.eyeColor.blue());
                                Log.i("radio button testing", "eyes button checked");
                                currFace.hairChecked = false;
                                currFace.eyesChecked = true;
                                currFace.skinChecked = false;
                                currFaceView.invalidate();
                                break;
                            case R.id.skin_radio:
                                redSB.setProgress((int) currFace.skinColor.red());
                                greenSB.setProgress((int) currFace.skinColor.green());
                                blueSB.setProgress((int) currFace.skinColor.blue());
                                Log.i("radio button testing", "skin button checked");
                                currFace.hairChecked = false;
                                currFace.eyesChecked = false;
                                currFace.skinChecked = true;
                                currFaceView.invalidate();
                                break;

                        }
                    }


                });

    }

    /*
     *When an item is selected, the method
     * will grab the value from it and display it
     * as well as change it in the instance of the Face
     * which is being used as a controller in the mvc
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        currFace.hairStyle = position;
        Log.i("Button testing", "Current Hair Style changed to " + hairChoices.get(position));
    }


    /*
    *if nothing is selected, default
    * to the first object
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        currFace.hairStyle = 0;
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.randomize){
            currFace.randomize();
            if(currFace.hairChecked) {
                currFaceView.invalidate();
                redSB.setProgress((int) currFace.hairColor.red());
                greenSB.setProgress((int) currFace.hairColor.green());
                blueSB.setProgress((int) currFace.hairColor.blue());
            } else if(currFace.eyesChecked) {
                currFaceView.invalidate();
                redSB.setProgress((int) currFace.eyeColor.red());
                greenSB.setProgress((int) currFace.eyeColor.green());
                blueSB.setProgress((int) currFace.eyeColor.blue());
            } else if(currFace.skinChecked) {
                currFaceView.invalidate();
                redSB.setProgress((int) currFace.skinColor.red());
                greenSB.setProgress((int) currFace.skinColor.green());
                blueSB.setProgress((int) currFace.skinColor.blue());
            }
            hairSpinner.setSelection(currFace.hairStyle);
       }

        if(v.getId() == R.id.quit_button){
            finish();
        }


    }


    /*
    TODO add progress changed code for user sliding on SeekBar
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch(seekBar.getId()){
            case R.id.red_seekbar:
                if(currFace.hairChecked)
                    currFace.hairColor = Color.valueOf(progress, currFace.hairColor.green(), currFace.hairColor.blue());
                if(currFace.eyesChecked)
                    currFace.eyeColor = Color.valueOf(progress, currFace.eyeColor.green(), currFace.eyeColor.blue());
                if(currFace.skinChecked)
                    currFace.skinColor = Color.valueOf(progress, currFace.skinColor.green(), currFace.skinColor.blue());
                currFaceView.invalidate();
                break;
            case R.id.green_seekbar:
                if(currFace.hairChecked)
                    currFace.hairColor = Color.valueOf(currFace.hairColor.red(), progress, currFace.hairColor.blue());
                if(currFace.eyesChecked)
                    currFace.eyeColor = Color.valueOf(currFace.eyeColor.red(), progress, currFace.eyeColor.blue());
                if(currFace.skinChecked)
                    currFace.skinColor = Color.valueOf(currFace.skinColor.red(), progress, currFace.skinColor.blue());
                currFaceView.invalidate();
                break;
            case R.id.blue_seekbar:
                if(currFace.hairChecked)
                    currFace.hairColor = Color.valueOf(currFace.hairColor.red(), currFace.hairColor.green(), progress);
                if(currFace.eyesChecked)
                    currFace.eyeColor = Color.valueOf(currFace.eyeColor.red(), currFace.eyeColor.green(), progress);
                if(currFace.skinChecked)
                    currFace.skinColor = Color.valueOf(currFace.skinColor.red(), currFace.skinColor.green(), progress);
                currFaceView.invalidate();
                break;

        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //do nothing
        //do not need to track for this app
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //do nothing
        //do not need to track for this app
    }

}