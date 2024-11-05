package src.main.java.br.com.techchallenge1.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.main.java.br.com.techchallenge1.model.Aporte;
import src.main.java.br.com.techchallenge1.model.AtivoInvestidor;
import src.main.java.br.com.techchallenge1.model.ClasseAtivo;
import src.main.java.br.com.techchallenge1.model.Investidor;
import src.main.java.br.com.techchallenge1.model.dto.DadosAporteClasseAtivosInvestidorDto;
import src.main.java.br.com.techchallenge1.model.dto.RespostaRecomendacaoAporteClasseAtivoInvestidorDto;
import src.main.java.br.com.techchallenge1.utils.Arredondar;

public class ListarRecomendacaoAporteClasseAtivoInvestidorService {
    private Investidor investidor;
    private HashMap<String, ClasseAtivo> hashMapClasseAtivoPorCodigoAtivo = new HashMap<>();
    private HashMap<String, Double> discrepanciaMetaClasse = new HashMap<>();
    
    public ListarRecomendacaoAporteClasseAtivoInvestidorService(Investidor investidor,
            HashMap<String, ClasseAtivo> hashMapClasseAtivoPorCodigoAtivo) {
        this.investidor = investidor;
        this.hashMapClasseAtivoPorCodigoAtivo = hashMapClasseAtivoPorCodigoAtivo;
    }

    public List<RespostaRecomendacaoAporteClasseAtivoInvestidorDto> listarRecomendacaoAporteClasseAtivoInvestidor() {
        List<RespostaRecomendacaoAporteClasseAtivoInvestidorDto> recomendacoesAporteClasseAtivo = new ArrayList<>();
        HashMap<String, Double> listaCotacaoAtivo = new HashMap<>();

        for (AtivoInvestidor ativoInvestidor : investidor.obterAtivosInvestidor()) {
            listaCotacaoAtivo.put(
                ativoInvestidor.getAtivo().getCodigoAtivo(),
                hashMapClasseAtivoPorCodigoAtivo.get(ativoInvestidor.getAtivo().getCodigoAtivo()).getParceiroExterno().obterCotacao(ativoInvestidor));
        };

        HashMap<String, DadosAporteClasseAtivosInvestidorDto> mapAportesPorClasseAtivo = Aporte.totalizadoresAporteClasseAtivo(investidor, listaCotacaoAtivo, investidor.obterClasseAtivoInvestidorDoAtivo());
        
        Double aporteTotal = 0.00;
        for (DadosAporteClasseAtivosInvestidorDto dadosAporteClasseAtivosInvestidorDto : mapAportesPorClasseAtivo.values()) {
            aporteTotal += dadosAporteClasseAtivosInvestidorDto.getAlocacaoAtual();
        };

        for (Map.Entry<String, DadosAporteClasseAtivosInvestidorDto> entry : mapAportesPorClasseAtivo.entrySet()) {
            Double alocacaoAtual = Arredondar.duasCasas(entry.getValue().getAlocacaoAtual()*100/aporteTotal);
            Double alocacaoMeta = investidor.obterMetaPorClasse().get(entry.getKey());
            Double discrepanciaMeta = alocacaoMeta - alocacaoAtual;
            discrepanciaMetaClasse.put(entry.getKey(), discrepanciaMeta);
        }

        for (Map.Entry<String, Double> entry : discrepanciaMetaClasse.entrySet()) {   
            recomendacoesAporteClasseAtivo.add(
                new RespostaRecomendacaoAporteClasseAtivoInvestidorDto(
                    entry.getValue() > 0, 
                    entry.getValue(),
                    Arredondar.duasCasas(mapAportesPorClasseAtivo.get(entry.getKey()).getAlocacaoAtual()*100/aporteTotal),
                    mapAportesPorClasseAtivo.get(entry.getKey()).getClasseAtivoInvestidor()
                )
            );
        }

        return recomendacoesAporteClasseAtivo;
    }
}
