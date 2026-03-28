package org.example;

public class ProtocoloMQTT implements ProtocoloComunicacao {

    @Override
    public String enviar(Leitura leitura) {
        if (leitura == null) {
            throw new MonitoramentoException("Leitura nao pode ser nula para envio MQTT");
        }
        return String.format("{\"protocolo\":\"MQTT\",\"topic\":\"sensores/%s\",\"payload\":{\"valor\":%.2f,\"unidade\":\"%s\"}}", leitura.getTipo(), leitura.getValor(), leitura.getUnidade());
    }

    @Override
    public String getNomeProtocolo() {
        return "MQTT";
    }
}