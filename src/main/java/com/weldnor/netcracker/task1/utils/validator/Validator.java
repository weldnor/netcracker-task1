package com.weldnor.netcracker.task1.utils.validator;

import java.util.List;

public interface Validator<T> {
    /**
     * @param t объект для валидации
     * @return {@link java.util.List} из результатов валидации
     */
    List<ValidationResult> validate(T t);


    /**
     * Проверка на то, можно ли валидировать переданный обьект.
     *
     * @param o проверяемый обьект
     * @return true, если обьект можно валидировать
     */
    boolean canValidate(Object o);

    /**
     * @param t объект для валидации
     * @return true - если объект валиден, false - если нет
     */
    default boolean isValid(T t) {
        for (ValidationResult result : validate(t)) {
            if (result.getStatus() == ValidationResultStatus.ERROR) {
                return false;
            }
        }
        return true;
    }
}
