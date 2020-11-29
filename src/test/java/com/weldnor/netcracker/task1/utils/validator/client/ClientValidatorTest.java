package com.weldnor.netcracker.task1.utils.validator.client;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientValidatorTest {
    private final ClientValidator validator = new ClientValidator();

    @Test
    public void validate() {
        // TODO
    }

    @Test
    public void validateFullName() {
        assertThat(validator.validateFullName("Alan Wake")).hasSize(0);
        assertThat(validator.validateFullName("lol 123 lol 123 lol 123 lol 123 lol 123 lol 123 lol 123")).hasSize(2);
    }

    @Test
    public void checkFullNameIsTooLong() {
        assertThat(validator.checkFullNameIsTooLong("01234567890123456789012345678901234567891")).isTrue();
        assertThat(validator.checkFullNameIsTooLong("0123456789012345678901234567890123456789")).isFalse();
    }

    @Test
    public void checkFullNameIsTooSmall() {
        assertThat(validator.checkFullNameIsTooSmall("012")).isTrue();
        assertThat(validator.checkFullNameIsTooSmall("0123")).isFalse();
    }

    @Test
    public void checkFullNameContainsInvalidCharacters() {
        assertThat(validator.checkFullNameContainsInvalidCharacters("Prof Mollie Thomas 213%")).isTrue();
        assertThat(validator.checkFullNameContainsInvalidCharacters("Prof Mollie Thomas")).isFalse();
    }

    @Test
    public void validatePassport() {
        assertThat(validator.validatePassport("2014156789")).hasSize(0);
        assertThat(validator.validatePassport("123456z ")).hasSize(2);
    }

    @Test
    public void checkPassportLengthIsNotCorrect() {
        assertThat(validator.checkPassportLengthIsNotCorrect("12345678901")).isTrue();
        assertThat(validator.checkPassportLengthIsNotCorrect("1234567890")).isFalse();
    }

    @Test
    public void checkPassportContainsNoIntCharacters() {
        assertThat(validator.checkPassportContainsNoIntCharacters("asd4578$90")).isTrue();
        assertThat(validator.checkPassportContainsNoIntCharacters("1234567890")).isFalse();
    }

    @Test
    public void validateBirthDate() {
        assertThat(validator.validateBirthDate(LocalDate.now().plusYears(10))).hasSize(2);
        assertThat(validator.validateBirthDate(LocalDate.now().minusYears(17))).hasSize(1);
        assertThat(validator.validateBirthDate(LocalDate.now().minusYears(18))).hasSize(0);
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