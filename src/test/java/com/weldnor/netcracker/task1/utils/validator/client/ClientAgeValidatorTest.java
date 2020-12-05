package com.weldnor.netcracker.task1.utils.validator.client;

import com.weldnor.netcracker.task1.entity.client.Client;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


public class ClientAgeValidatorTest {
    private final ClientAgeValidator validator = new ClientAgeValidator();

    @Test
    public void canValidate() {
        assertThat(validator.canValidate(new Object())).isFalse();
        assertThat(validator.canValidate(new Client())).isTrue();
    }

    @Test
    public void checkBirthDateIsNegative() {
        assertThat(validator.checkBirthDateIsNegative(LocalDate.of(2000, 3, 20))).isFalse();
        assertThat(validator.checkBirthDateIsNegative(LocalDate.now().plusDays(1))).isTrue();
    }

    @Test
    public void checkAgeLessThenMin() {
        assertThat(validator.checkAgeLessThenMin(18)).isFalse();
        assertThat(validator.checkAgeLessThenMin(17)).isTrue();
    }


}