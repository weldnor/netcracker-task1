package com.weldnor.netcracker.task1.contract;

import com.weldnor.netcracker.task1.client.Client;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InternetContract extends Contract {
    private int maxSpeed;

    public InternetContract(Client client, LocalDate startDate, LocalDate expirationDate, int maxSpeed) {
        super(client, startDate, expirationDate);
        this.maxSpeed = maxSpeed;
    }
}
