package com.weldnor.netcracker.task1.csvloader;

import com.opencsv.CSVReader;
import com.weldnor.netcracker.task1.csvloader.parser.ContractParser;
import com.weldnor.netcracker.task1.entity.contract.Contract;
import com.weldnor.netcracker.task1.entity.contract.DigitalTvContract;
import com.weldnor.netcracker.task1.entity.contract.InternetContract;
import com.weldnor.netcracker.task1.entity.contract.MobileContract;
import com.weldnor.netcracker.task1.repository.ContractRepository;
import com.weldnor.netcracker.task1.utils.validator.contract.DigitalTvContractValidator;
import com.weldnor.netcracker.task1.utils.validator.contract.InternetContractValidator;
import com.weldnor.netcracker.task1.utils.validator.contract.MobileContractValidator;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public final class ContractCsvLoader {

    private ContractCsvLoader() {
    }


    /**
     * Загрузка {@link Contract контрактов} из csv файла в {@link ContractRepository репозиторий контрактов}.
     *
     * @param repository репозиторий, куда добавляются новые контракты
     * @param path       путь к csv файлу
     * @throws IOException если не получается считать файл
     */
    public static void loadContractsToRepositoryFromCsvFile(ContractRepository repository, String path)
            throws IOException {
        CSVReader reader = new CSVReader(new FileReader(path));
        ContractParser contractParser = new ContractParser();

        String[] line;
        while ((line = reader.readNextSilently()) != null) {
            HashMap<String, String> params = new HashMap<>();

            params.put("contract_id", line[0]);
            params.put("client_id", line[1]);
            params.put("client_fullname", line[2]);
            params.put("client_gender", line[3]);
            params.put("client_passport", line[4]);
            params.put("client_birthdate", line[5]);
            params.put("contract_start_date", line[6]);
            params.put("contract_expiration_date", line[7]);
            params.put("contract_type", line[8]);
            params.put("contract_add_info", line[9]);

            try {
                Contract contract = contractParser.parse(params);
                if (isContractValid(contract)) {
                    repository.add(contract);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private static boolean isContractValid(Contract contract) {
        if (contract instanceof DigitalTvContract) {
            DigitalTvContractValidator validator = new DigitalTvContractValidator();
            return validator.isValid((DigitalTvContract) contract);
        }
        if (contract instanceof InternetContract) {
            InternetContractValidator validator = new InternetContractValidator();
            return validator.isValid((InternetContract) contract);
        }
        if (contract instanceof MobileContract) {
            MobileContractValidator validator = new MobileContractValidator();
            return validator.isValid((MobileContract) contract);
        }
        return false; // FIXME?
    }
}
