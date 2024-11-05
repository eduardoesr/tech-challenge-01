package src.main.java.br.com.techchallenge1.model.dto;

public class RespostaRelatorioRentabilidadeAtivoDto {
    String nomeAtivo;
    String codigoAtivo;
    String valorTotalAtivo;
    String valorRentabilidadeAtivo;
    String percentualAtivoCarteira;

    public RespostaRelatorioRentabilidadeAtivoDto(String nomeAtivo, String codigoAtivo, String valorTotalAtivo,
            String valorRentabilidadeAtivo, String percentualAtivoCarteira) {
        this.nomeAtivo = nomeAtivo;
        this.codigoAtivo = codigoAtivo;
        this.valorTotalAtivo = valorTotalAtivo;
        this.valorRentabilidadeAtivo = valorRentabilidadeAtivo;
        this.percentualAtivoCarteira = percentualAtivoCarteira;
    }

    public String getNomeAtivo() {
        return nomeAtivo;
    }

    public String getCodigoAtivo() {
        return codigoAtivo;
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
