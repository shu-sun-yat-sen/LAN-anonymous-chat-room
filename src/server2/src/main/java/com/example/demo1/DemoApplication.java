package com.example.demo1;

import com.example.demo1.model.Room;
import com.example.demo1.model.Talk;
import com.example.demo1.model.User;
import com.example.demo1.service.CacheService.RoomMap;
import com.example.demo1.service.CacheService.TalkMap;
import com.example.demo1.service.CacheService.UserMap;
import com.example.demo1.service.DbService.RoomService;
import com.example.demo1.service.DbService.TalkService;
import com.example.demo1.service.DbService.UserService;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}

	@Component
	public static class InitService {  // 把本地的数据上传到集群
		@Autowired
		HazelcastInstance hazelcastInstance;
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
		@PostConstruct
		public void init() {
			// 初始化方法中不再写入数据
		}

		@EventListener(ApplicationReadyEvent.class)
		public void populateDataAfterTableCreation() {
			int members=hazelcastInstance.getCluster().getMembers().size();
			if(members<2) {
				List<User> users = userService.findAllUsers();
				for (User i : users) {
					userMap.writeToMap(i.getUserId(), i);
				}
				List<Room> rooms = roomService.findAllRooms();
				for (Room i : rooms) {
					roomMap.writeToMap(i.getRoomName(), i);
				}
				List<Talk> talks = talkService.findalltalk();
				for (Talk i : talks) {
					talkMap.writeToMap(i.getTime(), i);
				}
			}
			else {
				userService.deleteAllUsers();
				roomService.deleteAllRooms();
				talkService.deleteAllTalks();
				IMap<String, User> mapUser=hazelcastInstance.getMap("UserMap");
				for (Map.Entry<String, User> entry : mapUser.entrySet()) {
					userService.saveUser(entry.getValue());
				}
				IMap<String, Room> mapRoom=hazelcastInstance.getMap("RoomMap");
				for (Map.Entry<String, Room> entry : mapRoom.entrySet()) {
					roomService.saveRoom(entry.getValue());
				}
				IMap<String, Talk> mapTalk=hazelcastInstance.getMap("TalkMap");
				for (Map.Entry<String, Talk> entry : mapTalk.entrySet()) {
					talkService.saveTalk(entry.getValue());
				}
			}

		}
	}

}
