package app.demo.validator;

import app.demo.model.DragonCave;
import app.demo.model.dto.CaveFromClient;
import app.demo.model.Error;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CaveValidator {
    public DragonCave validate(CaveFromClient cave) throws IllegalAccessException, ValidateFieldsException {
        List<Error> errorList = new ArrayList<>();
        DragonCave validatedCave = new DragonCave();

        if (cave == null) {
            throw new ValidateFieldsException(errorList);
        }

        for (Field f : CaveFromClient.class.getDeclaredFields()) {
            f.setAccessible(true);
            if (f.get(cave) == null) {
                errorList.add(new Error(700, f.getName(), String.format("Cave %s is not specified", f.getName())));
            }
        }

        try {
            double depth;
            if (cave.getDepth() != null) {
                depth = Double.parseDouble(cave.getDepth());
                validatedCave.setDepth(depth);
            }
        } catch (NumberFormatException e) {
            errorList.add(new Error(702, "depth", "The entered value is not a double value"));
        }

        if (errorList.size() > 0) {
            throw new ValidateFieldsException(errorList);
        }
        if (cave.getId() != 0) validatedCave.setId(cave.getId());
        return validatedCave;
    }
}