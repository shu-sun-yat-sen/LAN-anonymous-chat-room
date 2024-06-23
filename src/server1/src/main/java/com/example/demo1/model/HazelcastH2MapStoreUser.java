package com.example.demo1.model;

import com.hazelcast.map.MapStore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

public class HazelcastH2MapStoreUser implements MapStore<String,User> {
    private final DataSource dataSource;
    public HazelcastH2MapStoreUser(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public void store(String s, User user) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement("SELECT COUNT(*) FROM T_USER WHERE ID = ?");
             PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO T_USER (ID, FAKE_NAME, PASSWD,COOKIE,USERPIC) VALUES (?, ?, ?,?,?)")) {

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
            insertStmt.setString(2, user.getFakeName());
            insertStmt.setString(3, user.getPasswd());
            insertStmt.setString(4,user.getCookie());
            insertStmt.setString(5, user.getUserpic());
            insertStmt.executeUpdate();
            System.out.print("存入:");
            System.out.println(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void storeAll(Map<String, User> map) {
        for (Map.Entry<String, User> entry : map.entrySet()) {
            store(entry.getKey(), entry.getValue());
            System.out.print("存入:");
            System.out.println(entry.getValue());
        }
    }

    @Override
    public void delete(String s) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM T_USER WHERE ID = ?")) {
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
    public User load(String s) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM T_USER WHERE ID = ?")) {
            stmt.setString(1, s);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User customer = new User(rs.getString("ID"), rs.getString("FAKE_NAME"), rs.getString("PASSWD"));
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
    public Map<String, User> loadAll(Collection<String> collection) {
        Map<String, User> result = new HashMap<>();
        for (String key : collection) {
            User customer = load(key);
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
