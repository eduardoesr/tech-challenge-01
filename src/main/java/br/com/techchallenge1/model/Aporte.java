package src.main.java.br.com.techchallenge1.model;

import java.util.HashMap;

import src.main.java.br.com.techchallenge1.model.dto.DadosAporteClasseAtivosInvestidorDto;
import src.main.java.br.com.techchallenge1.model.dto.DadosRelatorioRentabilidadeAtivoDto;
import src.main.java.br.com.techchallenge1.utils.Arredondar;

public class Aporte {
    public static HashMap<String, DadosAporteClasseAtivosInvestidorDto> mapAportesPorClasseAtivo = new HashMap<>();

    public static HashMap<String, DadosAporteClasseAtivosInvestidorDto> totalizadoresAporteClasseAtivo (
        Investidor investidor,
        HashMap<String, Double> listCotacaoAtivos,
        HashMap<String, ClasseAtivoInvestidor> classeAtivoPorCodigoAtivo) {

        for (DadosRelatorioRentabilidadeAtivoDto rentabilidadeAtivo : Relatorio.rentabilidadeAtivo(investidor, listCotacaoAtivos)) {
            String nomeClasseAtivo = classeAtivoPorCodigoAtivo.get(rentabilidadeAtivo.getAtivoInvestidor()
                    .getAtivo()
                    .getCodigoAtivo())
                .getClasseAtivo().getNomeClasseAtivo();

            Double valorAtual = Arredondar.duasCasas(rentabilidadeAtivo.getAtivoInvestidor().obterTotalDeAportes() * (1 + rentabilidadeAtivo.getRentabilidadeAtivo()/100));

            if (mapAportesPorClasseAtivo.get(nomeClasseAtivo) != null) {
                mapAportesPorClasseAtivo.get(nomeClasseAtivo).adicionarAporte(valorAtual);
            } else {
                mapAportesPorClasseAtivo.put(
                    nomeClasseAtivo,
                    new DadosAporteClasseAtivosInvestidorDto(
                        valorAtual,
                        classeAtivoPorCodigoAtivo.get(rentabilidadeAtivo.getAtivoInvestidor().getAtivo().getCodigoAtivo())
                ));
            }
        }
        return mapAportesPorClasseAtivo;
    }
}
