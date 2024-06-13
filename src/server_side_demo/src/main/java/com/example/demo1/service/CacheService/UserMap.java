package com.example.demo1.service.CacheService;

import com.example.demo1.model.User;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMap {
    @Autowired
    private HazelcastInstance hazelcastInstance;
    private IMap<String, User> usertMap;

    @PostConstruct
    private void init(){
        usertMap = hazelcastInstance.getMap("UsertMap");
    }

    public void writeToMap(String key, User value) {
        usertMap.put(key, value);
    }

    public User readFromMap(String key) {
        return usertMap.get(key);
    }


}
