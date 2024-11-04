package src.main.java.br.com.techchallenge1.model;

import java.util.concurrent.atomic.AtomicInteger;

public class ClasseAtivo {
    private static AtomicInteger uniqueIdGenerator = new AtomicInteger();
    private Integer id;
    private String nomeClasseAtivo;
    private String descricaoClasseAtivo;
    private ParceiroExterno parceiroExterno;

    public ClasseAtivo(String nomeClasseAtivo, String descricaoClasseAtivo, ParceiroExterno parceiroExterno) {
        this.id = uniqueIdGenerator.incrementAndGet();
        this.nomeClasseAtivo = nomeClasseAtivo;
        this.descricaoClasseAtivo = descricaoClasseAtivo;
        this.parceiroExterno = parceiroExterno;
    };

    public Integer getId() {
        return id;
    };

    public String getNomeClasseAtivo() {
        return nomeClasseAtivo;
    }

    public String getDescricaoClasseAtivo() {
        return descricaoClasseAtivo;
    }

    public ParceiroExterno getParceiroExterno() {
        return parceiroExterno;
    }
}