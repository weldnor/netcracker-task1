package com.weldnor.netcracker.task1.utils.validator.client;

import com.weldnor.netcracker.task1.entity.client.Client;
import com.weldnor.netcracker.task1.utils.validator.ValidationResult;
import com.weldnor.netcracker.task1.utils.validator.Validator;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class ClientPasswordValidator implements Validator<Client> {
    private static final int PASSPORT_LENGTH = 10;

    /**
     * @param client проверяемый клиент
     * @return {@link java.util.List} из результатов валидации
     */
    @Override
    public List<ValidationResult> validate(Client client) {
        String passport = client.getPassport();
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
     * @param o проверяемый обьект
     * @return true, если обьект можно валидировать
     */
    @Override
    public boolean canValidate(Object o) {
        return o instanceof Client;
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
}
