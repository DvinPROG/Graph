package com.david.dvinskykh.graph;

import com.sun.javafx.binding.StringFormatter;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class WordCounter {

    public static void getDuplicates(String inputPath, String outputPath) throws IOException {
        Map<String, Long> frequencyMap = Files.lines(Paths.get(inputPath))
                .map(StringUtils::split)
                .flatMap(Arrays::stream)
                .collect(Collectors.groupingByConcurrent(o -> o, Collectors.counting()));
        List<CharSequence> results = frequencyMap
                .entrySet().stream()
                .map(o -> (CharSequence) String.format("%d %s", o.getValue(), o.getKey()))
                .collect(Collectors.toList());
        Files.write(Paths.get(outputPath), results);
    }

}
