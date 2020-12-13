package com.weldnor.netcracker.task1.repository;

import com.weldnor.netcracker.task1.entity.contract.Contract;
import com.weldnor.netcracker.task1.utils.sorter.QuickSorter;
import com.weldnor.netcracker.task1.utils.sorter.Sorter;

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

    /**
     * Создание пустого репозитория с начальным размером {@value INITIAL_SIZE}.
     */
    public ContractRepository() {
        data = new Contract[INITIAL_SIZE];
    }

    /**
     * Добавление контракта в репозиторий.
     * Если контракт с таким id уже существует, то вызывается ошибка {@link ContractAlreadyExistException}.
     *
     * @param contract добавляемый контракт
     * @throws ContractAlreadyExistException если контакт с таким id уже существует
     */
    public void add(Contract contract) throws ContractAlreadyExistException {
        long contractId = contract.getId();

        if (contains(contractId)) {
            throw new ContractAlreadyExistException("contract with id: " + contractId + " already exist");
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
        int newSize = data.length * 2;
        Contract[] newData = new Contract[newSize];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    /**
     * Проверка существования контракта с заданным id.
     *
     * @param contractId id контракта, который мы пытаемся найти
     * @return true, если контракт существует, false, если нет
     */
    public boolean contains(long contractId) {
        return get(contractId).isPresent();
    }

    /**
     * Получение контракта по id.
     *
     * @param contractId id контракта
     * @return Optional с контрактом, если он найден
     */
    public Optional<Contract> get(long contractId) {
        for (int i = 0; i < size; i++) {
            if (data[i].getId() == contractId) {
                return Optional.of(data[i]);
            }
        }
        return Optional.empty();
    }

    /**
     * Получение всех контрактов.
     *
     * @return {@link List} из всех хранящихся в репозитории контрактов
     */
    public List<Contract> getAll() {
        return new LinkedList<>(Arrays.asList(data).subList(0, size));
    }

    /**
     * Удаление контракта по его id.
     *
     * @param contractId id удаляемого контракта
     */
    public void delete(long contractId) {
        int index = findIndex(contractId);

        if (index == -1) {
            return;
        }

        if (size - index + 1 >= 0) {
            System.arraycopy(data, index + 1, data, index, size - index + 1);
        }
        size--;
    }

    private int findIndex(long contractId) {
        for (int i = 0; i < size; i++) {
            if (data[i].getId() == contractId) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Поиск контракта по заданному критерию.
     *
     * @param predicate критерий поиска
     * @return {{@link List<Contract>}}
     */
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
     * @param comparator компаратор для сравнения контрактов
     */
    public void sortBy(Comparator<Contract> comparator) {
        sorter.sort(data, comparator, 0, size - 1);
    }

    /**
     * Получение количества контрактов  в репозитории.
     *
     * @return количество контрактов
     */
    public int size() {
        return size;
    }
}
