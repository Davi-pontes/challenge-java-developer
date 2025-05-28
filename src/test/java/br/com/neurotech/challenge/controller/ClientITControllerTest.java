package br.com.neurotech.challenge.controller;

import br.com.neurotech.challenge.dto.CreateClientDTO;
import static org.junit.jupiter.api.Assertions.*;

import br.com.neurotech.challenge.dto.ResponseClientDTO;
import br.com.neurotech.challenge.dto.ResponseCreditEligibility;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ClientITControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private URI saveClient(String nome, int idade, double renda) {
        CreateClientDTO dto = new CreateClientDTO(nome, idade, renda);
        ResponseEntity<Void> response = restTemplate.postForEntity("/api/v1/client", dto, Void.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        URI location = response.getHeaders().getLocation();
        assertNotNull(location);
        return location;
    }

    @Test
    @DisplayName("POST /api/v1/client deve retornar 201 Created com Location")
    void shouldCreateClientAndReturnLocation() {
        saveClient("Teste", 25, 6000.0);
    }

    @Test
    @DisplayName("GET /api/v1/client/{id} deve retornar os dados do cliente")
    void shouldGetClientById() {
        URI location = saveClient("João", 30, 7000.0);

        ResponseEntity<ResponseClientDTO> response = restTemplate.getForEntity(location, ResponseClientDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseClientDTO dto = response.getBody();
        assertNotNull(dto);
        assertEquals("João", dto.name());
        assertEquals(30, dto.age());
        assertEquals(7000.0, dto.income());
    }
    @Test
    @DisplayName("POST com dados inválidos deve retornar 400")
    void shouldReturn400WhenDataIsInvalid() {
        CreateClientDTO dto = new CreateClientDTO("", -1, -1000.0);
        ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/client", dto, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    @Test
    @DisplayName("GET /api/v1/client/{id}/car-credit Deve retornar elegibilidade positiva para o modelo HATCH com renda entre 5000 e 15000")
    void checkCreditEligibilityForHatch() {
        URI location = saveClient("Jose", 30, 8000.0);

        ResponseEntity<ResponseCreditEligibility> creditResponse = restTemplate.getForEntity(
                location + "/car-credit?model=HATCH",
                ResponseCreditEligibility.class
        );

        assertEquals(HttpStatus.OK, creditResponse.getStatusCode());

        ResponseCreditEligibility response = creditResponse.getBody();
        assertNotNull(response);
        assertTrue(response.eligible());
        assertEquals("Cliente apto para financiamento do modelo Hatch.", response.message());
    }
}