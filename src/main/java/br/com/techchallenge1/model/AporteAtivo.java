package src.main.java.br.com.techchallenge1.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class AporteAtivo {
    private static AtomicInteger uniqueIdGenerator = new AtomicInteger();
    private int id;
    private double quantidadeAporte;
    private LocalDateTime dataDeInsercao;

    public AporteAtivo(double quantidadeAporte) {
        this.id = uniqueIdGenerator.incrementAndGet();
        this.quantidadeAporte = quantidadeAporte;
        this.dataDeInsercao = LocalDateTime.now();
    };

    public int getId() {
        return id;
    }

    public double getQuantidadeAporte() {
        return quantidadeAporte;
    }

    public LocalDateTime getDataDeInsercao() {
        return dataDeInsercao;
    }
};