package app.demo.validator;

import app.demo.model.Dragon;
import app.demo.model.dto.DragonFromClient;
import app.demo.model.Error;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DragonValidator {
    private final CaveValidator caveValidator = new CaveValidator();
    private final CoordinatesValidator coordinatesValidator = new CoordinatesValidator();

    public Dragon validate(DragonFromClient dragon) throws IllegalAccessException, ValidateFieldsException {

        List<Error> errorList = new ArrayList<>();
        Dragon validatedDragon = new Dragon();

        try {
            if (dragon.getCoordinates() != null) validatedDragon.setCoordinates(coordinatesValidator.validate(dragon.getCoordinates()));
        } catch (ValidateFieldsException ex) {
            errorList.addAll(ex.getErrorMsg());
        }

        try {
            if (dragon.getCave() != null) validatedDragon.setCave(caveValidator.validate(dragon.getCave()));
        } catch (ValidateFieldsException ex) {
            errorList.addAll(ex.getErrorMsg());
        }

        for (Field f : DragonFromClient.class.getDeclaredFields()) {
            f.setAccessible(true);
            if (f.get(dragon) == null) {
                errorList.add(new Error(700, f.getName(), (String.format("Dragon's %s is not specified", f.getName()))));
            }
        }

        if (dragon.getName() != null &&dragon.getName().isEmpty()) {
            errorList.add(new Error(701,"name", "Dragon's name should not be empty"));
        }  else {
            validatedDragon.setName(dragon.getName());
        }
        // проверка на соответствие типу
        try {
            long age = 0L;
            if (dragon.getAge() != null) {
                age = Long.parseLong(dragon.getAge());
            }
            if (age <= 0) {
                errorList.add(new Error(701, "age","Dragon's age should be bigger than 0"));
            } else {
                validatedDragon.setAge(age);
            }
        } catch (NumberFormatException e) {
            errorList.add(new Error(702, "area", "The entered value is not a long value"));
        }

        if (errorList.size() > 0) {
            throw new ValidateFieldsException(errorList);
        }

        if (dragon.getId() > 0) validatedDragon.setId(dragon.getId());
        validatedDragon.setColor(dragon.getColor());
        validatedDragon.setType(dragon.getType());
        validatedDragon.setDescription(dragon.getDescription());

        return validatedDragon;
    }
}