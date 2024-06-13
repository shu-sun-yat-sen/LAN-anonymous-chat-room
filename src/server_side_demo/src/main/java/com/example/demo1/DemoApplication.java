package com.example.demo1;

import com.example.demo1.model.User;
import com.example.demo1.service.CacheService.UserMap;
import com.example.demo1.service.DbService.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}

	@Component
	public static class InitService {  // 示例代码，演示Hazelcast中Imap的使用

		@Autowired
		private UserMap userMap;

		@Autowired
		UserService userService;

		@PostConstruct
		public void init() {
			// 初始化方法中不再写入数据
		}

		@EventListener(ApplicationReadyEvent.class)
		public void populateDataAfterTableCreation() {


			// 表创建完成后执行的代码写在这里
			userMap.writeToMap("u1", new User("u1", "name1", "passwd1", "cookie1"));
			userMap.writeToMap("u2", new User("u2", "name2", "passwd2", "cookie2"));
			userMap.writeToMap("u3", new User("u3", "name3", "passwd3", "cookie3"));

			User c1 = userMap.readFromMap("u1");
			User c2 = userMap.readFromMap("u2");
			User c3 = userMap.readFromMap("u3");
			System.out.println(c1);
			System.out.println(c2);
			System.out.println(c3);

		}
	}

}
