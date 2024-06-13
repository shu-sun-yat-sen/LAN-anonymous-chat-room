package com.example.demo1.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class H2Config implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2Config.class);
    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.username}")
    private String dataSourceUsername;

    @Value("${spring.datasource.password}")
    private String dataSourcePassword;

    @Override
    public void run(String... args) throws Exception {
        try (Connection connection = DriverManager.getConnection(dataSourceUrl, dataSourceUsername, dataSourcePassword);
             Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS CUSTOMERS (id INT PRIMARY KEY, firstName VARCHAR(255), lastName VARCHAR(255))");
            statement.execute("CREATE TABLE IF NOT EXISTS PERSONS (id INT PRIMARY KEY, firstName VARCHAR(255), lastName VARCHAR(255))");
            LOGGER.info("h2数据库启动成功");
        } catch (SQLException e) {
            System.out.println("h2数据库启动失败");
            e.printStackTrace();
        }
    }
}