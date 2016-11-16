package com.example.kathi.touchevent;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.Touch;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity_layout);
       // setContentView(new TouchEventView(this,null));
       final TouchEventView2 vt = (TouchEventView2) findViewById(R.id.vt);
        //vt = new TouchEventView( this,null);


       final Button b1= (Button) findViewById(R.id.btn_pruefen);
        final Button b2=(Button) findViewById(R.id.btn_loeschen);
        final TextView txt=(TextView) findViewById(R.id.txt);
        // ***************************PROBLEM******************
        // Liste aus TouchEventView lesen mit getList()
       final List l= vt.getList();
        //int index= 0;





        b1.setOnClickListener( new View.OnClickListener() {
            public void onClick (View view){

                pruefen(l, b1, txt);
                //b1.setBackgroundColor(Color.GREEN);
            }});

        b2.setOnClickListener (new View.OnClickListener(){
            public void onClick(View view){
                vt.loescheView();
                b2.clearAnimation();
                txt.setText("");

            }
        });



    }//Ende onCreate

    void pruefen ( List l, Button b, TextView t){
        // liest die anzahl der Elemente der LIste aus
        int anzahl= l.size();
        // STartindex, bei dem aus der Liste gelesen wird (ACHTUNG: Arrays starten bei 0!)
        int index=0;
        // Gibt die Anzahl der Elemente der Liste als STring aus
        String s="anzahl: "+anzahl+" | ";
        // läuft jedes Element durch, da Array bei 0 anfängt 'kleiner als' benutzen
        while (index <anzahl) {
            //liest den WErt an der jeweiligen index-Stelle in der Liste aus
            float wert = l.get(index);
            // fügt diesen Wert zum STring hinzu
            s = s + String.valueOf(wert) + " | ";
            index++;
        }
        // gibt die WErte in der Liste zu Testzwecken als TExt aus
        t.setText (s);
    }
}
