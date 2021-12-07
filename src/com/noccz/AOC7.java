package com.noccz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AOC7 extends AOC {
    public static void solveAOC_7_1() {
        try {
            String filePath = "input/input_aoc7";
            String inputLine = new BufferedReader(new FileReader(filePath)).readLine();
            List<Integer> positions = Stream.of(inputLine.split(",")).map(Integer::parseInt).sorted().collect(Collectors.toList());
            AtomicInteger fuelConsumption = new AtomicInteger();
            AtomicInteger lowestFuelConsumption = new AtomicInteger(Integer.MAX_VALUE);
            List<Integer> uniquePositions = positions.stream().distinct().collect(Collectors.toList());
            uniquePositions.forEach(uniquePosition -> {
                positions.forEach(position -> {
                    if (position >= uniquePosition) {
                        fuelConsumption.addAndGet(position - uniquePosition);
                    } else {
                        fuelConsumption.addAndGet(uniquePosition - position);
                    }
                });
                if (fuelConsumption.get() < lowestFuelConsumption.get()) {
                    lowestFuelConsumption.set(fuelConsumption.get());
                }
                fuelConsumption.set(0);
            });
            OUT.printf("AOC_7_1 - Fuel consumption: %d\n", lowestFuelConsumption.get());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void solveAOC_7_2() {
        try {
            String filePath = "input/input_aoc7";
            String inputLine = new BufferedReader(new FileReader(filePath)).readLine();
            List<Integer> positions = Stream.of(inputLine.split(",")).map(Integer::parseInt).sorted().collect(Collectors.toList());
            AtomicInteger fuelConsumption = new AtomicInteger();
            AtomicInteger lowestFuelConsumption = new AtomicInteger(Integer.MAX_VALUE);
            for (int index = positions.get(0); index < positions.get(positions.size()-1); index++) {
                int finalIndex = index;
                positions.forEach(position -> {
                    int accumulativeSum = 0;
                    int difference = position > finalIndex
                            ? position - finalIndex
                            : finalIndex - position;
                    for (int i = 0; i <= difference; i++) {
                        accumulativeSum += i;
                    }
                    fuelConsumption.addAndGet(accumulativeSum);
                });
                if (fuelConsumption.get() < lowestFuelConsumption.get()) {
                    lowestFuelConsumption.set(fuelConsumption.get());
                }
                fuelConsumption.set(0);
            };
            OUT.printf("AOC_7_2 - Fuel consumption: %d\n", lowestFuelConsumption.get());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
