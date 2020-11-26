package com.weldnor.netcracker.task1.csvloader.parser;

import com.weldnor.netcracker.task1.contract.InternetContract;
import com.weldnor.netcracker.task1.utils.parser.ParseException;
import com.weldnor.netcracker.task1.utils.parser.Parser;

import java.util.Map;

public class InternetContractParser implements Parser<InternetContract> {
    /**
     * @param params {@link Map} содержащий параметры для создания контракта.
     *               Необходимые параметры: contract_add_info
     * @return контракт, в котором заполнены только уникальные для {@link InternetContract} поля
     * @throws ParseException при ошибке парсинга какого-либо параметра.
     */
    @Override
    public InternetContract parse(Map<String, String> params) throws ParseException {
        try {
            InternetContract contract = new InternetContract();

            String[] splattedAddInfo = params.get("contract_add_info").split(";");
            int maxSpeed = Integer.parseInt(splattedAddInfo[0]);
            contract.setMaxSpeed(maxSpeed);
            return contract;
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }
}
