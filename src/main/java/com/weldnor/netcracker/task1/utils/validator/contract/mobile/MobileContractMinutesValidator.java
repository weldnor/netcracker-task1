package com.weldnor.netcracker.task1.utils.validator.contract.mobile;

import com.weldnor.netcracker.task1.entity.contract.Contract;
import com.weldnor.netcracker.task1.entity.contract.MobileContract;
import com.weldnor.netcracker.task1.utils.validator.ValidationResult;
import com.weldnor.netcracker.task1.utils.validator.Validator;

import java.util.LinkedList;
import java.util.List;

public class MobileContractMinutesValidator implements Validator<Contract> {
    private static final int MIN_MINUTES = 0;
    private static final int MAX_MINUTES = 4000;

    /**
     * @param contract контракт для валидации
     * @return {@link java.util.List} из результатов валидации
     */
    @Override
    public List<ValidationResult> validate(Contract contract) {
        MobileContract mobileContract = (MobileContract) contract;
        int minutes = mobileContract.getMinutes();
        List<ValidationResult> result = new LinkedList<>();

        if (checkMinutesOutOfRange(minutes)) {
            result.add(new ValidationResult(String.format("minutes %s is out of range (%s, %s)",
                    minutes, MIN_MINUTES, MAX_MINUTES)));
        }
        return result;
    }

    /**
     * @param o проверяемый обьект
     * @return true, если обьект можно валидировать
     */
    @Override
    public boolean canValidate(Object o) {
        return o instanceof MobileContract;
    }

    /**
     * @param minutes количество количество минут у клиента
     * @return true - если оно не лежит в допустимом диапазоне
     */
    public boolean checkMinutesOutOfRange(int minutes) {
        return minutes < MIN_MINUTES || minutes > MAX_MINUTES;
    }
}
