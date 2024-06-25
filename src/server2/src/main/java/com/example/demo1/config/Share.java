package com.example.demo1.config;

import com.example.demo1.model.GAME;
import com.example.demo1.model.Room;
import com.example.demo1.model.Talk;
import com.example.demo1.model.User;
import com.example.demo1.service.CacheService.GameMap;
import com.example.demo1.service.CacheService.RoomMap;
import com.example.demo1.service.CacheService.TalkMap;
import com.example.demo1.service.CacheService.UserMap;
import com.example.demo1.service.DbService.GameService;
import com.example.demo1.service.DbService.RoomService;
import com.example.demo1.service.DbService.TalkService;
import com.example.demo1.service.DbService.UserService;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
public class Share {
    @Autowired
    private HazelcastInstance hazelcastInstance;
    @Autowired
    private UserMap userMap;
    @Autowired
    private UserService userService;
    @Autowired
    private RoomMap roomMap;
    @Autowired
    private RoomService roomService;
    @Autowired
    private TalkMap talkMap;
    @Autowired
    private TalkService talkService;
    @Autowired
    private GameMap gameMap;
    @Autowired
    private GameService gameService;

    @Scheduled(cron="0/1 * *  * * ? ")   //每5秒执行一次
    public void execute(){

        IMap<String, User> mapUser=hazelcastInstance.getMap("UserMap");

        for (Map.Entry<String, User> entry : mapUser.entrySet()) {
//            System.out.print("User：");
//            System.out.print(entry.getKey());
//            System.out.print("\n");
                userService.saveUser(entry.getValue());
        }
        IMap<String, Room> mapRoom=hazelcastInstance.getMap("RoomMap");

        for (Map.Entry<String, Room> entry : mapRoom.entrySet()) {
//            System.out.print("Room：");
//            System.out.print(entry.getKey());
//            System.out.print("\n");

                roomService.saveRoom(entry.getValue());

        }
        IMap<String, Talk> mapTalk=hazelcastInstance.getMap("TalkMap");

        for (Map.Entry<String, Talk> entry : mapTalk.entrySet()) {
//                System.out.print("Talk：");
//                System.out.print(entry.getKey());
//                System.out.print("\n");
                talkService.saveTalk(entry.getValue());

        }
        IMap<String, GAME> mapGame=hazelcastInstance.getMap("GameMap");

        for (Map.Entry<String, GAME> entry : mapGame.entrySet()) {

//                System.out.print(entry.getKey());
//                System.out.print("\n");
                gameService.saveGame(entry.getValue());
        }
    }
}
