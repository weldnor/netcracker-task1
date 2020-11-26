package com.weldnor.netcracker.task1.utils.parser;

import java.util.Map;

public interface Parser<T> {
    /**
     * @param params {@link Map} содержащий параметры для создания объекта
     * @return получившийся объект
     * @throws ParseException при ошибке парсинга какого-либо параметра.
     */
    T parse(Map<String, String> params) throws ParseException;
}

