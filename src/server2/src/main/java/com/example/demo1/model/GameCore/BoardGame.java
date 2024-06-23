package com.example.demo1.model.GameCore;

import java.io.*;
import java.util.List;
import java.util.ArrayList;


public abstract class BoardGame implements Serializable {
    private static final long serialVersionUID = 1L;

    protected int gameID;
    public int getGameID() {
        return gameID;
    }

    /**
     * 棋谱
     */
    protected List<int[][]> boardHistory;

    /**
     * 当前棋盘
     */
    protected int[][] board;

    /**
     * 棋盘大小
     */
    protected int rows;
    protected int cols;

    /**
     * 回合数
     */
    protected int turn;

    /**
     * 玩家数量!
     */
    protected int playersNum;

    /**
     * 玩家列表
     */
    protected String[] players;

    /**
     * 当前行动者
     */
    protected int actor;

    /**
     * 获胜者
     */
    protected int winner;

    public BoardGame(String[] _players, int _gameID, int _rows, int _cols, int _playersNum) {
        gameID = _gameID;

        rows = _rows;
        cols = _cols;
        board = new int[rows][cols];

        playersNum = _playersNum;
        players = _players;
        winner = -1;
        actor = 0;

        boardHistory = new ArrayList<int[][]>();
        turn = 0;
        initialize();
        boardHistory.add(board);
    }

    /**
     * 摆棋!
     */
    protected abstract void initialize();

    public void setBoard(int[][] board) {
        this.board = copyBoard(board);
    }

    /**
     * 落子
     *
     * @param player 行动者
     * @param action 要执行的(系列)动作
     * @return 动作是否成功
     */
    public boolean move(String player, int [][]action){
        if (isEnd()){
            return false;
        }
        if (!player.equals(getActor())) {
            return false;
        }
        if (!isValidMove(action)){
            return false;
        }
        board = copyBoard(board);
        applyAction(action);
        boardHistory.add(board);
        actor = nextActor(action);
        turn++;
        return true;
    }

    /**
     * 悔棋
     *
     * @return 是否成功悔棋
     */
    public boolean undoMove() {
        if (turn == 0) {
            return false; // No moves to undo
        }
        boardHistory.remove(boardHistory.size() - 1);
        board = boardHistory.get(boardHistory.size() - 1);
        actor = (actor - 1 + playersNum) % playersNum;
        turn--;
        winner = -1; // Reset the winner since the game state is reverted
        return true;
    }
    public void draw(){

    }

    /**
     * 判断游戏是否结束!
     *
     * @return 是否结束
     */
    public abstract boolean isEnd();

    public void setActor(int actor) {
        this.actor = actor;
    }

    /**
     * 判断动作是否合法!
     *
     * @param action 要执行的(系列)动作
     * @return 动作是否合法
     */
    protected abstract boolean isValidMove(int[][]action);

    /**
     * 执行动作,保证动作合法!
     *
     * @param action 要执行的(系列)动作
     */
    protected abstract void applyAction(int[][]action);

    /**
     * 计算下一个行动者
     *
     * @param action 要执行的(系列)动作
     * @return 动作是否合法
     */
    protected int nextActor(int[][]action){
        return (actor+1)%playersNum;
    }

    /**
     * 获取获胜者id
     *
     * @return 获胜者id
     */
    public int getWinner() {
        return winner;
    }

    /**
     * 获取当前回合行动者
     *
     * @return 行动者id
     */
    public String getActor() {
        return isEnd() ? "$None$" : players[actor];
    }

    /**
     * 获取回合数
     *
     * @return 行动者id
     */
    public int getTurn() {
        return turn;
    }

    /**
     * 获取回棋盘大小
     *
     * @return 棋盘行列
     */
    public int[] getBoardSize(){
        return new int[]{rows, cols};
    }

    /**
     * 获取当前棋盘
     *
     * @return 当前棋盘
     */
    public int[][] getBoard() {
        return copyBoard(board);
    }

    /**
     * 返回棋盘历史记录
     *
     * @return 棋盘历史记录的深度拷贝列表
     */
    public List<int[][]> getBoardHistory() {
        List<int[][]> deepCopy = new ArrayList<>();
        for (int[][] board : boardHistory) {
            deepCopy.add(copyBoard(board));
        }
        return deepCopy;
    }

    /**
     * 创建给定棋盘的深度拷贝。
     *
     * @param original 要拷贝的原始棋盘
     * @return 原始棋盘的深度拷贝
     */
    protected int[][] copyBoard(int[][] original) {
        if (original == null) {
            return null;
        }
        int[][] copy = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].clone(); // 使用 clone 方法创建二维数组的浅拷贝
        }
        return copy;
    }

    /**
     * 判断位置是否在棋盘内
     *
     * @param row 行
     * @param col 列
     * @return 是否在棋盘范围内
     */
    public boolean inBoard(int row,int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }


    public String serialize() {
        try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
             ObjectOutputStream objectStream = new ObjectOutputStream(byteStream)) {
            objectStream.writeObject(this);
            return byteStream.toString("ISO-8859-1");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
