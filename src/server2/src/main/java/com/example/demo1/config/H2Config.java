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
            statement.execute("CREATE TABLE IF NOT EXISTS T_USER (id VARCHAR(255) PRIMARY KEY, FAKE_NAME VARCHAR(255) , PASSWD VARCHAR(255),cookie VARCHAR(255),USERPIC VARCHAR(255))");
            statement.execute("CREATE TABLE IF NOT EXISTS ROOM ( ROOM_NAME VARCHAR(255) PRIMARY KEY, ROOM_OWNER_ID VARCHAR(255),ROOMPIC VARCHAR(255),NUMOFPEOPLE INT,PASS_WORD VARCHAR(255),MEMBERS_ID VARCHAR(255))");
            statement.execute("CREATE TABLE IF NOT EXISTS TALK ( TIME VARCHAR(255) PRIMARY KEY,CHATROOMNAME VARCHAR(255),SENDERID VARCHAR(255),SENDERNAME VARCHAR(255),SENDERPIC VARCHAR(255),CONTEXT TEXT,TYPE VARCHAR(255))");
            LOGGER.info("h2数据库启动成功");
        } catch (SQLException e) {
            System.out.println("h2数据库启动失败");
            e.printStackTrace();
        }
    }
}