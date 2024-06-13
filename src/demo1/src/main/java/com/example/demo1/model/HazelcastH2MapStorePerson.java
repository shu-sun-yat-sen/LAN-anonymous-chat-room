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

public class HazelcastH2MapStorePerson implements MapStore<Integer, Person> {

    private final DataSource dataSource;

    public HazelcastH2MapStorePerson(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void store(Integer key, Person value) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement("SELECT COUNT(*) FROM persons WHERE id = ?");
             PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO persons (id, firstName, lastName) VALUES (?, ?, ?)")) {

            // 检查是否已经存在相同ID的记录
            checkStmt.setInt(1, key);
            ResultSet resultSet = checkStmt.executeQuery();
            resultSet.next(); // 移动到第一行
            int count = resultSet.getInt(1); // 获取结果集中的计数值
            if (count > 0) {
                System.out.println("ID " + key + " 已存在，不执行插入操作。");
                return; // 不执行插入操作
            }

            // 插入新记录
            insertStmt.setInt(1, key);
            insertStmt.setString(2, value._firstName);
            insertStmt.setString(3, value._lastName);
            insertStmt.executeUpdate();
            System.out.print("存入:");
            System.out.println(value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void storeAll(Map<Integer, Person> map) {
        for (Map.Entry<Integer, Person> entry : map.entrySet()) {
            store(entry.getKey(), entry.getValue());
            System.out.print("存入:");
            System.out.println(entry.getValue());
        }
    }

    @Override
    public void delete(Integer key) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM persons WHERE id = ?")) {
            stmt.setInt(1, key);
            stmt.executeUpdate();
            System.out.print("删除:");
            System.out.println(key);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll(Collection<Integer> keys) {
        for (Integer key : keys) {
            delete(key);
            System.out.print("删除:");
            System.out.println(key);
        }
    }

    @Override
    public Person load(Integer key) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM persons WHERE id = ?")) {
            stmt.setInt(1, key);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Person person = new Person(rs.getString("firstName"), rs.getString("lastName"));
                    System.out.print("读取:");
                    System.out.println(person);
                    return person;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<Integer, Person> loadAll(Collection<Integer> keys) {
        Map<Integer, Person> result = new HashMap<>();
        for (Integer key : keys) {
            Person person = load(key);
            if (person != null) {
                System.out.print("读取:");
                System.out.println(person);
                result.put(key, person);
            }
        }
        return result;
    }

    @Override
    public Iterable<Integer> loadAllKeys() {
        // 这里根据实际情况返回所有键的集合
        return null;
    }
}
