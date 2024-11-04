package src.main.java.br.com.techchallenge1.model;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ClasseAtivoInvestidor {
    private static AtomicInteger uniqueIdGenerator = new AtomicInteger();
    private int id;
    private float metaAlocacaoClasseAtivo;
    private ClasseAtivo classeAtivo;
    private HashMap<String, CategoriaAtivo> hashMapCategoriaAtivo = new HashMap<>();

    public ClasseAtivoInvestidor(float metaAlocacaoClasseAtivo, ClasseAtivo classeAtivo) {
        this.id = uniqueIdGenerator.incrementAndGet();
        this.metaAlocacaoClasseAtivo = metaAlocacaoClasseAtivo;
        this.classeAtivo = classeAtivo;
    }

    public void adicionarCategoriaAtivo(CategoriaAtivo categoriaAtivo) {
        this.hashMapCategoriaAtivo.put(categoriaAtivo.getNomeCategoriaAtivo(), categoriaAtivo);
    }

    public int getId() {
        return id;
    }

    public float getMetaAlocacaoClasseAtivo() {
        return metaAlocacaoClasseAtivo;
    }

    public ClasseAtivo getClasseAtivo() {
        return classeAtivo;
    }

    public HashMap<String, CategoriaAtivo> getHashMapCategoriaAtivo() {
        return hashMapCategoriaAtivo;
    }
}
