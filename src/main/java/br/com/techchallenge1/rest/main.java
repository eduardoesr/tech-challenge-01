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
import src.main.java.br.com.techchallenge1.model.dto.RespostaRelatorioRentabilidadeAtivoDto;
import src.main.java.br.com.techchallenge1.service.DefineClasseAtivoInvestidorService;
import src.main.java.br.com.techchallenge1.service.IncluirAtivoInvestidorService;
import src.main.java.br.com.techchallenge1.service.IncluirCategoriaAtivoInvestidorService;
import src.main.java.br.com.techchallenge1.service.IncluirInvestidorService;
import src.main.java.br.com.techchallenge1.service.ListarRelatorioRentabilidadeAtivoService;

public class main {
    public static HashMap<String, ClasseAtivo> hashMapClasseAtivoPorNomeClasse = new HashMap<>(); // Obter classe pelo nome da classe
    public static HashMap<String, ClasseAtivo> hashMapClasseAtivoPorCodigoAtivo = new HashMap<>(); // Obter classe pelo código do ativo
    public static HashMap<String, Ativo> HashMapAtivoPorCodigoAtivo = new HashMap<>(); // Obter ativo pelo código do ativo
    public static List<Investidor> investidores = new ArrayList<>();
    public static void main(String[] args) {
        configuracoesAdministrador();

        IncluirInvestidorService investidorService = new IncluirInvestidorService(investidores);
        
        // Cadastrar usuário
        Investidor investidor = investidorService.incluirInvestidor("Nome Teste", "email@teste.com.br");

        DefineClasseAtivoInvestidorService definirClasseAtivoService = new DefineClasseAtivoInvestidorService(investidor);
        IncluirCategoriaAtivoInvestidorService incluirCategoriaAtivoInvestidorService = new IncluirCategoriaAtivoInvestidorService(investidor);
        IncluirAtivoInvestidorService incluirAtivoInvestidorService = new IncluirAtivoInvestidorService(investidor);

        // Definiu percentual meta de alocação da classe na carteira
        definirClasseAtivoService.definirClasseAtivo(10, hashMapClasseAtivoPorNomeClasse.get("Criptoativos"));
        definirClasseAtivoService.definirClasseAtivo(70, hashMapClasseAtivoPorNomeClasse.get("Renda Fixa"));
        definirClasseAtivoService.definirClasseAtivo(20, hashMapClasseAtivoPorNomeClasse.get("Ações"));

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

        // Preencheu ativos presentes dentro da classe de ativos
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
                new AporteAtivoInvestidor(1, 15489.85, LocalDateTime.now().minusMonths(1))
            ));

        ListarRelatorioRentabilidadeAtivoService listarRelatorioRentabilidadeAtivoService = new ListarRelatorioRentabilidadeAtivoService(
            investidor, hashMapClasseAtivoPorCodigoAtivo
            );

        for(RespostaRelatorioRentabilidadeAtivoDto rentabilidadeAtivo : listarRelatorioRentabilidadeAtivoService.listarRelatorioRentabilidadeAtivo()) {
            System.out.println(
                "Nome " + rentabilidadeAtivo.getNomeAtivo() +
                " | Valor total R$ " + rentabilidadeAtivo.getValorTotalAtivo() +
                " | Rentabilidade " + rentabilidadeAtivo.getValorRentabilidadeAtivo() + 
                "% | Percentual Carteira: " + rentabilidadeAtivo.getPercentualAtivoCarteira() + "%");
        }
        // Contexto 3 - Recomendação de aportes
        investidor.setAportePeriodico(2000.00);

    }
                    
    // Configurações iniciais pré definidas do sistema. O investidor não participa desse processo. 
    public static void configuracoesAdministrador() {
        ParceiroExterno parceiroRendaFixa = new ParceiroExterno("Parceiro Renda Fixa", "url1");
        ParceiroExterno parceiroCripto = new ParceiroExterno("Parceiro Cripto", "url2");
        ParceiroExterno parceiroBolsa = new ParceiroExterno("Parceiro Bolsa", "url3");

        parceiroCripto.adicionarAtivo("BTC", 200000.00);
        parceiroCripto.adicionarAtivo("ETH", 14000.00);
        parceiroCripto.adicionarAtivo("CDC", 500.00);
        parceiroRendaFixa.adicionarAtivo("BRSTNCLF1RL5", 15489.85);

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

        // Usuário: Ativo -> Preencher ativos -> MUDANÇA: Primeiro preencheu classe da categoria, depois os ativos
        // DÚVIDA: Ativo precisa ser pré definido no sistema? O cliente só seleciona? Porque o sistema precisa de alguma forma ver o valor do ativo.
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
