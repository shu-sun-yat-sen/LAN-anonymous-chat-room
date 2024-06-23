package com.example.demo1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class GAME {
    @Id
    private String id;
    private String chatroom;
    private String time;
    private int gameid;
    private int winner;
    private int turn;

    private String board;
    private String players;
    private int isend;
    public GAME(){}
    public GAME(int gameid){
        this.gameid=gameid;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedString = now.format(formatter);
        this.time=formattedString;
    }
    //get

    public String getId() {
        return id;
    }

    public String getChatroom() {
        return chatroom;
    }

    public int getIsend() {
        return isend;
    }

    public String getTime() {
        return time;
    }

    public String getPlayers() {
        return players;
    }

    public int getTurn() {
        return turn;
    }

    public int getGameid() {
        return gameid;
    }

    public int getWinner() {
        return winner;
    }

    public String getBoard() {
        return board;
    }
    //set

    public void setId(String id) {
        this.id = id;
    }

    public void setChatroom(String chatroom) {
        this.chatroom = chatroom;
    }

    public void setIsend(int isend) {
        this.isend = isend;
    }

    public void setPlayers(String players) {
        this.players = players;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public void setGameid(int gameid) {
        this.gameid = gameid;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

}
