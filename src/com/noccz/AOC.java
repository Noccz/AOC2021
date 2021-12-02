package com.noccz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class AOC {
    private static final PrintStream OUT = System.out;

    public static void main(String[] args) {
        solve_aoc2_1();
        solve_aoc2_2();
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
}
