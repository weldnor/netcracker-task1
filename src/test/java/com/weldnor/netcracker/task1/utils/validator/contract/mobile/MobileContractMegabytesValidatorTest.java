package com.weldnor.netcracker.task1.utils.validator.contract.mobile;

import com.weldnor.netcracker.task1.entity.contract.MobileContract;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class MobileContractMegabytesValidatorTest {
    private final MobileContractMegabytesValidator validator = new MobileContractMegabytesValidator();

    @Test
    public void canValidate() {
        assertThat(validator.canValidate(new Object())).isFalse();
        assertThat(validator.canValidate(new MobileContract())).isTrue();
    }

    @Test
    public void checkMegabytesOutOfRange() {
        assertThat(validator.checkMegabytesOutOfRange(-1)).isTrue();
        assertThat(validator.checkMegabytesOutOfRange(1024 * 50 + 1)).isTrue();
        assertThat(validator.checkMegabytesOutOfRange(1024 * 50)).isFalse();
        assertThat(validator.checkMegabytesOutOfRange(0)).isFalse();
    }
}