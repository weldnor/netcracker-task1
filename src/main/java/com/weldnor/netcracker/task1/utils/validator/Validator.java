package com.weldnor.netcracker.task1.utils.validator;

import java.util.List;

public abstract class Validator<T> {
    /**
     * @param t объект для валидации
     * @return {@link java.util.List} из результатов валидации
     */
    public abstract List<ValidationResult> validate(T t);

    /**
     * @param t объект для валидации
     * @return true - если объект валиден, false - если нет
     */
    public boolean isValid(T t) {
        for (ValidationResult result : validate(t)) {
            if (result.getStatus() == ValidationResultStatus.ERROR) {
                return false;
            }
        }
        return true;
    }
}
