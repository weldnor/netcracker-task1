package com.weldnor.netcracker.task1.utils.validator.contract.digitaltv;

import com.weldnor.netcracker.task1.entity.contract.Contract;
import com.weldnor.netcracker.task1.entity.contract.DigitalTvContract;
import com.weldnor.netcracker.task1.utils.validator.ValidationResult;
import com.weldnor.netcracker.task1.utils.validator.Validator;

import java.util.LinkedList;
import java.util.List;

public class DigitalTvContractValidator implements Validator<Contract> {
    private final List<Validator<Contract>> validators = new LinkedList<>();

    /**
     * @param contract контракт для валидации
     * @return {@link java.util.List} из результатов валидации
     */
    @Override
    public List<ValidationResult> validate(Contract contract) {
        DigitalTvContract digitalTvContract = (DigitalTvContract) contract;

        List<ValidationResult> result = new LinkedList<>();
        validators.stream()
                .filter(validator -> validator.canValidate(digitalTvContract))
                .map(validator -> validator.validate(digitalTvContract))
                .filter(currentResult -> currentResult.size() != 0)
                .forEach(result::addAll);

        return result;
    }

    /**
     * @param o проверяемый обьект
     * @return true, если обьект можно валидировать
     */
    @Override
    public boolean canValidate(Object o) {
        return o instanceof DigitalTvContract;
    }
}
