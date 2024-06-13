package com.example.demo1;

import com.example.demo1.model.Customer;
import com.example.demo1.model.Person;
import com.example.demo1.service.CustomerService;
import com.example.demo1.service.PersonService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@SpringBootApplication
public class Demo1Application {

	@Autowired
	private CustomerService hazelcastService;

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);

	}

	@Component
	public static class InitService {

		@Autowired
		private CustomerService customerService;

		@Autowired
		private PersonService personService;
		@PostConstruct
		public void init() {
			// 初始化方法中不再写入数据
		}

		@EventListener(ApplicationReadyEvent.class)
		public void populateDataAfterTableCreation() {


			// 表创建完成后执行的代码写在这里
//			customerService.writeToMap(1, new Customer("Joe", "Smith"));
			customerService.writeToMap(2, new Customer("Ali", "Selam"));
			customerService.writeToMap(3, new Customer("Avi", "Noyan"));

//			Customer c1 = customerService.readFromMap(1);
			Customer c2 = customerService.readFromMap(2);
			Customer c3 = customerService.readFromMap(3);
//			System.out.println(c1);
			System.out.println(c2);
			System.out.println(c3);

//			personService.addPerson(new Person("P1", "Smith"));
			personService.addPerson(new Person("P2", "Selam"));
			personService.addPerson(new Person("P3", "Noyan"));

			List<Person> list = personService.getAllPersons();
			for(Person p : list){
				System.out.println(p);
			}
		}
	}

}
