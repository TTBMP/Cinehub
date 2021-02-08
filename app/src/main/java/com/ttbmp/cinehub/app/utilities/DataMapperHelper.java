package com.ttbmp.cinehub.app.utilities;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class DataMapperHelper {

    private DataMapperHelper() {

    }

    public static <I, O> List<O> mapList(List<I> input, Function<I, O> mapElement) {
        return input.stream()
                .map(mapElement)
                .collect(Collectors.toList());
    }

}
