package com.weldnor.netcracker.task1.utils.validator.contract.mobile;

import com.weldnor.netcracker.task1.entity.contract.MobileContract;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class MobileContractSmsValidatorTest {
    private final MobileContractSmsValidator validator = new MobileContractSmsValidator();

    @Test
    public void canValidate() {
        assertThat(validator.canValidate(new Object())).isFalse();
        assertThat(validator.canValidate(new MobileContract())).isTrue();
    }

    @Test
    public void checkSmsOutOfRange() {
        assertThat(validator.checkSmsOutOfRange(-1)).isTrue();
        assertThat(validator.checkSmsOutOfRange(10001)).isTrue();
        assertThat(validator.checkSmsOutOfRange(1000)).isFalse();
        assertThat(validator.checkSmsOutOfRange(0)).isFalse();
    }
}