package com.example.kathi.touchevent;

import java.util.ArrayList;

/**
 * Created by Kathi on 10.11.2016.
 */

  class List extends ArrayList <Float> {


    /** Constructor */
    List (){
    };

    /*
    Methoden void add (int index, E element) und E get (int index) sind schon vorgeben
    und werden so wie vorgegeben verwendet und nicht überschrieben
     */

    /** getList()
     * gibt Liste zurück
     * @return Liste
     */
    public List getList (){
        return this;
    }
}