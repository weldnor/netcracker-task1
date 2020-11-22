package com.weldnor.netcracker.task1.utils.csvloader;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.weldnor.netcracker.task1.entity.client.Client;
import com.weldnor.netcracker.task1.entity.client.Gender;
import com.weldnor.netcracker.task1.entity.contract.*;
import com.weldnor.netcracker.task1.repository.ContractRepository;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class ContractCsvLoader {

    private ContractCsvLoader() {
    }


    /**
     * Загрузка {@link Contract контрактов} из csv файла в {@link ContractRepository репозиторий контрактов}.
     *
     * @param repository репозиторий, куда добавляются новые контракты
     * @param path       путь к csv файлу
     * @throws IOException  если не получается считать файл
     * @throws CsvException если во время парсинга файла возникли проблемы
     */
    public static void loadContractsToRepositoryFromCsvFile(ContractRepository repository, String path)
            throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader(path));

        String[] line;
        while ((line = reader.readNext()) != null) {
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
                Contract contract = parseContract(params);
                repository.add(contract);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }


    private static Contract parseContract(HashMap<String, String> params) throws ParseException {

        long contractId = Long.parseLong(params.get("contract_id"));
        LocalDate contractStartDate = LocalDate.parse(params.get("contract_start_date"));
        LocalDate contractExpirationDate = LocalDate.parse(params.get("contract_expiration_date"));

        // отбираем параметры, которые относятся к клиенту
        Map<String, String> clientParams = params.entrySet()
                .stream()
                .filter(entry -> entry.getKey().startsWith("client"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        Client client = parseClient(clientParams);

        String contractType = params.get("contract_type");
        String[] splattedAddInfo = params.get("contract_add_info").split(";");

        Contract contract;

        switch (contractType) {
            case "mobile":
                int sms = Integer.parseInt(splattedAddInfo[0]);
                int megabytes = Integer.parseInt(splattedAddInfo[1]);
                int minutes = Integer.parseInt(splattedAddInfo[2]);
                contract = MobileContract.builder()
                        .id(contractId)
                        .client(client)
                        .startDate(contractStartDate)
                        .expirationDate(contractExpirationDate)
                        .sms(sms)
                        .megabytes(megabytes)
                        .minutes(minutes)
                        .build();
                break;
            case "digital_tv":
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
                        throw new ParseException("cant parse contract channel package");
                }
                contract = DigitalTvContract.builder()
                        .id(contractId)
                        .client(client)
                        .startDate(contractStartDate)
                        .expirationDate(contractExpirationDate)
                        .channelPackage(channelPackage)
                        .build();
                break;
            case "internet":
                int maxSpeed = Integer.parseInt(splattedAddInfo[0]);
                contract = InternetContract.builder()
                        .id(contractId)
                        .client(client)
                        .startDate(contractStartDate)
                        .expirationDate(contractExpirationDate)
                        .maxSpeed(maxSpeed)
                        .build();
                break;
            default:
                throw new ParseException("cant parse contract type");
        }
        return contract;

    }

    private static Client parseClient(Map<String, String> params) throws ParseException {

        long clientId = Long.parseLong(params.get("client_id"));
        String clientFullName = params.get("client_fullname");
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
        String clientPassport = params.get("client_passport");
        LocalDate clientBirthDate = LocalDate.parse(params.get("client_birthdate"));

        return Client.builder()
                .id(clientId)
                .fullName(clientFullName)
                .gender(clientGender)
                .passport(clientPassport)
                .birthDate(clientBirthDate).build();
    }
}
