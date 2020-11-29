package com.weldnor.netcracker.task1.utils.validator.contract;

import com.weldnor.netcracker.task1.entity.contract.DigitalTvContract;
import com.weldnor.netcracker.task1.utils.validator.ValidationResult;
import com.weldnor.netcracker.task1.utils.validator.Validator;

import java.util.List;

public class DigitalTvContractValidator extends Validator<DigitalTvContract> {
    private final ContractValidator contractValidator = new ContractValidator();

    /**
     * @param digitalTvContract контракт для валидации
     * @return {@link java.util.List} из результатов валидации
     */
    @Override
    public List<ValidationResult> validate(DigitalTvContract digitalTvContract) {
        return contractValidator.validate(digitalTvContract);
    }
}
