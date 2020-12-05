package com.weldnor.netcracker.task1.utils.validator.contract;

import com.weldnor.netcracker.task1.entity.contract.InternetContract;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class ContractDateValidatorTest {
    private final ContractDateValidator validator = new ContractDateValidator();

    @Test
    public void canValidate() {
        assertThat(validator.canValidate(new Object())).isFalse();
        assertThat(validator.canValidate(new InternetContract())).isTrue();
    }

    @Test
    public void checkStartDateIsAfterExpirationDate() {
        assertThat(validator.checkStartDateIsAfterExpirationDate(LocalDate.of(2000, 1, 1), LocalDate.of(2001, 1, 1))).isFalse();
        assertThat(validator.checkStartDateIsAfterExpirationDate(LocalDate.of(2001, 1, 1), LocalDate.of(2000, 1, 1))).isTrue();
    }
}