package com.example.nygren23_facemakerapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class FaceView extends SurfaceView {

    private Face thisFace;
    Paint facePaint = new Paint();
    Paint hairPaint = new Paint();
    Paint eyesPaint = new Paint();
    Paint mouthPaint = new Paint();

    private final float eyeXposition1 = 650.0f;
    private final float eyeXposition2 = 1150.0f;
    private final float eyeYPosition = 550.0f;
    private final float eyeRadius = 70.0f;
    private final float faceCenterX = 900.0f;
    private final float faceCenterY = 700.0f;


    public FaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        thisFace = new Face();



        mouthPaint.setColor(Color.BLACK);

        setBackgroundColor(Color.WHITE);
    }

    /*
    TODO add all components to draw for the face
     */
    @Override
    public void onDraw(Canvas canvas){
        setColors();

        //draw head (P)
        canvas.drawCircle(faceCenterX, faceCenterY, 500.0f, facePaint);

        //draw hair (P)

        //draw eyes (P)
        canvas.drawCircle(eyeXposition1, eyeYPosition, eyeRadius, eyesPaint);
        canvas.drawCircle(eyeXposition2, eyeYPosition, eyeRadius, eyesPaint);

        //draw mouth (c)
        RectF oval = new RectF(eyeXposition1, faceCenterY, eyeXposition2, 1000.0f);
        canvas.drawArc(oval, 0, 180, false, mouthPaint);

    }

    public void drawHair(Canvas canvas, int hairStyle){
        switch(hairStyle){
            case 0:

                break;
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;

            default:
                break;


        }
    }

    public void setColors(){
        facePaint.setColor(thisFace.skinColor.toArgb());
        hairPaint.setColor(thisFace.hairColor.toArgb());
        eyesPaint.setColor(thisFace.eyeColor.toArgb());
    }

    public Face getFace(){
        return thisFace;
    }



}
