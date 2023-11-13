package com.calculator.api.operation;

import com.calculator.api.dtos.CalculatorInputDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class TestCalculatorEndpoint_TestAdd {
  @Autowired
  TestRestTemplate restTemplate;

  @Test
  public void TestAddWithValidInput() {
    CalculatorInputDto input = new CalculatorInputDto();
    input.setInput("2+2");

    ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/calculator", input ,String.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("{\"response\":\"4.0\"}", response.getBody());
  }

  @Test
  public void TestAddWithInvalidInput() {
    CalculatorInputDto input = new CalculatorInputDto();
    input.setInput("-1 + 22");

    ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/calculator", input ,String.class);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }
}
