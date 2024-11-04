package src.main.java.br.com.techchallenge1.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import src.main.java.br.com.techchallenge1.model.Investidor;

public class IncluirInvestidorService {

    private List<Investidor> database; 

    public IncluirInvestidorService(List<Investidor> database) {
        this.database = database;
    }

    private Investidor buscarInvestidorPorEmail(String email) {
        Investidor investidor = null;

        investidor = database.stream().filter(invest -> invest.getEmail().equals(email)).collect(Collectors.toList()).get(0);

        return investidor;
    }

    public Investidor incluirInvestidor(String nome, String email) {
        Investidor investidor = null;
        if(buscarInvestidorPorEmail(email) != null) {
            return investidor;
        }

        investidor = new Investidor(nome, email, nome.toLowerCase().trim()+"#"+database.size(), "");
        
        database.add(investidor);
        
        return investidor;
    }
}
