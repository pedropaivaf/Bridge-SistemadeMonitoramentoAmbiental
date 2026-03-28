package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SensorAmbientalTest {

    @Test
    void mesmaLeituraQualidadeArPorProtocolosDiferentes() {
        SensorQualidadeAr sensorMqtt = new SensorQualidadeAr(new ProtocoloMQTT(), 75.0);
        SensorQualidadeAr sensorHttp = new SensorQualidadeAr(new ProtocoloHTTP(), 75.0);
        SensorQualidadeAr sensorLora = new SensorQualidadeAr(new ProtocoloLoRaWAN(), 75.0);

        String payloadMqtt = sensorMqtt.transmitirLeitura();
        String payloadHttp = sensorHttp.transmitirLeitura();
        String payloadLora = sensorLora.transmitirLeitura();

        assertTrue(payloadMqtt.contains("MQTT"));
        assertTrue(payloadHttp.contains("HTTP"));
        assertTrue(payloadLora.contains("LoRaWAN"));
        assertTrue(payloadMqtt.contains("75"));
        assertTrue(payloadHttp.contains("75"));
        assertTrue(payloadLora.contains("75"));
    }

    @Test
    void mesmaLeituraNivelAguaPorProtocolosDiferentes() {
        SensorNivelAgua sensorMqtt = new SensorNivelAgua(new ProtocoloMQTT(), 3.5);
        SensorNivelAgua sensorHttp = new SensorNivelAgua(new ProtocoloHTTP(), 3.5);

        String payloadMqtt = sensorMqtt.transmitirLeitura();
        String payloadHttp = sensorHttp.transmitirLeitura();

        assertTrue(payloadMqtt.contains("MQTT"));
        assertTrue(payloadHttp.contains("HTTP"));
        assertTrue(payloadMqtt.contains("3,50") || payloadMqtt.contains("3.50"));
        assertTrue(payloadHttp.contains("3,50") || payloadHttp.contains("3.50"));
    }

    @Test
    void trocaDeProtocoloEmRuntime() {
        SensorTemperaturaSolo sensor = new SensorTemperaturaSolo(new ProtocoloMQTT(), 22.5);
        assertTrue(sensor.transmitirLeitura().contains("MQTT"));

        sensor.setProtocolo(new ProtocoloHTTP());
        assertTrue(sensor.transmitirLeitura().contains("HTTP"));

        sensor.setProtocolo(new ProtocoloLoRaWAN());
        assertTrue(sensor.transmitirLeitura().contains("LoRaWAN"));
    }

    @Test
    void validacaoPayloadMQTT() {
        SensorQualidadeAr sensor = new SensorQualidadeAr(new ProtocoloMQTT(), 120.0);
        String payload = sensor.transmitirLeitura();
        assertTrue(payload.contains("\"protocolo\":\"MQTT\""));
        assertTrue(payload.contains("sensores/QualidadeAr"));
        assertTrue(payload.contains("AQI"));
    }

    @Test
    void validacaoPayloadHTTP() {
        SensorNivelAgua sensor = new SensorNivelAgua(new ProtocoloHTTP(), 5.0);
        String payload = sensor.transmitirLeitura();
        assertTrue(payload.contains("\"protocolo\":\"HTTP\""));
        assertTrue(payload.contains("POST"));
        assertTrue(payload.contains("/api/sensores/NivelAgua"));
    }

    @Test
    void validacaoPayloadLoRaWAN() {
        SensorTemperaturaSolo sensor = new SensorTemperaturaSolo(new ProtocoloLoRaWAN(), 18.0);
        String payload = sensor.transmitirLeitura();
        assertTrue(payload.contains("\"protocolo\":\"LoRaWAN\""));
        assertTrue(payload.contains("sensor-TemperaturaSolo"));
        assertTrue(payload.contains("fPort"));
    }

    @Test
    void qualidadeArValorInvalido() {
        assertThrows(MonitoramentoException.class, () -> new SensorQualidadeAr(new ProtocoloMQTT(), -1));
        assertThrows(MonitoramentoException.class, () -> new SensorQualidadeAr(new ProtocoloMQTT(), 501));
    }

    @Test
    void valoresInvalidosNivelAguaETemperatura() {
        assertThrows(MonitoramentoException.class, () -> new SensorNivelAgua(new ProtocoloMQTT(), -1));
        assertThrows(MonitoramentoException.class, () -> new SensorTemperaturaSolo(new ProtocoloMQTT(), -51));
        assertThrows(MonitoramentoException.class, () -> new SensorTemperaturaSolo(new ProtocoloMQTT(), 81));
    }

    @Test
    void protocoloNuloLancaExcecao() {
        assertThrows(MonitoramentoException.class, () -> new SensorQualidadeAr(null, 50.0));
        SensorNivelAgua sensor = new SensorNivelAgua(new ProtocoloMQTT(), 2.0);
        assertThrows(MonitoramentoException.class, () -> sensor.setProtocolo(null));
    }

    @Test
    void leituraRetornaDadosCorretos() {
        SensorQualidadeAr sensorAr = new SensorQualidadeAr(new ProtocoloMQTT(), 85.0);
        Leitura leituraAr = sensorAr.realizarLeitura();
        assertEquals("QualidadeAr", leituraAr.getTipo());
        assertEquals(85.0, leituraAr.getValor());
        assertEquals("AQI", leituraAr.getUnidade());

        SensorNivelAgua sensorAgua = new SensorNivelAgua(new ProtocoloHTTP(), 4.2);
        Leitura leituraAgua = sensorAgua.realizarLeitura();
        assertEquals("NivelAgua", leituraAgua.getTipo());
        assertEquals(4.2, leituraAgua.getValor());

        SensorTemperaturaSolo sensorTemp = new SensorTemperaturaSolo(new ProtocoloLoRaWAN(), 15.0);
        Leitura leituraTemp = sensorTemp.realizarLeitura();
        assertEquals("TemperaturaSolo", leituraTemp.getTipo());
        assertEquals(15.0, leituraTemp.getValor());
        assertEquals("C", leituraTemp.getUnidade());
    }
}