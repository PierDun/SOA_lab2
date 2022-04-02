package app.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import app.demo.controller.DragonRequestParams;
import app.demo.model.Dragon;
import app.demo.model.OperationResponse;
import app.demo.model.PaginationResult;
import app.demo.model.dto.DragonFromClient;
import app.demo.repo.DragonFilterSpecification;
import app.demo.repo.DragonRepository;
import app.demo.validator.DragonValidator;
import app.demo.validator.ValidateFieldsException;
import app.demo.model.Error;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DragonService {
    private final DragonValidator dragonValidator;
    private final DragonRepository dragonRepository;

    public DragonService(DragonRepository dragonRepository) {
        this.dragonValidator = new DragonValidator();
        this.dragonRepository = dragonRepository;
    }

    public ResponseEntity<?> createDragon(DragonFromClient newDragon) throws Exception {
        try {
            Dragon validDragon = dragonValidator.validate(newDragon);
            validDragon.setCreationDate(ZonedDateTime.now());
            Long id = dragonRepository.save(validDragon).getId();
            return ResponseEntity.status(201).body(new OperationResponse(id, "Dragon created successfully"));
        } catch (ValidateFieldsException ex) {
            return sendErrorList(ex);
        }
    }

    public ResponseEntity<?> updateDragon(DragonFromClient updatedDragon) throws Exception {
        try {
            Dragon validDragon = dragonValidator.validate(updatedDragon);
            boolean isFound = dragonRepository.existsById(updatedDragon.getId());
            if (isFound) {
                validDragon.setCreationDate(dragonRepository.findCreationDateByDragonId(updatedDragon.getId()));
                dragonRepository.save(validDragon);
                return ResponseEntity.status(200).body(new OperationResponse(updatedDragon.getId(), "Dragon updated successfully"));
            } else {
                return ResponseEntity.status(404).body(new OperationResponse(updatedDragon.getId(), "Cannot find dragon with id " + updatedDragon.getId()));
            }
        } catch (ValidateFieldsException ex) {
            return sendErrorList(ex);
        }
    }

    public ResponseEntity<?> deleteDragon(Long id) {
        boolean isFound = dragonRepository.existsById(id);
        dragonRepository.deleteById(id);
        if (isFound) {
            return ResponseEntity.status(200).body(new OperationResponse(id, "Dragon deleted successfully"));
        } else {
            return ResponseEntity.status(404).body(new OperationResponse(id, "Cannot find dragon with id " + id));
        }
    }

    public ResponseEntity<?> getAllDragons(DragonRequestParams filterParams) {
        DragonFilterSpecification spec = new DragonFilterSpecification(filterParams);
        try {
            Sort currentSorting = filterParams.parseSorting();
            Pageable sortedBy = PageRequest.of(filterParams.page, filterParams.size, currentSorting);
            Page<Dragon> res = dragonRepository.findAll(spec, sortedBy);
            long count = dragonRepository.count(spec);
            PaginationResult r = new PaginationResult(filterParams.size, filterParams.page, count, res.getContent());
            return ResponseEntity.status(200).body(r);
        } catch (ParseException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    public List<Dragon> getAllDragons () {
        return dragonRepository.findAllDragons();
    }

    public Dragon getDragonById(Long id) {
        return dragonRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    private ResponseEntity<?> sendErrorList(ValidateFieldsException ex) {
        StringBuilder errors = new StringBuilder();
        for (Error errorMsg: ex.getErrorMsg()) {
            errors.append(errorMsg.getName()).append(": ");
            errors.append(errorMsg.getDesc()).append("\n");
        }
        return ResponseEntity.status(400).body(errors);
    }
}