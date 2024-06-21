package com.example.demo1.service.CacheService;

import com.example.demo1.model.Gomoku;
import com.example.demo1.model.Room;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GomoMap {
    @Autowired
    private HazelcastInstance hazelcastInstance;
    private IMap<String, Gomoku> gomoMap;

    @PostConstruct
    private void init(){
        gomoMap = hazelcastInstance.getMap("Gomoap");
    }

    public void writeToMap(String key, Gomoku value) {
        gomoMap.put(key, value);
    }
    public void updateMap(String key,Gomoku value){
        gomoMap.delete(key);
        gomoMap.put(key,value);
    }
    public void deletefromMap(String key){
        gomoMap.delete(key);
    }
    public Gomoku readFromMap(String key) {
        return gomoMap.get(key);
    }
}
