package src.main.java.br.com.techchallenge1.model.dto;

import src.main.java.br.com.techchallenge1.model.ClasseAtivoInvestidor;

public class RespostaRecomendacaoAporteClasseAtivoInvestidorDto {
    private Boolean recomendaAporte;
    private Double diferencaAlocacaoMetaContraAtual;
    private Double percentualAtualClasseAtivoInvestidor;
    private ClasseAtivoInvestidor classeAtivoInvestidor;
    
    public RespostaRecomendacaoAporteClasseAtivoInvestidorDto(Boolean recomendaAporte,
            Double diferencaAlocacaoMetaContraAtual, Double percentualAtualClasseAtivoInvestidor,
            ClasseAtivoInvestidor classeAtivoInvestidor) {
        this.recomendaAporte = recomendaAporte;
        this.diferencaAlocacaoMetaContraAtual = diferencaAlocacaoMetaContraAtual;
        this.percentualAtualClasseAtivoInvestidor = percentualAtualClasseAtivoInvestidor;
        this.classeAtivoInvestidor = classeAtivoInvestidor;
    }

    public Boolean getRecomendaAporte() {
        return recomendaAporte;
    }

    public void setRecomendaAporte(Boolean recomendaAporte) {
        this.recomendaAporte = recomendaAporte;
    }

    public Double getDiferencaAlocacaoMetaContraAtual() {
        return diferencaAlocacaoMetaContraAtual;
    }

    public void setDiferencaAlocacaoMetaContraAtual(Double diferencaAlocacaoMetaContraAtual) {
        this.diferencaAlocacaoMetaContraAtual = diferencaAlocacaoMetaContraAtual;
    }

    public Double getPercentualAtualClasseAtivoInvestidor() {
        return percentualAtualClasseAtivoInvestidor;
    }

    public void setPercentualAtualClasseAtivoInvestidor(Double percentualAtualClasseAtivoInvestidor) {
        this.percentualAtualClasseAtivoInvestidor = percentualAtualClasseAtivoInvestidor;
    }

    public ClasseAtivoInvestidor getClasseAtivoInvestidor() {
        return classeAtivoInvestidor;
    }

    public void setClasseAtivoInvestidor(ClasseAtivoInvestidor classeAtivoInvestidor) {
        this.classeAtivoInvestidor = classeAtivoInvestidor;
    }

    
}
