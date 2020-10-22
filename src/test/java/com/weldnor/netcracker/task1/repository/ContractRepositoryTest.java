package com.weldnor.netcracker.task1.repository;

import com.weldnor.netcracker.task1.client.Client;
import com.weldnor.netcracker.task1.client.Gender;
import com.weldnor.netcracker.task1.contract.Contract;
import com.weldnor.netcracker.task1.contract.InternetContract;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ContractRepositoryTest {

    private final Contract firstContract = new InternetContract(
            15,
            new Client(42, "Alex", Gender.MALE, "202408573923"),
            LocalDate.of(2005, 7, 17),
            LocalDate.of(20021, 3, 9),
            1024
    );

    private final Contract secondContract = new InternetContract(
            21,
            new Client(39, "Andrey", Gender.MALE, "09823313897"),
            LocalDate.of(2009, 8, 14),
            LocalDate.of(2022, 9, 2),
            2048
    );

    private final Contract thirdContract = new InternetContract(
            18,
            new Client(39, "Lisa", Gender.FEMALE, "79283742309"),
            LocalDate.of(2015, 1, 29),
            LocalDate.of(2025, 5, 6),
            512
    );

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
}