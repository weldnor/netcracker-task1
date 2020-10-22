package com.weldnor.netcracker.task1.contract;

import com.weldnor.netcracker.task1.client.Client;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CellularContract extends Contract {
    int sms;
    int megabytes;
    int minutes;

    public CellularContract(
            Client client,
            LocalDate startDate,
            LocalDate expirationDate,
            long id,
            int sms,
            int megabytes,
            int minutes
    ) {
        super(id, client, startDate, expirationDate);
        this.sms = sms;
        this.megabytes = megabytes;
        this.minutes = minutes;
    }
}
