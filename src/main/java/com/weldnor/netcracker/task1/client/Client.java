package com.weldnor.netcracker.task1.client;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;

@Data
@AllArgsConstructor
public class Client {
    private long id;
    private String fullName;
    private Gender gender;
    private String passport;
    private LocalDate birthDate;

    public int getAge() {
        var currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }
}
