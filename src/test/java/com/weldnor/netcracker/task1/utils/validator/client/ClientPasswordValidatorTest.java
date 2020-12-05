package com.weldnor.netcracker.task1.utils.validator.client;

import com.weldnor.netcracker.task1.entity.client.Client;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientPasswordValidatorTest {
    private final ClientPasswordValidator validator = new ClientPasswordValidator();

    @Test
    public void canValidate() {
        assertThat(validator.canValidate(new Object())).isFalse();
        assertThat(validator.canValidate(new Client())).isTrue();
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
}