# Sistema de Análise de Crédito - API RESTful
- Este projeto é uma API RESTful desenvolvida em Java 21 com Spring Boot, que realiza a análise e aplicação de diferentes modalidades de crédito para clientes pessoa física, com base em critérios de idade e renda. Também verifica a elegibilidade de crédito automotivo para diferentes modelos de veículos.
## Requisitos Atendidos

- Cadastro de clientes (nome, idade, renda)
- Retorno do cliente por ID
- Determinação de modalidade de crédito
- Verificação de elegibilidade para crédito automotivo (Hatch/SUV)
- Filtro adicional de clientes com crédito fixo e elegíveis para Hatch
- Banco de dados em memória H2
- Validações com Bean Validation
- Documentação via Swagger (OpenAPI)
- Testes unitários implementados
- Códigos HTTP adequados (201, 400, 404, 500 etc.)

## Tecnologias Utilizadas

- Java 21
- Spring Boot 3.2.4
- Spring Web
- Spring Data JPA
- H2 Database
- Swagger/OpenAPI
- JUnit 5
- Mockito
- Maven

## Como Executar o Projeto
1. Clone o repositório:
``` bash
git clone https://github.com/Davi-pontes/challenge-java-developer.git
cd challenge-java-developer
```
2. Compile o projeto:
``` bash
./mvnw clean install
```
3. Execute a aplicação:
``` bash
./mvnw spring-boot:run
```
4. Acesse a API via Swagger:
```
http://localhost:5000/swagger-ui/index.html
```
## Teste
Para rodar os testes automatizados:
``` bash
./mvnw test
```
## Banco de dados
- Banco: H2(em memória)
- Console Web: Disponível em http://localhost:8080/h2-console
- Credenciais padrão:
  - JDBC URL: jdbc:h2:mem:challenge-neurotech
  - Usuário: admin
  - Senha: admin
> O banco H2 é embarcado e já vem configurado no projeto. Não é necessário instalar nada separadamente.
  O link oficial está disponível para fins de documentação ou uso avançado: https://www.h2database.com/html/main.html

## Regras de crédito
| Modalidade      | Critérios                                                | Taxa     |
| --------------- | -------------------------------------------------------- | -------- |
| Juros Fixos     | Idade entre 18 e 25 anos                                 | 5% a.a   |
| Juros Variáveis | Idade entre 21 e 65 anos, renda entre R\$5000 e R\$15000 | Dinâmico |
| Consignado      | Idade acima de 65 anos                                   | N/A      |

### Crédito automotivo
- Hatch: Renda entre R$5000 e R$15000
- SUV: Renda acima de R$8000 e idade superior a 20 anos

### Filtro adicional (extra):
- Endpoint para listar todos os clientes entre 23 e 49 anos com crédito fixo e aptos para Hatch.

Obrigado pela oportunidade!