package com.example.demo1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Room {

    @Id
    private String roomName;
    private String roomOwnerId;

    // 无参构造函数
    public Room() {
    }

    // 构造函数
    public Room(String roomName, String roomOwnerId) {
        this.roomName = roomName;
        this.roomOwnerId = roomOwnerId;
    }

    // Getter 方法
    public String getRoomName() {
        return roomName;
    }

    public String getRoomOwnerId() {
        return roomOwnerId;
    }
}