package com.eparking.springboot.app;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.eparking.springboot.app.service.impl.SendEmailService;*/

@SpringBootApplication
public class SpringBootEparkingApplication{

	/*@Autowired
	private SendEmailService emailService;*/
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootEparkingApplication.class, args);
	}
	
	/*@EventListener(ApplicationReadyEvent.class)
	public void triggerWhenStarts() {
		emailService.sendEmail("fabiola201811028@gmail.com", "Hi there!", "test");
	}*/
	
	

}
