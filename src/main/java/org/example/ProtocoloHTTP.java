package org.example;

public class ProtocoloHTTP implements ProtocoloComunicacao {

    @Override
    public String enviar(Leitura leitura) {
        if (leitura == null) {
            throw new MonitoramentoException("Leitura nao pode ser nula para envio HTTP");
        }
        return String.format("{\"protocolo\":\"HTTP\",\"method\":\"POST\",\"endpoint\":\"/api/sensores/%s\",\"body\":{\"valor\":%.2f,\"unidade\":\"%s\"}}", leitura.getTipo(), leitura.getValor(), leitura.getUnidade());
    }

    @Override
    public String getNomeProtocolo() {
        return "HTTP";
    }
}