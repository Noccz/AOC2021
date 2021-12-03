package com.noccz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AOC {
    private static final PrintStream OUT = System.out;

    public static void main(String[] args) {
        solve_aoc2_1();
        solve_aoc2_2();
        solve_aoc3_1();
        solve_aoc3_2();
    }

    public static void solve_aoc2_1() {
        try {
            String filePath = "input/input_aoc2";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            int horizontalValue = 0;
            int depthValue = 0;
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split(" ", 2);
                switch (parts[0]) {
                    case "forward":
                        horizontalValue += Integer.parseInt(parts[1]);
                        break;
                    case "up":
                        depthValue -= Integer.parseInt(parts[1]);
                        break;
                    case "down":
                        depthValue += Integer.parseInt(parts[1]);
                        break;
                }
            }
            reader.close();

            OUT.printf("AOC_2_1 - Combined value of %d and %d is: %d\n", horizontalValue, depthValue, depthValue*horizontalValue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void solve_aoc2_2() {
        try {
            String filePath = "input/input_aoc2";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            int horizontalValue = 0;
            int depthValue = 0;
            int aim = 0;
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split(" ", 2);
                switch (parts[0]) {
                    case "forward":
                        horizontalValue += Integer.parseInt(parts[1]);
                        depthValue += Integer.parseInt(parts[1]) * aim;
                        break;
                    case "up":
                        aim -= Integer.parseInt(parts[1]);
                        break;
                    case "down":
                        aim += Integer.parseInt(parts[1]);
                        break;
                }
            }
            reader.close();

            OUT.printf("AOC_2_2 - Combined value of %d and %d is: %d\n", horizontalValue, depthValue, depthValue*horizontalValue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void solve_aoc3_1() {
        try {
            int nrOfLines = 1000;
            int nrOfBits = 12;
            String filePath = "input/input_aoc3";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            int[][] bitValues = new int[nrOfLines][nrOfBits];
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                int charIndex = 0;
                for (char c : line.toCharArray()) {
                    bitValues[index][charIndex++] = Integer.parseInt(String.valueOf(c));
                }
                index++;
            }
            int[] mostCommonBits = new int[nrOfBits];
            int[] leastCommonBits = new int[nrOfBits];
            for (int i = 0; i < nrOfBits; i++) {
                int nrOfOnes = 0;
                int nrOfZeros = 0;
                for (int j = 0; j < nrOfLines; j++) {
                    if(bitValues[j][i] == 0) {
                        nrOfZeros++;
                    } else {
                        nrOfOnes++;
                    }
                }
                if (nrOfZeros > nrOfOnes) {
                    mostCommonBits[i] = 0;
                    leastCommonBits[i] = 1;
                } else {
                    mostCommonBits[i] = 1;
                    leastCommonBits[i] = 0;
                }
            }
            String gammaBits = IntStream.of(mostCommonBits)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(""));
            String epsilonBits = IntStream.of(leastCommonBits)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(""));
            int gammaRate = Integer.parseInt(gammaBits, 2);
            int epsilonRate = Integer.parseInt(epsilonBits, 2);
            OUT.printf("AOC_3_1 - Power consumption: %d\n", gammaRate*epsilonRate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void solve_aoc3_2() {
        try {
            int nrOfBits = 12;
            String filePath = "input/input_aoc3";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            List<String> bitValueList = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                bitValueList.add(line);
            }
            String oxygenRating = "";
            String co2Rating = "";
            List<String> remainingValues = new ArrayList<>(bitValueList);
            int currentBitPos = 0;
            while (currentBitPos != nrOfBits && remainingValues.size() > 1) {
                int nrOfOnes = 0;
                int nrOfZeros = 0;
                for (String remainingValue : remainingValues) {
                    char[] currentValue = remainingValue.toCharArray();
                    if (currentValue[currentBitPos] == '0') {
                        nrOfZeros++;
                    } else {
                        nrOfOnes++;
                    }
                }
                if (nrOfOnes >= nrOfZeros) {
                    oxygenRating += "1";
                } else {
                    oxygenRating += "0";
                }
                String unfinishedOxygenRating = oxygenRating;
                remainingValues = bitValueList.stream().filter(bit -> bit.startsWith(unfinishedOxygenRating)).collect(Collectors.toList());
                if (remainingValues.size() == 1) {
                    oxygenRating = remainingValues.get(0);
                }
                currentBitPos++;
            }

            remainingValues = new ArrayList<>(bitValueList);
            currentBitPos = 0;
            while (currentBitPos != nrOfBits && remainingValues.size() > 1) {
                int nrOfOnes = 0;
                int nrOfZeros = 0;
                for (String remainingValue : remainingValues) {
                    char[] currentValue = remainingValue.toCharArray();
                    if (currentValue[currentBitPos] == '0') {
                        nrOfZeros++;
                    } else {
                        nrOfOnes++;
                    }
                }
                if (nrOfZeros <= nrOfOnes) {
                    co2Rating += "0";
                } else {
                    co2Rating += "1";
                }

                String unfinishedOxygenRating = co2Rating;
                remainingValues = bitValueList.stream().filter(bit -> bit.startsWith(unfinishedOxygenRating)).collect(Collectors.toList());
                if (remainingValues.size() == 1) {
                    co2Rating = remainingValues.get(0);
                }
                currentBitPos++;
            }

            int oxygenRate = Integer.parseInt(oxygenRating, 2);
            int co2Rate = Integer.parseInt(co2Rating, 2);
            OUT.printf("AOC_3_2 - Life support rating: %d\n", oxygenRate*co2Rate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
