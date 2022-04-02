package app.demo.controller;

import app.demo.model.Dragon;
import app.demo.repo.DragonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/pages")
public class PagesController {
    DragonRepository dragonRepository;

    PagesController (DragonRepository dragonRepository) {
        this.dragonRepository = dragonRepository;
    }

    @RequestMapping(value = { "/add-dragon-form" }, method = RequestMethod.GET)
    public String showNewForm(Model model) {
        return "dragon-form";
    }

    @RequestMapping(value = { "/edit-form" }, method = RequestMethod.GET)
    public String showEditForm(HttpServletRequest request, Model model) {
        Long id = Long.parseLong(request.getParameter("id"));
        Optional<Dragon> dragon = dragonRepository.findById(id);
        model.addAttribute("dragon",dragon.get());
        return "dragon-form";
    }

    @RequestMapping(value = { "/get-by-id-form" }, method = RequestMethod.GET)
    public String showGetByIdForm(Model model) {
        return "get-by-id";
    }
}