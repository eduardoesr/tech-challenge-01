package src.main.java.br.com.techchallenge1.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Ativo {
    private static AtomicInteger uniqueIdGenerator = new AtomicInteger();
    private int id;
    private String nomeAtivo;
    private String sigla;
    private double indexadoPre;
    private String indexadoPos;
    private String codigoB3;
    private String codigoBolsa;
    private LocalDateTime dtCadastro;

    public Ativo(String nomeAtivo) {
        this.id = uniqueIdGenerator.incrementAndGet();
        this.nomeAtivo = nomeAtivo;
    };

    public int getId() {
        return id;
    }

    public String getNomeAtivo() {
        return nomeAtivo;
    }

    public String getSigla() {
        return sigla;
    }

    public LocalDateTime getDtCadastro() {
        return dtCadastro;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public void setIndexadoPre(double indexadoPre) {
        this.indexadoPre = indexadoPre;
    }

    public void setIndexadoPos(String indexadoPos) {
        this.indexadoPos = indexadoPos;
    }

    public void setCodigoB3(String codigoB3) {
        this.codigoB3 = codigoB3;
    }

    public void setCodigoBolsa(String codigoBolsa) {
        this.codigoBolsa = codigoBolsa;
    }
    
}
