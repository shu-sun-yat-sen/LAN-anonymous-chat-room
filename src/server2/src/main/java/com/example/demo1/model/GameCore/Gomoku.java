package com.example.demo1.model.GameCore;


import java.io.*;
import java.util.Arrays;



public class Gomoku extends BoardGame {

    public Gomoku(String[] _players){
        super(_players,1, 15,15, 2);
    }

    @Override
    protected void initialize(){
        for (int[] ints : board) {
            Arrays.fill(ints, -1);
        }
    }

    @Override
    public boolean isEnd() {
        if (winner != -1) {
            return true;
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == -1) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected boolean isValidMove(int[][] action) {
        if (action.length != 1 || action[0].length != 2) {
            return false;
        }
        return board[action[0][0]][action[0][1]] == -1;
    }

    @Override
    protected void applyAction(int[][] action) {
        board[action[0][0]][action[0][1]] = actor;
        if (checkWin(action[0][0], action[0][1])) {
            winner = actor;
        }
    }

    private boolean checkWin(int row, int col) {
        int[][] directions = {{1, 0}, {0, 1}, {1, 1}, {1, -1}};
        for (int[] d : directions) {
            int counter = 1;
            for (int j = 1; counter < 5; j++) {
                int newRow = row + d[0] * j;
                int newCol = col + d[1] * j;
                if (inBoard(newRow, newCol) && board[newRow][newCol] == board[row][col]) {
                    counter++;
                } else {
                    break;
                }
            }
            for (int j = -1; counter < 5; j--) {
                int newRow = row + d[0] * j;
                int newCol = col + d[1] * j;
                if (inBoard(newRow, newCol) && board[newRow][newCol] == board[row][col]) {
                    counter++;
                } else {
                    break;
                }
            }

            if (counter >= 5) {
                return true;
            }
        }
        return false;
    }

    public static Gomoku deserialize(String serializedData) {
        try (ByteArrayInputStream byteStream = new ByteArrayInputStream(serializedData.getBytes("ISO-8859-1"));
             ObjectInputStream objectStream = new ObjectInputStream(byteStream)) {
            return (Gomoku) objectStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
