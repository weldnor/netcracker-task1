package com.weldnor.netcracker.task1.client;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Client {
    private long id;
    private String fullName;
    private Gender gender;
    private String passport; //FIXME?
}
