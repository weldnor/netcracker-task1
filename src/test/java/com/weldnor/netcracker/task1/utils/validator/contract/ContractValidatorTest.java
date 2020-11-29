package com.weldnor.netcracker.task1.utils.validator.contract;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ContractValidatorTest {
    private final ContractValidator validator = new ContractValidator();

    @Test
    public void validate() {
        //TODO
    }

    @Test
    public void validateDates() {
        assertThat(validator.validateDates(LocalDate.of(2018, 1, 1), LocalDate.of(2018, 1, 2))).hasSize(0);
    }

    @Test
    public void checkStartDateIsAfterExpirationDate() {
        assertThat(validator.checkStartDateIsAfterExpirationDate(LocalDate.of(2000, 1, 1), LocalDate.of(2001, 1, 1))).isFalse();
        assertThat(validator.checkStartDateIsAfterExpirationDate(LocalDate.of(2001, 1, 1), LocalDate.of(2000, 1, 1))).isTrue();
    }
}