# Bridge - Sistema de Monitoramento Ambiental

## Descricao

Implementacao do padrao de projeto **Bridge** aplicado a um sistema de monitoramento ambiental com sensores IoT. O Bridge separa a abstracao (tipo de medicao) da implementacao (protocolo de comunicacao), permitindo que qualquer sensor transmita por qualquer protocolo sem explosao de subclasses.

## Padrao Bridge

- **Implementor:** `ProtocoloComunicacao` - interface que define o contrato de envio de dados
- **ConcreteImplementor:** `ProtocoloMQTT`, `ProtocoloHTTP`, `ProtocoloLoRaWAN` - protocolos de comunicacao
- **Abstraction:** `SensorAmbiental` - classe abstrata que contem referencia ao protocolo
- **RefinedAbstraction:** `SensorQualidadeAr`, `SensorNivelAgua`, `SensorTemperaturaSolo` - sensores concretos

## Operacoes

| Sensor | Unidade | Faixa Valida |
|--------|---------|--------------|
| QualidadeAr | AQI | 0 - 500 |
| NivelAgua | metros | >= 0 |
| TemperaturaSolo | Celsius | -50 a 80 |

## Protocolos

| Protocolo | Formato Payload |
|-----------|----------------|
| MQTT | topic + payload JSON |
| HTTP | POST + endpoint + body JSON |
| LoRaWAN | devEUI + fPort + payload JSON |

## Tecnologias

- Java 21
- Maven
- JUnit 5

## Testes

10 testes unitarios cobrindo:
- Mesma leitura por protocolos diferentes
- Troca de protocolo em runtime
- Validacao de payload por protocolo
- Validacao de valores invalidos
- Protocolo nulo lanca excecao
- Leitura retorna dados corretos