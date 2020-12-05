package com.weldnor.netcracker.task1.utils.validator.contract.mobile;

import com.weldnor.netcracker.task1.entity.contract.InternetContract;
import com.weldnor.netcracker.task1.entity.contract.MobileContract;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class MobileContractValidatorTest {
    private final MobileContractValidator validator = new MobileContractValidator();

    @Test
    public void canValidate() {
        assertThat(validator.canValidate(new Object())).isFalse();
        assertThat(validator.canValidate(new MobileContract())).isTrue();
    }
}