package com.example.nygren23_facemakerapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
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
    private final float faceCenterY = 750.0f;


    public FaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        thisFace = new Face();

        facePaint.setStyle(Paint.Style.FILL);
        eyesPaint.setStyle(Paint.Style.FILL);
        hairPaint.setStyle(Paint.Style.FILL);
        mouthPaint.setColor(Color.BLACK);

        setBackgroundColor(Color.WHITE);

    }

    /*

     */
    @Override
    public void onDraw(Canvas canvas){

        setColors();

        drawHair(canvas, thisFace.hairStyle);
        //draw head (P)
        canvas.drawCircle(faceCenterX, faceCenterY, 500.0f, facePaint);

        //draw hair (P)


        //draw eyes (P)
        canvas.drawCircle(eyeXposition1, eyeYPosition, eyeRadius, eyesPaint);
        canvas.drawCircle(eyeXposition2, eyeYPosition, eyeRadius, eyesPaint);

        //draw mouth (c)
        RectF oval = new RectF(eyeXposition1, faceCenterY, eyeXposition2, 1100.0f);
        canvas.drawArc(oval, 0, 180, false, mouthPaint);

    }

    public void drawHair(Canvas canvas, int hairStyle){
        switch(hairStyle){
            case 0:
                for(int i = 0; i < 1000; i++){
                    canvas.drawLine((400 + (i)),700,  (400 + i), 1600.0f, hairPaint );
                }
                break;
            case 1:

                for(int i = 0; i < 1000; i++){
                    canvas.drawLine((400 + (i)),700,  (400 + i), 70, hairPaint );
                }
                break;
            case 2:
                float verts[] = {
                        400.0f, 700.0f, 900.0f, 70.0f, 1400.0f, 700.0f
                };
                canvas.drawVertices(Canvas.VertexMode.TRIANGLES, verts.length, verts, 0, null, 0, null,   0, null, 0, 0, hairPaint);

                Path path = new Path();
                path.setFillType(Path.FillType.EVEN_ODD);
                path.moveTo(400.0f,700.0f);
                path.lineTo(900.0f,20.0f);
                path.lineTo(1400.0f,700.0f);
                path.close();

                canvas.drawPath(path, hairPaint);
                break;
            case 3:
                //draw no hair for bald
                break;
        }
    }

    public void setColors(){

        facePaint.setARGB(255, ((int) thisFace.skinColor.red()), ((int) thisFace.skinColor.green()), ((int) thisFace.skinColor.blue()));

        hairPaint.setARGB(255, ((int) thisFace.hairColor.red()), ((int)  thisFace.hairColor.green()), ((int) thisFace.hairColor.blue()));

        eyesPaint.setARGB(255, ((int) thisFace.eyeColor.red()), ((int) thisFace.eyeColor.green()), ((int) thisFace.eyeColor.blue()));

    }

    public Face getFace(){
        return thisFace;
    }



}
