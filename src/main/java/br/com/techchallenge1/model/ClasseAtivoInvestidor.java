package src.main.java.br.com.techchallenge1.model;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ClasseAtivoInvestidor {
    private static AtomicInteger uniqueIdGenerator = new AtomicInteger();
    private int id;
    private Double metaPercentualAlocacaoClasseAtivo;
    private ClasseAtivo classeAtivo;
    private HashMap<String, CategoriaAtivoInvestidor> hashMapCategoriaAtivoInvestidor = new HashMap<>();

    public ClasseAtivoInvestidor(Double metaPercentualAlocacaoClasseAtivo, ClasseAtivo classeAtivo) {
        this.id = uniqueIdGenerator.incrementAndGet();
        this.metaPercentualAlocacaoClasseAtivo = metaPercentualAlocacaoClasseAtivo;
        this.classeAtivo = classeAtivo;
    }

    public void adicionarCategoriaAtivoInvestidor(CategoriaAtivoInvestidor categoriaAtivoInvestidor) {
        this.hashMapCategoriaAtivoInvestidor.put(categoriaAtivoInvestidor.getNomeCategoriaAtivoInvestidor(), categoriaAtivoInvestidor);
    }

    public int getId() {
        return id;
    }

    public Double getMetaPercentualAlocacaoClasseAtivo() {
        return metaPercentualAlocacaoClasseAtivo;
    }

    public ClasseAtivo getClasseAtivo() {
        return classeAtivo;
    }

    public HashMap<String, CategoriaAtivoInvestidor> getHashMapCategoriaAtivoInvestidor() {
        return hashMapCategoriaAtivoInvestidor;
    }

    public void setMetaPercentualAlocacaoClasseAtivo(double metaPercentualAlocacaoClasseAtivo) {
        this.metaPercentualAlocacaoClasseAtivo = metaPercentualAlocacaoClasseAtivo;
    }
    
}
