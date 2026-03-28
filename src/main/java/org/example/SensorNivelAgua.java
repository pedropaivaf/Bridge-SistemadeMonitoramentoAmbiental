package org.example;

public class SensorNivelAgua extends SensorAmbiental {
    private double nivelMetros;

    public SensorNivelAgua(ProtocoloComunicacao protocolo, double nivelMetros) {
        super(protocolo);
        if (nivelMetros < 0) {
            throw new MonitoramentoException("Nivel de agua nao pode ser negativo");
        }
        this.nivelMetros = nivelMetros;
    }

    @Override
    public Leitura realizarLeitura() {
        return new Leitura("NivelAgua", nivelMetros, "m");
    }

    public double getNivelMetros() { return nivelMetros; }

    public void setNivelMetros(double nivelMetros) {
        if (nivelMetros < 0) {
            throw new MonitoramentoException("Nivel de agua nao pode ser negativo");
        }
        this.nivelMetros = nivelMetros;
    }
}