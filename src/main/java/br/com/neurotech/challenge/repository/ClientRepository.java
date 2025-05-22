package br.com.neurotech.challenge.repository;

import br.com.neurotech.challenge.entity.CreditType;
import br.com.neurotech.challenge.entity.NeurotechClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<NeurotechClient,Long> {
    List<NeurotechClient> findByAgeBetweenAndIncomeBetweenAndCreditType(
            Integer minAge,
            Integer maxAge,
            Double minIncome,
            Double maxIncome,
            CreditType creditType
    );
}
