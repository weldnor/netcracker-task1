package com.weldnor.netcracker.task1.repository;

public class ContractAlreadyExistException extends Exception {
    /**
     * @param message сообщение об ошибке
     */
    public ContractAlreadyExistException(final String message) {
        super(message);
    }
}
