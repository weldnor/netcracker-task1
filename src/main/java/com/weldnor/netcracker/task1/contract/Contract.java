package com.weldnor.netcracker.task1.contract;

import com.weldnor.netcracker.task1.client.Client;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
public abstract class Contract {
    private long id;
    private Client client;
    private LocalDate startDate;
    private LocalDate expirationDate;
}
