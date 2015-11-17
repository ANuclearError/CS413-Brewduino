package cs413.brewduino_server.controller;

import cs413.brewduino_server.model.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Aidan O'Grady
 * @since 1.0
 */
@Controller
public class BrewduinoController {

    @RequestMapping(value="/brewduino", method = RequestMethod.POST)
    public String brewduinoSubmit(@ModelAttribute Request req, Model model) {
        model.addAttribute("brewduino", req);
        return "result";
    }
}
