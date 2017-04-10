package com.capalupo.service;

import com.capalupo.flattner.ArrayFlattener;

/**
 * Created by Igor Capalupo on 10/04/2017.
 */
public class Main {

    public static void main(String[] args) {

        Integer[][][] array = new Integer[][][] {{{2},{9}},{{1}, {3,4}, {6,7,8}},{{9}}};

        ArrayFlattener flattner = new ArrayFlattener();
        System.out.println("The flattened version of the Array: {{{2},{9}},{{1}, {3,4}, {6,7,8}},{{9}}} is:\n");
        System.out.println(flattner.flattenIt(array));

    }
}
