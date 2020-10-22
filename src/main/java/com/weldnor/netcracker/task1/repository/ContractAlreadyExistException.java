package com.weldnor.netcracker.task1.repository;

public class ContractAlreadyExistException extends Exception {
    public ContractAlreadyExistException(String message) {
        super(message);
    }
}
