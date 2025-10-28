# MottuBracelet 🏍️

O Mottu Bracelet é um projeto desenvolvido para a empresa Mottu, que visa o gerenciamento eficiente de suas motos nos pátios de manutenção. Trata-se de um bracelete que é acoplado às motos que chegam ao pátio.
Esse bracelete é configurado através de um aplicativo integrando todos os dados da moto com os dados do pátio e do próprio dispositivo. Para encontrar a moto no pátio, basta acionar o bracelete através do dispositivo e 
esse emitirá sinal sonoro e sinal infravermelho capaz de ser visualizado através da câmera do aplicativo.

Este repositório contém uma versão preliminar do código backend que se integra tanto com o banco de dados quanto com a aplicação front-end. É um projeto em Java utilizando arquitetura Maven e criado através do
Spring Boot.

## Integrantes
Pedro Abrantes Andrade | RM558186
Ricardo Tavares de Oliveira Filho | RM556092
Victor Alves Carmona | RM555726

## Tecnologias Utilizadas
- Java 21
- Spring Boot 3.4.5
- Maven
- Oracle Database
- JPA/Hibernate
- Jakarta Validation (validação de dados)

## Estrutura do Projeto

| Pacote         | Descrição                                                                 |
|----------------|---------------------------------------------------------------------------|
| `model`        | Entidades JPA que representam as tabelas do banco de dados                |
| `repository`   | Interfaces Spring Data JPA para acesso aos dados                          |
| `service`      | Lógica de negócio e regras de aplicação                                   |
| `controller`   | Endpoints REST para interação com o sistema                               |
| `dto`          | Objetos de Transferência de Dados (request/response)                      |
| `exception`    | Tratamento de exceções                                                    |
| `util`         | Enum para status do dispositivo                                           |

## Endpoints Principais

### Pátios (`/patios`)
| Método | Rota               | Descrição                               |
|--------|--------------------|-----------------------------------------|
| POST   | `/patios`          | Cria um novo pátio                      |
| GET    | `/patios`          | Lista todos os pátios                   |
| GET    | `/patios/{id}`     | Busca pátio por id                      |
| PUT    | `/patios/{id}`     | Atualiza um pátio                       |
| DELETE | `/patios/{id}`     | Remove um pátio                         |

### Motos (`/motos`)
| Método | Rota               | Descrição                               |
|--------|--------------------|-----------------------------------------|
| POST   | `/motos`           | Cadastra uma nova moto                  |
| GET    | `/motos`           | Lista todas as motos                    |
| GET    | `/motos/{id}`      | Busca moto por id                       |
| GET    | `/motos/imei/{imei}`| Busca moto por IMEI                    |
| GET    | `/motos/placa/{placa}`| Busca moto por placa                 |
| PUT    | `/motos/{id}`      | Atualiza uma moto                       |
| DELETE | `/motos/{id}`      | Remove uma moto                         |

### Dispositivos (`/dispositivos`)
| Método | Rota               | Descrição                               |
|--------|--------------------|-----------------------------------------|
| POST   | `/dispositivos`    | Cadastra um novo dispositivo            |
| GET    | `/dispositivos`    | Lista todos os dispositivos             |
| GET    | `/dispositivos/{id}`| Busca dispositivo por id               |
| GET    | `/dispositivos/status/{status}`| Filtra por status           |
| PUT    | `/dispositivos/{id}`| Atualiza um dispositivo                |
| DELETE | `/dispositivos/{id}`| Remove um dispositivo                  |

### Histórico (`/historicos`)
| Método | Rota               | Descrição                               |
|--------|--------------------|-----------------------------------------|
| POST   | `/historicos`      | Registra entrada/saída no pátio         |
| GET    | `/historicos`      | Lista todo o histórico                  |

## Como Executar o Projeto

### 1. Pré-requisitos
- Java 21 instalado
- Maven instalado
- Acesso ao banco Oracle (configurar no `application.properties`)

### 2. Clone do projeto do Github
Executar os seguintes comandos no terminal:

```
git clone https://github.com/seu-usuario/mottu-bracelet.git
cd mottu-bracelet
```

### 2. Configuração
Edite o arquivo `application.properties` com suas credenciais:

```properties
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### 3. Execução
Executar o seguinte comando no terminal:
```
./mvnw spring-boot:run
```
A aplicação estará disponível em: http://localhost:8080

### 4. Exemplos json para testes de endpoints

Observação: para respeitar o relacionamento entre as tabelas é necessário criar objetos na seguinte ordem:
patio -> dispositivo -> moto -> historicoPatio

#### Criar pátio
```
{
  "nome": "Pátio Centro",
  "endereco": {
    "logradouro": "Av. Paulista",
    "numero": "1000",
    "cep": "01310-100",
    "complemento": "Andar 5",
    "cidade": "São Paulo",
    "pais": "Brasil"
  },
  "capacidadeMaxima": 50,
  "administradorResponsavel": "João Silva"
}
```

#### Criar Moto
```
{
  "imei": "123456789012345",
  "chassi": "9BWZZZ377VT004251",
  "placa": "ABC1D23",
  "patioId": 1
}
```

#### Criar Dispositivo
```
{
  "status": "ATIVO",
  "motoId": 1,
  "patioId": 1
}
```

#### Registrar Histórico
```
{
  "motoId": 1,
  "patioId": 1,
  "dataEntrada": "2023-11-20T08:00:00",
  "dataSaida": "2023-11-20T18:30:00"
}
```

### 5. Visualização dos objetos criados, no navegador

![image](https://github.com/user-attachments/assets/90dd76bc-f17f-475f-aa29-28c800641649)
![image](https://github.com/user-attachments/assets/7072675f-323e-433f-b5cb-a94c0552deeb)
![image](https://github.com/user-attachments/assets/ccb1e0e5-ee9f-4a77-9dac-8b40683317d1)
![image](https://github.com/user-attachments/assets/21b21007-d477-4851-b84d-4d93aa6a9b09)



