package com.weldnor.netcracker.task1.client;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientTest {

    @Test
    public void getAge() {
        Client client = new Client(5, "Alan", Gender.MALE, "1230715346123", LocalDate.of(2000, 6, 13));
        assertThat(client.getAge()).isEqualTo(20);
    }
}