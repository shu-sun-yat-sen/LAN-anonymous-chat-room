package com.example.demo1.service.CacheService;

import com.example.demo1.model.Room;
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
    private IMap<String, User> userMap;

    @PostConstruct
    private void init(){
        userMap = hazelcastInstance.getMap("UserMap");
    }

    public void writeToMap(String key, User value) {
        userMap.put(key, value);
    }
    public void updateMap(String key, User value){
        userMap.delete(key);
        userMap.put(key,value);
    }
    public void deletefromMap(String key){
        userMap.delete(key);
    }
    public User readFromMap(String key) {
        return userMap.get(key);
    }


}
