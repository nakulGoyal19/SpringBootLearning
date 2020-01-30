
package io.learning.springbootstarter;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseApiApp {

	public static void main(String[] args) {
		//SpringApplication.run(CourseApiApp.class, args);
		SpringApplication app = new SpringApplication(CourseApiApp.class);
        app.setDefaultProperties(Collections
          .singletonMap("server.port", "8082"));
        app.run(args);
	}

}
