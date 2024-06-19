package com.example.demo1.service.CacheService;

import com.example.demo1.model.Room;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomMap {
    @Autowired
    private HazelcastInstance hazelcastInstance;
    private IMap<String, Room> roomMap;

    @PostConstruct
    private void init(){
        roomMap = hazelcastInstance.getMap("RoomMap");
    }

    public void writeToMap(String key, Room value) {
        roomMap.put(key, value);
    }
    public void updateMap(String key,Room value){
        roomMap.delete(key);
        roomMap.put(key,value);
    }
    public void deletefromMap(String key){
        roomMap.delete(key);
    }
    public Room readFromMap(String key) {
        return roomMap.get(key);
    }
}
