package com.weldnor.netcracker.task1.utils.validator.contract;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class InternetContractValidatorTest {

    private final InternetContractValidator validator = new InternetContractValidator();

    @Test
    public void validate() {
        //TODO
    }

    @Test
    public void validateMaxSpeed() {
        assertThat(validator.validateMaxSpeed(-1)).hasSize(2);
        assertThat(validator.validateMaxSpeed(1)).hasSize(1);
        assertThat(validator.validateMaxSpeed(5)).hasSize(0);
        assertThat(validator.validateMaxSpeed(3000)).hasSize(0);
        assertThat(validator.validateMaxSpeed(3001)).hasSize(1);
    }

    @Test
    public void checkMaxSpeedIsNegativeOrZero() {
        assertThat(validator.checkMaxSpeedIsNegativeOrZero(-1)).isTrue();
        assertThat(validator.checkMaxSpeedIsNegativeOrZero(0)).isTrue();
        assertThat(validator.checkMaxSpeedIsNegativeOrZero(204)).isFalse();
    }

    @Test
    public void checkMaxSpeedLessThenMin() {
        assertThat(validator.checkMaxSpeedLessThenMin(4)).isTrue();
        assertThat(validator.checkMaxSpeedLessThenMin(5)).isFalse();
    }

    @Test
    public void checkMaxSpeedMoreThenMax() {
        assertThat(validator.checkMaxSpeedMoreThenMax(3001)).isTrue();
        assertThat(validator.checkMaxSpeedMoreThenMax(3000)).isFalse();
    }
}