package wcci.org.pawsclaws.Services; // Package declaration for Services related to Paws and Claws application

import java.util.*; // Importing utility classes for handling collections

import org.springframework.http.*; // Importing Spring's HTTP classes
import org.springframework.stereotype.Service; // Importing Spring's Service annotation
import org.springframework.web.client.HttpClientErrorException; // Importing exception for HTTP client errors
import org.springframework.web.client.RestTemplate; // Importing RestTemplate for making HTTP requests
import wcci.org.pawsclaws.DTO.*; // Importing Data Transfer Objects
import wcci.org.pawsclaws.Enums.PetType; // Importing the PetType enumeration

// Service class for handling pet-related operations
@Service
public class PetService {
    private final RestTemplate restTemplate; // RestTemplate instance for making REST API calls
    public final String server = "http://localhost:8080"; // Base URL for the API server

    // Constructor to initialize PetService with RestTemplate
    public PetService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Method to retrieve all pets from the shelter
    public List<PetDTO> getAllPets() {
        String url = server + "/api/v1/shelter"; // Constructing the URL for fetching pets
        PetDTO[] pets = restTemplate.getForObject(url, PetDTO[].class); // Making the GET request
        return Arrays.asList(pets); // Converting array to list and returning
    }

    // Method to retrieve a pet by its ID
    public PetDTO getPetById(long id) {
        String url = server + "/api/v1/shelter/" + id; // Constructing the URL for the specific pet
        try {
            return restTemplate.getForObject(url, PetDTO.class); // Making the GET request
        } catch (HttpClientErrorException ex) {
            // Handling HTTP client errors
            if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
                return null; // Pet not found, return null
            }
            throw ex; // Re-throw other errors
        }
    }

    // Method to save a new pet (admissions)
    public PetDTO saveAdd(AdmissionsDTO admit) {
        String url = server + "/api/v1/shelter"; // Constructing the URL for adding a pet
        HttpHeaders headers = new HttpHeaders(); // Creating HTTP headers
        headers.set("Content-Type", "application/json"); // Setting the content type

        // Creating the request entity with the admissions data and headers
        HttpEntity<AdmissionsDTO> requestEntity = new HttpEntity<>(admit, headers);
        PetDTO pet = restTemplate.postForObject(url, requestEntity, PetDTO.class); // Making the POST request
        return pet; // Returning the created pet object
    }

    // Method to edit an existing pet's details
    public PetDTO saveEdit(EditPetDTO edit) {
        String url = server + "/api/v1/shelter"; // Constructing the URL for editing a pet
        HttpHeaders headers = new HttpHeaders(); // Creating HTTP headers
        headers.set("Content-Type", "application/json"); // Setting the content type

        // Creating the request entity with the edit data and headers
        HttpEntity<EditPetDTO> requestEntity = new HttpEntity<>(edit, headers);
        PetDTO pet = restTemplate.postForObject(url, requestEntity, PetDTO.class); // Making the POST request
        return pet; // Returning the updated pet object
    }

    // Method to care for a pet by performing a specific action
    public StatusDTO carePet(long id, String action) {
        String url = server + "/api/v1/shelter/" + action + "/" + id; // Constructing the URL for the action
        StatusDTO status = restTemplate.getForObject(url, StatusDTO.class); // Making the GET request
        return status; // Returning the status of the action
    }

    // Method to delete a pet by its ID
    public void deletePetById(long id) {
        String url = server + "/api/v1/shelter/" + id; // Constructing the URL for deleting a pet
        restTemplate.delete(url); // Making the DELETE request
    }

    // Method to get a pet's image based on its name and type
    public String getPetImage(String name, String petTypeString) {
        name = name.replace(" ", "_"); // Replacing spaces in the pet's name with underscores
        String result = ""; // Variable to store the result URL
        PetType petType = PetType.valueOf(petTypeString); // Converting the string to PetType enum

        // Determining the type of pet and getting the corresponding image
        switch (petType) {
            case Cat: {
                result = getCatImage(name); // Getting cat image
                break;
            }
            case Dog: {
                result = getDogImage(name); // Getting dog image
                break;
            }
            case RoboticDog:
            case RoboticCat: {
                result = getRobotImage(name); // Getting robotic pet image
                break;
            }
            default: {
                break; // No action for unrecognized pet type
            }
        }
        return result; // Returning the image URL
    }

    // Method to fetch a cat image from an external API
    public String getCatImage(String name) {
        String url = "https://api.thecatapi.com/v1/images/search"; // URL for fetching cat images
        CatImageDTO[] pet = restTemplate.getForObject(url, CatImageDTO[].class); // Making the GET request

        if (pet == null) {
            return ""; // Returning an empty string if no image is found
        }
        return pet[0].getUrl(); // Returning the URL of the first cat image
    }

    // Method to fetch a dog image (static placeholder)
    public String getDogImage(String name) {
        String url = "https://place.dog/300/300"; // Static URL for fetching dog images
        return url; // Returning the URL
    }

    // Method to generate a robot image based on the pet's name
    public String getRobotImage(String name) {
        String url = "https://robohash.org/" + name; // URL for generating robot images
        return url; // Returning the URL
    }
}
