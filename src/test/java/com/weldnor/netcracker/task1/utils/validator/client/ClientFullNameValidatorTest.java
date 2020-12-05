package com.weldnor.netcracker.task1.utils.validator.client;

import com.weldnor.netcracker.task1.entity.client.Client;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientFullNameValidatorTest {
    ClientFullNameValidator validator = new ClientFullNameValidator();

    @Test
    public void canValidate() {
        assertThat(validator.canValidate(new Object())).isFalse();
        assertThat(validator.canValidate(new Client())).isTrue();
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


}