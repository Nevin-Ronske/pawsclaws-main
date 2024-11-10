package wcci.org.pawsclaws.Controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import wcci.org.pawsclaws.DTO.*;
import wcci.org.pawsclaws.Enums.*;
import wcci.org.pawsclaws.Services.ConfirmationService;
import wcci.org.pawsclaws.Services.PetService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@Controller
public class PetController {

    private final ConfirmationService confirmationService; // Service for handling confirmations

    // Constructor injection for confirmationService and petService
    public PetController(ConfirmationService confirmationService, PetService petService) {
        this.confirmationService = confirmationService; // Initializing confirmation service
    }

    @Autowired
    private PetService service; // Automatically wires the PetService bean into this controller

    // Mapping for the root and home URLs
    @GetMapping({ "/", "/home" })
    public String getAllPets(Model model) { // Returns the view to display all pets
        try {
            List<PetDTO> pets = service.getAllPets(); // Retrieves the list of pets
            model.addAttribute("pets", pets); // Adds pets list to the model
            model.addAttribute("title", "Shelter Pets"); // Sets the page title

            return "Shelter/ViewPets"; // Renders the Shelter/ViewPets view

        } catch (Exception ex) {
            // If an error occurs, set error information and return an error page
            ErrorDataDTO errorData = new ErrorDataDTO();
            errorData.setErrorMessage(ex.getMessage());
            errorData.setErrorCode(500);
            model.addAttribute("errorData", errorData); // Adds error data to the model
            model.addAttribute("title", "Shelter Pets - Error");

            return "Shelter/ErrorMessage"; // Renders the Shelter/ErrorMessage view
        }
    }

