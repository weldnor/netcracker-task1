package com.weldnor.netcracker.task1.csvloader.parser;

import com.weldnor.netcracker.task1.contract.MobileContract;
import com.weldnor.netcracker.task1.utils.parser.ParseException;
import com.weldnor.netcracker.task1.utils.parser.Parser;

import java.util.Map;

public class MobileContractParser implements Parser<MobileContract> {
    /**
     * @param params {@link Map} содержащий параметры для создания контракта.
     *               Необходимые параметры: contract_add_info
     * @return @return контракт, в котором заполнены только уникальные для {@link MobileContract} поля
     * @throws ParseException при ошибке парсинга какого-либо параметра.
     */
    @Override
    public MobileContract parse(Map<String, String> params) throws ParseException {
        try {
            MobileContract mobileContract = new MobileContract();

            String[] splattedAddInfo = params.get("contract_add_info").split(";");
            mobileContract.setSms(Integer.parseInt(splattedAddInfo[0]));
            mobileContract.setMegabytes(Integer.parseInt(splattedAddInfo[1]));
            mobileContract.setMinutes(Integer.parseInt(splattedAddInfo[2]));
            return mobileContract;
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }
}
