package com.weldnor.netcracker.task1.repository;

import com.weldnor.netcracker.task1.entity.client.Client;
import com.weldnor.netcracker.task1.entity.client.Gender;
import com.weldnor.netcracker.task1.entity.contract.*;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ContractRepositoryTest {

    private final Contract firstContract = InternetContract.builder()
            .id(15)
            .client(Client.builder()
                    .id(42)
                    .fullName("Alex")
                    .gender(Gender.MALE)
                    .passport("202408573923")
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
                    .passport("202408573923")
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
                    .passport("123908213")
                    .birthDate(LocalDate.of(2000, 6, 1))
                    .build())
            .startDate(LocalDate.of(2005, 7, 17))
            .expirationDate(LocalDate.of(2021, 3, 9))
            .megabytes(1024)
            .minutes(100)
            .sms(100)
            .build();

    @Test
    public void add_InsertOneContract_SizeShouldBeEqualOne() throws ContractAlreadyExistException {
        ContractRepository contractRepository = new ContractRepository();

        contractRepository.add(firstContract);

        assertThat(contractRepository.size()).isEqualTo(1);
    }

    @Test
    public void add_InsertExistingContract_ShouldThrowException() throws ContractAlreadyExistException {
        ContractRepository contractRepository = new ContractRepository();

        contractRepository.add(firstContract);

        assertThatThrownBy(() -> {
            contractRepository.add(firstContract);
        }).isInstanceOf(ContractAlreadyExistException.class);
    }

    @Test
    public void contains_ContractExist_ShouldReturnTrue() throws ContractAlreadyExistException {
        ContractRepository contractRepository = new ContractRepository();

        contractRepository.add(firstContract);

        assertThat(contractRepository.contains(firstContract.getId())).isTrue();
    }

    @Test
    public void contains_ContractDoesNotExist_ShouldReturnFalse() {
        ContractRepository contractRepository = new ContractRepository();

        assertThat(contractRepository.contains(firstContract.getId())).isFalse();
    }

    @Test
    public void get_ContractExist_ShouldReturnOptionalWithContract() throws ContractAlreadyExistException {
        ContractRepository contractRepository = new ContractRepository();

        contractRepository.add(firstContract);
        contractRepository.add(secondContract);
        contractRepository.add(thirdContract);

        assertThat(contractRepository.get(thirdContract.getId())).isNotEmpty();
    }

    @Test
    public void get_ContractDoesNotExist_ShouldReturnEmptyOptional() throws ContractAlreadyExistException {
        ContractRepository contractRepository = new ContractRepository();

        contractRepository.add(firstContract);

        assertThat(contractRepository.get(thirdContract.getId())).isEmpty();
    }

    @Test
    public void getAll_RepositoryIsEmpty_ShouldReturnEmptyList() {
        ContractRepository contractRepository = new ContractRepository();

        assertThat(contractRepository.getAll()).isEmpty();
    }

    @Test
    public void getAll_RepositoryDoesNotEmpty_ShouldReturnListWithContracts() throws ContractAlreadyExistException {
        ContractRepository contractRepository = new ContractRepository();

        contractRepository.add(firstContract);
        contractRepository.add(secondContract);
        contractRepository.add(thirdContract);

        assertThat(contractRepository.getAll()).containsAll(List.of(firstContract, secondContract, thirdContract));
    }

    @Test
    public void delete_RepositoryContainsThreeContracts_RepositoryShouldContainsTwoContracts() throws ContractAlreadyExistException {
        ContractRepository contractRepository = new ContractRepository();

        contractRepository.add(firstContract);
        contractRepository.add(secondContract);
        contractRepository.add(thirdContract);
        contractRepository.delete(secondContract.getId());

        assertThat(contractRepository.getAll()).containsAll(List.of(firstContract, thirdContract));
    }

    @Test
    public void delete_ContractsDoesNotExist_ShouldNotThrowAnyException() {
        ContractRepository contractRepository = new ContractRepository();

        assertThatCode(() -> {
            contractRepository.delete(firstContract.getId());
        }).doesNotThrowAnyException();
    }

    @Test
    public void size_RepositoryContainsThreeContracts_ShouldReturnThree() throws ContractAlreadyExistException {
        ContractRepository contractRepository = new ContractRepository();

        contractRepository.add(firstContract);
        contractRepository.add(secondContract);
        contractRepository.add(thirdContract);

        assertThat(contractRepository.size()).isEqualTo(3);
    }

    @Test
    public void size_ContractsDoesNotExist_ShouldReturnZero() {
        ContractRepository contractRepository = new ContractRepository();

        assertThat(contractRepository.size()).isZero();
    }

    @Test
    public void findBy_RepositoryIsEmpty_ShouldReturnEmptyList() {
        ContractRepository contractRepository = new ContractRepository();

        // select all contracts
        List<Contract> result = contractRepository.findBy(contract -> {
            return contract.getId() == firstContract.getId();
        });

        assertThat(result).isEmpty();
    }

    @Test
    public void findBy_SelectOneElement() throws ContractAlreadyExistException {
        ContractRepository contractRepository = new ContractRepository();

        contractRepository.add(firstContract);
        contractRepository.add(thirdContract);
        contractRepository.add(secondContract);
        List<Contract> result = contractRepository.findBy(contract -> {
            return contract.getId() == firstContract.getId();
        });

        assertThat(result).containsOnly(firstContract);
    }

    @Test
    public void findBy_SelectSomeElements() throws ContractAlreadyExistException {
        ContractRepository contractRepository = new ContractRepository();

        contractRepository.add(firstContract);
        contractRepository.add(thirdContract);
        contractRepository.add(secondContract);
        List<Contract> result = contractRepository.findBy(contract -> {
            return contract.getId() == firstContract.getId() || contract.getId() == secondContract.getId();
        });

        assertThat(result).containsOnly(firstContract, secondContract);
    }

    @Test
    public void sortBy_RepositoryIsEmpty() {
        ContractRepository contractRepository = new ContractRepository();

        assertThatCode(() -> contractRepository.sortBy((o1, o2) -> {
            if (o1.getId() == o2.getId()) return 0;
            return o1.getId() > o2.getId() ? 1 : -1;
        })).doesNotThrowAnyException();
    }
}