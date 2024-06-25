package com.example.demo1.service.CacheService;

import com.example.demo1.model.GAME;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameMap {
    @Autowired
    private HazelcastInstance hazelcastInstance;
    private IMap<String, GAME> gomoMap;

    @PostConstruct
    private void init(){
        gomoMap = hazelcastInstance.getMap("GameMap");
    }

    public void writeToMap(String key, GAME value) {
        gomoMap.put(key, value);
    }
    public void updateMap(String key,GAME value){
        gomoMap.delete(key);
        gomoMap.put(key,value);
    }
    public void deletefromMap(String key){
        gomoMap.delete(key);
    }
    public GAME readFromMap(String key) {
        return gomoMap.get(key);
    }
}
