package src.main.java.br.com.techchallenge1.model.dto;

public class RespostaRelatorioRentabilidadeAtivoDto {
    String nomeAtivo;
    String valorTotalAtivo;
    String valorRentabilidadeAtivo;
    String percentualAtivoCarteira;

    public RespostaRelatorioRentabilidadeAtivoDto(String nomeAtivo, String valorTotalAtivo,
            String valorRentabilidadeAtivo, String percentualAtivoCarteira) {
        this.nomeAtivo = nomeAtivo;
        this.valorTotalAtivo = valorTotalAtivo;
        this.valorRentabilidadeAtivo = valorRentabilidadeAtivo;
        this.percentualAtivoCarteira = percentualAtivoCarteira;
    }

    public String getNomeAtivo() {
        return nomeAtivo;
    }

    public String getValorTotalAtivo() {
        return valorTotalAtivo;
    }

    public String getValorRentabilidadeAtivo() {
        return valorRentabilidadeAtivo;
    }

    public String getPercentualAtivoCarteira() {
        return percentualAtivoCarteira;
    }

    
}
