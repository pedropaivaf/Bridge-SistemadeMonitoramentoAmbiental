# Bridge - Sistema de Monitoramento Ambiental

![Testes](https://img.shields.io/badge/Testes-10%2F10%20passando-brightgreen)
![Java](https://img.shields.io/badge/Java-21-orange)
![JUnit](https://img.shields.io/badge/JUnit-5-blue)

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

## Resultado dos Testes

```
[INFO] Tests run: 10, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

| # | Teste | Resultado |
|---|-------|-----------|
| 1 | mesmaLeituraQualidadeArPorProtocolosDiferentes | :white_check_mark: Passou |
| 2 | mesmaLeituraNivelAguaPorProtocolosDiferentes | :white_check_mark: Passou |
| 3 | trocaDeProtocoloEmRuntime | :white_check_mark: Passou |
| 4 | validacaoPayloadMQTT | :white_check_mark: Passou |
| 5 | validacaoPayloadHTTP | :white_check_mark: Passou |
| 6 | validacaoPayloadLoRaWAN | :white_check_mark: Passou |
| 7 | qualidadeArValorInvalido | :white_check_mark: Passou |
| 8 | valoresInvalidosNivelAguaETemperatura | :white_check_mark: Passou |
| 9 | protocoloNuloLancaExcecao | :white_check_mark: Passou |
| 10 | leituraRetornaDadosCorretos | :white_check_mark: Passou |

## Tecnologias

- Java 21
- Maven
- JUnit 5