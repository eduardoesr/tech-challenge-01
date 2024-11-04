package src.main.java.br.com.techchallenge1.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class AporteAtivoInvestidor {
    private static AtomicInteger uniqueIdGenerator = new AtomicInteger();
    private int id;
    private double quantidadeAtivo;
    private double cotacaoAtivo;
    private LocalDateTime dataDeInsercao;
    private LocalDateTime dataDeCompra;

    public AporteAtivoInvestidor(double quantidadeAtivo, double cotacaoAtivo, LocalDateTime dataDeCompra) {
        this.id = uniqueIdGenerator.incrementAndGet();
        this.quantidadeAtivo = quantidadeAtivo;
        this.cotacaoAtivo = cotacaoAtivo;
        this.dataDeCompra = dataDeCompra;
        this.dataDeInsercao = LocalDateTime.now();
    };

    public double obterValorTotalAporte() {
        return quantidadeAtivo*cotacaoAtivo;
    };

    public int getId() {
        return id;
    };

    public double getQuantidadeAtivo() {
        return quantidadeAtivo;
    };

    public double getCotacaoAtivo() {
        return cotacaoAtivo;
    };

    public LocalDateTime getDataDeInsercao() {
        return dataDeInsercao;
    };

    public LocalDateTime getDataDeCompra() {
        return dataDeCompra;
    };
};