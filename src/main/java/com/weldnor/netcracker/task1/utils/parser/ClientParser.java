package com.weldnor.netcracker.task1.utils.parser;

import com.weldnor.netcracker.task1.entity.client.Client;
import com.weldnor.netcracker.task1.entity.client.Gender;

import java.time.LocalDate;
import java.util.Map;

public class ClientParser implements Parser<Client> {
    /**
     * Парсинг клиента из строковых представлений его параметров.
     *
     * @param params {@link Map} содержащий параметры для создания клиента.
     *               Необходимые параметры: client_id,client_fullname,client_gender,client_passport,client_birthdate
     * @return заполненный клиент
     * @throws ParseException при ошибке парсинга какого-либо параметра.
     */
    @Override
    public Client parse(Map<String, String> params) throws ParseException {
        try {
            Client client = new Client();
            client.setId(Long.parseLong(params.get("client_id")));
            client.setFullName(params.get("client_fullname"));
            Gender clientGender;
            switch (params.get("client_gender")) {
                case "male":
                    clientGender = Gender.MALE;
                    break;
                case "female":
                    clientGender = Gender.FEMALE;
                    break;
                default:
                    throw new ParseException("cant parse client gender");
            }
            client.setGender(clientGender);
            client.setPassport(params.get("client_passport"));
            client.setBirthDate(LocalDate.parse(params.get("client_birthdate")));
            return client;
        } catch (ParseException e) {
            throw e;
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }
}
