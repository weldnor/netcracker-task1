package com.weldnor.netcracker.task1.utils.parser;

public class ParseException extends Exception {
    /**
     * @param message сообщение об ошибке
     */
    public ParseException(String message) {
        super(message);
    }

    /**
     * @param exception исключение, вызванное при парсинге
     */
    public ParseException(Exception exception) {
        super(exception);
    }
}
