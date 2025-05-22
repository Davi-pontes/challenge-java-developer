package br.com.neurotech.challenge.service;

import br.com.neurotech.challenge.dto.ResponseClientDTO;
import br.com.neurotech.challenge.dto.ResponseCreditEligibility;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.service.impl.CreditServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreditServiceImplTest {
    @Mock
    private ClientService clientService;

    @InjectMocks
    private CreditServiceImpl creditService;

    @Test
    @DisplayName("Deve retornar elegível quando cliente com renda R$5000 solicita financiamento para Hatch")
    void checkCredit() {
        String clientId = "123";
        ResponseClientDTO clientDTO = new ResponseClientDTO("Davi",24, 5000.0);
        Mockito.when(clientService.get(clientId)).thenReturn(clientDTO);

        ResponseCreditEligibility result = creditService.checkCredit(clientId, VehicleModel.HATCH);

        assertTrue(result.eligible());
        assertEquals("Cliente apto para financiamento do modelo Hatch.", result.message());
        Mockito.verify(clientService, Mockito.times(1)).get(clientId);
    }
    @Test
    @DisplayName("Deve retornar não elegível quando cliente com renda abaixo de R$5000 solicita Hatch")
    void checkCreditHatchModelIncomeBelow5000ShouldNotBeEligible() {
        String clientId = "123";
        ResponseClientDTO clientDTO = new ResponseClientDTO("Davi", 24, 4999.99);
        Mockito.when(clientService.get(clientId)).thenReturn(clientDTO);

        ResponseCreditEligibility result = creditService.checkCredit(clientId, VehicleModel.HATCH);

        assertFalse(result.eligible(), "Cliente não deveria ser elegível com renda abaixo de R$5000");
        assertEquals("Cliente não atende aos critérios para o modelo Hatch.", result.message());
    }
    @Test
    @DisplayName("Deve retornar elegível quando cliente com renda R$15000 solicita Hatch")
    void checkCreditHatchModelIncome15000ShouldBeEligible() {
        String clientId = "123";
        ResponseClientDTO clientDTO = new ResponseClientDTO("Davi", 24, 15000.0);
        Mockito.when(clientService.get(clientId)).thenReturn(clientDTO);

        ResponseCreditEligibility result = creditService.checkCredit(clientId, VehicleModel.HATCH);

        assertTrue(result.eligible(), "Cliente deveria ser elegível para Hatch com renda R$15000");
    }
    @Test
    @DisplayName("Deve retornar elegível quando cliente com renda R$8001 e idade 21 solicita SUV")
    void checkCredit_SuvModel_Income8001_Age21_ShouldBeEligible() {
        String clientId = "123";
        ResponseClientDTO clientDTO = new ResponseClientDTO("Davi", 21, 8001.0);
        Mockito.when(clientService.get(clientId)).thenReturn(clientDTO);

        ResponseCreditEligibility result = creditService.checkCredit(clientId, VehicleModel.SUV);

        assertTrue(result.eligible(), "Cliente deveria ser elegível para SUV com renda R$8001 e idade 21");
        assertEquals("Cliente apto para financiamento do modelo SUV.", result.message());
    }
}