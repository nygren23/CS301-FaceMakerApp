/**
 * @author: Blake Nygren
 */

package com.example.nygren23_facemakerapp;

public class Face {

    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle;

    public Face(){
        randomize();
    }

    //randomize values to begin for the face colors needed
    //TODO convert numbers to hex?
    public void randomize(){
        skinColor = (int)(Math.random() * 255);
        eyeColor = (int)(Math.random() * 255);
        hairColor = (int)(Math.random() * 255);
        hairStyle = (int)(Math.random() * 4) + 1;

    }
}
