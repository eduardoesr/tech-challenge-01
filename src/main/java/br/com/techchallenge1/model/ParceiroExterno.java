package src.main.java.br.com.techchallenge1.model;

import java.util.concurrent.atomic.AtomicInteger;

public class ParceiroExterno {
    private static AtomicInteger uniqueIdGenerator = new AtomicInteger();
    private int id;
    private String nomeParceiroExterno;
    private String urlRequisicao;

    public ParceiroExterno(String nomeParceiroExterno, String urlRequisicao) {
        this.id = uniqueIdGenerator.incrementAndGet();
        this.nomeParceiroExterno = nomeParceiroExterno;
        this.urlRequisicao = urlRequisicao;
    };

    public int getId() {
        return id;
    }

    public String getNomeParceiroExterno() {
        return nomeParceiroExterno;
    }

    public String getUrlRequisicao() {
        return urlRequisicao;
    }
};