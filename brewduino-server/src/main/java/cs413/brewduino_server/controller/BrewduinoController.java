package cs413.brewduino_server.controller;

import cs413.brewduino_server.model.Brewduino;
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

    private Brewduino brewduino = new Brewduino();

    @RequestMapping(value="/brewduino", method=RequestMethod.GET)
    public String brewForm(Model model) {
        model.addAttribute("request", new Request());
        return "brewduino";
    }

    @RequestMapping(value="/brewduino", method = RequestMethod.POST)
    public String brewSubmit(@ModelAttribute Request request, Model model) {
        request.setString(request.toString());
        model.addAttribute("request", request);
        brewduino.brew(request);
        return "result";
    }
}
