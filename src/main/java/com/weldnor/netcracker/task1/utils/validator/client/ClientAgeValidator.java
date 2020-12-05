package com.weldnor.netcracker.task1.utils.validator.client;

import com.weldnor.netcracker.task1.entity.client.Client;
import com.weldnor.netcracker.task1.utils.validator.ValidationResult;
import com.weldnor.netcracker.task1.utils.validator.Validator;

import java.time.LocalDate;
import java.time.Period;
import java.util.LinkedList;
import java.util.List;

public class ClientAgeValidator implements Validator<Client> {
    private static final int MIN_AGE = 18;

    /**
     * @param client проверяемый клиент
     * @return {@link java.util.List} из результатов валидации
     */
    @Override
    public List<ValidationResult> validate(Client client) {
        LocalDate birthDate = client.getBirthDate();
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthDate, currentDate).getYears();

        List<ValidationResult> result = new LinkedList<>();

        if (checkBirthDateIsNegative(birthDate)) {
            result.add(new ValidationResult("birthDate " + birthDate + " is negative"));
        }

        if (checkAgeLessThenMin(age)) {
            result.add(new ValidationResult("age " + age + " less then " + MIN_AGE));
        }
        return result;
    }

    /**
     * @param o проверяемый обьект
     * @return true, если обьект можно валидировать
     */
    @Override
    public boolean canValidate(Object o) {
        return o instanceof Client;
    }

    /**
     * @param birthDate дата рождения клиента
     * @return true - если дата рождения больше, чем текущая дата
     */
    public boolean checkBirthDateIsNegative(LocalDate birthDate) {
        return birthDate.isAfter(LocalDate.now());
    }

    /**
     * @param age возраст клиента
     * @return true - если возраст клиента меньше минимального допустимого
     */
    public boolean checkAgeLessThenMin(int age) {
        return age < MIN_AGE;
    }

}
