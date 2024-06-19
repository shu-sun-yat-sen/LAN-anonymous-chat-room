package com.example.demo1.service.CacheService;

import com.example.demo1.model.Talk;
import com.example.demo1.model.User;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TalkMap {
    @Autowired
    private HazelcastInstance hazelcastInstance;
    private IMap<String, Talk> talkMap;

    @PostConstruct
    private void init(){
        talkMap = hazelcastInstance.getMap("TalkMap");
    }

    public void writeToMap(String key, Talk value) {
        talkMap.put(key, value);
    }
    public void updateMap(String key, Talk value){
        talkMap.delete(key);
        talkMap.put(key,value);
    }
    public void deletefromMap(String key){
        talkMap.delete(key);
    }
    public Talk readFromMap(String key) {
        return talkMap.get(key);
    }
}
