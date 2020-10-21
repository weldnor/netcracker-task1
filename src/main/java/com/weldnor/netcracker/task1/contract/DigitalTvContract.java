package com.weldnor.netcracker.task1.contract;

import com.weldnor.netcracker.task1.client.Client;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DigitalTvContract extends Contract {
    private ChannelPackage channelPackage;

    public DigitalTvContract(Client client, LocalDate startDate, LocalDate expirationDate, ChannelPackage channelPackage) {
        super(client, startDate, expirationDate);
        this.channelPackage = channelPackage;
    }
}
