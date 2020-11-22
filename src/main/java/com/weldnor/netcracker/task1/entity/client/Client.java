package com.weldnor.netcracker.task1.entity.client;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;

@Data
@Builder
public class Client {
    private long id;
    private String fullName;
    private Gender gender;
    private String passport;
    private LocalDate birthDate;


    /**
     * @return возраст клиента
     */
    public int getAge() {
        var currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }
}
