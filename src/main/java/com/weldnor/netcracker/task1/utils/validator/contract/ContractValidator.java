package com.weldnor.netcracker.task1.utils.validator.contract;

import com.weldnor.netcracker.task1.entity.contract.Contract;
import com.weldnor.netcracker.task1.utils.validator.ValidationResult;
import com.weldnor.netcracker.task1.utils.validator.Validator;
import com.weldnor.netcracker.task1.utils.validator.client.ClientValidator;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class ContractValidator extends Validator<Contract> {
    private final ClientValidator clientValidator = new ClientValidator();

    /**
     * @param contract контракт для валидации
     * @return {@link java.util.List} из результатов валидации
     */
    @Override
    public List<ValidationResult> validate(Contract contract) {
        List<ValidationResult> result = new LinkedList<>();

        result.addAll(clientValidator.validate(contract.getClient()));
        result.addAll(validateDates(contract.getStartDate(), contract.getExpirationDate()));
        return result;
    }

    /**
     * @param startDate      дата начала контракта
     * @param expirationDate дата конца контракта
     * @return {@link java.util.List} из результатов валидации
     */
    public List<ValidationResult> validateDates(LocalDate startDate, LocalDate expirationDate) {
        List<ValidationResult> result = new LinkedList<>();

        if (checkStartDateIsAfterExpirationDate(startDate, expirationDate)) {
            result.add(new ValidationResult("date " + startDate + " is after date " + expirationDate));
        }
        return result;
    }

    /**
     * @param startDate      дата начала контракта
     * @param expirationDate дата конца контракта
     * @return true - если они стоят в правильном хронологическом порядке
     */
    public boolean checkStartDateIsAfterExpirationDate(LocalDate startDate, LocalDate expirationDate) {
        return startDate.isAfter(expirationDate);
    }
}
