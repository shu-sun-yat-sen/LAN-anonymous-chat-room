package com.example.demo1.model;

import com.hazelcast.map.MapStore;
import org.hibernate.boot.model.relational.Database;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class HazelcastH2MapStoreGame implements MapStore<String,GAME> {
    private final DataSource dataSource;

    public HazelcastH2MapStoreGame(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void store(String s, GAME game) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement("SELECT COUNT(*) FROM GAME WHERE ID = ?");
             PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO GAME (ID,CHATROOM,TIME,GAMEID,WINNER,TURN,BOARD,PLAYERS,ISEND) VALUES (?,?,?,?,? ?, ?,?,?)")) {

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
            insertStmt.setString(2, game.getChatroom());
            insertStmt.setString(3,game.getTime());
            insertStmt.setInt(4, game.getGameid());
            insertStmt.setInt(5,game.getWinner());
            insertStmt.setInt(6,game.getTurn());
            insertStmt.setString(7, game.getBoard());
            insertStmt.setString(8,game.getPlayers());
            insertStmt.setInt(9, game.getIsend());
            insertStmt.executeUpdate();
            System.out.print("存入:");
            System.out.println(game);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void storeAll(Map<String, GAME> map) {
        for (Map.Entry<String, GAME> entry : map.entrySet()) {
            store(entry.getKey(), entry.getValue());
            System.out.print("存入:");
            System.out.println(entry.getValue());
        }
    }

    @Override
    public void delete(String s) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM GAME WHERE ID = ?")) {
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
    public GAME load(String s) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM GAME WHERE ID = ?")) {
            stmt.setString(1, s);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    GAME customer = new GAME(rs.getInt("GAMEID"));
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
    public Map<String, GAME> loadAll(Collection<String> collection) {
        Map<String, GAME> result = new HashMap<>();
        for (String key : collection) {
            GAME customer = load(key);
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
