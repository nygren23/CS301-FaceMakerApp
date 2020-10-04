/**
 * @author: Blake Nygren
 */

package com.example.nygren23_facemakerapp;

import android.graphics.Color;
import android.os.Build;
import android.util.Log;


public class Face {


    public boolean hairChecked = false;
    public boolean eyesChecked = false;
    public boolean skinChecked = false;
    public Color skinColor = new Color();
    public Color eyeColor = new Color();
    public Color hairColor = new Color();
    public int hairStyle = 0;

    public Face() {
        randomize();
    }

    //randomize values to begin for the face colors needed
    //TODO convert numbers to hex?

    public void randomize(){

        int tempRed;
        int tempGreen;
        int tempBlue;

        tempRed = (int) (Math.random() * 255);
        tempGreen = ((int) (Math.random() * 255));
        tempBlue = (int) (Math.random() * 255);
        skinColor =  Color.valueOf(tempRed, tempGreen, tempBlue);
       Log.i("color testing", "new skin color" + skinColor.toString());

        tempRed = (int) (Math.random() * 255);
        tempGreen = (int) (Math.random() * 255);
        tempBlue = (int) (Math.random() * 255);
        eyeColor = Color.valueOf(tempRed, tempGreen, tempBlue);
       Log.i("color testing", "new eye color" + eyeColor.toString());

        tempRed = (int) (Math.random() * 255);
        tempGreen = (int) (Math.random() * 255);
        tempBlue = (int) (Math.random() * 255);
        hairColor = Color.valueOf(tempRed, tempGreen, tempBlue);
       Log.i("color testing", "new hair color" + hairColor.toString());

        hairStyle = (int) ((Math.random() * 4));

    }
}
