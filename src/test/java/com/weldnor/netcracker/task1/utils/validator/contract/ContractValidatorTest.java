package com.weldnor.netcracker.task1.utils.validator.contract;

import com.weldnor.netcracker.task1.entity.client.Client;
import com.weldnor.netcracker.task1.entity.contract.Contract;
import com.weldnor.netcracker.task1.entity.contract.InternetContract;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class ContractValidatorTest {
    private final ContractValidator validator = new ContractValidator();

    @Test
    public void canValidate() {
        assertThat(validator.canValidate(new Object())).isFalse();
        assertThat(validator.canValidate(new InternetContract())).isTrue();
    }
}