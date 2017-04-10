package com.capalupo.flattner;


import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by Igor Capalupo on 07/04/2017.
 */

public class ArrayFlattenerTest {

    private static ArrayFlattener flattener;




    @BeforeAll
    public static void setup(){
        flattener = new ArrayFlattener();
    }


    @TestFactory
    public Stream<DynamicTest> flattenerDynamicTests() {

        List<Object> inData = new ArrayList<>();
        inData.add(new Integer[][][] {{{2},{9}},{{1}, {3,4}, {6,7,8}},{{9}}});
        inData.add(new Integer[0]);

        List<String> outData = new ArrayList<>();
        outData.add("2,9,1,3,4,6,7,8,9");
        outData.add("");

        return inData.stream().map( array -> DynamicTest.dynamicTest("Test flattening" + array, () ->{
            int index = inData.indexOf(array);
            assertEquals(outData.get(index), flattener.flattenIt(array));
        }));


    }

    @Test
    public void arrayWithANoIntegerValue(){

        Object[] arrayWithNoIntgerValue = {1,"2", 2};
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            flattener.flattenIt(arrayWithNoIntgerValue);
        });

        assertEquals("The informed array contains a not Integer element", exception.getMessage());

    }

}