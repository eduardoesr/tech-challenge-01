package src.main.java.br.com.techchallenge1.model.dto;

import src.main.java.br.com.techchallenge1.model.ClasseAtivoInvestidor;

public class DadosAporteClasseAtivosInvestidorDto {
    private Double alocacaoAtual;
    private ClasseAtivoInvestidor classeAtivoInvestidor;
    // private Double totalAportesInvestidor;

    public DadosAporteClasseAtivosInvestidorDto(Double alocacaoAtual, ClasseAtivoInvestidor classeAtivoInvestidor) {
        this.alocacaoAtual = alocacaoAtual;
        // this.totalAportesInvestidor = totalAportesInvestidor;
        this.classeAtivoInvestidor = classeAtivoInvestidor;
    }

    public Double getAlocacaoAtual() {
        return alocacaoAtual;
    }

    // public Double getTotalAportesInvestidor() {
    //     return totalAportesInvestidor;
    // }

    public ClasseAtivoInvestidor getClasseAtivoInvestidor() {
        return classeAtivoInvestidor;
    }

    // public void setTotalAportesInvestidor(Double totalAportesInvestidor) {
    //     this.totalAportesInvestidor = totalAportesInvestidor;
    // }

    // public void setAlocacaoAtual(Double alocacaoAtual) {
    //     this.alocacaoAtual = alocacaoAtual;
    // }

    public void adicionarAporte(Double aporte) {
        this.alocacaoAtual += aporte;
    }

    public void setClasseAtivoInvestidor(ClasseAtivoInvestidor classeAtivoInvestidor) {
        this.classeAtivoInvestidor = classeAtivoInvestidor;
    }

    
}
