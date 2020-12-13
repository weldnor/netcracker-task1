package com.weldnor.netcracker.task1.utils.validator.client;

import com.weldnor.netcracker.task1.entity.client.Client;
import com.weldnor.netcracker.task1.utils.validator.ValidationResult;
import com.weldnor.netcracker.task1.utils.validator.Validator;

import java.util.LinkedList;
import java.util.List;

public class ClientValidator implements Validator<Client> {

    private final List<Validator<Client>> validators = new LinkedList<>();

    {
        validators.add(new ClientAgeValidator());
        validators.add(new ClientFullNameValidator());
        validators.add(new ClientPasswordValidator());
    }

    /**
     * @param client проверяемый клиент
     * @return {@link java.util.List} из результатов валидации
     */
    @Override
    public List<ValidationResult> validate(Client client) {
        List<ValidationResult> result = new LinkedList<>();
        validators.stream()
                .filter(validator -> validator.canValidate(client))
                .map(validator -> validator.validate(client))
                .filter(currentResult -> currentResult.size() != 0)
                .forEach(result::addAll);

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
}
