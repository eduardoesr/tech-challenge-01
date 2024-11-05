package src.main.java.br.com.techchallenge1.rest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import src.main.java.br.com.techchallenge1.model.AporteAtivoInvestidor;
import src.main.java.br.com.techchallenge1.model.Ativo;
import src.main.java.br.com.techchallenge1.model.AtivoInvestidor;
import src.main.java.br.com.techchallenge1.model.CategoriaAtivoInvestidor;
import src.main.java.br.com.techchallenge1.model.ClasseAtivo;
import src.main.java.br.com.techchallenge1.model.Investidor;
import src.main.java.br.com.techchallenge1.model.ParceiroExterno;
import src.main.java.br.com.techchallenge1.model.dto.RespostaRecomendacaoAporteClasseAtivoInvestidorDto;
import src.main.java.br.com.techchallenge1.model.dto.RespostaRelatorioRentabilidadeAtivoDto;
import src.main.java.br.com.techchallenge1.service.DefineClasseAtivoInvestidorService;
import src.main.java.br.com.techchallenge1.service.IncluirAtivoInvestidorService;
import src.main.java.br.com.techchallenge1.service.IncluirCategoriaAtivoInvestidorService;
import src.main.java.br.com.techchallenge1.service.IncluirInvestidorService;
import src.main.java.br.com.techchallenge1.service.ListarRecomendacaoAporteClasseAtivoInvestidorService;
import src.main.java.br.com.techchallenge1.service.ListarRelatorioRentabilidadeAtivoService;

public class SimularUsoResource {
    public static HashMap<String, ClasseAtivo> hashMapClasseAtivoPorNomeClasse = new HashMap<>(); // Obter classe pelo nome da classe
    public static HashMap<String, ClasseAtivo> hashMapClasseAtivoPorCodigoAtivo = new HashMap<>(); // Obter classe pelo código do ativo
    public static HashMap<String, Ativo> HashMapAtivoPorCodigoAtivo = new HashMap<>(); // Obter ativo pelo código do ativo
    public static List<Investidor> investidores = new ArrayList<>(); // Usuários cadastrados
    
    public static void main(String[] args) {
        // Operações não realizadas pelo usuário e sim pelo administrador, para preparar o sistema para uso. 
        configuracoesInicializacao();
        IncluirInvestidorService investidorService = new IncluirInvestidorService(investidores);
        
        // Cadastrar usuário
        Investidor investidor = investidorService.incluirInvestidor("Nome Teste", "email@teste.com.br");

        // Contexto Delimitado 1 - Configuração de Ativos
        configuracaoDeAtivos(investidor);

        // Contexto Delimitado 2 - Relatório
        geracaoDeRelatorio(investidor);

        // Contexto Delimitado 3 - Recomendação de Aportes
        recomendacaoDeAportes(investidor);
    }

