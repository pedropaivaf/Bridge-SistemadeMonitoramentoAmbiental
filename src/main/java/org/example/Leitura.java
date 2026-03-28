package org.example;

import java.time.LocalDateTime;

public class Leitura {
    private final String tipo;
    private final double valor;
    private final String unidade;
    private final LocalDateTime timestamp;

    public Leitura(String tipo, double valor, String unidade) {
        this.tipo = tipo;
        this.valor = valor;
        this.unidade = unidade;
        this.timestamp = LocalDateTime.now();
    }

    public String getTipo() { return tipo; }
    public double getValor() { return valor; }
    public String getUnidade() { return unidade; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return String.format("Leitura{tipo='%s', valor=%.2f %s, timestamp=%s}", tipo, valor, unidade, timestamp);
    }
}