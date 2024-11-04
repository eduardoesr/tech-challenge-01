package src.main.java.br.com.techchallenge1.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Investidor {
    private static AtomicInteger uniqueIdGenerator = new AtomicInteger();
    private int id;
    private String usuario;
    private String nome;
    private String email;
    private String senha;
    private LocalDateTime dataDeCadastro;
    private float aportePeriodico;
    private HashMap<String, ClasseAtivoInvestidor> hashMapClasseAtivoInvestidor = new HashMap<>();

    public Investidor(String nome, String email, String usuario, String senha) {
        this.id = uniqueIdGenerator.incrementAndGet();
        this.usuario = nome;
        this.usuario = email;
        this.usuario = usuario;
        this.senha = senha;
        this.dataDeCadastro = LocalDateTime.now();
    }

    public void adicionarClasseAtivoInvestidor(ClasseAtivoInvestidor classeAtivoInvestidor) {
        this.hashMapClasseAtivoInvestidor.put(classeAtivoInvestidor.getClasseAtivo().getNomeClasseAtivo(), classeAtivoInvestidor);
    }

    public int getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getDataDeCadastro() {
        return dataDeCadastro;
    }

    public float getAportePeriodico() {
        return aportePeriodico;
    }

    public HashMap<String, ClasseAtivoInvestidor> getHashMapClasseAtivoInvestidor() {
        return hashMapClasseAtivoInvestidor;
    }

    // @Override
    // public String toString() {
    //     return "Usuário: " + usuario;
    // }
}