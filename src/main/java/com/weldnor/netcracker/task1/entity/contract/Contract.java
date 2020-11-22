package com.weldnor.netcracker.task1.entity.contract;

import com.weldnor.netcracker.task1.entity.client.Client;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@SuperBuilder
public abstract class Contract {
    private long id;
    private Client client;
    private LocalDate startDate;
    private LocalDate expirationDate;
}
