package com.weldnor.netcracker.task1.utils;

import com.weldnor.netcracker.task1.entity.client.Client;
import com.weldnor.netcracker.task1.entity.client.Gender;
import com.weldnor.netcracker.task1.entity.contract.*;
import com.weldnor.netcracker.task1.repository.ContractRepository;
import com.weldnor.netcracker.task1.utils.csvloader.ContractCsvLoader;
import com.weldnor.netcracker.task1.utils.di.Injector;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ContractCsvLoaderTest {
    private final Contract firstContract = InternetContract.builder()
            .id(15)
            .client(Client.builder()
                    .id(42)
                    .fullName("Alex")
                    .gender(Gender.MALE)
                    .passport("1000123456")
                    .birthDate(LocalDate.of(2000, 6, 1))
                    .build())
            .startDate(LocalDate.of(2005, 7, 17))
            .expirationDate(LocalDate.of(2021, 3, 9))
            .maxSpeed(1024)
            .build();

    private final Contract secondContract = DigitalTvContract.builder()
            .id(48)
            .client(Client.builder()
                    .id(23)
                    .fullName("Anton")
                    .gender(Gender.MALE)
                    .passport("1007653456")
                    .birthDate(LocalDate.of(2000, 6, 1))
                    .build())
            .startDate(LocalDate.of(2005, 7, 17))
            .expirationDate(LocalDate.of(2021, 3, 9))
            .channelPackage(ChannelPackage.STANDARD)
            .build();

    private final Contract thirdContract = MobileContract.builder()
            .id(32)
            .client(Client.builder()
                    .id(23)
                    .fullName("Liza")
                    .gender(Gender.FEMALE)
                    .passport("0987654321")
                    .birthDate(LocalDate.of(2000, 6, 1))
                    .build())
            .startDate(LocalDate.of(2005, 7, 17))
            .expirationDate(LocalDate.of(2021, 3, 9))
            .sms(100)
            .megabytes(1024)
            .minutes(100)
            .build();


    @Test
    public void loadContractsToRepositoryFromCsvFile() throws IOException {
        ContractRepository repository = new ContractRepository();
        ContractCsvLoader contractCsvLoader = Injector.inject(new ContractCsvLoader());

        contractCsvLoader.loadContractsToRepositoryFromCsvFile(repository, "src/test/data/contracts.csv");

        assertThat(repository.getAll()).contains(firstContract, secondContract, thirdContract);
    }
}