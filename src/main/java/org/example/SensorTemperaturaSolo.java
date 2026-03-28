package org.example;

public class SensorTemperaturaSolo extends SensorAmbiental {
    private double temperaturaCelsius;

    public SensorTemperaturaSolo(ProtocoloComunicacao protocolo, double temperaturaCelsius) {
        super(protocolo);
        if (temperaturaCelsius < -50 || temperaturaCelsius > 80) {
            throw new MonitoramentoException("Temperatura do solo deve estar entre -50 e 80 graus Celsius");
        }
        this.temperaturaCelsius = temperaturaCelsius;
    }

    @Override
    public Leitura realizarLeitura() {
        return new Leitura("TemperaturaSolo", temperaturaCelsius, "C");
    }

    public double getTemperaturaCelsius() { return temperaturaCelsius; }

    public void setTemperaturaCelsius(double temperaturaCelsius) {
        if (temperaturaCelsius < -50 || temperaturaCelsius > 80) {
            throw new MonitoramentoException("Temperatura do solo deve estar entre -50 e 80 graus Celsius");
        }
        this.temperaturaCelsius = temperaturaCelsius;
    }
}