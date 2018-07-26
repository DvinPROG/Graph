package com.david.dvinskykh.graph;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordCounter {

    public static void getDuplicates(String inputPath, String outputPath) throws IOException {
        List<Map.Entry<String, Long>> frequencyMap = Files.lines(Paths.get(inputPath))
                .map(StringUtils::split)
                .flatMap(Arrays::stream)
                .collect(Collectors.groupingByConcurrent(o -> o, Collectors.counting()))
                .entrySet().stream()
                .sorted(Comparator.comparing(WordCounter::negateMapValue).thenComparing(Map.Entry::getKey))
                .collect(Collectors.toList());
        List<CharSequence> results = frequencyMap.stream()
                .map(o -> (CharSequence) String.format("%d %s", o.getValue(), o.getKey()))
                .collect(Collectors.toList());
        Files.write(Paths.get(outputPath), results);
    }

    private static Long negateMapValue(Map.Entry<String, Long> stringLongPair) {
        return Math.negateExact(stringLongPair.getValue());
    }

}
