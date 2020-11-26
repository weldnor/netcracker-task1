package com.weldnor.netcracker.task1.csvloader.parser;

import com.weldnor.netcracker.task1.entity.contract.ChannelPackage;
import com.weldnor.netcracker.task1.entity.contract.DigitalTvContract;
import com.weldnor.netcracker.task1.utils.parser.ParseException;
import com.weldnor.netcracker.task1.utils.parser.Parser;

import java.util.Map;

public class DigitalTvContractParser implements Parser<DigitalTvContract> {
    /**
     * @param params {@link Map} содержащий параметры для создания контракта.
     *               Необходимые параметры: contract_add_info
     * @return контракт, в котором заполнены только уникальные для {@link DigitalTvContract} поля
     * @throws ParseException при ошибке парсинга какого-либо параметра.
     */
    @Override
    public DigitalTvContract parse(Map<String, String> params) throws ParseException {
        try {
            DigitalTvContract contract = new DigitalTvContract();

            String[] splattedAddInfo = params.get("contract_add_info").split(";");
            ChannelPackage channelPackage;

            switch (splattedAddInfo[0]) {
                case "standard":
                    channelPackage = ChannelPackage.STANDARD;
                    break;
                case "extra":
                    channelPackage = ChannelPackage.EXTRA;
                    break;
                case "ultra":
                    channelPackage = ChannelPackage.ULTRA;
                    break;
                default:
                    throw new ParseException("cant parse contract channel package: " + splattedAddInfo[0]);
            }
            contract.setChannelPackage(channelPackage);
            return contract;
        } catch (ParseException e) {
            throw e;
        } catch (Exception e) {
            throw new ParseException(e);
        }

    }
}
