package com.base.集合;

import java.util.Collections;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class VectorMain {

    public static void main(String[] args) {
        Vector vector = new Vector();

        Collections.synchronizedList(vector);

        CopyOnWriteArrayList sy_list = new CopyOnWriteArrayList();


    }
}
