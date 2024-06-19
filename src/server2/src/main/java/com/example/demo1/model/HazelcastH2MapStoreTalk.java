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

public class HazelcastH2MapStoreTalk implements MapStore<String,Talk> {
    private final DataSource dataSource;

    public HazelcastH2MapStoreTalk(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void store(String s, Talk talk) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement("SELECT COUNT(*) FROM TALK WHERE TIME = ?");
             PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO TALK (TIME, CHATROOMNAME, SENDERNAME,SENDERPIC,CONTEXT) VALUES (?, ?, ?,?,?)")) {

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
            insertStmt.setString(2, talk.getChatroomname());
            insertStmt.setString(3, talk.getSendername());
            insertStmt.setString(4, talk.getSenderpic());
            insertStmt.setString(5, talk.getContext());
            insertStmt.executeUpdate();
            System.out.print("存入:");
            System.out.println(talk);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void storeAll(Map<String, Talk> map) {
        for (Map.Entry<String, Talk> entry : map.entrySet()) {
            store(entry.getKey(), entry.getValue());
            System.out.print("存入:");
            System.out.println(entry.getValue());
        }
    }

    @Override
    public void delete(String s) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM TALK WHERE TIME = ?")) {
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
    public Talk load(String s) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM TALK WHERE TIME = ?")) {
            stmt.setString(1, s);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Talk customer = new Talk(rs.getString("CONTEXT"));
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
    public Map<String, Talk> loadAll(Collection<String> collection) {
        Map<String, Talk> result = new HashMap<>();
        for (String key : collection) {
            Talk customer = load(key);
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
