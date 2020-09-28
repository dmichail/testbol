package com.bol.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AssignmentApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(AssignmentApplication.class, args);
		}catch (Exception ex) {
			ex.printStackTrace();
			ex.getCause();
		}
	}

}