    // Mapping for viewing details of a specific pet
    @GetMapping("details/{id}")
    public String getDetails(@PathVariable long id, Model model) {
        StatusDTO status = new StatusDTO(); // Status data for the pet
        PetDTO pet = service.getPetById(id); // Retrieves pet by ID
        if (pet == null) {
            // If the pet isn't found, return an error message
            model.addAttribute("errorMessage",
                    "Pet not found. It might have been adopted, died, or is no longer in the shelter.");
            return "Shelter/ErrorMessage"; // Redirect to an error page
        }
        try {
            model.addAttribute("pet", pet); // Adds pet details to the model
            model.addAttribute("title", "Details for " + pet.getName()); // Sets the page title
            model.addAttribute("status", status); // Adds pet status to the model
            String image = service.getPetImage(pet.getName(), pet.getPetType().toString()); // Fetches the pet's image
            model.addAttribute("image", image); // Adds image to the model
            return "Shelter/ViewDetails"; // Renders the Shelter/ViewDetails view
        } catch (HttpClientErrorException ex) {
            // Handles specific error when fetching the pet details
            if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
                model.addAttribute("errorMessage", "Pet not found or has been adopted.");
                return "Shelter/ErrorMessage"; // Redirect to an error page
            }
            throw ex; // Re-throw other exceptions
        }
    }

    // Mapping for the create pet form
    @GetMapping("create")
    public String createPet(Model model) {
        AdmissionsDTO addedPet = new AdmissionsDTO(null, 0, null); // New pet form data
        model.addAttribute("pet", addedPet); // Adds pet form data to the model
        model.addAttribute("title", "Create pet"); // Sets the page title
        model.addAttribute("petTypes", PetType.values()); // Adds pet types to the model for a dropdown

        return "Shelter/CreatePet"; // Renders the Shelter/CreatePet view
    }

    // Handling the submission of a new pet creation form
    @PostMapping("saveadd")
    public String saveAdd(@ModelAttribute AdmissionsDTO pet, Model model) {
        try {
            service.saveAdd(pet); // Saves the new pet to the system
        } catch (Exception ex) {
            // If an error occurs, populate the form with error details and redisplay
            pet.setErrorMessage(ex.getMessage());
            pet.setErrorCode(400);
            model.addAttribute("pet", pet); // Adds pet with error data to the model
            model.addAttribute("title", "Details for " + "Create Pet");
            model.addAttribute("petTypes", PetType.values()); // Adds pet types to the model

            return "Shelter/CreatePet"; // Redisplay the form with errors
        }
        return "redirect:/home"; // Redirects to the home page upon success
    }

    // Mapping for the edit pet form
    @GetMapping("edit/{id}")
    public String editPet(@PathVariable long id, Model model) {
        EditPetDTO pet = new EditPetDTO(service.getPetById(id)); // Retrieves the pet to edit
        model.addAttribute("pet", pet); // Adds pet data to the model
        model.addAttribute("title", "Edit pet"); // Sets the page title
        model.addAttribute("petTypes", PetType.values()); // Adds pet types for editing

        return "Shelter/EditPet"; // Renders the Shelter/EditPet view
    }

    // Mapping to feed a pet
    @GetMapping("feed/{id}")
    public String feedAPet(@PathVariable long id, Model model) {
        StatusDTO status = service.carePet(id, "feed"); // Feeds the pet and retrieves its status
        PetDTO pet = service.getPetById(id); // Retrieves the pet by ID
        if (pet == null) {
            // If the pet isn't found, return an error message
            model.addAttribute("errorMessage",
                    "Pet not found. It might have been adopted, died, or is no longer in the shelter.");
            return "Shelter/ErrorMessage"; // Redirect to error page
        }
        model.addAttribute("pet", pet); // Adds pet details to the model
        model.addAttribute("title", "Details for " + pet.getName()); // Sets the page title
        model.addAttribute("petTypes", PetType.values()); // Adds pet types to the model
        model.addAttribute("status", status); // Adds status to the model

        return "Shelter/ViewDetails"; // Renders the Shelter/ViewDetails view
    }

    // Similar mappings for watering, playing, healing pets
    @GetMapping("water/{id}")
    public String waterAPet(@PathVariable long id, Model model) {
        StatusDTO status = service.carePet(id, "water");
        PetDTO pet = service.getPetById(id);
        if (pet == null) {
            model.addAttribute("errorMessage",
                    "Pet not found. It might have been adopted, died, or is no longer in the shelter.");
            return "Shelter/ErrorMessage"; // Redirect to error page
        }
        model.addAttribute("pet", pet);
        model.addAttribute("title", "Details for " + pet.getName());
        model.addAttribute("petTypes", PetType.values());
        model.addAttribute("status", status);

        return "Shelter/ViewDetails";
    }

    @GetMapping("play/{id}")
    public String playAPet(@PathVariable long id, Model model) {
        StatusDTO status = service.carePet(id, "play");
        PetDTO pet = service.getPetById(id);
        if (pet == null) {
            model.addAttribute("errorMessage",
                    "Pet not found. It might have been adopted, died, or is no longer in the shelter.");
            return "Shelter/ErrorMessage"; // Redirect to error page
        }
        model.addAttribute("pet", pet);
        model.addAttribute("title", "Details for " + pet.getName());
        model.addAttribute("petTypes", PetType.values());
        model.addAttribute("status", status);

        return "Shelter/ViewDetails";
    }

    @GetMapping("heal/{id}")
    public String healAPet(@PathVariable long id, Model model) {
        StatusDTO status = service.carePet(id, "heal");
        PetDTO pet = service.getPetById(id);
        if (pet == null) {
            model.addAttribute("errorMessage",
                    "Pet not found. It might have been adopted, died, or is no longer in the shelter.");
            return "Shelter/ErrorMessage"; // Redirect to error page
        }
        model.addAttribute("pet", pet);
        model.addAttribute("title", "Details for " + pet.getName());
        model.addAttribute("petTypes", PetType.values());
        model.addAttribute("status", status);

        return "Shelter/ViewDetails";
    }

    // Mapping to adopt a pet
    @GetMapping("adoptPet/{id}")
    public String removeAPet(@PathVariable long id, Model model) {
        try {
            service.deletePetById(id); // Deletes the pet by ID
        } catch (Exception ex) {
            ErrorDataDTO errorData = new ErrorDataDTO();
            errorData.setErrorMessage(ex.getMessage());
            errorData.setErrorCode(500);
            model.addAttribute("errorData", errorData);
            model.addAttribute("title", "Error Adopting Pet");
            return "Shelter/ErrorMessage"; // Renders error page
        }
        return "redirect:/home"; // Redirect to home upon success
    }

    // Handling the submission of an edited pet form
    @PutMapping("saveedit")
    public String saveEdit(@ModelAttribute EditPetDTO pet, Model model) {
        try {
            service.saveEdit(pet); // Saves the edited pet details
        } catch (Exception ex) {
            pet.setErrorMessage(ex.getMessage());
            pet.setErrorCode(400);
            model.addAttribute("pet", pet); // Adds pet with error data to the model
            model.addAttribute("title", "Details for " + "Edit Pet");
            model.addAttribute("petTypes", PetType.values());

            return "Shelter/EditPet"; // Redisplay the form with errors
        }
        return "redirect:/home"; // Redirect to home upon success
    }

    // Mapping for the adoption confirmation
    @GetMapping("/adoptPetConfirmation/{id}")
    public String confirmAction(@PathVariable EditPetDTO pet, Long id, Model model) {
        boolean confirmed = confirmationService.confirmAction("adopt"); // Confirm adoption

        if (confirmed) {
            service.deletePetById(id); // Deletes the pet upon confirmation
            return "redirect:/adoptionSuccess"; // Redirect to a success page
        } else {
            return "redirect:/adoptionCancelled"; // Redirect to a cancellation page or message
        }
    }
}