    // Contexto Delimitado 1
    public static void configuracaoDeAtivos(Investidor investidor) {
        DefineClasseAtivoInvestidorService definirClasseAtivoService = new DefineClasseAtivoInvestidorService(investidor);
        IncluirCategoriaAtivoInvestidorService incluirCategoriaAtivoInvestidorService = new IncluirCategoriaAtivoInvestidorService(investidor);
        IncluirAtivoInvestidorService incluirAtivoInvestidorService = new IncluirAtivoInvestidorService(investidor);

        // Definiu classe de ativo e percentual meta de alocação da classe na carteira
        definirClasseAtivoService.definirClasseAtivo(10.00, hashMapClasseAtivoPorNomeClasse.get("Criptoativos"));
        definirClasseAtivoService.definirClasseAtivo(70.00, hashMapClasseAtivoPorNomeClasse.get("Renda Fixa"));
        definirClasseAtivoService.definirClasseAtivo(20.00, hashMapClasseAtivoPorNomeClasse.get("Ações"));

        // Criação de uma categoria de uma classe de ativo
        incluirCategoriaAtivoInvestidorService.incluirCategoriaAtivoInvestidor(
            "Criptoativos", 
            new CategoriaAtivoInvestidor("Moedas seguras", "Moedas já estabelecidas e com bom histórico")
        );
        incluirCategoriaAtivoInvestidorService.incluirCategoriaAtivoInvestidor(
            "Criptoativos", 
            new CategoriaAtivoInvestidor("NFT alto risco", "Projetos novos NFT")
        );
        incluirCategoriaAtivoInvestidorService.incluirCategoriaAtivoInvestidor(
            "Renda Fixa",
            new CategoriaAtivoInvestidor("Tesouro Direto", "Renda fixa seguro")
        );
        incluirCategoriaAtivoInvestidorService.incluirCategoriaAtivoInvestidor(
            "Ações", 
            new CategoriaAtivoInvestidor("Ações dividendos", "Ações que pagam dividendos")
        );

        // Preencheu ativos presentes dentro da classe de ativos, quantidade e categoria.
        incluirAtivoInvestidorService.incluirAtivoInvestidor(
            "Criptoativos", 
            "Moedas seguras", 
            new AtivoInvestidor(
                HashMapAtivoPorCodigoAtivo.get("BTC"),
                new AporteAtivoInvestidor(0.5, 200000, LocalDateTime.now().minusMonths(1))
        ));
        incluirAtivoInvestidorService.incluirAtivoInvestidor(
            "Criptoativos", 
            "Moedas seguras",
            new AtivoInvestidor(
                HashMapAtivoPorCodigoAtivo.get("ETH"),
                new AporteAtivoInvestidor(0.2, 14000, LocalDateTime.now().minusMonths(1))
        ));
        incluirAtivoInvestidorService.incluirAtivoInvestidor(
            "Criptoativos", 
            "NFT alto risco", 
            new AtivoInvestidor(
                HashMapAtivoPorCodigoAtivo.get("CDC"),
                new AporteAtivoInvestidor(1, 500, LocalDateTime.now().minusMonths(1))
        ));
        incluirAtivoInvestidorService.incluirAtivoInvestidor(
            "Renda Fixa", 
            "Tesouro Direto", 
            new AtivoInvestidor(
                HashMapAtivoPorCodigoAtivo.get("BRSTNCLF1RL5"),
                new AporteAtivoInvestidor(1, 15489.85, LocalDateTime.now().minusMonths(1))
        ));
        incluirAtivoInvestidorService.incluirAtivoInvestidor(
            "Ações", 
            "Ações dividendos", 
            new AtivoInvestidor(
                HashMapAtivoPorCodigoAtivo.get("BBAS3"),
                new AporteAtivoInvestidor(1, 26.00, LocalDateTime.now().minusMonths(1))
        ));
    }

    // Contexto Delimitado 2
    public static void geracaoDeRelatorio(Investidor investidor) {
        ListarRelatorioRentabilidadeAtivoService listarRelatorioRentabilidadeAtivoService = new ListarRelatorioRentabilidadeAtivoService(
            investidor, hashMapClasseAtivoPorCodigoAtivo
        );
        System.out.println(" ");
        System.out.println("Relatório de ativos");
        for (RespostaRelatorioRentabilidadeAtivoDto rentabilidadeAtivo : listarRelatorioRentabilidadeAtivoService.listarRelatorioRentabilidadeAtivo()) {
            System.out.println(
                "Nome " + rentabilidadeAtivo.getNomeAtivo() +
                " (" + rentabilidadeAtivo.getCodigoAtivo() + ")" +
                " | Valor total R$ " + rentabilidadeAtivo.getValorTotalAtivo() +
                " | Rentabilidade " + rentabilidadeAtivo.getValorRentabilidadeAtivo() + 
                "% | Percentual Carteira " + rentabilidadeAtivo.getPercentualAtivoCarteira() + "%");
        };
        System.out.println(" ");
    }


