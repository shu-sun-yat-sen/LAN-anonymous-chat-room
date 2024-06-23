package com.example.demo1.model.GameCore;

import com.example.demo1.model.GameCore.BoardGame;
import com.example.demo1.model.GameCore.Reversi;

import java.util.Arrays;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        String str = "[[1, 2, 3], [4, 5, 6], [7, 8, 9]]";

        // 移除首尾的方括号
        str = str.substring(2, str.length() - 1);
        System.out.print(str);
        // 根据外部数组的分隔符（方括号对之间的逗号）分割字符串
        String[] rows = str.split(", \\[");
        System.out.print(rows[1]);
        // 初始化二维数组
        int[][] twoDimensionalArray = new int[rows.length][];

        // 遍历行并解析每行的元素
        for (int i = 0; i < rows.length; i++) {
            // 移除每行首尾的方括号
            rows[i] = rows[i].trim().substring(0, rows[i].length() - 1);

            // 根据内部数组的分隔符（逗号）分割字符串
            String[] cols = rows[i].split(",");

            // 将字符串转换为整数并存储在二维数组中
            int[] rowArray = new int[cols.length];
            for (int j = 0; j < cols.length; j++) {
                rowArray[j] = Integer.parseInt(cols[j].trim());
            }

            twoDimensionalArray[i] = rowArray;
        }

        // 打印二维数组以验证结果
        for (int[] row : twoDimensionalArray) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static boolean isValidMoveFormat(String move) {
        if (move.length() < 2) {
            return false;
        }

        char colChar = move.charAt(0);
        if (colChar < 'a' || colChar > 'h') {
            return false;
        }

        String rowStr = move.substring(1);
        try {
            int row = Integer.parseInt(rowStr);
            return row > 0 && row <= 8;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void printBoard(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        System.out.print("   ");
        for (int c = 0; c < cols; c++) {
            System.out.print((char) ('a' + c) + " ");
        }
        System.out.println();

        for (int r = 0; r < rows; r++) {
            System.out.printf("%2d ", r + 1);
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == -1) {
                    System.out.print(". ");
                } else {
                    System.out.print((board[r][c] == 0 ? "X " : "O "));
                }
            }
            System.out.println();
        }
    }
}


//import GameCore.BoardGame;
//import GameCore.Gomoku;
//
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String[] players = {"Player1", "Player2"};
//        BoardGame game = new Gomoku(players);
//
//        System.out.println("Welcome to Gomoku!");
//        printBoard(game.getBoard());
//
//        while (!game.isEnd()) {
//            String currentPlayer = game.getActor();
//            System.out.println(currentPlayer + "'s turn. Please enter your move (e.g., a1):");
//
//            String move = scanner.nextLine().toLowerCase();
//            if (!isValidMoveFormat(move)) {
//                System.out.println("Invalid move format. Please try again.");
//                continue;
//            }
//
//            int row = Integer.parseInt(move.substring(1)) - 1;
//            int col = move.charAt(0) - 'a';
//
////            这里开始和游戏内核交互
////            if (!game.inBoard(row, col)) {
////                System.out.println("Move out of bounds. Please try again.");
////                continue;
////            }
//
//            int[][] action = {{row, col}};
//            if (!game.move(currentPlayer, action)) {
//                System.out.println("Invalid move. Please try again.");
//                continue;
//            }
//
//            printBoard(game.getBoard());
//
//            System.out.println("Test");
////            序列化反序列化悔棋啥的看这里!
//            String message=game.serialize();
//            System.out.println(message);
//
//            BoardGame newGame = BoardGame.deserialize(message);
//            if (newGame != null) {
//                newGame.undoMove();
//                printBoard(newGame.getBoard());
//            }
//
//        }
//
//        System.out.println("Game over! " + game.getWinner() + " wins!");
//    }
//
//    private static boolean isValidMoveFormat(String move) {
//        if (move.length() < 2) {
//            return false;
//        }
//
//        char colChar = move.charAt(0);
//        if (colChar < 'a' || colChar > 'o') {
//            return false;
//        }
//
//        String rowStr = move.substring(1);
//        try {
//            int row = Integer.parseInt(rowStr);
//            return row > 0 && row <= 15;
//        } catch (NumberFormatException e) {
//            return false;
//        }
//    }
//
//    private static void printBoard(int[][] board) {
//        int rows = board.length;
//        int cols = board[0].length;
//
//        System.out.print("   ");
//        for (int c = 0; c < cols; c++) {
//            System.out.print((char) ('a' + c) + " ");
//        }
//        System.out.println();
//
//        for (int r = 0; r < rows; r++) {
//            System.out.printf("%2d ", r + 1);
//            for (int c = 0; c < cols; c++) {
//                if (board[r][c] == -1) {
//                    System.out.print(". ");
//                } else {
//                    System.out.print((board[r][c] == 0 ? "X " : "O "));
//                }
//            }
//            System.out.println();
//        }
//    }
//}
