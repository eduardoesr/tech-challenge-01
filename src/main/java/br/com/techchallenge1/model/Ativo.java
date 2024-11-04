package src.main.java.br.com.techchallenge1.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Ativo {
    private static AtomicInteger uniqueIdGenerator = new AtomicInteger();
    private int id;
    private String nomeAtivo;
    private String coditoAtivo;
    private double indexadoPre;
    private String indexadoPos;
    private LocalDateTime dtCadastro;

    public Ativo(String nomeAtivo, String coditoAtivo) {
        this.id = uniqueIdGenerator.incrementAndGet();
        this.nomeAtivo = nomeAtivo;
        this.coditoAtivo = coditoAtivo;
    };

    public int getId() {
        return id;
    }

    public String getNomeAtivo() {
        return nomeAtivo;
    }

    public String getCoditoAtivo() {
        return coditoAtivo;
    }

    public LocalDateTime getDtCadastro() {
        return dtCadastro;
    }

    public void setCoditoAtivo(String coditoAtivo) {
        this.coditoAtivo = coditoAtivo;
    }

    public void setIndexadoPre(double indexadoPre) {
        this.indexadoPre = indexadoPre;
    }

    public void setIndexadoPos(String indexadoPos) {
        this.indexadoPos = indexadoPos;
    }    
}
