package wcci.org.pawsclaws.Services;

import java.util.*;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import wcci.org.pawsclaws.DTO.*;

@Service
public class PetService {
    private final RestTemplate restTemplate;
    public final String server = "http://localhost:8080";

    public PetService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

    }

    public List<PetDTO> getAllPets() {
        String url = server + "/api/v1/shelter";
        PetDTO[] pets = restTemplate.getForObject(url, PetDTO[].class);
        return Arrays.asList(pets);
    }

    public PetDTO getPetById(long id) {
        String url = server + "/api/v1/shelter/" + id;
        try {
            return restTemplate.getForObject(url, PetDTO.class);
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
                return null; // Pet not found, return null
            }
            throw ex; // Re-throw other errors
        }
    }

    public PetDTO saveAdd(AdmissionsDTO admit) {
        String url = server + "/api/v1/shelter";
        HttpHeaders headers = new HttpHeaders();

        headers.set("Content-Type", "application/json");
        HttpEntity<AdmissionsDTO> requestEntity = new HttpEntity<>(admit, headers);
        PetDTO pet = restTemplate.postForObject(url, requestEntity, PetDTO.class);
        return pet;
    }

    public PetDTO saveEdit(EditPetDTO edit) {
        String url = server + "/api/v1/shelter";
        HttpHeaders headers = new HttpHeaders();

        headers.set("Content-Type", "application/json");
        HttpEntity<EditPetDTO> requestEntity = new HttpEntity<>(edit, headers);
        PetDTO pet = restTemplate.postForObject(url, requestEntity, PetDTO.class);
        return pet;
    }

    public StatusDTO carePet(long id, String action) {
        String url = server + "/api/v1/shelter/" + action + "/" + id;
        StatusDTO status = restTemplate.getForObject(url, StatusDTO.class);
        return status;
    }

    public void deletePetById(long id) {
        String url = server + "/api/v1/shelter/" + id;
        restTemplate.delete(url);

    }

}