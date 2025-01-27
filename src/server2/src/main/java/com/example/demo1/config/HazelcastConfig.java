package com.example.demo1.config;
import com.example.demo1.model.HazelcastH2MapStoreGame;
import com.example.demo1.model.HazelcastH2MapStoreRoom;
import com.example.demo1.model.HazelcastH2MapStoreTalk;
import com.example.demo1.model.HazelcastH2MapStoreUser;
import com.example.demo1.utils.Getip;
import com.hazelcast.cluster.Cluster;
import com.hazelcast.cluster.Member;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Objects;

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
            Getip ip1=new Getip();
            if(Objects.equals(ip1.getip(), "error"))
                config.getNetworkConfig().getInterfaces().setEnabled(true).addInterface(ip);
            else
                config.getNetworkConfig().getInterfaces().setEnabled(true).addInterface(ip1.getip());
            config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(true);
            config.getNetworkConfig().getJoin().getMulticastConfig().setMulticastGroup(multicastIp);
            config.getNetworkConfig().getJoin().getMulticastConfig().setMulticastPort(multicastport);

            MapConfig mapConfigUser=config.getMapConfig("UserMap");
            mapConfigUser.setBackupCount(2);
            mapConfigUser.setReadBackupData(true);
            mapConfigUser.getMapStoreConfig()
                    .setEnabled(true)
                    .setImplementation(new HazelcastH2MapStoreUser(dataSource()));
            MapConfig gameConfigUser1=config.getMapConfig("GameMap");
            gameConfigUser1.setBackupCount(2);
            gameConfigUser1.setReadBackupData(true);
            gameConfigUser1.getMapStoreConfig()
                    .setEnabled(true)
                    .setImplementation(new HazelcastH2MapStoreGame(dataSource()));
            MapConfig mapConfigRoom=config.getMapConfig("RoomMap");
            mapConfigRoom.setBackupCount(2);
            mapConfigRoom.setReadBackupData(true);
            mapConfigRoom.getMapStoreConfig()
                    .setEnabled(true)
                    .setImplementation(new HazelcastH2MapStoreRoom(dataSource()));
            MapConfig mapConfigTalk=config.getMapConfig("TalkMap");
            mapConfigTalk.setBackupCount(2);
            mapConfigTalk.setReadBackupData(true);
            mapConfigTalk.getMapStoreConfig()
                    .setEnabled(true)
                    .setImplementation(new HazelcastH2MapStoreTalk(dataSource()));
            HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);
            ClusterMembershipListener membershipListener = new ClusterMembershipListener(instance);

            instance.getCluster().addMembershipListener(membershipListener);
            return instance;
    }
}
