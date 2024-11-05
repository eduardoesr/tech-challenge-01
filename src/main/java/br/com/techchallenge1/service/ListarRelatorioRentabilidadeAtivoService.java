package src.main.java.br.com.techchallenge1.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import src.main.java.br.com.techchallenge1.model.AtivoInvestidor;
import src.main.java.br.com.techchallenge1.model.ClasseAtivo;
import src.main.java.br.com.techchallenge1.model.Investidor;
import src.main.java.br.com.techchallenge1.model.Relatorio;
import src.main.java.br.com.techchallenge1.model.dto.DadosRelatorioRentabilidadeAtivoDto;
import src.main.java.br.com.techchallenge1.model.dto.RespostaRelatorioRentabilidadeAtivoDto;
import src.main.java.br.com.techchallenge1.utils.MotorCalculo;

public class ListarRelatorioRentabilidadeAtivoService {

    private Investidor investidor;
    private HashMap<String, ClasseAtivo> hashMapClasseAtivoPorCodigoAtivo;

    public ListarRelatorioRentabilidadeAtivoService(Investidor investidor,HashMap<String, ClasseAtivo> hashMapClasseAtivoPorCodigoAtivo) {
        this.hashMapClasseAtivoPorCodigoAtivo = hashMapClasseAtivoPorCodigoAtivo;
        this.investidor = investidor;
    }

    public List<RespostaRelatorioRentabilidadeAtivoDto> listarRelatorioRentabilidadeAtivo() {
        List<RespostaRelatorioRentabilidadeAtivoDto> ListarRelatorioRentabilidadeAtivo = new ArrayList<>();
        HashMap<String, Double> listCotacaoAtivo = new HashMap<>();
        double valorTotalCarteira = 0;

        for (AtivoInvestidor ativoInvestidor : investidor.obterAtivosInvestidor()) {
            listCotacaoAtivo.put(
                ativoInvestidor.getAtivo().getCodigoAtivo(),
                hashMapClasseAtivoPorCodigoAtivo.get(ativoInvestidor.getAtivo().getCodigoAtivo()).getParceiroExterno().obterCotacao(ativoInvestidor));
            valorTotalCarteira += ativoInvestidor.obterTotalDeAportes();
        };
        // Dados relatório
        for (DadosRelatorioRentabilidadeAtivoDto dadosRelatorioRentabilidade : Relatorio.rentabilidadeAtivo(investidor, listCotacaoAtivo)) {
            Double percentualCarteira = Math.round(
                    dadosRelatorioRentabilidade.getAtivoInvestidor().obterTotalDeAportes()*100/valorTotalCarteira *
                    100.0
                )/100.0;
            Double valorAtual = dadosRelatorioRentabilidade.getAtivoInvestidor().obterTotalDeAportes() *
                                (1 + dadosRelatorioRentabilidade.getRentabilidadeAtivo()/100);
            valorAtual = Math.round(valorAtual * 100.0)/100.0;
            ListarRelatorioRentabilidadeAtivo.add(new RespostaRelatorioRentabilidadeAtivoDto(
                dadosRelatorioRentabilidade.getAtivoInvestidor().getAtivo().getNomeAtivo(),
                valorAtual.toString(),
                dadosRelatorioRentabilidade.getRentabilidadeAtivo().toString(),
                percentualCarteira.toString()
                )
            );
        }


        return ListarRelatorioRentabilidadeAtivo;
    }
}
