package app.demo.controller;

import app.demo.model.Coordinates;
import app.demo.model.Dragon;
import app.demo.model.DragonCave;
import app.demo.model.Person;
import app.demo.model.enums.Color;
import app.demo.model.enums.DragonType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    private static final List<Person> persons = new ArrayList<>();

    private static final List<Dragon> dragons = new ArrayList<>();

    static {
        persons.add(new Person("Bill", "Gates"));
        persons.add(new Person("Steve", "Jobs"));

        Coordinates coordinate1 = new Coordinates(0, 5, 5);
        Coordinates coordinate2 = new Coordinates(1, 2, 4);
        DragonCave dragonCave1 = new DragonCave(0, 5.0);
        DragonCave dragonCave2 = new DragonCave(1, 8.0);

        Dragon smaug = new Dragon(1L, "Smaug", 500L, Color.BLACK, DragonType.AIR, ZonedDateTime.now(), "fire-breather",
                coordinate1, dragonCave1);
        Dragon midir = new Dragon(2L, "Midir", 5000L, Color.RED, DragonType.UNDERGROUND, ZonedDateTime.now(), "laser",
                coordinate2, dragonCave2);

        dragons.add(smaug);
        dragons.add(midir);
    }

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {

        String message = "Hello Spring Boot + JSP";

        model.addAttribute("dragons", dragons);

        return "index";
    }

    @RequestMapping(value = { "/personList" }, method = RequestMethod.GET)
    public String viewPersonList(Model model) {
        model.addAttribute("persons", persons);

        return "personList";
    }
}