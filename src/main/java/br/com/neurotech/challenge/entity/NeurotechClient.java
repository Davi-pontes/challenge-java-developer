package br.com.neurotech.challenge.entity;

import br.com.neurotech.challenge.dto.CreateClientDTO;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "clients")
public class NeurotechClient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private Integer age;
	private Double income;

	@Enumerated(EnumType.STRING)
	private CreditType creditType;

	public NeurotechClient(){

	}

	public NeurotechClient(CreateClientDTO clientDTO, CreditType creditType1){
		this.name = clientDTO.name();
		this.age = clientDTO.age();
		this.income = clientDTO.income();
		this.creditType = creditType1;
	}
}