package wcci.org.pawsclaws; // Package declaration for the Paws and Claws application

import org.springframework.context.annotation.Bean; // Importing Spring's Bean annotation
import org.springframework.context.annotation.Configuration; // Importing Spring's Configuration annotation
import org.springframework.web.client.RestTemplate; // Importing RestTemplate for making HTTP requests

// Configuration class for setting up application beans
@Configuration
public class AppConfig {

    // Method to create and configure a RestTemplate bean
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(); // Returning a new instance of RestTemplate
    }
}
