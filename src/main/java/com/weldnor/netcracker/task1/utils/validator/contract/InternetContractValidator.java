package com.weldnor.netcracker.task1.utils.validator.contract;

import com.weldnor.netcracker.task1.entity.contract.InternetContract;
import com.weldnor.netcracker.task1.utils.validator.ValidationResult;
import com.weldnor.netcracker.task1.utils.validator.Validator;

import java.util.LinkedList;
import java.util.List;

public class InternetContractValidator extends Validator<InternetContract> {
    private static final int MIN_SPEED = 5;
    private static final int MAX_SPEED = 3000;

    private final ContractValidator contractValidator = new ContractValidator();

    /**
     * @param internetContract контракт для валидации
     * @return {@link java.util.List} из результатов валидации
     */
    @Override
    public List<ValidationResult> validate(InternetContract internetContract) {
        List<ValidationResult> result = new LinkedList<>();

        result.addAll(contractValidator.validate(internetContract));
        result.addAll(validateMaxSpeed(internetContract.getMaxSpeed()));
        return result;
    }

    /**
     * @param maxSpeed скорость интернета
     * @return {@link java.util.List} из результатов валидации
     */
    public List<ValidationResult> validateMaxSpeed(int maxSpeed) {
        List<ValidationResult> result = new LinkedList<>();

        if (checkMaxSpeedIsNegativeOrZero(maxSpeed)) {
            result.add(new ValidationResult("speed " + maxSpeed + " is negative or zero"));
        }
        if (checkMaxSpeedLessThenMin(maxSpeed)) {
            result.add(new ValidationResult("speed " + maxSpeed + " is less then min " + MIN_SPEED));
        }
        if (checkMaxSpeedMoreThenMax(maxSpeed)) {
            result.add(new ValidationResult("speed " + maxSpeed + " is more then max " + MAX_SPEED));
        }
        return result;
    }

    /**
     * @param maxSpeed скорость интернета
     * @return true - если скорость неположительная
     */
    public boolean checkMaxSpeedIsNegativeOrZero(int maxSpeed) {
        return maxSpeed <= 0;
    }

    /**
     * @param maxSpeed скорость интернета
     * @return true - если скорость меньше чем минимальная
     */
    public boolean checkMaxSpeedLessThenMin(int maxSpeed) {
        return maxSpeed < MIN_SPEED;
    }

    /**
     * @param maxSpeed скорость интернета
     * @return true - если скорость больше чем положительная
     */
    public boolean checkMaxSpeedMoreThenMax(int maxSpeed) {
        return maxSpeed > MAX_SPEED;
    }
}
