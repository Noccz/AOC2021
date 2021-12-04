package com.noccz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AOC4 extends AOC {
    public static void solveAOC_4_1() {
        try {
            String filePath = "input/input_aoc4";
            Map<Integer, List<BoardNumber>> currentBoard = new HashMap<>();
            List<Map<Integer, List<BoardNumber>>> boardList = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String[] bingoNumbers = reader.readLine().split(",");
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    List<BoardNumber> currentRow = Arrays.stream(line.trim().split("\\s+"))
                            .map(nr -> new BoardNumber(false, nr)).collect(Collectors.toList());
                    currentBoard.put(index, currentRow);
                    index++;

                    if (index == 5) {
                        index = 0;
                        boardList.add(currentBoard);
                        currentBoard = new HashMap<>();
                    }
                }
            }
            index = 0;
            Map<Integer, List<BoardNumber>> winningBoard = new HashMap<>();
            String latestNumber = "";
            mainLoop: while (index < bingoNumbers.length) {
                for (Map<Integer, List<BoardNumber>> board : boardList) {
                    for (int i = 0; i < 5; i++) {
                        for (BoardNumber boardNumber : board.get(i)) {
                            if (boardNumber.getValue().equals(bingoNumbers[index])) {
                                boardNumber.setMarked(true);
                            }
                        }
                    }
                }

                int nrOfMarks = 0;
                // Check rows
                for (Map<Integer, List<BoardNumber>> board : boardList) {
                    for (int i = 0; i < 5; i++) {
                        for (BoardNumber boardNumber : board.get(i)) {
                            if (boardNumber.isMarked()) {
                                nrOfMarks++;
                            }
                        }
                        if (nrOfMarks == 5) {
                            winningBoard = board;
                            latestNumber = bingoNumbers[index];
                            break mainLoop;
                        } else {
                            nrOfMarks = 0;
                        }
                    }
                }

                // Check columns
                for (Map<Integer, List<BoardNumber>> board : boardList) {
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            if (board.get(j).get(i).isMarked()) {
                                nrOfMarks++;
                            }
                        }
                        if (nrOfMarks == 5) {
                            winningBoard = board;
                            latestNumber = bingoNumbers[index];
                            break mainLoop;
                        } else {
                            nrOfMarks = 0;
                        }
                    }
                }
                index++;
            }

            int nonMarkedSum = 0;
            for (int i = 0; i < 5; i++) {
                for (BoardNumber boardNumber : winningBoard.get(i)) {
                    if (!boardNumber.isMarked()) {
                        nonMarkedSum += Integer.parseInt(boardNumber.getValue());
                    }
                }
            }
            OUT.printf("AOC_4_1 - Final score: %d\n", nonMarkedSum*Integer.parseInt(latestNumber));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void solveAOC_4_2() {
        try {
            String filePath = "input/input_aoc4";
            Map<Integer, List<BoardNumber>> currentBoard = new HashMap<>();
            List<Map<Integer, List<BoardNumber>>> boardList = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String[] bingoNumbers = reader.readLine().split(",");
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    List<BoardNumber> currentRow = Arrays.stream(line.trim().split("\\s+"))
                            .map(nr -> new BoardNumber(false, nr)).collect(Collectors.toList());
                    currentBoard.put(index, currentRow);
                    index++;

                    if (index == 5) {
                        index = 0;
                        boardList.add(currentBoard);
                        currentBoard = new HashMap<>();
                    }
                }
            }
            index = -1;
            Map<Integer, List<BoardNumber>> lastWinningBoard = new HashMap<>();
            while (boardList.size() > 0) {
                index++;
                lastWinningBoard = removeWinningBoards(bingoNumbers, boardList, index);
            }

            int nonMarkedSum = 0;
            for (int i = 0; i < 5; i++) {
                for (BoardNumber boardNumber : lastWinningBoard.get(i)) {
                    if (!boardNumber.isMarked()) {
                        nonMarkedSum += Integer.parseInt(boardNumber.getValue());
                    }
                }
            }
            OUT.printf("AOC_4_2 - Final score: %d\n", nonMarkedSum*Integer.parseInt(bingoNumbers[index]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<Integer, List<BoardNumber>> removeWinningBoards(String[] bingoNumbers, List<Map<Integer, List<BoardNumber>>> boardList, int index) {
        Set<Map<Integer, List<BoardNumber>>> removalSet = new HashSet<>();
        Map<Integer, List<BoardNumber>> lastBoard = new HashMap<>();
        for (Map<Integer, List<BoardNumber>> board : boardList) {
            for (int i = 0; i < 5; i++) {
                for (BoardNumber boardNumber : board.get(i)) {
                    if (boardNumber.getValue().equals(bingoNumbers[index])) {
                        boardNumber.setMarked(true);
                    }
                }
            }
        }
        int nrOfMarks = 0;
        // Check rows
        for (Map<Integer, List<BoardNumber>> board : boardList) {
            for (int i = 0; i < 5; i++) {
                for (BoardNumber boardNumber : board.get(i)) {
                    if (boardNumber.isMarked()) {
                        nrOfMarks++;
                    }
                }
                if (nrOfMarks == 5) {
                    removalSet.add(board);
                    lastBoard = board;
                } else {
                    nrOfMarks = 0;
                }
            }
        }

        // Check columns
        for (Map<Integer, List<BoardNumber>> board : boardList) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (board.get(j).get(i).isMarked()) {
                        nrOfMarks++;
                    }
                }
                if (nrOfMarks == 5) {
                    removalSet.add(board);
                    lastBoard = board;
                } else {
                    nrOfMarks = 0;
                }
            }
        }
        boardList.removeAll(removalSet);
        return lastBoard;
    }

    private static class BoardNumber {
        private boolean myIsMarked;
        private final String myValue;

        public BoardNumber(boolean isMarked, String value) {
            myIsMarked = isMarked;
            myValue = value;
        }

        public boolean isMarked() {
            return myIsMarked;
        }

        public String getValue() {
            return myValue;
        }

        public void setMarked(boolean marked) {
            myIsMarked = marked;
        }
    }
}
