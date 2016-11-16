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

public class TouchEventView2 extends View {

    private Paint paint =new Paint();
    private Path path= new Path();

    PathMeasure pm;
    float pos []=  new float [2];
    float distance;
    float length;
    float step;

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
    public TouchEventView2(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);

        distance =0;

    }

    @Override
    protected void onDraw (Canvas canvas) {

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
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(xPos,yPos);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:return false;
        }
        invalidate();
       einfuegen (path);
        return true;
    }

    /** einfugen ()
     * fügt mitgegeben y WErt an nächste IndexStelle
     * @param p einzutragender yWert
     */
    public void einfuegen (Path p){
        // alte Inhalte der Liste löschen
        l.clear();
        //Startwert bzw. laufenden WErt auf 0 setzten
        distance=0;
        // Eintrag in die Liste sollen bei 0 beginnen
        index=0;
        // Der übergebene Path wir dem Pathmeasure übergeben
        pm=new PathMeasure(p,true);
        //liest die Länge des Pfades aus
        length =pm.getLength();
        //Step in dem die WErte ausgelesen werden sollen, geht hier 100f auf dem pfad entlang
        step=length/10;
        //TEst, d.h. erster Wert der angezeigt wird ist die länge des pfades
        l.add (index, length);
        index++;
        while (distance <= length){
            // speichert die x-Koordinate an STelle pos[0]
            // und die y-Koordinate an Stelle pos[1]
            pm.getPosTan(distance,pos,null);
            //speichert nur den y-WErt in die Liste
            // x-WErt wird durch step vorgegeben
            //FRAGE: Path beginnt nciht immer gleich, x-Wert richtet sich an gemalten path nicht am koordniatensystem!?
            l.add (index, pos [0]);
            index++;
           distance +=step;
      }

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
