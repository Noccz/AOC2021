package com.noccz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AOC6 extends AOC {
    public static void solveAOC_6_1() {
        try {
            String filePath = "input/input_aoc6";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            String[] inputStrings;
            List<Integer> inputNumbers = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                inputStrings = line.split(",");
                for (String input : inputStrings) {
                    inputNumbers.add(Integer.parseInt(input));
                }
            }
            int nrOfDays = 80;
            for (int i = 0; i < nrOfDays; i++) {
                List<Integer> newFish = new ArrayList<>();
                for (int j = 0; j < inputNumbers.size(); j++) {
                    int currentFishTimer = inputNumbers.get(j);
                    if (currentFishTimer == 0) {
                        currentFishTimer = 6;
                        newFish.add(8);
                    } else {
                        currentFishTimer--;
                    }
                    inputNumbers.set(j, currentFishTimer);
                }
                inputNumbers.addAll(newFish);
            }
            OUT.printf("AOC_6_1 - Number of Fish after %d days: %d\n", nrOfDays, inputNumbers.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void solveAOC_6_2() {
        try {
            String filePath = "input/input_aoc6";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            Map<Integer, Long> internalFishTimer = new HashMap<>();
            for (int i = 0; i < 9; i++) {
                internalFishTimer.put(i, 0L);
            }
            String[] inputStrings = reader.readLine().split(",");
            for (String inputString : inputStrings) {
                int inputValue = Integer.parseInt(inputString);
                internalFishTimer.put(inputValue, internalFishTimer.get(inputValue) + 1L);
            }

            int nrOfDays = 256;
            for (int i = 0; i < nrOfDays; i++) {
                Long temp = internalFishTimer.get(0);
                internalFishTimer.put(0, internalFishTimer.get(1));
                internalFishTimer.put(1, internalFishTimer.get(2));
                internalFishTimer.put(2, internalFishTimer.get(3));
                internalFishTimer.put(3, internalFishTimer.get(4));
                internalFishTimer.put(4, internalFishTimer.get(5));
                internalFishTimer.put(5, internalFishTimer.get(6));
                internalFishTimer.put(6, temp + internalFishTimer.get(7));
                internalFishTimer.put(7, internalFishTimer.get(8));
                internalFishTimer.put(8, temp);
            }
            OUT.printf("AOC_6_2 - Number of Fish after %d days: %d\n", nrOfDays, internalFishTimer.values().stream().reduce(0L, Long::sum));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
