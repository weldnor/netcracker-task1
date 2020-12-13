package com.weldnor.netcracker.task1.utils.validator.contract.mobile;

import com.weldnor.netcracker.task1.entity.contract.MobileContract;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class MobileContractMinutesValidatorTest {
    private final MobileContractMinutesValidator validator = new MobileContractMinutesValidator();

    @Test
    public void canValidate() {
        assertThat(validator.canValidate(new Object())).isFalse();
        assertThat(validator.canValidate(new MobileContract())).isTrue();
    }

    @Test
    public void checkMinutesOutOfRange() {
        assertThat(validator.checkMinutesOutOfRange(-1)).isTrue();
        assertThat(validator.checkMinutesOutOfRange(4001)).isTrue();
        assertThat(validator.checkMinutesOutOfRange(4000)).isFalse();
        assertThat(validator.checkMinutesOutOfRange(0)).isFalse();
    }
}