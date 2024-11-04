package src.main.java.br.com.techchallenge1.model;

import java.time.LocalDateTime;
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

    public int getId() {
        return this.id;
    }
    
    public Ativo getAtivo() {
        return this.ativo;
    }

    public ArrayList<AporteAtivoInvestidor> getAporteAtivo() {
        return this.listaAportesAtivo;
    }
}
