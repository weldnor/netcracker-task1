package com.weldnor.netcracker.task1.contract;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class MobileContract extends Contract {
    private int sms;
    private int megabytes;
    private int minutes;
}
