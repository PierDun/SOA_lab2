package app.demo.controller;

import app.demo.model.Dragon;
import app.demo.model.dto.DragonFromClient;
import app.demo.repo.DragonRepository;
import app.demo.service.DragonService;
import app.demo.util.ServletUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/dragons")
public class DragonController {
    private final DragonService dragonService;

    DragonController(DragonRepository dragonRepository) {
        this.dragonService = new DragonService(dragonRepository);
    }

    private DragonRequestParams getFilterParams(Map<String, String[]> map) {
        return new DragonRequestParams(map);
    }

    @GetMapping(value = "/filter")
    ResponseEntity<?> getAllDragons(HttpServletRequest httpServletRequest ) {
        Map<String, String[]> requestParameterMap = httpServletRequest.getParameterMap();
        DragonRequestParams filterParams = this.getFilterParams(requestParameterMap);
        return dragonService.getAllDragons(filterParams);
    }

    @GetMapping
    public String getAllDragons(Model model) {
        int numberOfRecordsPerPage = 10;
        List<Dragon> dragons = dragonService.getAllDragons();
        int dragonsQuality = (int)Math. ceil( (double) (dragons.size()+1) / numberOfRecordsPerPage);
        model.addAttribute("pagesQuality", IntStream.range(1, (int)Math. ceil( dragonsQuality + 1)).toArray());
        model.addAttribute("dragonsLength", dragons.size());
        model.addAttribute("dragons", dragons);
        return "index";
    }

    @GetMapping(value = "/{id}")
    String getDragonById (@PathVariable String id, Model model) {
        try {
            long dragon_id = Long.parseLong(id.replace("?", ""));
            Dragon dragon = dragonService.getDragonById(dragon_id);
            model.addAttribute("dragon", dragon);
        } catch (EntityNotFoundException e) {
            model.addAttribute("msg", "Cannot find dragon with id " + id);
        } catch (NumberFormatException e) {
            model.addAttribute("msg", "The following value isn't a number");
        }
        return "get-by-id";
    }

    @PostMapping
    ResponseEntity<?> addDragon(HttpServletRequest request) throws Exception {
        String dragonBody = ServletUtil.getBody(request);
        DragonFromClient newDragon = new DragonFromClient(dragonBody);
        return dragonService.createDragon(newDragon);
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<?> updateDragon(HttpServletRequest request, @PathVariable Long id) throws Exception {
        String dragonBody = ServletUtil.getBody(request);
        DragonFromClient updatedDragon = new DragonFromClient(dragonBody);
        updatedDragon.setId(id);
        return dragonService.updateDragon(updatedDragon);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> deleteCity(@PathVariable Long id) {
        return dragonService.deleteDragon(id);
    }
}