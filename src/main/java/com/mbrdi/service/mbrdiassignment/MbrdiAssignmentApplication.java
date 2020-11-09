package com.mbrdi.service.mbrdiassignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 
 * @author dhananjay
 *
 */
@SpringBootApplication
@EnableCaching
public class MbrdiAssignmentApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(MbrdiAssignmentApplication.class, args);
	}

}
