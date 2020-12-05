package com.weldnor.netcracker.task1.utils.validator.contract.mobile;

import com.weldnor.netcracker.task1.entity.contract.Contract;
import com.weldnor.netcracker.task1.entity.contract.MobileContract;
import com.weldnor.netcracker.task1.utils.validator.ValidationResult;
import com.weldnor.netcracker.task1.utils.validator.Validator;

import java.util.LinkedList;
import java.util.List;

public class MobileContractMegabytesValidator implements Validator<Contract> {
    private static final int MIN_MEGABYTES = 0;
    private static final int MAX_MEGABYTES = 1024 * 50;

    /**
     * @param contract контракт для валидации
     * @return {@link java.util.List} из результатов валидации
     */
    @Override
    public List<ValidationResult> validate(Contract contract) {
        MobileContract mobileContract = (MobileContract) contract;
        int megabytes = mobileContract.getMegabytes();

        List<ValidationResult> result = new LinkedList<>();

        if (checkMegabytesOutOfRange(megabytes)) {
            result.add(new ValidationResult(String.format("megabytes %s is out of range (%s, %s)",
                    megabytes, MIN_MEGABYTES, MAX_MEGABYTES)));
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
     * @param megabytes количество мегабайт у клиента
     * @return true - если оно не лежит в допустимом диапазоне
     */
    public boolean checkMegabytesOutOfRange(int megabytes) {
        return megabytes < MIN_MEGABYTES || megabytes > MAX_MEGABYTES;
    }
}
