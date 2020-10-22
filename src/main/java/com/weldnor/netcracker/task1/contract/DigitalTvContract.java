package com.weldnor.netcracker.task1.contract;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
public class DigitalTvContract extends Contract {
    private ChannelPackage channelPackage;
}
