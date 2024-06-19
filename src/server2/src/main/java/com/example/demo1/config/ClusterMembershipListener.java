package com.example.demo1.config;

import com.example.demo1.model.Room;
import com.example.demo1.model.User;
import com.example.demo1.service.CacheService.RoomMap;
import com.example.demo1.service.CacheService.TalkMap;
import com.example.demo1.service.CacheService.UserMap;
import com.hazelcast.cluster.MembershipEvent;
import com.hazelcast.cluster.MembershipListener;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class ClusterMembershipListener implements MembershipListener {
    @Autowired
    private final HazelcastInstance hazelcastInstance;
    @Autowired
    private RoomMap roomMap;
    @Autowired
    private UserMap userMap;
    @Autowired
    private TalkMap talkMap;
    @Override
    public void memberAdded(MembershipEvent membershipEvent) {
        System.out.println("Member added: " + membershipEvent.getMember().getAddress());
        synchronizeDatabase(membershipEvent);
    }
    @Override
    public void memberRemoved(MembershipEvent membershipEvent) {
        // 在这里实现成员移除时的逻辑，如果需要的话
    }
    public ClusterMembershipListener(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }
    private void synchronizeDatabase(MembershipEvent membershipEvent) {
        IMap<String, User> mapUser=hazelcastInstance.getMap("UserMap");
        for (Map.Entry<String, User> entry : mapUser.entrySet()) {
            String key = entry.getKey();
            User value = entry.getValue();
            System.out.println("Synced data: Key=" + key + ", Value=" + value);
        }
    }
}
