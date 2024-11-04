package src.main.java.br.com.techchallenge1.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Ativo {
    private static AtomicInteger uniqueIdGenerator = new AtomicInteger();
    private int id;
    private String nomeAtivo;
    private String descricaoAtivo;
    private String codigoAtivo;
    private float valorTotalAportes;
    private ArrayList<AporteAtivo> listaAporteAtivo = new ArrayList<>();
    private LocalDateTime dataDeCadastro;

    public Ativo(String nomeAtivo, String descricaoAtivo, String codigoAtivo) {
        this.id = uniqueIdGenerator.incrementAndGet();
        this.nomeAtivo = nomeAtivo;
        this.descricaoAtivo = descricaoAtivo;
        this.codigoAtivo = codigoAtivo;
    };

    public void adicionarAporteAtivo(AporteAtivo aporteAtivo) {
        this.listaAporteAtivo.add(aporteAtivo);
        valorTotalAportes += aporteAtivo.getQuantidadeAporte();
    }

    public int getId() {
        return id;
    }

    public String getNomeAtivo() {
        return nomeAtivo;
    }

    public String getDescricaoAtivo() {
        return descricaoAtivo;
    }

    public float getValorTotalAportes() {
        return valorTotalAportes;
    }

    public ArrayList<AporteAtivo> getAporteAtivo() {
        return listaAporteAtivo;
    }

    public LocalDateTime getDataDeCadastro() {
        return dataDeCadastro;
    }
    
}
