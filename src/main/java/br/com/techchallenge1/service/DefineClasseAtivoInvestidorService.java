package src.main.java.br.com.techchallenge1.service;

import src.main.java.br.com.techchallenge1.model.ClasseAtivo;
import src.main.java.br.com.techchallenge1.model.ClasseAtivoInvestidor;
import src.main.java.br.com.techchallenge1.model.Investidor;

public class DefineClasseAtivoInvestidorService {

    private Investidor investidor;  

    public DefineClasseAtivoInvestidorService(Investidor investidor) {
        this.investidor = investidor;
    }

    public void definirClasseAtivo(double percentualAlocacao, ClasseAtivo classeAtivo) {
        if(investidor.getHashMapClasseAtivoInvestidor().get(classeAtivo.getNomeClasseAtivo()) != null) {
            investidor.atualizarClasseAtivoInvestidor(classeAtivo.getNomeClasseAtivo(), percentualAlocacao);
        } else {
            investidor.adicionarClasseAtivoInvestidor(new ClasseAtivoInvestidor(percentualAlocacao, classeAtivo));
        }
    }
}
