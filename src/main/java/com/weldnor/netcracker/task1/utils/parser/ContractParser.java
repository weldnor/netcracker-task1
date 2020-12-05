package com.weldnor.netcracker.task1.utils.parser;

import com.weldnor.netcracker.task1.entity.client.Client;
import com.weldnor.netcracker.task1.entity.contract.Contract;

import java.time.LocalDate;
import java.util.Map;


public class ContractParser implements Parser<Contract> {
    /**
     * @param params {@link Map} содержащий параметры для создания контракта.
     *               Необходимые параметры: client_id,client_fullname,client_gender,client_passport,client_birthdate
     *               contract_type,contract_id,contract_start_date,contract_expiration_date
     * @return заполненный контракт
     * @throws ParseException при ошибке парсинга какого-либо параметра.
     */
    @Override
    public Contract parse(Map<String, String> params) throws ParseException {
        try {
            Contract contract;

            String contractType = params.get("contract_type");
            switch (contractType) {
                case "mobile":
                    MobileContractParser mobileContractParser = new MobileContractParser();
                    contract = mobileContractParser.parse(params);
                    break;
                case "digital_tv":
                    DigitalTvContractParser digitalTvContractParser = new DigitalTvContractParser();
                    contract = digitalTvContractParser.parse(params);
                    break;
                case "internet":
                    InternetContractParser internetContractParser = new InternetContractParser();
                    contract = internetContractParser.parse(params);
                    break;
                default:
                    throw new ParseException("cant parse contract type: " + contractType);
            }

            ClientParser clientParser = new ClientParser();
            Client client = clientParser.parse(params);
            contract.setId(Long.parseLong(params.get("contract_id")));
            contract.setClient(client);
            contract.setStartDate(LocalDate.parse(params.get("contract_start_date")));
            contract.setExpirationDate(LocalDate.parse(params.get("contract_expiration_date")));
            return contract;
        } catch (ParseException e) {
            throw e;
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

}
