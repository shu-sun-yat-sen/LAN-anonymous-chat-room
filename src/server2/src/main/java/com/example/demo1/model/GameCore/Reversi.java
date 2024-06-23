// GPT BUG Warning!!!
package com.example.demo1.model.GameCore;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;

public class Reversi extends BoardGame {

    public Reversi(String[] _players){
        super(_players, 3, 8, 8, 2); // 两名玩家，8x8 的棋盘
    }

    @Override
    protected void initialize(){
        // 初始化棋盘，将所有位置初始赋值为 -1
        board = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(board[i], -1);
        }
        // 设置初始的四个棋子位置
        int center = rows / 2;
        board[center - 1][center - 1] = 1; // 黑棋
        board[center][center] = 1;         // 黑棋
        board[center - 1][center] = 0;     // 白棋
        board[center][center - 1] = 0;     // 白棋
    }

    @Override
    public boolean isEnd() {
        if (winner != -1) {
            return true;
        }
        // 检查棋盘是否已满或没有有效的移动
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == -1 && canFlip(i, j)) {
                    return false;  // 至少有一个有效移动
                }
            }
        }
        return true;  // 没有有效移动了
    }

    @Override
    protected boolean isValidMove(int[][] action) {
        if (action.length != 1 || action[0].length != 2) {
            return false;
        }
        int row = action[0][0];
        int col = action[0][1];
        if (!inBoard(row, col) || board[row][col] != -1) {
            return false;  // 位置超出边界或已经被占据
        }
        return canFlip(row, col);
    }

    @Override
    protected void applyAction(int[][] action) {
        int row = action[0][0];
        int col = action[0][1];
        board[row][col] = actor;
        flipTiles(row, col);
        if (isEnd()) {
            calculateWinner();
        }
    }

    private void flipTiles(int row, int col) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        for (int[] dir : directions) {
            int r = row + dir[0];
            int c = col + dir[1];
            while (inBoard(r, c) && board[r][c] != -1 && board[r][c] != actor) {
                r += dir[0];
                c += dir[1];
            }
            if (inBoard(r, c) && board[r][c] == actor) {
                // 翻转这个方向上的棋子
                int flipR = row + dir[0];
                int flipC = col + dir[1];
                while (flipR != r || flipC != c) {
                    board[flipR][flipC] = actor;
                    flipR += dir[0];
                    flipC += dir[1];
                }
            }
        }
    }

    private boolean canFlip(int row, int col) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        for (int[] dir : directions) {
            int r = row + dir[0];
            int c = col + dir[1];
            boolean foundOpponent = false;
            while (inBoard(r, c) && board[r][c] != -1) {
                if (board[r][c] == actor) {
                    if (foundOpponent) {
                        return true; // 找到对手的棋子后再找到己方的棋子
                    } else {
                        break; // 只找到了己方的棋子，不能翻转
                    }
                }
                foundOpponent = true;
                r += dir[0];
                c += dir[1];
            }
        }
        return false;
    }

    public void calculateWinner() {
        int countBlack = 0;
        int countWhite = 0;
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0) {
                    countWhite++;
                } else if (cell == 1) {
                    countBlack++;
                }
            }
        }
        if (countBlack > countWhite) {
            winner = 1;  // 黑棋赢
        } else if (countWhite > countBlack) {
            winner = 0;  // 白棋赢
        } else {
            winner = -1;  // 平局
        }
    }

    public static Reversi deserialize(String serializedData) {
        try (ByteArrayInputStream byteStream = new ByteArrayInputStream(serializedData.getBytes("ISO-8859-1"));
             ObjectInputStream objectStream = new ObjectInputStream(byteStream)) {
            return (Reversi) objectStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
