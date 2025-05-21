package br.com.neurotech.challenge.controller;

import br.com.neurotech.challenge.dto.CreateClientDTO;
import br.com.neurotech.challenge.dto.ResponseClientDTO;
import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.service.impl.ClientServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/client")
@Tag(name = "Clientes", description = "Operações relacionadas a clientes")
public class ClientController {

    private final ClientServiceImpl clientService;

    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Criar um novo cliente", description = "Cria um novo cliente com os dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    public ResponseEntity<Void> saveClient(@RequestBody @Valid CreateClientDTO client, UriComponentsBuilder uriBuilder){
        String clientId = clientService.save(client);

        URI uri = uriBuilder.path("/api/v1/client/{id}")
                .buildAndExpand(clientId)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar cliente por ID",
            description = "Retorna os dados de um cliente a partir do seu identificador único."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseClientDTO.class))),
            @ApiResponse(responseCode = "400", description = "ID inválido ou em branco", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    public ResponseEntity<ResponseClientDTO> getById(
            @PathVariable("id")
            @Parameter(description = "ID do cliente", required = true, example = "1")
            @NotBlank(message = "O ID do cliente é obrigatório.")
            String clientId
    ){
        ResponseClientDTO clientDTO = clientService.get(clientId);

        return  ResponseEntity.ok(clientDTO);
    }
}