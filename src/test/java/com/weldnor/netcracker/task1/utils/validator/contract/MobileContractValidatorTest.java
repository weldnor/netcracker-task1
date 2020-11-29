package com.weldnor.netcracker.task1.utils.validator.contract;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class MobileContractValidatorTest {
    private final MobileContractValidator validator = new MobileContractValidator();

    @Test
    public void validate() {
        // TODO
    }

    @Test
    public void validateSms() {
        assertThat(validator.validateSms(-1)).hasSize(1);
        assertThat(validator.validateSms(10001)).hasSize(1);
        assertThat(validator.validateSms(1000)).hasSize(0);
        assertThat(validator.validateSms(0)).hasSize(0);
    }

    @Test
    public void checkSmsOutOfRange() {
        assertThat(validator.checkSmsOutOfRange(-1)).isTrue();
        assertThat(validator.checkSmsOutOfRange(10001)).isTrue();
        assertThat(validator.checkSmsOutOfRange(1000)).isFalse();
        assertThat(validator.checkSmsOutOfRange(0)).isFalse();
    }

    @Test
    public void validateMegabytes() {
        assertThat(validator.validateMegabytes(-1)).hasSize(1);
        assertThat(validator.validateMegabytes(1024 * 50 + 1)).hasSize(1);
        assertThat(validator.validateMegabytes(1024 * 50)).hasSize(0);
        assertThat(validator.validateMegabytes(0)).hasSize(0);
    }

    @Test
    public void checkMegabytesOutOfRange() {
        assertThat(validator.checkMegabytesOutOfRange(-1)).isTrue();
        assertThat(validator.checkMegabytesOutOfRange(1024 * 50 + 1)).isTrue();
        assertThat(validator.checkMegabytesOutOfRange(1024 * 50)).isFalse();
        assertThat(validator.checkMegabytesOutOfRange(0)).isFalse();
    }

    @Test
    public void validateMinutes() {
    }

    @Test
    public void checkMinutesOutOfRange() {
        assertThat(validator.checkMinutesOutOfRange(-1)).isTrue();
        assertThat(validator.checkMinutesOutOfRange(4001)).isTrue();
        assertThat(validator.checkMinutesOutOfRange(4000)).isFalse();
        assertThat(validator.checkMinutesOutOfRange(0)).isFalse();
    }
}