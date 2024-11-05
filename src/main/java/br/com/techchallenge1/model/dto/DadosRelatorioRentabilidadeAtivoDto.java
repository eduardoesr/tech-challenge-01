package src.main.java.br.com.techchallenge1.model.dto;

import src.main.java.br.com.techchallenge1.model.AtivoInvestidor;

public class DadosRelatorioRentabilidadeAtivoDto {
    private AtivoInvestidor ativoInvestidor;
    private Double rentabilidadeAtivo;

    public DadosRelatorioRentabilidadeAtivoDto(
            AtivoInvestidor ativoInvestidor,
            Double rentabilidadeAtivo
            )
    {
        this.ativoInvestidor = ativoInvestidor;
        this.rentabilidadeAtivo = rentabilidadeAtivo;
    }

    public AtivoInvestidor getAtivoInvestidor() {
        return ativoInvestidor;
    }

    public Double getRentabilidadeAtivo() {
        return rentabilidadeAtivo;
    }

    
}
