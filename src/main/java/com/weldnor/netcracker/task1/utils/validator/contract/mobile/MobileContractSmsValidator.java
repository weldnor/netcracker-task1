package com.weldnor.netcracker.task1.utils.validator.contract.mobile;

import com.weldnor.netcracker.task1.entity.contract.Contract;
import com.weldnor.netcracker.task1.entity.contract.MobileContract;
import com.weldnor.netcracker.task1.utils.validator.ValidationResult;
import com.weldnor.netcracker.task1.utils.validator.Validator;

import java.util.LinkedList;
import java.util.List;

public class MobileContractSmsValidator implements Validator<Contract> {
    private static final int MIN_SMS = 0;
    private static final int MAX_SMS = 10000;

    /**
     * @param contract контракт для валидации
     * @return {@link java.util.List} из результатов валидации
     */
    @Override
    public List<ValidationResult> validate(Contract contract) {
        MobileContract mobileContract = (MobileContract) contract;
        int sms = mobileContract.getSms();
        List<ValidationResult> result = new LinkedList<>();

        if (checkSmsOutOfRange(sms)) {
            result.add(new ValidationResult(String.format("sms %s is out of range (%s, %s)", sms, MIN_SMS, MAX_SMS)));
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
     * @param sms количество смс у клиента
     * @return true - если оно не лежит в допустимом диапазоне
     */
    public boolean checkSmsOutOfRange(int sms) {
        return sms < MIN_SMS || sms > MAX_SMS;
    }
}
