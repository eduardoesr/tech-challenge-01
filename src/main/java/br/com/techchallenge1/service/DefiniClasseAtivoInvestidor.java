package src.main.java.br.com.techchallenge1.service;

import src.main.java.br.com.techchallenge1.model.ClasseAtivo;
import src.main.java.br.com.techchallenge1.model.ClasseAtivoInvestidor;
import src.main.java.br.com.techchallenge1.model.Investidor;

public class DefiniClasseAtivoInvestidor {

    private Investidor investidor;  

    public DefiniClasseAtivoInvestidor(Investidor investidor) {
        this.investidor = investidor;
    }

    public void definirClasseAtivo(double percentualAlocacao, ClasseAtivo classeAtivo) {
        investidor.adicionarClasseAtivoInvestidor(new ClasseAtivoInvestidor(percentualAlocacao, classeAtivo));
    }
}
