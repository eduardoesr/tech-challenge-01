package src.main.java.br.com.techchallenge1.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CategoriaAtivoInvestidor {
    private static AtomicInteger uniqueIdGenerator = new AtomicInteger();
    private int id;
    private String nomeCategoriaAtivoInvestidor;
    private String descricaoCategoriaAtivoInvestidor;
    private HashMap<String, Ativo> hashMapAtivos = new HashMap<>();
    private LocalDateTime dataDeCadastro;

    public CategoriaAtivoInvestidor(String nomeCategoriaAtivoInvestidor, String descricaoCategoriaAtivoInvestidor) {
        this.id = uniqueIdGenerator.incrementAndGet();
        this.nomeCategoriaAtivoInvestidor = nomeCategoriaAtivoInvestidor;
        this.descricaoCategoriaAtivoInvestidor = descricaoCategoriaAtivoInvestidor;
        this.dataDeCadastro = LocalDateTime.now();
    };

    public void adicionarAtivo(Ativo ativo) {
        this.hashMapAtivos.put(ativo.getNomeAtivo(), ativo);
    }

    public int getId() {
        return id;
    }

    public String getNomeCategoriaAtivoInvestidor() {
        return nomeCategoriaAtivoInvestidor;
    }

    public String getDescricaoCategoriaAtivo() {
        return descricaoCategoriaAtivoInvestidor;
    }

    public HashMap<String, Ativo> getHashMapAtivos() {
        return hashMapAtivos;
    }

    public LocalDateTime getDataDeCadastro() {
        return dataDeCadastro;
    }
    
}
