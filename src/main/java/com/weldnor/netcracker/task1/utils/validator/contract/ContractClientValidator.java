package com.weldnor.netcracker.task1.utils.validator.contract;

import com.weldnor.netcracker.task1.entity.contract.Contract;
import com.weldnor.netcracker.task1.utils.validator.ValidationResult;
import com.weldnor.netcracker.task1.utils.validator.Validator;
import com.weldnor.netcracker.task1.utils.validator.client.ClientValidator;

import java.util.List;

public class ContractClientValidator implements Validator<Contract> {

    /**
     * @param contract контракт для валидации
     * @return {@link java.util.List} из результатов валидации
     */
    @Override
    public List<ValidationResult> validate(Contract contract) {
        ClientValidator clientValidator = new ClientValidator();
        return clientValidator.validate(contract.getClient());
    }


    /**
     * @param o проверяемый обьект
     * @return true, если обьект можно валидировать
     */
    @Override
    public boolean canValidate(Object o) {
        return o instanceof Contract;
    }
}
