/**
 * @author: Blake Nygren
 */

package com.example.nygren23_facemakerapp;

import android.graphics.Color;
import android.os.Build;
import android.util.Log;


public class Face {

    public boolean hairChecked;
    public boolean eyesChecked;
    public boolean skinChecked;
    public Color skinColor;
    public Color eyeColor;
    public Color hairColor;
    public int hairStyle;

    public Face() {
        hairChecked = false;
        eyesChecked = true;
        skinChecked = false;
        hairStyle = 2;
        randomize();
    }

    //randomize values to begin for the face colors needed

    public void randomize(){

        int tempRed;
        int tempGreen;
        int tempBlue;

        tempRed = (int) (Math.random() * 255);
        tempGreen = ((int) (Math.random() * 255));
        tempBlue = (int) (Math.random() * 255);
        skinColor =  Color.valueOf(tempRed, tempGreen, tempBlue);

        tempRed = (int) (Math.random() * 255);
        tempGreen = (int) (Math.random() * 255);
        tempBlue = (int) (Math.random() * 255);
        eyeColor = Color.valueOf(tempRed, tempGreen, tempBlue);

        tempRed = (int) (Math.random() * 255);
        tempGreen = (int) (Math.random() * 255);
        tempBlue = (int) (Math.random() * 255);
        hairColor = Color.valueOf(tempRed, tempGreen, tempBlue);

        hairStyle = (int) ((Math.random() * 4));

    }
}
