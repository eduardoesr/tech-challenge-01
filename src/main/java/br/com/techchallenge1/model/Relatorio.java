package src.main.java.br.com.techchallenge1.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import src.main.java.br.com.techchallenge1.model.dto.DadosRelatorioRentabilidadeAtivoDto;
import src.main.java.br.com.techchallenge1.utils.MotorCalculo;

public class Relatorio {
    public static List<DadosRelatorioRentabilidadeAtivoDto> rentabilidadeAtivo(Investidor investidor, HashMap<String, Double>listCotacaoAtivos) {
        List<DadosRelatorioRentabilidadeAtivoDto> rentabilidadeAtivos = new ArrayList<>();

        for (AtivoInvestidor ativoInvestidor : investidor.obterAtivosInvestidor()) {
            double valorMedioAtivoPorAportes = ativoInvestidor.obterMediaDeAportes()/ativoInvestidor.obterQuantidade();
            double rentabilidade = MotorCalculo.calculaRentabilidadeAtivo(
                valorMedioAtivoPorAportes,
                listCotacaoAtivos.get(ativoInvestidor.getAtivo().getCodigoAtivo()).doubleValue()
                );
            // Dados relatório
            rentabilidadeAtivos.add(
                new DadosRelatorioRentabilidadeAtivoDto(
                    ativoInvestidor,
                    rentabilidade)
            );
        };

        return rentabilidadeAtivos;
    }
}
