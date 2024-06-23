package com.example.demo1.model;

import jakarta.persistence.Entity;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class Gomoku implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int GAME_ID = 0;
    private List<int[][]> chessboardHistory;
    private int[][] chessboard;
    private int turn;
    private int round;
    private int winner;
    private static final int SIZE = 15;
    private static final int WINNING_COUNT = 5;
    private static final int PLAYERS_NUM = 2;
    private String[] players;

    public Gomoku(String player0, String player1) {
        players = new String[PLAYERS_NUM];
        players[0] = player0;
        players[1] = player1;

        chessboardHistory = new ArrayList<>();
        chessboard = new int[SIZE][SIZE];
        initializeBoard(chessboard);
        chessboardHistory.add(chessboard);
        turn = 0;
        round = 0;
        winner = -1;
    }

    public boolean move(int row, int col, String player) {
        if (!player.equals(players[turn])) {
            return false;
        }
        if (!isValidMove(row, col)) {
            return false;
        }
        chessboard = copyChessboard(chessboard);
        chessboard[row][col] = turn;
        chessboardHistory.add(chessboard);

        if (checkWin(row, col)) {
            winner = turn;
        }

        turn = turn ^ 1;
        round++;
        return true;
    }

    public boolean isEnd() {
        if (winner != -1) {
            return true;
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (chessboard[i][j] == -1) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getWinner() {
        return winner;
    }

    public int getTurn() {
        return turn;
    }

    public int getRound() {
        return round;
    }

    public String[] getPlayers() {
        return players;
    }

    public int[][] getChessboard() {
        return chessboard;
    }

    public List<int[][]> getChessboardHistory() {
        return chessboardHistory;
    }

    private void initializeBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 0;
            }
        }
    }

    private int[][] copyChessboard(int[][] original) {
        int[][] copy = new int[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, original[i].length);
        }
        return copy;
    }

    private boolean inBoard(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }

    private boolean isValidMove(int row, int col) {
        return !isEnd() && inBoard(row, col) && chessboard[row][col] == -1;
    }

    private boolean checkWin(int row, int col) {
        int[][] directions = {{1, 0}, {0, 1}, {1, 1}, {1, -1}};
        for (int[] d : directions) {
            int counter = 1;
            for (int j = 1; counter < WINNING_COUNT; j++) {
                int newRow = row + d[0] * j;
                int newCol = col + d[1] * j;
                if (inBoard(newRow, newCol) && chessboard[newRow][newCol] == chessboard[row][col]) {
                    counter++;
                } else {
                    break;
                }
            }

            for (int j = -1; counter < WINNING_COUNT; j--) {
                int newRow = row + d[0] * j;
                int newCol = col + d[1] * j;
                if (inBoard(newRow, newCol) && chessboard[newRow][newCol] == chessboard[row][col]) {
                    counter++;
                } else {
                    break;
                }
            }

            if (counter >= WINNING_COUNT) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(GAME_ID).append("\n");
        sb.append(PLAYERS_NUM).append("\n");
        sb.append(players[0]).append(",").append(players[1]).append("\n");
        sb.append(turn).append(",").append(round).append(",").append(winner).append("\n");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sb.append(chessboard[i][j]).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("\n");

        sb.append(chessboardHistory.size()).append("\n");
        for (int[][] board : chessboardHistory) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    sb.append(board[i][j]).append(",");
                }
                sb.deleteCharAt(sb.length() - 1);
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public static Gomoku fromString(String data) {
        String[] lines = data.split("\n");
        int gameId = Integer.parseInt(lines[0]);  // Read GAME_ID but it is static and doesn't need to be set
        int playersNum = Integer.parseInt(lines[1]);  // Read PLAYERS_NUM but it is static and doesn't need to be set
        String[] playerNames = lines[2].split(",");
        String player0 = playerNames[0];
        String player1 = playerNames[1];

        String[] header = lines[3].split(",");
        int turn = Integer.parseInt(header[0]);
        int round = Integer.parseInt(header[1]);
        int winner = Integer.parseInt(header[2]);

        int[][] chessboard = new int[SIZE][SIZE];
        int index = 4;
        for (int i = 0; i < SIZE; i++) {
            String[] line = lines[index++].split(",");
            for (int j = 0; j < SIZE; j++) {
                chessboard[i][j] = Integer.parseInt(line[j]);
            }
        }

        List<int[][]> chessboardHistory = new ArrayList<>();
        int historySize = Integer.parseInt(lines[index++]);
        for (int k = 0; k < historySize; k++) {
            int[][] board = new int[SIZE][SIZE];
            for (int i = 0; i < SIZE; i++) {
                String[] line = lines[index++].split(",");
                for (int j = 0; j < SIZE; j++) {
                    board[i][j] = Integer.parseInt(line[j]);
                }
            }
            chessboardHistory.add(board);
        }

        Gomoku gomoku = new Gomoku(player0, player1);
        gomoku.chessboard = chessboard;
        gomoku.turn = turn;
        gomoku.round = round;
        gomoku.winner = winner;
        gomoku.chessboardHistory = chessboardHistory;

        return gomoku;
    }
}
