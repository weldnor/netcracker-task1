package com.weldnor.netcracker.task1.utils.validator.contract;

import com.weldnor.netcracker.task1.entity.contract.MobileContract;
import com.weldnor.netcracker.task1.utils.validator.ValidationResult;
import com.weldnor.netcracker.task1.utils.validator.Validator;

import java.util.LinkedList;
import java.util.List;

public class MobileContractValidator extends Validator<MobileContract> {
    private static final int MIN_SMS = 0;
    private static final int MAX_SMS = 10000;
    private static final int MIN_MEGABYTES = 0;
    private static final int MAX_MEGABYTES = 1024 * 50;
    private static final int MIN_MINUTES = 0;
    private static final int MAX_MINUTES = 4000;

    private final ContractValidator contractValidator = new ContractValidator();


    /**
     * @param mobileContract контракт для валидации
     * @return {@link java.util.List} из результатов валидации
     */
    @Override
    public List<ValidationResult> validate(MobileContract mobileContract) {
        List<ValidationResult> result = new LinkedList<>();

        result.addAll(contractValidator.validate(mobileContract));
        result.addAll(validateSms(mobileContract.getSms()));
        result.addAll(validateMegabytes(mobileContract.getMegabytes()));
        result.addAll(validateMinutes(mobileContract.getMinutes()));
        return result;
    }

    /**
     * @param sms количество смс у клиента
     * @return {@link java.util.List} из результатов валидации
     */
    public List<ValidationResult> validateSms(int sms) {
        List<ValidationResult> result = new LinkedList<>();

        if (checkSmsOutOfRange(sms)) {
            result.add(new ValidationResult(String.format("sms %s is out of range (%s, %s)", sms, MIN_SMS, MAX_SMS)));
        }
        return result;
    }

    /**
     * @param sms количество смс у клиента
     * @return true - если оно не лежит в допустимом диапазоне
     */
    public boolean checkSmsOutOfRange(int sms) {
        return sms < MIN_SMS || sms > MAX_SMS;
    }

    /**
     * @param megabytes количество мегабайт у клиента
     * @return {@link java.util.List} из результатов валидации
     */
    public List<ValidationResult> validateMegabytes(int megabytes) {
        List<ValidationResult> result = new LinkedList<>();

        if (checkMegabytesOutOfRange(megabytes)) {
            result.add(new ValidationResult(String.format("megabytes %s is out of range (%s, %s)",
                    megabytes, MIN_MEGABYTES, MAX_MEGABYTES)));
        }
        return result;
    }

    /**
     * @param megabytes количество мегабайт у клиента
     * @return true - если оно не лежит в допустимом диапазоне
     */
    public boolean checkMegabytesOutOfRange(int megabytes) {
        return megabytes < MIN_MEGABYTES || megabytes > MAX_MEGABYTES;
    }


    /**
     * @param minutes количество минут у клиента
     * @return {@link java.util.List} из результатов валидации
     */
    public List<ValidationResult> validateMinutes(int minutes) {
        List<ValidationResult> result = new LinkedList<>();

        if (checkMinutesOutOfRange(minutes)) {
            result.add(new ValidationResult(String.format("minutes %s is out of range (%s, %s)",
                    minutes, MIN_MINUTES, MAX_MINUTES)));
        }
        return result;
    }

    /**
     * @param minutes количество количество минут у клиента
     * @return true - если оно не лежит в допустимом диапазоне
     */
    public boolean checkMinutesOutOfRange(int minutes) {
        return minutes < MIN_MINUTES || minutes > MAX_MINUTES;
    }
}
