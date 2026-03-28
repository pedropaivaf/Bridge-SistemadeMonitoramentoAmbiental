package org.example;

public class ProtocoloLoRaWAN implements ProtocoloComunicacao {

    @Override
    public String enviar(Leitura leitura) {
        if (leitura == null) {
            throw new MonitoramentoException("Leitura nao pode ser nula para envio LoRaWAN");
        }
        return String.format("{\"protocolo\":\"LoRaWAN\",\"devEUI\":\"sensor-%s\",\"fPort\":1,\"payload\":{\"valor\":%.2f,\"unidade\":\"%s\"}}", leitura.getTipo(), leitura.getValor(), leitura.getUnidade());
    }

    @Override
    public String getNomeProtocolo() {
        return "LoRaWAN";
    }
}