    // Contexto Delimitado 3
    public static void recomendacaoDeAportes(Investidor investidor) {
        investidor.setAportePeriodico(2000.00);
        
        ListarRecomendacaoAporteClasseAtivoInvestidorService listarRecomendacaoAporteClasseAtivoInvestidorService = new ListarRecomendacaoAporteClasseAtivoInvestidorService(
            investidor, hashMapClasseAtivoPorCodigoAtivo
        );

        System.out.println("Recomendações");
        for (RespostaRecomendacaoAporteClasseAtivoInvestidorDto recomendacaoAporte : 
            listarRecomendacaoAporteClasseAtivoInvestidorService.listarRecomendacaoAporteClasseAtivoInvestidor()) {

            String nomeClasseAtivo = recomendacaoAporte.getClasseAtivoInvestidor().getClasseAtivo().getNomeClasseAtivo();
            Double metaClasseAtivo = recomendacaoAporte.getClasseAtivoInvestidor().getMetaPercentualAlocacaoClasseAtivo();
            Double percentualAtualClasseAtivo = recomendacaoAporte.getPercentualAtualClasseAtivoInvestidor();

            if (recomendacaoAporte.getRecomendaAporte()) {
                System.out.println("Compre mais ativos da classe '" + nomeClasseAtivo +
                "'. Pois sua meta é " + metaClasseAtivo.toString() + "% e atualmente ela compõe " + percentualAtualClasseAtivo + "% da sua carteira.");
            } else {
                System.out.println("Evite comprar ativo da classe '" + nomeClasseAtivo +
                "'. Pois sua meta é " + metaClasseAtivo.toString() + "% e atualmente ela compõe " + percentualAtualClasseAtivo + "% da sua carteira.");
            }

        }
        System.out.println(" ");
    }

    // Configurações iniciais pré definidas do sistema. O investidor não participa desse processo. 
    public static void configuracoesInicializacao() {
        // Adicionar parceiro externo, seus ativos e cotação (simular retorno API)
        ParceiroExterno parceiroRendaFixa = new ParceiroExterno("Parceiro Renda Fixa", "url1");
        ParceiroExterno parceiroCripto = new ParceiroExterno("Parceiro Cripto", "url2");
        ParceiroExterno parceiroBolsa = new ParceiroExterno("Parceiro Bolsa", "url3");

        parceiroCripto.adicionarAtivo("BTC", 200000.00);
        parceiroCripto.adicionarAtivo("ETH", 14000.00);
        parceiroCripto.adicionarAtivo("CDC", 500.00);
        parceiroRendaFixa.adicionarAtivo("BRSTNCLF1RL5", 15489.85);
        parceiroBolsa.adicionarAtivo("BBAS3", 27.85);

        // Configurar classes de ativo pré definidas
        ClasseAtivo ativosRendaFixa = new ClasseAtivo("Renda Fixa", "Investimentos renda fixa", parceiroRendaFixa);
        ClasseAtivo ativosCripto = new ClasseAtivo("Criptoativos", "Investimentos criptoativos", parceiroCripto);
        ClasseAtivo ativosAcoes = new ClasseAtivo("Ações", "Investimentos Ações", parceiroBolsa);

        hashMapClasseAtivoPorNomeClasse.put(ativosRendaFixa.getNomeClasseAtivo(), ativosRendaFixa);
        hashMapClasseAtivoPorNomeClasse.put(ativosCripto.getNomeClasseAtivo(), ativosCripto);
        hashMapClasseAtivoPorNomeClasse.put(ativosAcoes.getNomeClasseAtivo(), ativosAcoes);

        hashMapClasseAtivoPorCodigoAtivo.put("BTC", ativosCripto);
        hashMapClasseAtivoPorCodigoAtivo.put("ETH", ativosCripto);
        hashMapClasseAtivoPorCodigoAtivo.put("CDC", ativosCripto);
        hashMapClasseAtivoPorCodigoAtivo.put("BRSTNCLF1RL5", ativosRendaFixa);
        hashMapClasseAtivoPorCodigoAtivo.put("BBAS3", ativosAcoes);

        // Ativos cadastrados no sistema
        Ativo bitcoinAtivo = new Ativo("Bitcoin", "BTC", false);
        Ativo ethereumAtivo = new Ativo("Ethereum",  "ETH", false);
        Ativo nftAtivo = new Ativo("CapivaraDeChapeu", "CDC", false);
        Ativo selicAtivo = new Ativo("Tesouro Selic 2027",  "BRSTNCLF1RL5", true);
        Ativo acaoBB = new Ativo("Banco do Brasil SA",  "BBAS3", false);

        HashMapAtivoPorCodigoAtivo.put("BTC", bitcoinAtivo);
        HashMapAtivoPorCodigoAtivo.put("ETH", ethereumAtivo);
        HashMapAtivoPorCodigoAtivo.put("CDC", nftAtivo);
        HashMapAtivoPorCodigoAtivo.put("BRSTNCLF1RL5", selicAtivo);
        HashMapAtivoPorCodigoAtivo.put("BBAS3", acaoBB);
    }
}
