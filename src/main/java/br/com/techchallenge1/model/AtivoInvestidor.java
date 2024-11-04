package src.main.java.br.com.techchallenge1.model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class AtivoInvestidor {
    private static AtomicInteger uniqueIdGenerator = new AtomicInteger();
    private int id;
    private Ativo ativo;
    private ArrayList<AporteAtivoInvestidor> listaAportesAtivo = new ArrayList<>();

    public AtivoInvestidor(Ativo ativo, AporteAtivoInvestidor aporte) {
        this.id = uniqueIdGenerator.incrementAndGet();
        this.ativo = ativo;
        this.listaAportesAtivo.add(aporte);
    };

    public void adicionarAporteAtivo(AporteAtivoInvestidor aporteAtivo) {
        this.listaAportesAtivo.add(aporteAtivo);
    }

    public double obterMediaDeAportes() {
        return obterTotalDeAportes()/listaAportesAtivo.size();
    }

    public double obterTotalDeAportes() {
        double totalAportes = 0;
        for (AporteAtivoInvestidor aporteAtivoInvestidor : listaAportesAtivo) {
            totalAportes += aporteAtivoInvestidor.obterValorTotalAporte();
        };
        return totalAportes;
    }

    public double obterQuantidade() {
        double quantidade = 0;
        for (AporteAtivoInvestidor aporteAtivoInvestidor : listaAportesAtivo) {
            quantidade += aporteAtivoInvestidor.getQuantidadeAtivo();
        };
        return quantidade;
    }


    public int getId() {
        return this.id;
    }
    
    public Ativo getAtivo() {
        return this.ativo;
    }

    public ArrayList<AporteAtivoInvestidor> getListaAporteAtivo() {
        return this.listaAportesAtivo;
    }
}
