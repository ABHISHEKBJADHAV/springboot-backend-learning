package com.demo.SpringBootDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootDemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootDemoApplication.class, args); // Context is like IOC container
//		Note : Object of Alien (or we can say bean) gets created at line no 11 i.e. when context created
		Alien obj1 = context.getBean(Alien.class);
		obj1.code();
//		Alien obj2 = context.getBean(Alien.class);
//		obj1.code();
//		System.out.println("Are beans equal: "+obj1.equals(obj2));
//		System.out.println(obj1.hashCode()+" "+obj2.hashCode());
	}

}
