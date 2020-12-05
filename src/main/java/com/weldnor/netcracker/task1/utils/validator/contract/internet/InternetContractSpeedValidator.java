package com.weldnor.netcracker.task1.utils.validator.contract.internet;

import com.weldnor.netcracker.task1.entity.contract.Contract;
import com.weldnor.netcracker.task1.entity.contract.InternetContract;
import com.weldnor.netcracker.task1.utils.validator.ValidationResult;
import com.weldnor.netcracker.task1.utils.validator.Validator;

import java.util.LinkedList;
import java.util.List;

public class InternetContractSpeedValidator implements Validator<Contract> {
    private static final int MIN_SPEED = 5;
    private static final int MAX_SPEED = 3000;

    /**
     * @param contract контракт для валидации
     * @return {@link java.util.List} из результатов валидации
     */
    @Override
    public List<ValidationResult> validate(Contract contract) {
        InternetContract internetContract = (InternetContract) contract;
        int maxSpeed = internetContract.getMaxSpeed();
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
     * @param o проверяемый обьект
     * @return true, если обьект можно валидировать
     */
    @Override
    public boolean canValidate(Object o) {
        return o instanceof InternetContract;
    }

    /**
     * @param maxSpeed скорость интернета
     * @return true - если скорость меньше, чем 0
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
