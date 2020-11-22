package com.weldnor.netcracker.task1.entity.contract;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
public class InternetContract extends Contract {
    private int maxSpeed;
}
