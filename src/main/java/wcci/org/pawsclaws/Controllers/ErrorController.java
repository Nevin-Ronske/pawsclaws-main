package wcci.org.pawsclaws.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// Controller to handle error pages
@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    // Maps requests to the "/error" URL to this method
    @RequestMapping("/error")
    public String handleError() {
        return "Shelter/ErrorMessage"; // Returns the view name for the error page
    }
}
