package com.weldnor.netcracker.task1.utils.validator.contract;

import com.weldnor.netcracker.task1.entity.contract.Contract;
import com.weldnor.netcracker.task1.utils.validator.ValidationResult;
import com.weldnor.netcracker.task1.utils.validator.Validator;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class ContractDateValidator implements Validator<Contract> {

    /**
     * @param contract контракт для валидации
     * @return {@link java.util.List} из результатов валидации
     */
    @Override
    public List<ValidationResult> validate(Contract contract) {
        LocalDate startDate = contract.getStartDate();
        LocalDate expirationDate = contract.getExpirationDate();

        List<ValidationResult> result = new LinkedList<>();

        if (checkStartDateIsAfterExpirationDate(startDate, expirationDate)) {
            result.add(new ValidationResult("date " + startDate + " is after date " + expirationDate));
        }
        return result;
    }

    /**
     * @param o проверяемый обьект
     * @return true, если обьект можно валидировать
     */
    @Override
    public boolean canValidate(Object o) {
        return o instanceof Contract;
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
