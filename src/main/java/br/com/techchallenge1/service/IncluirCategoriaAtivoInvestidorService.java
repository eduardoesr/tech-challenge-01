package src.main.java.br.com.techchallenge1.service;

import src.main.java.br.com.techchallenge1.model.CategoriaAtivoInvestidor;
import src.main.java.br.com.techchallenge1.model.ClasseAtivoInvestidor;
import src.main.java.br.com.techchallenge1.model.Investidor;

public class IncluirCategoriaAtivoInvestidorService {

    private Investidor investidor;

    public IncluirCategoriaAtivoInvestidorService(Investidor investidor) {
        this.investidor = investidor;
    }

    public void incluirCategoriaAtivoInvestidor(String keyClasseAtivo, CategoriaAtivoInvestidor categoriaAtivoInvestidor) {
        ClasseAtivoInvestidor classeAtivoInvestidor = investidor.getHashMapClasseAtivoInvestidor().get(keyClasseAtivo);
        classeAtivoInvestidor.adicionarCategoriaAtivoInvestidor(categoriaAtivoInvestidor);
    }
}
