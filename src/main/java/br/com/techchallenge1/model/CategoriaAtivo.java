package src.main.java.br.com.techchallenge1.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CategoriaAtivo {
    private static AtomicInteger uniqueIdGenerator = new AtomicInteger();
    private int id;
    private String nomeCategoriaAtivo;
    private String descricaoCategoriaAtivo;
    private HashMap<String, Ativo> hashMapAtivos = new HashMap<>();
    private LocalDateTime dataDeCadastro;

    public CategoriaAtivo(String nomeCategoriaAtivo, String descricaoCategoriaAtivo) {
        this.id = uniqueIdGenerator.incrementAndGet();
        this.nomeCategoriaAtivo = nomeCategoriaAtivo;
        this.descricaoCategoriaAtivo = descricaoCategoriaAtivo;
        this.dataDeCadastro = LocalDateTime.now();
    };

    public void adicionarAtivo(Ativo ativo) {
        this.hashMapAtivos.put(ativo.getNomeAtivo(), ativo);
    }

    public int getId() {
        return id;
    }

    public String getNomeCategoriaAtivo() {
        return nomeCategoriaAtivo;
    }

    public String getDescricaoCategoriaAtivo() {
        return descricaoCategoriaAtivo;
    }

    public HashMap<String, Ativo> getHashMapAtivos() {
        return hashMapAtivos;
    }

    public LocalDateTime getDataDeCadastro() {
        return dataDeCadastro;
    }
    
}
