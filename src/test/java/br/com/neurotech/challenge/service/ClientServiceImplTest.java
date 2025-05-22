package br.com.neurotech.challenge.service;

import br.com.neurotech.challenge.dto.CreateClientDTO;
import br.com.neurotech.challenge.dto.ResponseClientDTO;
import br.com.neurotech.challenge.entity.CreditType;
import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.repository.ClientRepository;
import br.com.neurotech.challenge.service.impl.ClientServiceImpl;
import br.com.neurotech.challenge.service.impl.CreditEvaluatorServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class ClientServiceImplTest {

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private CreditEvaluatorService creditEvaluatorService;

    @Test
    @DisplayName("Deve salvar um cliente com sucesso e retornar o ID")
    void save() {
        CreateClientDTO dto = new CreateClientDTO("João", 25, 4000.0);

        CreditType creditType = CreditType.JUROS_FIXOS;

        NeurotechClient clientSave = new NeurotechClient(dto, creditType);
        clientSave.setId(1L);

        Mockito.when(creditEvaluatorService.evaluate(dto.age(), dto.income()))
                .thenReturn(creditType);

        Mockito.when(clientRepository.save(any(NeurotechClient.class)))
                .thenReturn(clientSave);

        String id = clientService.save(dto);

        assertEquals("1", id);

        verify(creditEvaluatorService).evaluate(25, 4000.0);

        verify(clientRepository).save(any(NeurotechClient.class));
    }

    @Test
    @DisplayName("Deve retornar os dados de um cliente existente pelo ID")
    void get() {
        CreateClientDTO dto = new CreateClientDTO("Maria", 65, 6000.0);

        CreditType creditType = CreditType.JUROS_VARIAVEIS;

        NeurotechClient client = new NeurotechClient(dto, creditType);
        client.setId(2L);

        Mockito.when(clientRepository.findById(2L)).thenReturn(Optional.of(client));

        ResponseClientDTO response = clientService.get("2");

        assertEquals("Maria", response.name());
        assertEquals(65, response.age());
        verify(clientRepository).findById(2L);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o cliente não for encontrado")
    void getShouldThrowExceptionWhenClientDoesNotExist() {

        Mockito.when(clientRepository.findById(999L)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> {
            clientService.get("999");
        });

        assertEquals("Client não encontrado.", ex.getMessage());
        verify(clientRepository).findById(999L);
    }
}
