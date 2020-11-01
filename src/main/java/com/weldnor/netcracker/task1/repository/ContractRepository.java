package com.weldnor.netcracker.task1.repository;

import com.weldnor.netcracker.task1.contract.Contract;
import com.weldnor.netcracker.task1.sorter.QuickSorter;
import com.weldnor.netcracker.task1.sorter.Sorter;

import java.util.*;
import java.util.function.Predicate;

/**
 * Класс для хранения контрактов.
 */
public class ContractRepository {

    private static final int INITIAL_SIZE = 8;
    private Contract[] data;
    private int size = 0;

    private final Sorter<Contract> sorter = new QuickSorter<>();

    public ContractRepository() {
        data = new Contract[INITIAL_SIZE];
    }

    /**
     * Добавление контракта в репозиторий.
     * Если контракт с таким id уже существует, то вызывается ошибка {@link ContractAlreadyExistException}.
     *
     * @param contract добавляемый контракт.
     * @throws ContractAlreadyExistException контакт с таким id уже существует.
     */
    public void add(Contract contract) throws ContractAlreadyExistException {
        long contract_id = contract.getId();

        if (contains(contract_id)) {
            throw new ContractAlreadyExistException("contract with id: " + contract_id + " already exist");
        }

        if (isFull()) {
            expand();
        }
        data[size] = contract;
        size++;
    }

    private boolean isFull() {
        return data.length == size;
    }

    private void expand() {
        int new_size = data.length * 2;
        Contract[] new_data = new Contract[new_size];
        System.arraycopy(data, 0, new_data, 0, size);
        data = new_data;
    }

    /**
     * Проверкa существования контракта с заданным id
     *
     * @param contract_id id контракта, который мы пытаемся найти.
     * @return true, если контракт существует, false, если нет.
     */
    public boolean contains(long contract_id) {
        return get(contract_id).isPresent();
    }

    public Optional<Contract> get(long contract_id) {
        for (int i = 0; i < size; i++) {
            if (data[i].getId() == contract_id)
                return Optional.of(data[i]);
        }
        return Optional.empty();
    }

    /**
     * Получение всех контрактов.
     *
     * @return @link{{@link List<Contract>}} из всех хранящихся в репозитории контрактов.
     */
    public List<Contract> getAll() {
        List<Contract> result = new LinkedList<>(Arrays.asList(data).subList(0, size));
        return result;
    }

    /**
     * Удаление контракта по его id.
     *
     * @param contract_id id удаляемого контракта.
     */
    public void delete(long contract_id) {
        int index = findIndex(contract_id);

        if (index == -1)
            return;

        if (size - index + 1 >= 0) {
            System.arraycopy(data, index + 1, data, index, size - index + 1);
        }
        size--;
    }

    private int findIndex(long contract_id) {
        for (int i = 0; i < size; i++) {
            if (data[i].getId() == contract_id)
                return i;
        }
        return -1;
    }

    public List<Contract> findBy(Predicate<Contract> predicate) {
        List<Contract> result = new ArrayList<>();

        for (int i = 0; i < size(); i++) {
            Contract curr = data[i];
            if (predicate.test(curr)) {
                result.add(curr);
            }
        }
        return result;
    }

    /**
     * Сортировка контрактов по заданному критерию.
     *
     * @param comparator компаратор для сравнения контрактов.
     */
    public void sortBy(Comparator<Contract> comparator) {
        sorter.sort(data, comparator, 0, size - 1);
    }

    /**
     * Получение количества контрактов  в репозитории.
     *
     * @return количество контрактов.
     */
    public int size() {
        return size;
    }
}
