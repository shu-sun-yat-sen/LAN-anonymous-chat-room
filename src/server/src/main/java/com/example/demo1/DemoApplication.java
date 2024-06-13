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




		}
	}

}
