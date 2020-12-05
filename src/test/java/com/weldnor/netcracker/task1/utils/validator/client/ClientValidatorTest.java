package com.weldnor.netcracker.task1.utils.validator.client;

import com.weldnor.netcracker.task1.entity.client.Client;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientValidatorTest {
    private final ClientPasswordValidator validator = new ClientPasswordValidator();

    @Test
    public void canValidate() {
        assertThat(validator.canValidate(new Object())).isFalse();
        assertThat(validator.canValidate(new Client())).isTrue();
    }
}