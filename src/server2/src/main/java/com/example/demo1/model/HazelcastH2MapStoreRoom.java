package com.example.demo1.model;

import com.hazelcast.map.MapStore;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class HazelcastH2MapStoreRoom implements MapStore<String,Room> {
    private final DataSource dataSource;

    public HazelcastH2MapStoreRoom(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void store(String s, Room room) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement("SELECT COUNT(*) FROM ROOM WHERE ROOM_NAME = ?");
             PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO ROOM (ROOM_NAME,ROOM_OWNER_ID, NUMOFPEOPLE,PASS_WORD,MEMBERS_ID) VALUES (?, ?, ?,?,?)")) {

            // 检查是否已经存在相同ID的记录
            checkStmt.setString(1, s);
            ResultSet resultSet = checkStmt.executeQuery();
            resultSet.next(); // 移动到第一行
            int count = resultSet.getInt(1); // 获取结果集中的计数值
            if (count > 0) {
                System.out.println("ID " + s + " 已存在，不执行插入操作。");
                return; // 不执行插入操作
            }

            // 插入新记录
            insertStmt.setString(1, s);
            insertStmt.setString(2, room.getRoomOwnerId());
            insertStmt.setInt(3, room.getNumofpeople());
            insertStmt.setString(4, room.getPassWord());
            insertStmt.setString(5, room.gettureMemberid());
            insertStmt.executeUpdate();
            System.out.print("存入:");
            System.out.println(room);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void storeAll(Map<String, Room> map) {
        for (Map.Entry<String, Room> entry : map.entrySet()) {
            store(entry.getKey(), entry.getValue());
            System.out.print("存入:");
            System.out.println(entry.getValue());
        }
    }

    @Override
    public void delete(String s) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM ROOM WHERE ROOM_NAME = ?")) {
            stmt.setString(1, s);
            stmt.executeUpdate();
            System.out.print("删除:");
            System.out.println(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll(Collection<String> collection) {
        for (String key : collection) {
            delete(key);
            System.out.print("删除:");
            System.out.println(key);
        }
    }

    @Override
    public Room load(String s) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ROOM WHERE ROOM_NAME = ?")) {
            stmt.setString(1, s);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Room customer = new Room(rs.getString("ROOM_NAME"));
                    System.out.print("读取:");
                    System.out.println(customer);
                    return customer;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, Room> loadAll(Collection<String> collection) {
        Map<String, Room> result = new HashMap<>();
        for (String key : collection) {
            Room customer = load(key);
            if (customer != null) {
                System.out.print("读取:");
                System.out.println(customer);
                result.put(key, customer);
            }
        }
        return result;
    }

    @Override
    public Iterable<String> loadAllKeys() {
        return null;
    }
}
