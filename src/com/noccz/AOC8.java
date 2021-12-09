package com.noccz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC8 extends AOC {
    public static void solveAOC_8_1() {
        try {
            String filePath = "input/input_aoc8";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            int nrOfUniquePatterns = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] inputValues = line.substring(line.indexOf("| ")).split(" ");
                for (String inputValue : inputValues) {
                    switch (inputValue.length()) {
                        case 2:
                        case 3:
                        case 4:
                        case 7:
                            nrOfUniquePatterns++;
                            break;
                    }
                }
            }
            OUT.printf("AOC_8_1: Number of unique pattern matches: %d", nrOfUniquePatterns);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
