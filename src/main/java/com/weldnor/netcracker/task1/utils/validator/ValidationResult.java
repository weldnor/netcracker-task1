package com.weldnor.netcracker.task1.utils.validator;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationResult {
    private String message;
    private ValidationResultStatus status;

    /**
     * @param message сообщение о результате валидации
     */
    public ValidationResult(String message) {
        this.message = message;
        this.status = ValidationResultStatus.ERROR;
    }
}
