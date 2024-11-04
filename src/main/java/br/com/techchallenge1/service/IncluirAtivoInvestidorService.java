package src.main.java.br.com.techchallenge1.service;

import src.main.java.br.com.techchallenge1.model.AtivoInvestidor;
import src.main.java.br.com.techchallenge1.model.Investidor;

public class IncluirAtivoInvestidorService {
    private Investidor investidor;

    public IncluirAtivoInvestidorService(Investidor investidor) {
        this.investidor = investidor;
    }

    public void incluirAtivoInvestidor(String keyClasseAtivo, String KeyCategoriaAtivo, AtivoInvestidor ativoInvestidor) {
        if(investidor.getHashMapClasseAtivoInvestidor()
            .get(KeyCategoriaAtivo).getHashMapCategoriaAtivoInvestidor()
            .get(KeyCategoriaAtivo).getHashMapAtivos()
            .get(ativoInvestidor.getAtivo().getNomeAtivo()) != null) {
            return;
        }
        investidor.getHashMapClasseAtivoInvestidor()
            .get(KeyCategoriaAtivo)
            .getHashMapCategoriaAtivoInvestidor()
            .get(KeyCategoriaAtivo).adicionarAtivo(ativoInvestidor);
    }
}
