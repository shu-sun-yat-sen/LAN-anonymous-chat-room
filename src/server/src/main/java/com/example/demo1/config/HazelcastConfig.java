package com.example.demo1.config;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class HazelcastConfig {

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.username}")
    private String dataSourceUsername;

    @Value("${spring.datasource.password}")
    private String dataSourcePassword;

    @Value("${mycomputerIp}")
    private String ip;

    @Value("${multicastIp}")
    private  String multicastIp;

    @Value("${multicastport}")
    private Integer multicastport;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(dataSourceUrl);
        dataSource.setUsername(dataSourceUsername);
        dataSource.setPassword(dataSourcePassword);
        return dataSource;
    }

    @Bean
    public HazelcastInstance hazelcastInstance() {
            // 创建并配置Hazelcast实例
            Config config = new Config();
            config.setClusterName("my-cluster");
            config.getJetConfig().setEnabled(true);
        config.getNetworkConfig().getInterfaces().setEnabled(true).addInterface(ip);
        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(true);
        config.getNetworkConfig().getJoin().getMulticastConfig().setMulticastGroup(multicastIp);
        config.getNetworkConfig().getJoin().getMulticastConfig().setMulticastPort(multicastport);
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);
        return instance;
    }
}
