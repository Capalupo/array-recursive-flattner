package com.capalupo.flattner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Utilitary class to process multi-dimensional numeric arrays (Integer only) and return a textual representaion of it.
 *
 *
 * @author Igor Capalupo
 * @since 1.0
 */
public class ArrayFlattener {

    private RecursiveFlattener<Function> recursiveFlattener = new RecursiveFlattener<>();
    private List<String> flattenedNumbers;

    public ArrayFlattener(){
        recursiveFlattener.flattenFunction = o -> {
            if (o.getClass().isArray()){
                Arrays.stream((Object[])o).map(recursiveFlattener.flattenFunction).count();
            }else {
                if (o instanceof Integer) {
                    flattenedNumbers.add(String.valueOf(o));
                }else{
                    throw new IllegalArgumentException("The informed array contains a not Integer element");
                }
            }
            return flattenedNumbers.stream();
        };
    }

    /**
     *
     * @param array
     * @return String - array's elements as a flat text.
     */

    public String flattenIt(Object array){
        flattenedNumbers = new ArrayList<>();
        Arrays.stream((Object[])array)
               .flatMap(this.recursiveFlattener.flattenFunction).count();

        return flattenedNumbers.stream().collect(Collectors.joining(","));
    }

    class RecursiveFlattener<F> {
        public F flattenFunction;
    }


}
