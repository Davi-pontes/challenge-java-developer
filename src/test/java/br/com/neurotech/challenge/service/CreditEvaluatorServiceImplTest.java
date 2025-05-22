package br.com.neurotech.challenge.service;

import br.com.neurotech.challenge.entity.CreditType;
import br.com.neurotech.challenge.service.impl.CreditEvaluatorServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditEvaluatorServiceImplTest {

    private final CreditEvaluatorServiceImpl evaluator = new CreditEvaluatorServiceImpl();

    @Test
    @DisplayName("Deve retornar JUROS_FIXOS para jovens adultos de 18 a 25 anos")
    void shouldReturnJurosFixosForYoungAdults() {
        assertEquals(CreditType.JUROS_FIXOS, evaluator.evaluate(18, 1000));
        assertEquals(CreditType.JUROS_FIXOS, evaluator.evaluate(25, 3000));
    }
    @Test
    @DisplayName("Deve retornar CONSIGNADO para clientes com mais de 65 anos")
    void shouldReturnConsignadoForSeniors() {
        assertEquals(CreditType.CONSIGNADO, evaluator.evaluate(66, 2000));
        assertEquals(CreditType.CONSIGNADO, evaluator.evaluate(80, 5000));
    }
    @Test
    @DisplayName("Deve retornar JUROS_VARIAVEIS para adultos entre 21 e 65 com renda entre 5000 e 15000")
    void shouldReturnJurosVariaveisForAdultsWithStableIncome() {
        assertEquals(CreditType.JUROS_VARIAVEIS, evaluator.evaluate(30, 6000));
        assertEquals(CreditType.JUROS_VARIAVEIS, evaluator.evaluate(50, 10000));
        assertEquals(CreditType.JUROS_VARIAVEIS, evaluator.evaluate(65, 15000));
    }

    @Test
    @DisplayName("Deve lançar exceção se cliente não se encaixar em nenhum critério")
    void shouldThrowExceptionForInvalidClients() {
        assertThrows(IllegalArgumentException.class, () -> evaluator.evaluate(30, 4000));
        assertThrows(IllegalArgumentException.class, () -> evaluator.evaluate(26, 2000));
    }
}