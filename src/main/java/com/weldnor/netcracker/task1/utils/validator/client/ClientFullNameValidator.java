package com.weldnor.netcracker.task1.utils.validator.client;

import com.weldnor.netcracker.task1.entity.client.Client;
import com.weldnor.netcracker.task1.utils.validator.ValidationResult;
import com.weldnor.netcracker.task1.utils.validator.Validator;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class ClientFullNameValidator implements Validator<Client> {

    private static final int MAX_FULLNAME_LENGTH = 40;
    private static final int MIN_FULLNAME_LENGTH = 4;

    /**
     * @param client проверяемый клиент
     * @return {@link java.util.List} из результатов валидации
     */
    @Override
    public List<ValidationResult> validate(Client client) {
        String fullName = client.getFullName();

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
     * @param o проверяемый обьект
     * @return true, если обьект можно валидировать
     */
    @Override
    public boolean canValidate(Object o) {
        return o instanceof Client;
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


}
