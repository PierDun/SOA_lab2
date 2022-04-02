package app.demo.validator;

import java.util.List;
import app.demo.model.Error;

public class ValidateFieldsException extends Exception {
    private List<Error> errorMsg;

    public ValidateFieldsException(List<Error> errorMessage) {
        this.errorMsg = errorMessage;
    }

    public List<Error> getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(List<Error> errorMsg) {
        this.errorMsg = errorMsg;
    }
}