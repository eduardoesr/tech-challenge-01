package src.main.java.br.com.techchallenge1.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import src.main.java.br.com.techchallenge1.model.Aporte;
import src.main.java.br.com.techchallenge1.model.AtivoInvestidor;
import src.main.java.br.com.techchallenge1.model.ClasseAtivo;
import src.main.java.br.com.techchallenge1.model.Investidor;
import src.main.java.br.com.techchallenge1.model.Relatorio;
import src.main.java.br.com.techchallenge1.model.dto.DadosAporteClasseAtivosInvestidorDto;
import src.main.java.br.com.techchallenge1.model.dto.DadosRelatorioRentabilidadeAtivoDto;
import src.main.java.br.com.techchallenge1.model.dto.RespostaRecomendacaoAporteClasseAtivoInvestidorDto;
import src.main.java.br.com.techchallenge1.model.dto.RespostaRelatorioRentabilidadeAtivoDto;
import src.main.java.br.com.techchallenge1.utils.Arredondar;

public class ListarRecomendacaoAporteClasseAtivoInvestidorService {
    private Investidor investidor;
    private HashMap<String, ClasseAtivo> hashMapClasseAtivoPorCodigoAtivo;
    public static final Double DISCREPANCIA_META = 0.00;
    
    public ListarRecomendacaoAporteClasseAtivoInvestidorService(Investidor investidor,
            HashMap<String, ClasseAtivo> hashMapClasseAtivoPorCodigoAtivo) {
        this.investidor = investidor;
        this.hashMapClasseAtivoPorCodigoAtivo = hashMapClasseAtivoPorCodigoAtivo;
    }

    public List<RespostaRecomendacaoAporteClasseAtivoInvestidorDto> listarRecomendacaoAporteClasseAtivoInvestidor() {
        List<RespostaRecomendacaoAporteClasseAtivoInvestidorDto> recomendacoesAporteClasseAtivo = new ArrayList<>();
        HashMap<String, Double> listCotacaoAtivo = new HashMap<>();

        for (AtivoInvestidor ativoInvestidor : investidor.obterAtivosInvestidor()) {
            listCotacaoAtivo.put(
                ativoInvestidor.getAtivo().getCodigoAtivo(),
                hashMapClasseAtivoPorCodigoAtivo.get(ativoInvestidor.getAtivo().getCodigoAtivo()).getParceiroExterno().obterCotacao(ativoInvestidor));
        };

        for(
            DadosAporteClasseAtivosInvestidorDto aporteClasseAtivo :
            Aporte.totalizadoresAporteClasseAtivo(investidor, listCotacaoAtivo, investidor.obterClasseAtivoInvestidorDoAtivo())
        ) {
            Double alocaoAtual = Arredondar.duasCasas(aporteClasseAtivo.getAlocacaoAtual()*100/aporteClasseAtivo.getTotalAportesInvestidor());

            recomendacoesAporteClasseAtivo.add(
                new RespostaRecomendacaoAporteClasseAtivoInvestidorDto(
                    aporteClasseAtivo.getClasseAtivoInvestidor().getMetaPercentualAlocacaoClasseAtivo() - alocaoAtual > DISCREPANCIA_META, aporteClasseAtivo.getClasseAtivoInvestidor().getMetaPercentualAlocacaoClasseAtivo() - alocaoAtual,
                    alocaoAtual,
                    aporteClasseAtivo.getClasseAtivoInvestidor())
            );
        }

        return recomendacoesAporteClasseAtivo;
    }
}
