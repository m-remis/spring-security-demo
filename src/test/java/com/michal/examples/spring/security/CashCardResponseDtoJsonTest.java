package com.michal.examples.spring.security;

import com.michal.examples.spring.security.dto.CashCardResponseDto;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class CashCardResponseDtoJsonTest {

    @Autowired
    private JacksonTester<CashCardResponseDto> json;

    @Autowired
    private JacksonTester<CashCardResponseDto[]> jsonList;

    private CashCardResponseDto[] cashCardResponseDtos;

    @BeforeEach
    void setUp() {
        cashCardResponseDtos = Arrays.array(
                new CashCardResponseDto(99L, 123.45, "sarah1"),
                new CashCardResponseDto(100L, 1.00, "sarah1"),
                new CashCardResponseDto(101L, 150.00, "sarah1"));
    }

    @Test
    void cashCardSerializationTest() throws IOException {
        CashCardResponseDto cashCardResponseDto = cashCardResponseDtos[0];
        assertThat(json.write(cashCardResponseDto)).isStrictlyEqualToJson("/json/single.json");
        assertThat(json.write(cashCardResponseDto)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(cashCardResponseDto)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(99);
        assertThat(json.write(cashCardResponseDto)).hasJsonPathNumberValue("@.amount");
        assertThat(json.write(cashCardResponseDto)).extractingJsonPathNumberValue("@.amount")
                .isEqualTo(123.45);
    }

    @Test
    void cashCardDeserializationTest() throws IOException {
        String expected = """
                {
                    "id": 99,
                    "amount": 123.45, 
                    "owner": "sarah1"
                }
                """;
        assertThat(json.parse(expected))
                .isEqualTo(new CashCardResponseDto(99L, 123.45, "sarah1"));
        assertThat(json.parseObject(expected).id()).isEqualTo(99L);
        assertThat(json.parseObject(expected).amount()).isEqualTo(123.45);
    }

    @Test
    void cashCardListSerializationTest() throws IOException {
        assertThat(jsonList.write(cashCardResponseDtos)).isStrictlyEqualToJson("/json/list.json");
    }

    @Test
    void cashCardListDeserializationTest() throws IOException {
        String expected = """
                [
                     {"id": 99, "amount": 123.45 , "owner": "sarah1"},
                     {"id": 100, "amount": 1.00 , "owner": "sarah1"},
                     {"id": 101, "amount": 150.00, "owner": "sarah1"}
                                                  
                ]
                """;
        assertThat(jsonList.parse(expected)).isEqualTo(cashCardResponseDtos);
    }
}
