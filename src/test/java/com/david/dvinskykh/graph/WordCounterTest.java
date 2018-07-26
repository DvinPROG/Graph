package com.david.dvinskykh.graph;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class WordCounterTest {

    @Test
    public void getFrequentWords() throws IOException {
        List<String> words = Arrays.asList("First Second", "Third First");
        File inputFile = File.createTempFile("input", "temp");
        File outputFile = File.createTempFile("input", "temp");
        Files.write(inputFile.toPath(), words);
        WordCounter.getDuplicates(inputFile.getPath(), outputFile.getPath());
        Map<String, Long> frequentWords = Files.lines(outputFile.toPath())
                .collect(Collectors.toMap(k -> k.split(" ")[1], v -> Long.valueOf(v.split(" ")[0])));
        assertThat(frequentWords.get("First"), Is.is(2L));
        assertThat(frequentWords.get("Second"), Is.is(1L));
        assertThat(frequentWords.get("Third"), Is.is(1L));
    }

}