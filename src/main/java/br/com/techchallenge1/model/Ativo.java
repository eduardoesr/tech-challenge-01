package src.main.java.br.com.techchallenge1.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Ativo {
    private static AtomicInteger uniqueIdGenerator = new AtomicInteger();
    private int id;
    private String nomeAtivo;
    private String codigoAtivo;
    private Boolean calcularPorIndicador;
    private LocalDateTime dtCadastro;

    public Ativo(String nomeAtivo, String codigoAtivo, Boolean calcularPorIndicador) {
        this.id = uniqueIdGenerator.incrementAndGet();
        this.nomeAtivo = nomeAtivo;
        this.codigoAtivo = codigoAtivo;
        this.calcularPorIndicador = calcularPorIndicador;
    };

    public int getId() {
        return id;
    }

    public String getNomeAtivo() {
        return nomeAtivo;
    }

    public String getCodigoAtivo() {
        return codigoAtivo;
    }

    public Boolean getCalcularPorIndicador() {
        return calcularPorIndicador;
    }

    public LocalDateTime getDtCadastro() {
        return dtCadastro;
    }

    public void setcodigoAtivo(String codigoAtivo) {
        this.codigoAtivo = codigoAtivo;
    } 
}
