package com.noccz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AOC5 extends AOC {
    public static void solveAOC_5_1() {
        try {
            int oceanFloorSize = 1000;
            String filePath = "input/input_aoc5";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            int[][] oceanFloor = new int[oceanFloorSize][oceanFloorSize];
            String line;
            List<HydroThermalVent> ventCoordinates = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                HydroThermalVent hydroThermalVent = new HydroThermalVent();
                String[] lineSplit = line.split("\\s->\\s");
                List<String> stringCoords = new ArrayList<>();
                for (String split : lineSplit) {
                    stringCoords.addAll(Arrays.asList(split.split(",")));
                }
                hydroThermalVent.setStartX(Integer.parseInt(stringCoords.get(0)));
                hydroThermalVent.setStartY(Integer.parseInt(stringCoords.get(1)));
                hydroThermalVent.setEndX(Integer.parseInt(stringCoords.get(2)));
                hydroThermalVent.setEndY(Integer.parseInt(stringCoords.get(3)));
                ventCoordinates.add(hydroThermalVent);
            }
            ventCoordinates.forEach(hydroThermalVent -> {
                if (isLine(hydroThermalVent)) {
                    if (hydroThermalVent.getStartY() < hydroThermalVent.getEndY()) {
                        for (int y = hydroThermalVent.getStartY(); y <= hydroThermalVent.getEndY(); y++) {
                            oceanFloor[y][hydroThermalVent.getStartX()]++;
                        }
                    } else if (hydroThermalVent.getStartY() > hydroThermalVent.getEndY()) {
                        for (int y = hydroThermalVent.getStartY(); y >= hydroThermalVent.getEndY(); y--) {
                            oceanFloor[y][hydroThermalVent.getStartX()]++;
                        }
                    } else if (hydroThermalVent.getStartX() < hydroThermalVent.getEndX()) {
                        for (int x = hydroThermalVent.getStartX(); x <= hydroThermalVent.getEndX(); x++) {
                            oceanFloor[hydroThermalVent.getStartY()][x]++;
                        }
                    } else if (hydroThermalVent.getStartX() > hydroThermalVent.getEndX()) {
                        for (int x = hydroThermalVent.getStartX(); x >= hydroThermalVent.getEndX(); x--) {
                            oceanFloor[hydroThermalVent.getStartY()][x]++;
                        }
                    }
                }
            });
            int nrOfOverlaps = 0;
            for (int i = 0; i < oceanFloorSize; i++) {
                for (int j = 0; j < oceanFloorSize; j++) {
                    if (oceanFloor[i][j] > 1) {
                        nrOfOverlaps++;
                    }
                }
            }
            OUT.printf("AOC_5_1 - Number of overlaps: %d\n", nrOfOverlaps);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void solveAOC_5_2() {
        try {
            int oceanFloorSize = 1000;
            String filePath = "input/input_aoc5";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            int[][] oceanFloor = new int[oceanFloorSize][oceanFloorSize];
            String line;
            List<HydroThermalVent> ventCoordinates = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                HydroThermalVent hydroThermalVent = new HydroThermalVent();
                String[] lineSplit = line.split("\\s->\\s");
                List<String> stringCoords = new ArrayList<>();
                for (String split : lineSplit) {
                    stringCoords.addAll(Arrays.asList(split.split(",")));
                }

                hydroThermalVent.setStartX(Integer.parseInt(stringCoords.get(0)));
                hydroThermalVent.setStartY(Integer.parseInt(stringCoords.get(1)));
                hydroThermalVent.setEndX(Integer.parseInt(stringCoords.get(2)));
                hydroThermalVent.setEndY(Integer.parseInt(stringCoords.get(3)));
                ventCoordinates.add(hydroThermalVent);
            }

            ventCoordinates.forEach(hydroThermalVent -> {
                if (isLine(hydroThermalVent)) {
                    if (hydroThermalVent.getStartY() < hydroThermalVent.getEndY()) {
                        for (int y = hydroThermalVent.getStartY(); y <= hydroThermalVent.getEndY(); y++) {
                            oceanFloor[y][hydroThermalVent.getStartX()]++;
                        }
                    } else if (hydroThermalVent.getStartY() > hydroThermalVent.getEndY()) {
                        for (int y = hydroThermalVent.getStartY(); y >= hydroThermalVent.getEndY(); y--) {
                            oceanFloor[y][hydroThermalVent.getStartX()]++;
                        }
                    } else if (hydroThermalVent.getStartX() < hydroThermalVent.getEndX()) {
                        for (int x = hydroThermalVent.getStartX(); x <= hydroThermalVent.getEndX(); x++) {
                            oceanFloor[hydroThermalVent.getStartY()][x]++;
                        }
                    } else if (hydroThermalVent.getStartX() > hydroThermalVent.getEndX()) {
                        for (int x = hydroThermalVent.getStartX(); x >= hydroThermalVent.getEndX(); x--) {
                            oceanFloor[hydroThermalVent.getStartY()][x]++;
                        }
                    }
                } else if (isDiagonal(hydroThermalVent)) {
                    if (hydroThermalVent.getStartY() < hydroThermalVent.getEndY()
                            && hydroThermalVent.getStartX() < hydroThermalVent.getEndX()) {
                        for (int y = hydroThermalVent.getStartY(); y <= hydroThermalVent.getEndY(); y++) {
                            for (int x = hydroThermalVent.getStartX(); x <= hydroThermalVent.getEndX(); x++) {
                                if (y-x == hydroThermalVent.getStartY() - hydroThermalVent.getStartX()) {
                                    oceanFloor[y][x]++;
                                }
                            }
                        }
                    } else if (hydroThermalVent.getStartY() > hydroThermalVent.getEndY()
                                && hydroThermalVent.getStartX() < hydroThermalVent.getEndX()) {
                        for (int y = hydroThermalVent.getStartY(); y >= hydroThermalVent.getEndY(); y--) {
                            for (int x = hydroThermalVent.getStartX(); x <= hydroThermalVent.getEndX(); x++) {
                                if (y + x == hydroThermalVent.getStartX() + hydroThermalVent.getStartY()) {
                                    oceanFloor[y][x]++;
                                }
                            }
                        }
                    } else if (hydroThermalVent.getStartY() < hydroThermalVent.getEndY()
                            && hydroThermalVent.getStartX() > hydroThermalVent.getEndX()) {
                        for (int y = hydroThermalVent.getStartY(); y <= hydroThermalVent.getEndY(); y++) {
                            for (int x = hydroThermalVent.getStartX(); x >= hydroThermalVent.getEndX(); x--) {
                                if (y + x == hydroThermalVent.getStartX() + hydroThermalVent.getStartY()) {
                                    oceanFloor[y][x]++;
                                }
                            }
                        }
                    } else if (hydroThermalVent.getStartY() > hydroThermalVent.getEndY()
                            && hydroThermalVent.getStartX() > hydroThermalVent.getEndX()) {
                        for (int y = hydroThermalVent.getStartY(); y >= hydroThermalVent.getEndY(); y--) {
                            for (int x = hydroThermalVent.getStartX(); x >= hydroThermalVent.getEndX(); x--) {
                                if (y - x == hydroThermalVent.getStartY() - hydroThermalVent.getStartX()) {
                                    oceanFloor[y][x]++;
                                }
                            }
                        }
                    }
                }
            });
            int nrOfOverlaps = 0;
            for (int i = 0; i < oceanFloorSize; i++) {
                for (int j = 0; j < oceanFloorSize; j++) {
                    if (oceanFloor[i][j] >= 2) {
                        nrOfOverlaps++;
                    }
                }
            }
            OUT.printf("AOC_5_2 - Number of overlaps: %d\n", nrOfOverlaps);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isDiagonal(HydroThermalVent hydroThermalVent) {
        return (hydroThermalVent.getStartX() + hydroThermalVent.getEndX() == hydroThermalVent.getStartY() + hydroThermalVent.getEndY())
                || (hydroThermalVent.getStartX() + hydroThermalVent.getStartY() == hydroThermalVent.getEndX() + hydroThermalVent.getEndY())
                || (hydroThermalVent.getStartX() + hydroThermalVent.getEndY() == hydroThermalVent.getStartY() + hydroThermalVent.getEndX())
                || (hydroThermalVent.getStartX() - hydroThermalVent.getEndX() == hydroThermalVent.getStartY() - hydroThermalVent.getEndY())
                || (hydroThermalVent.getEndX() - hydroThermalVent.getStartX() == hydroThermalVent.getEndY() - hydroThermalVent.getStartY());
    }

    private static boolean isLine(HydroThermalVent hydroThermalVent) {
        return hydroThermalVent.getStartX() == hydroThermalVent.getEndX() || hydroThermalVent.getStartY() == hydroThermalVent.getEndY();
    }

    private static class HydroThermalVent {
        private int startX;
        private int endX;
        private int startY;
        private int endY;

        HydroThermalVent() {
            // Intentionally empty
        }

        public int getStartX() {
            return startX;
        }

        public void setStartX(int startX) {
            this.startX = startX;
        }

        public int getEndX() {
            return endX;
        }

        public void setEndX(int endX) {
            this.endX = endX;
        }

        public int getStartY() {
            return startY;
        }

        public void setStartY(int startY) {
            this.startY = startY;
        }

        public int getEndY() {
            return endY;
        }

        public void setEndY(int endY) {
            this.endY = endY;
        }
    }
}
