// GPT BUG Warning!!!

package com.example.demo1.model.GameCore;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;

public class Go extends BoardGame {

    public Go(String[] _players) {
        super(_players, 2, 19, 19, 2); // 游戏ID为2，棋盘大小为19x19，2名玩家
    }

    @Override
    protected void initialize() {
        for (int[] row : board) {
            Arrays.fill(row, -1);
        }
    }

    @Override
    public boolean isEnd() {
        // 游戏结束条件：连续三次棋面相同或棋盘已满
        if (boardHistory.size() >= 3) {
            // 获取最新的三个棋盘状态
            int[][] board1 = boardHistory.get(boardHistory.size() - 1);
            int[][] board2 = boardHistory.get(boardHistory.size() - 2);
            int[][] board3 = boardHistory.get(boardHistory.size() - 3);

            // 检查是否发生了三次相同的棋盘状态
            if (Arrays.deepEquals(board1, board2) && Arrays.deepEquals(board2, board3)) {
                return true; // 三次相同则判断游戏结束
            }
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
        if (action[0][0] == -1 && action[0][1] == -1) {
            // 停着操作总是合法的
            return true;
        }
        int row = action[0][0];
        int col = action[0][1];
        if (!inBoard(row, col) || board[row][col] != -1) {
            return false;
        }

        // 应用“劫”规则：判断当前动作是否会导致棋盘回到之前的状态
        int[][] tempBoard = copyBoard(board);
        tempBoard[row][col] = actor;
        captureStones(tempBoard, row, col, actor);
        if (turn >= 1) {
            int[][] lastBoard = boardHistory.get(turn - 1);
            if (Arrays.deepEquals(tempBoard, lastBoard)) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void applyAction(int[][] action) {
        if (action[0][0] == -1 && action[0][1] == -1) {
            // 处理停着操作
            boardHistory.add(copyBoard(board));
        } else {
            int row = action[0][0];
            int col = action[0][1];
            board[row][col] = actor;

            // 检查是否有提子
            captureStones(board, row, col, actor);

            // 检查自己的棋子是否无气
            if (isCaptured(row, col, actor)) {
                removeGroup(board, row, col, actor); // 如果无气，移除刚刚放置的棋子
            }

            // 记录当前棋面
            boardHistory.add(copyBoard(board));
        }

        // 临时+1为了能用isEnd()
        boardHistory.add(board);
        turn++;
        // 检查游戏是否结束
        if (isEnd()) {
            calculateWinner();
        }
        boardHistory.remove(boardHistory.size() - 1);
        turn--;
    }

    private void captureStones(int[][] board, int row, int col, int player) {
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int opponent = (player + 1) % playersNum;

        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            if (inBoard(newRow, newCol) && board[newRow][newCol] == opponent) {
                if (isCaptured(newRow, newCol, opponent)) {
                    removeGroup(board, newRow, newCol, opponent);
                }
            }
        }
    }

    private boolean isCaptured(int row, int col, int player) {
        boolean[][] visited = new boolean[rows][cols];
        return !hasLiberty(row, col, player, visited);
    }

    private boolean hasLiberty(int row, int col, int player, boolean[][] visited) {
        if (!inBoard(row, col)) {
            return false;
        }
        if (visited[row][col]) {
            return false;
        }
        if (board[row][col] == -1) {
            return true;
        }
        if (board[row][col] != player) {
            return false;
        }

        visited[row][col] = true;

        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int[] direction : directions) {
            if (hasLiberty(row + direction[0], col + direction[1], player, visited)) {
                return true;
            }
        }
        return false;
    }

    private void removeGroup(int[][] board, int row, int col, int player) {
        board[row][col] = -1;
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            if (inBoard(newRow, newCol) && board[newRow][newCol] == player) {
                removeGroup(board, newRow, newCol, player);
            }
        }
    }

    public void calculateWinner() {
        int[] scores = calculateTerritoryAndCaptureScore();
        if (scores[0] > scores[1]) {
            winner = 0;
        } else if (scores[0] < scores[1]) {
            winner = 1;
        } else {
            winner = -1; // 平局
        }
    }

    private int[] calculateTerritoryAndCaptureScore() {
        int[] scores = new int[playersNum];
        boolean[][] visited = new boolean[rows][cols];

        // 计算地盘和提子数
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j]) {
                    if (board[i][j] == -1) {
                        int owner = getTerritoryOwner(i, j, visited);
                        if (owner != -1) {
                            scores[owner] += countTerritory(i, j);
                        }
                    } else {
                        scores[board[i][j]]++; // 计入棋子数
                        visited[i][j] = true;
                    }
                }
            }
        }
        return scores;
    }

    private int getTerritoryOwner(int row, int col, boolean[][] visited) {
        // 获取地盘的归属玩家，返回 -1 表示该地盘不属于任何玩家
        int owner = -1;
        boolean[][] tempVisited = new boolean[rows][cols];
        return checkTerritoryOwner(row, col, visited, tempVisited, owner);
    }

    private int checkTerritoryOwner(int row, int col, boolean[][] visited, boolean[][] tempVisited, int owner) {
        if (!inBoard(row, col) || visited[row][col] || board[row][col] != -1) {
            return owner;
        }
        tempVisited[row][col] = true;
        visited[row][col] = true;

        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            if (inBoard(newRow, newCol)) {
                if (board[newRow][newCol] != -1) {
                    if (owner == -1) {
                        owner = board[newRow][newCol];
                    } else if (owner != board[newRow][newCol]) {
                        return -1; // 该地盘不属于任何玩家
                    }
                } else {
                    owner = checkTerritoryOwner(newRow, newCol, visited, tempVisited, owner);
                    if (owner == -1) {
                        return -1; // 该地盘不属于任何玩家
                    }
                }
            }
        }
        return owner;
    }

    private int countTerritory(int row, int col) {
        // 计算地盘的大小
        int count = 0;
        boolean[][] tempVisited = new boolean[rows][cols];
        return countTerritoryHelper(row, col, tempVisited, count);
    }

    private int countTerritoryHelper(int row, int col, boolean[][] tempVisited, int count) {
        if (!inBoard(row, col) || tempVisited[row][col] || board[row][col] != -1) {
            return count;
        }
        tempVisited[row][col] = true;
        count++;

        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            count = countTerritoryHelper(newRow, newCol, tempVisited, count);
        }
        return count;
    }

    public static Go deserialize(String serializedData) {
        try (ByteArrayInputStream byteStream = new ByteArrayInputStream(serializedData.getBytes("ISO-8859-1"));
             ObjectInputStream objectStream = new ObjectInputStream(byteStream)) {
            return (Go) objectStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
