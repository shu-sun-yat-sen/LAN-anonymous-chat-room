package com.example.demo1.service;

import com.example.demo1.model.Customer;
import com.example.demo1.model.Person;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private HazelcastInstance hazelcastInstance;

    private IMap<Integer, Customer> customerMap;
    @PostConstruct
    private void init(){
        customerMap = hazelcastInstance.getMap("CustomerMap");
    }

    public void writeToMap(Integer key, Customer value) {
        customerMap.put(key, value);
    }

    public Customer readFromMap(Integer key) {
        return customerMap.get(key);
    }
}
