package org.example;

public abstract class SensorAmbiental {
    protected ProtocoloComunicacao protocolo;

    public SensorAmbiental(ProtocoloComunicacao protocolo) {
        if (protocolo == null) {
            throw new MonitoramentoException("Protocolo de comunicacao nao pode ser nulo");
        }
        this.protocolo = protocolo;
    }

    public void setProtocolo(ProtocoloComunicacao protocolo) {
        if (protocolo == null) {
            throw new MonitoramentoException("Protocolo de comunicacao nao pode ser nulo");
        }
        this.protocolo = protocolo;
    }

    public ProtocoloComunicacao getProtocolo() {
        return protocolo;
    }

    public abstract Leitura realizarLeitura();

    public String transmitirLeitura() {
        Leitura leitura = realizarLeitura();
        return protocolo.enviar(leitura);
    }
}