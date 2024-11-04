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

    // Simula retorno API parceiro
    public double obterCotacao(AtivoInvestidor ativoInvestidor) {
        if (ativoInvestidor.getAtivo().getCalcularPorIndicador()) {
            double valorAtual = ativoInvestidor.obterTotalDeAportes()*(1 + 0.07+Math.random()/100);
            return Math.round(valorAtual * 100.0)/100.0;
        } else {
            double variacao = 1 + Math.random()/10;
            double valorAtual = hashMapAtivosValor.get(ativoInvestidor.getAtivo().getCodigoAtivo())*variacao;
            return Math.round(valorAtual * 100.0)/100.0;
        }
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