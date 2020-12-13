package com.weldnor.netcracker.task1.utils.csvloader;

import com.opencsv.CSVReader;
import com.weldnor.netcracker.task1.entity.contract.Contract;
import com.weldnor.netcracker.task1.repository.ContractRepository;
import com.weldnor.netcracker.task1.utils.parser.ContractParser;
import com.weldnor.netcracker.task1.utils.validator.ValidationResult;
import com.weldnor.netcracker.task1.utils.validator.ValidationResultStatus;
import com.weldnor.netcracker.task1.utils.validator.Validator;
import com.weldnor.netcracker.task1.utils.validator.contract.ContractValidator;
import com.weldnor.netcracker.task1.utils.validator.contract.digitaltv.DigitalTvContractValidator;
import com.weldnor.netcracker.task1.utils.validator.contract.internet.InternetContractSpeedValidator;
import com.weldnor.netcracker.task1.utils.validator.contract.mobile.MobileContractValidator;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public final class ContractCsvLoader {

    private static final List<Validator<Contract>> VALIDATORS = new LinkedList<>();

    static {
        VALIDATORS.add(new ContractValidator());
        VALIDATORS.add(new InternetContractSpeedValidator());
        VALIDATORS.add(new DigitalTvContractValidator());
        VALIDATORS.add(new MobileContractValidator());
    }

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
        List<ValidationResult> results = new LinkedList<>();

        VALIDATORS.stream()
                .filter(validator -> validator.canValidate(contract))
                .map(validator -> validator.validate(contract))
                .filter(currentResult -> currentResult.size() != 0)
                .forEach(results::addAll);

        for (ValidationResult result : results) {
            if (result.getStatus() == ValidationResultStatus.ERROR) {
                return false;
            }
        }
        return true;
    }
}
