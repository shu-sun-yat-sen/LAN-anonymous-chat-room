package com.example.demo1.service;

import com.example.demo1.model.Customer;
import com.example.demo1.model.Person;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private HazelcastInstance hazelcastInstance;

    private IMap<Integer, Person> personListMap;

    @PostConstruct
    private void init(){
        personListMap = hazelcastInstance.getMap("PersonMap");
    }

    public void addPerson(Person person) {
        int nextKey = getNextKey();
        personListMap.put(nextKey, person);
    }

    public void removePerson(int key) {
        personListMap.remove(key);
    }

    public Person getPerson(int key) {
        return personListMap.get(key);
    }

    public List<Person> getAllPersons() {
        Collection<Person> values = personListMap.values();
        return List.copyOf(values);
    }

    private int getNextKey() {
        int maxKey = personListMap.keySet().stream().mapToInt(Integer::intValue).max().orElse(0);
        return maxKey + 1;
    }
}
