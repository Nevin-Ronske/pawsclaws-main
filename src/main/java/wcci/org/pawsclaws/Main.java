package wcci.org.pawsclaws; // Package declaration for the Paws and Claws application

import org.springframework.boot.SpringApplication; // Importing SpringApplication for launching the application
import org.springframework.boot.autoconfigure.SpringBootApplication; // Importing SpringBootApplication annotation

// Main entry point for the Paws and Claws application
@SpringBootApplication // Indicates that this is a Spring Boot application
public class Main {

	// Main method that serves as the entry point of the application
	public static void main(String[] args) {
		// Running the Spring application
		SpringApplication.run(Main.class, args);
	}
}
