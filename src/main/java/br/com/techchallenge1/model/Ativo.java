package src.main.java.br.com.techchallenge1.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Ativo {
    private static AtomicInteger uniqueIdGenerator = new AtomicInteger();
    private int id;
    private String nomeAtivo;
    private String CodigoAtivo;
    private double indexadoPre;
    private String indexadoPos;
    private LocalDateTime dtCadastro;

    public Ativo(String nomeAtivo, String CodigoAtivo) {
        this.id = uniqueIdGenerator.incrementAndGet();
        this.nomeAtivo = nomeAtivo;
        this.CodigoAtivo = CodigoAtivo;
    };

    public int getId() {
        return id;
    }

    public String getNomeAtivo() {
        return nomeAtivo;
    }

    public String getCodigoAtivo() {
        return CodigoAtivo;
    }

    public LocalDateTime getDtCadastro() {
        return dtCadastro;
    }

    public void setCodigoAtivo(String CodigoAtivo) {
        this.CodigoAtivo = CodigoAtivo;
    }

    public void setIndexadoPre(double indexadoPre) {
        this.indexadoPre = indexadoPre;
    }

    public void setIndexadoPos(String indexadoPos) {
        this.indexadoPos = indexadoPos;
    }    
}
