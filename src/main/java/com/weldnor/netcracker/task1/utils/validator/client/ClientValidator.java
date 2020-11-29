package com.weldnor.netcracker.task1.utils.validator.client;

import com.weldnor.netcracker.task1.entity.client.Client;
import com.weldnor.netcracker.task1.utils.validator.ValidationResult;
import com.weldnor.netcracker.task1.utils.validator.Validator;

import java.time.LocalDate;
import java.time.Period;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class ClientValidator extends Validator<Client> {

    private static final int MAX_FULLNAME_LENGTH = 40;
    private static final int MIN_FULLNAME_LENGTH = 4;
    private static final int PASSPORT_LENGTH = 10;
    private static final int MIN_AGE = 18;


    /**
     * @param client проверяемый клиент
     * @return {@link java.util.List} из результатов валидации
     */
    @Override
    public List<ValidationResult> validate(Client client) {
        List<ValidationResult> result = new LinkedList<>();

        result.addAll(validateFullName(client.getFullName()));
        result.addAll(validatePassport(client.getPassport()));
        result.addAll(validateBirthDate(client.getBirthDate()));
        return result;
    }

    /**
     * @param fullName проверяемое полное имя клиента
     * @return {@link java.util.List} из результатов валидации
     */
    public List<ValidationResult> validateFullName(String fullName) {
        List<ValidationResult> result = new LinkedList<>();

        if (checkFullNameIsTooLong(fullName)) {
            result.add(new ValidationResult("name " + fullName + " is too long"));
        }

        if (checkFullNameIsTooSmall(fullName)) {
            result.add(new ValidationResult("name " + fullName + " is too small"));
        }

        if (checkFullNameContainsInvalidCharacters(fullName)) {
            result.add(new ValidationResult("name " + fullName + " contains invalid characters"));
        }
        return result;
    }

    /**
     * @param fullName полное имя клиента
     * @return true - если длина имени больше допустимого
     */
    public boolean checkFullNameIsTooLong(String fullName) {
        return fullName.length() > MAX_FULLNAME_LENGTH;
    }

    /**
     * @param fullName полное имя клиента
     * @return true - если длина имени меньше допустимого
     */
    public boolean checkFullNameIsTooSmall(String fullName) {
        return fullName.length() < MIN_FULLNAME_LENGTH;
    }

    /**
     * @param fullName полное имя клиента
     * @return true - если полное имя клиента содержит недопустимые символы
     */
    public boolean checkFullNameContainsInvalidCharacters(String fullName) {
        Pattern r = Pattern.compile("[^a-zA-Z ]");
        return r.matcher(fullName).find();
    }


    /**
     * @param passport паспортные данные клиента
     * @return {@link java.util.List} из результатов валидации
     */
    public List<ValidationResult> validatePassport(String passport) {
        List<ValidationResult> result = new LinkedList<>();

        if (checkPassportLengthIsNotCorrect(passport)) {
            result.add(new ValidationResult("length of passport " + passport + " not equals 10"));
        }

        if (checkPassportContainsNoIntCharacters(passport)) {
            result.add(new ValidationResult("passport " + passport + " contains no int characters"));
        }
        return result;
    }

    /**
     * @param passport паспортные данные клиента
     * @return true - если длина паспортных данных некорректна
     */
    public boolean checkPassportLengthIsNotCorrect(String passport) {
        return passport.length() != PASSPORT_LENGTH;
    }

    /**
     * @param passport паспортные данные клиента
     * @return true - если паспортные данные содержат нечисловые символы
     */
    public boolean checkPassportContainsNoIntCharacters(String passport) {
        Pattern r = Pattern.compile("[^0-9]");
        return r.matcher(passport).find();
    }

    /**
     * @param birthDate дата рождения клиента
     * @return {@link java.util.List} из результатов валидации
     */
    public List<ValidationResult> validateBirthDate(LocalDate birthDate) {
        List<ValidationResult> result = new LinkedList<>();
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthDate, currentDate).getYears();

        //TODO rename?
        if (checkBirthDateIsNegative(birthDate)) {
            result.add(new ValidationResult("birthDate " + birthDate + " is negative"));
        }

        if (checkAgeLessThenMin(age)) {
            result.add(new ValidationResult("age " + age + " less then " + MIN_AGE));
        }
        return result;
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
