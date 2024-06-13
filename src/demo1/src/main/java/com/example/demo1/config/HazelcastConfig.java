package com.example.demo1.config;

import com.example.demo1.model.HazelcastH2MapStoreCustomer;
import com.example.demo1.model.HazelcastH2MapStorePerson;
import com.hazelcast.cluster.Cluster;
import com.hazelcast.cluster.Member;
import com.hazelcast.cluster.MembershipEvent;
import com.hazelcast.cluster.MembershipListener;
import com.hazelcast.config.Config;
import com.hazelcast.config.ListConfig;
import com.hazelcast.config.ListenerConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.LifecycleEvent;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import javax.swing.event.MenuListener;

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

            MapConfig mapConfigCustomer = config.getMapConfig("CustomerMap");
            mapConfigCustomer.setBackupCount(1);
            mapConfigCustomer.setReadBackupData(true);
            mapConfigCustomer.getMapStoreConfig()
                .setEnabled(true)
                    .setWriteDelaySeconds(0)
                .setImplementation(new HazelcastH2MapStoreCustomer(dataSource()));

        MapConfig mapConfigPerson = config.getMapConfig("PersonMap");
        mapConfigPerson.setBackupCount(1);
        mapConfigPerson.setReadBackupData(true);
        mapConfigPerson.getMapStoreConfig()
                .setEnabled(true)
                .setWriteDelaySeconds(0)
                .setImplementation(new HazelcastH2MapStorePerson(dataSource()));

        config.getNetworkConfig().getInterfaces().setEnabled(true)
                .addInterface(ip);
        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(true);
        config.getNetworkConfig().getJoin().getMulticastConfig().setMulticastGroup("224.2.2.3");
        config.getNetworkConfig().getJoin().getMulticastConfig().setMulticastPort(54327);

        HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);
        adjustBackupCount(instance);

        instance.getLifecycleService().addLifecycleListener(event -> {
            adjustBackupCount(instance);
        });

        return instance;
    }

    private void adjustBackupCount(HazelcastInstance instance) {
        Cluster cluster = instance.getCluster();
        int numberOfMembers = cluster.getMembers().size();
        int backupCount = Math.max(numberOfMembers, 1); // 最少保持一个备
        Config config = instance.getConfig();
        config.getMapConfig("CustomerMap").setBackupCount(backupCount);
        config.getMapConfig("PersonMap").setBackupCount(backupCount);
        System.out.println("备份数量调整到：" + backupCount);
    }
}
