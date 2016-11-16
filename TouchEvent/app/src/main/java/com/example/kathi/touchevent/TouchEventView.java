package com.example.kathi.touchevent;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Kathi on 09.11.2016.
 */

public class TouchEventView extends View {

    private Paint paint =new Paint();
    private Path path= new Path();

   // PathMeasure pm;

    /* neue Liste wird hier erstellt
     während dem Zeichnen werden die y-Wert hier eingetragen
     */
    List l = new List();
    int index =0;
    /** Constructor
     * legt Werte des Pinsels fest
     * @param ctx  l
     * @param attrs xy
     */
    public TouchEventView(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);
    }

    @Override
    protected void onDraw (Canvas canvas){
    canvas.drawPath(path, paint);

    }


    @Override
    public boolean onTouchEvent (MotionEvent event){
        float xPos=event.getX();
        float yPos=event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                // löscht den alten pfad, sobald man neu aufsetzt, d.h. man nochmal zeichnen möchte --> warum löschen button noch?
                path.reset();
                path.moveTo(xPos,yPos);
                einfuegen(yPos);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(xPos,yPos);
                einfuegen(yPos);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:return false;
        }
        invalidate();
      //  pm= new PathMeasure(path, false);
        return true;
    }

    /** einfugen ()
     * fügt mitgegeben y WErt an nächste IndexStelle
     * @param f einzutragender yWert
     */
    public void einfuegen (float f){
        l.add(index, f);
        index++;
    }

    /** gibt aktelle Liste zurück
     * @return Liste
     */
    public List getList (){
        return l;
    }

    public void loescheView(){
        path.reset();
        invalidate();
        index=0;
        l.clear();

    }




}
