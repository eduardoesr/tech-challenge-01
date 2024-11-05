package src.main.java.br.com.techchallenge1.model;

import java.util.Collection;
import java.util.HashMap;

import src.main.java.br.com.techchallenge1.model.dto.DadosAporteClasseAtivosInvestidorDto;
import src.main.java.br.com.techchallenge1.model.dto.DadosRelatorioRentabilidadeAtivoDto;
import src.main.java.br.com.techchallenge1.utils.Arredondar;

public class Aporte {
    public static Collection<DadosAporteClasseAtivosInvestidorDto> totalizadoresAporteClasseAtivo(
        Investidor investidor,
        HashMap<String, Double>listCotacaoAtivos,
        HashMap<String, ClasseAtivoInvestidor>classeAtivoPorCodigoAtivo) {

        HashMap<String, DadosAporteClasseAtivosInvestidorDto> mapAportesPorClasseAtivo = new HashMap<>();
        Double valorTotalAportesInvestidor = 0.00;

        for(DadosRelatorioRentabilidadeAtivoDto rentabilidadeAtivo : Relatorio.rentabilidadeAtivo(investidor, listCotacaoAtivos)) {
            String nomeClasseAtivo = classeAtivoPorCodigoAtivo.get(rentabilidadeAtivo.getAtivoInvestidor()
                    .getAtivo()
                    .getCodigoAtivo())
                .getClasseAtivo().getNomeClasseAtivo();

            Double valorAtual = Arredondar.duasCasas(rentabilidadeAtivo.getAtivoInvestidor().obterTotalDeAportes() * (1 + rentabilidadeAtivo.getRentabilidadeAtivo()/100));
            valorTotalAportesInvestidor += valorAtual;

            if(mapAportesPorClasseAtivo.get(nomeClasseAtivo) != null) {
                var aporteClasseAtivos = mapAportesPorClasseAtivo.get(nomeClasseAtivo);
                aporteClasseAtivos.setAlocacaoAtual(aporteClasseAtivos.getAlocacaoAtual() + valorAtual);
                aporteClasseAtivos.setTotalAportesInvestidor(aporteClasseAtivos.getTotalAportesInvestidor() + valorTotalAportesInvestidor);
            } else {
                mapAportesPorClasseAtivo.put(
                    nomeClasseAtivo,
                    new DadosAporteClasseAtivosInvestidorDto(
                        valorAtual,
                        valorTotalAportesInvestidor,
                        classeAtivoPorCodigoAtivo.get(rentabilidadeAtivo.getAtivoInvestidor()
                        .getAtivo()
                        .getCodigoAtivo())
                    ));
            }
        }

        return mapAportesPorClasseAtivo.values();
    }
}
