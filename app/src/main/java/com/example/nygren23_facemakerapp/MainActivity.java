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
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, SeekBar.OnSeekBarChangeListener, RadioGroup.OnCheckedChangeListener {

    private Face currFace;
    private FaceView currFaceView;
    public List<String> hairChoices = new ArrayList<String>();
    private SeekBar redSB;
    private SeekBar greenSB;
    private SeekBar blueSB;
    private Spinner hairSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //add the hair styles to the spinner
        hairChoices.add("Long");
        hairChoices.add("Flat Top");
        hairChoices.add("Mohawk");
        hairChoices.add("Bald");

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
        radioGroup.setOnCheckedChangeListener(this);


        //sets the hair button to default selected with its colors showing on the seekbars
        hairBtn.setChecked(true);
        redSB.setProgress((int) currFace.hairColor.red());
        greenSB.setProgress((int) currFace.hairColor.green());
        blueSB.setProgress((int) currFace.hairColor.blue());

        //spinner adapter for hairstyles
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hairChoices);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //set the adapter to the found spinner element
        hairSpinner.setAdapter(spinnerAdapter);
        hairSpinner.setSelection(currFace.hairStyle);



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

    }


    /*
     *if nothing is selected, default
     * to the first object
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        currFace.hairStyle = 0;
    }

    /*
    *handles which button (random or quit) is clicked and handles accordingly
     */
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.randomize) {
            currFace.randomize();
            //displays relevant information for whichever radio button is selected at the time of randomization
            if (currFace.hairChecked) {
                redSB.setProgress((int) currFace.hairColor.red());
                greenSB.setProgress((int) currFace.hairColor.green());
                blueSB.setProgress((int) currFace.hairColor.blue());

            } else if (currFace.eyesChecked) {

                redSB.setProgress((int) currFace.eyeColor.red());
                greenSB.setProgress((int) currFace.eyeColor.green());
                blueSB.setProgress((int) currFace.eyeColor.blue());

            } else if (currFace.skinChecked) {
                redSB.setProgress((int) currFace.skinColor.red());
                greenSB.setProgress((int) currFace.skinColor.green());
                blueSB.setProgress((int) currFace.skinColor.blue());

            }
            hairSpinner.setSelection(currFace.hairStyle);
            currFaceView.invalidate();
        }

        if (v.getId() == R.id.quit_button) {
            finish();
        }


    }


    /*
    *ensures that the user adjusted the progress bar and then sets the selected element's color
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(fromUser) {
            switch (seekBar.getId()) {
                case R.id.red_seekbar:
                    if (currFace.hairChecked) {
                        currFace.hairColor = Color.valueOf(progress, currFace.hairColor.green(), currFace.hairColor.blue());
                    } else if (currFace.eyesChecked) {
                        currFace.eyeColor = Color.valueOf(progress, currFace.eyeColor.green(), currFace.eyeColor.blue());
                    } else if (currFace.skinChecked) {
                        currFace.skinColor = Color.valueOf(progress, currFace.skinColor.green(), currFace.skinColor.blue());
                    }
                    break;
                case R.id.green_seekbar:
                    if (currFace.hairChecked) {
                        currFace.hairColor = Color.valueOf(currFace.hairColor.red(), progress, currFace.hairColor.blue());
                    } else if (currFace.eyesChecked) {
                        currFace.eyeColor = Color.valueOf(currFace.eyeColor.red(), progress, currFace.eyeColor.blue());
                    } else if (currFace.skinChecked) {
                        currFace.skinColor = Color.valueOf(currFace.skinColor.red(), progress, currFace.skinColor.blue());
                    }
                    break;
                case R.id.blue_seekbar:
                    if (currFace.hairChecked) {
                        currFace.hairColor = Color.valueOf(currFace.hairColor.red(), currFace.hairColor.green(), progress);
                    } else if (currFace.eyesChecked) {
                        currFace.eyeColor = Color.valueOf(currFace.eyeColor.red(), currFace.eyeColor.green(), progress);
                    } else if (currFace.skinChecked) {
                        currFace.skinColor = Color.valueOf(currFace.skinColor.red(), currFace.skinColor.green(), progress);
                    }
                    break;
            }
            currFaceView.invalidate();
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

    /*
    *handles all the radio buttons as a radiogroup
    * checks which radio button was clicked with a switch
    * and sets the model values/seekbar values to reflect it
    * after setting, redraws face with new colors
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.hair_radio:
                redSB.setProgress((int) currFace.hairColor.red());
                greenSB.setProgress((int) currFace.hairColor.green());
                blueSB.setProgress((int) currFace.hairColor.blue());

                currFace.hairChecked = true;
                currFace.eyesChecked = false;
                currFace.skinChecked = false;

                break;
            case R.id.eyes_radio:
                redSB.setProgress((int) currFace.eyeColor.red());
                greenSB.setProgress((int) currFace.eyeColor.green());
                blueSB.setProgress((int) currFace.eyeColor.blue());

                currFace.hairChecked = false;
                currFace.eyesChecked = true;
                currFace.skinChecked = false;

                break;
            case R.id.skin_radio:
                redSB.setProgress((int) currFace.skinColor.red());
                greenSB.setProgress((int) currFace.skinColor.green());
                blueSB.setProgress((int) currFace.skinColor.blue());

                currFace.hairChecked = false;
                currFace.eyesChecked = false;
                currFace.skinChecked = true;

                break;

        }
        currFaceView.invalidate();
    }
}