package src.main.java.br.com.techchallenge1.model;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ParceiroExterno {
    private static AtomicInteger uniqueIdGenerator = new AtomicInteger();
    private int id;
    private String nomeParceiroExterno;
    private String urlRequisicao;
    private String tipoAtivo; // Cotação e indexadores
    private HashMap<String, Double> hashMapAtivosValor = new HashMap<>();

    public ParceiroExterno(String nomeParceiroExterno, String urlRequisicao) {
        this.id = uniqueIdGenerator.incrementAndGet();
        this.nomeParceiroExterno = nomeParceiroExterno;
        this.urlRequisicao = urlRequisicao;
    };

    public void adicionarAtivo(String codigoAtivo, Double valorAtivo) {
        this.hashMapAtivosValor.put(codigoAtivo, valorAtivo);
    };

    public double obterValorAtivo(String nomeAtivo) {
        return hashMapAtivosValor.get(nomeAtivo);
    }

    public int getId() {
        return id;
    };

    public String getNomeParceiroExterno() {
        return nomeParceiroExterno;
    };

    public String getUrlRequisicao() {
        return urlRequisicao;
    };
};