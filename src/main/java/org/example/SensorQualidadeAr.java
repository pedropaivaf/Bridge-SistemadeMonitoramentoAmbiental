package org.example;

public class SensorQualidadeAr extends SensorAmbiental {
    private double indiceQualidade;

    public SensorQualidadeAr(ProtocoloComunicacao protocolo, double indiceQualidade) {
        super(protocolo);
        if (indiceQualidade < 0 || indiceQualidade > 500) {
            throw new MonitoramentoException("Indice de qualidade do ar deve estar entre 0 e 500");
        }
        this.indiceQualidade = indiceQualidade;
    }

    @Override
    public Leitura realizarLeitura() {
        return new Leitura("QualidadeAr", indiceQualidade, "AQI");
    }

    public double getIndiceQualidade() { return indiceQualidade; }

    public void setIndiceQualidade(double indiceQualidade) {
        if (indiceQualidade < 0 || indiceQualidade > 500) {
            throw new MonitoramentoException("Indice de qualidade do ar deve estar entre 0 e 500");
        }
        this.indiceQualidade = indiceQualidade;
    }
}