package src.main.java.br.com.techchallenge1.rest;

import java.nio.channels.Pipe.SourceChannel;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import src.main.java.br.com.techchallenge1.model.AporteAtivoInvestidor;
import src.main.java.br.com.techchallenge1.model.Ativo;
import src.main.java.br.com.techchallenge1.model.AtivoInvestidor;
import src.main.java.br.com.techchallenge1.model.CategoriaAtivoInvestidor;
import src.main.java.br.com.techchallenge1.model.ClasseAtivo;
import src.main.java.br.com.techchallenge1.model.ClasseAtivoInvestidor;
import src.main.java.br.com.techchallenge1.model.Investidor;
import src.main.java.br.com.techchallenge1.model.ParceiroExterno;
import src.main.java.br.com.techchallenge1.utils.Arredondar;
import src.main.java.br.com.techchallenge1.utils.MotorCalculo;

public class SimularUso {
    public static HashMap<String, ClasseAtivo> hashMapClasseAtivoPorNomeClasse = new HashMap<>(); // Obter classe pelo nome da classe
    public static HashMap<String, ClasseAtivo> hashMapClasseAtivoPorCodigoAtivo = new HashMap<>(); // Obter classe pelo código do ativo
    public static HashMap<String, Ativo> HashMapAtivoPorCodigoAtivo = new HashMap<>(); // Obter ativo pelo código do ativo
    
    public static void main(String[] args) {
        configuracoesAdministrador();

        // Cadastrar usuário
        Investidor investidor = new Investidor("Nome Teste", "email@teste.com.br", "teste", "senha");

        // Contexto 1 - Configuração de ativos

        // Usuário: Classe Ativo -> Seleciona classe e define meta
        // Sistema: Classe Ativo -> Vincula classe de ativo e uma meta ao investidor
        ClasseAtivoInvestidor classeAtivoInvestidorCripto = new ClasseAtivoInvestidor(10.00, hashMapClasseAtivoPorNomeClasse.get("Criptoativos"));
        ClasseAtivoInvestidor classeAtivoInvestidorRendaFixa = new ClasseAtivoInvestidor(70.00, hashMapClasseAtivoPorNomeClasse.get("Renda Fixa"));
        ClasseAtivoInvestidor classeAtivoInvestidorBolsa = new ClasseAtivoInvestidor(20.00, hashMapClasseAtivoPorNomeClasse.get("Ações"));
        
        investidor.adicionarClasseAtivoInvestidor(classeAtivoInvestidorCripto);
        investidor.adicionarClasseAtivoInvestidor(classeAtivoInvestidorRendaFixa);
        investidor.adicionarClasseAtivoInvestidor(classeAtivoInvestidorBolsa);

        // Usuário: Categoria Ativo -> Criar categoria de ativo
        CategoriaAtivoInvestidor categoriaAtivoA = new CategoriaAtivoInvestidor("Moedas seguras", "Moedas já estabelecidas e com bom histórico");
        CategoriaAtivoInvestidor categoriaAtivoB = new CategoriaAtivoInvestidor("NFT alto risco", "Projetos novos NFT");
        CategoriaAtivoInvestidor categoriaAtivoC = new CategoriaAtivoInvestidor("Tesouro Direto", "Renda fixa seguro");
        CategoriaAtivoInvestidor categoriaAtivoD = new CategoriaAtivoInvestidor("Acoes Pagam Dividendos", "Renda fixa seguro");

        // Usuário: Definir classe de ativo da categoria -> MUDANÇA: Classe está da categoria em vez dos ativos
        classeAtivoInvestidorCripto.adicionarCategoriaAtivoInvestidor(categoriaAtivoA);
        classeAtivoInvestidorCripto.adicionarCategoriaAtivoInvestidor(categoriaAtivoB);
        classeAtivoInvestidorRendaFixa.adicionarCategoriaAtivoInvestidor(categoriaAtivoC);
        classeAtivoInvestidorBolsa.adicionarCategoriaAtivoInvestidor(categoriaAtivoD);

        // Usuário: Preencher categoria dos ativos
        categoriaAtivoA.adicionarAtivo(
            new AtivoInvestidor(
                HashMapAtivoPorCodigoAtivo.get("BTC"),
                new AporteAtivoInvestidor(0.5, 200000, LocalDateTime.now().minusMonths(1))
            )
        );
        categoriaAtivoB.adicionarAtivo(
            new AtivoInvestidor(
                HashMapAtivoPorCodigoAtivo.get("ETH"),
                new AporteAtivoInvestidor(0.2, 14000, LocalDateTime.now().minusMonths(1))
            )
        );
        categoriaAtivoC.adicionarAtivo(
            new AtivoInvestidor(
                HashMapAtivoPorCodigoAtivo.get("CDC"),
                new AporteAtivoInvestidor(1, 500, LocalDateTime.now().minusMonths(1))
            )
        );
        categoriaAtivoD.adicionarAtivo(
            new AtivoInvestidor(
                HashMapAtivoPorCodigoAtivo.get("BRSTNCLF1RL5"),
                new AporteAtivoInvestidor(1, 15489.85, LocalDateTime.now().minusMonths(1))
            )
        );




        // Contexto 2 - Relatórios

        // Buscar ativo em posse
        ArrayList<AtivoInvestidor> listaAtivosInvestidor = investidor.obterAtivosInvestidor();
        // Dados gerar relatório
        double valorTotalCarteira = 0;
        ArrayList<String> listaNomeAtivo = new ArrayList<>();
        ArrayList<Double> listaValorTotalAtivo = new ArrayList<>();
        ArrayList<Double> listaValorização = new ArrayList<>();
        for (AtivoInvestidor ativoInvestidor : listaAtivosInvestidor) {
            double valorMedioAportes = ativoInvestidor.obterMediaDeAportes()/ativoInvestidor.obterQuantidade();
            // Buscar cotações 
            double valorAtualAtivo = hashMapClasseAtivoPorCodigoAtivo.get(ativoInvestidor.getAtivo().getCodigoAtivo()).getParceiroExterno().obterCotacao(ativoInvestidor);
            // Calcular rentabilidade
            double rentabilidade = MotorCalculo.calculaRentabilidadeAtivo(valorMedioAportes, valorAtualAtivo);
            // Dados relatório
            listaNomeAtivo.add(ativoInvestidor.getAtivo().getNomeAtivo());
            listaValorTotalAtivo.add(ativoInvestidor.obterTotalDeAportes());
            listaValorização.add(rentabilidade);
            valorTotalCarteira += ativoInvestidor.obterTotalDeAportes();
        };

        // Dados relatório
        for (int i = 0; i < listaNomeAtivo.size(); i++) {
            double percentualCarteira = Arredondar.duasCasas(listaValorTotalAtivo.get(i)*100/valorTotalCarteira);
            double valorAtual = Arredondar.duasCasas(listaValorTotalAtivo.get(i)*100/valorTotalCarteira);
            System.out.println("Nome " + listaNomeAtivo.get(i) + " | Valor total R$ " + valorAtual + " | Rentabilidade " + listaValorização.get(i) + "% | Percentual Carteira: " + percentualCarteira + "%");
        }



        // Contexto 3 - Recomendação de aportes

        // Usuário preenche valor de aporte periódico
        investidor.setAportePeriodico(2000.00);

        // Buscar ativo em posse
        ArrayList<AtivoInvestidor> listaAtivosInvestidorC3 = investidor.obterAtivosInvestidor();
        HashMap<String, ClasseAtivoInvestidor> classeAtivos = investidor.obterClasseAtivoInvestidorDoAtivo();
        HashMap<String, Double> metaPorClasse = investidor.obterMetaPorClasse();
        HashMap<String, Double> aportesPorClasse = investidor.obterAportesPorClasse();

        for (AtivoInvestidor ativoInvestidor : listaAtivosInvestidorC3) {
            // Calcular rentabilidade
            double valorMedioAportes = ativoInvestidor.obterMediaDeAportes()/ativoInvestidor.obterQuantidade();
            double valorAtualAtivo = hashMapClasseAtivoPorCodigoAtivo.get(ativoInvestidor.getAtivo().getCodigoAtivo()).getParceiroExterno().obterCotacao(ativoInvestidor);
            double rentabilidade = MotorCalculo.calculaRentabilidadeAtivo(valorMedioAportes, valorAtualAtivo);

            double valorTotalAtivo = ativoInvestidor.obterTotalDeAportes()*(1+rentabilidade/100);

            String nomeClasseAtivo = classeAtivos.get(ativoInvestidor.getAtivo().getCodigoAtivo()).getClasseAtivo().getNomeClasseAtivo();

            Double aportesClasse = aportesPorClasse.get(nomeClasseAtivo);
            aportesClasse += valorTotalAtivo;
            aportesPorClasse.put(nomeClasseAtivo, Arredondar.duasCasas(aportesClasse));
        };

        Double aporteTotal = 0.00;
        for (Double aporte : aportesPorClasse.values()) {
            aporteTotal += aporte;
        };

        // System.out.println(metaPorClasse);
        HashMap<String, Double> discrepanciaMetaClasse = new HashMap<>();
        for (Map.Entry<String, Double> entry : aportesPorClasse.entrySet()) {
            Double alocacaoAtual = Arredondar.duasCasas(entry.getValue()*100/aporteTotal);
            Double alocacaoMeta = metaPorClasse.get(entry.getKey());
            Double discrepanciaMeta = alocacaoMeta - alocacaoAtual;
            discrepanciaMetaClasse.put(entry.getKey(), discrepanciaMeta);
        }

        String classeRecomendada = "";
        Double discrepanciaMeta = 0.00;
        for (Map.Entry<String, Double> entry : discrepanciaMetaClasse.entrySet()) {
            if (entry.getValue() < -10.00) {
                System.out.println("Você possui uma quantidade muito elevada de ativos da classe '" + entry.getKey() + "', evitar aportar nestes ativos.");
            }
            if (entry.getValue() > discrepanciaMeta) {
                classeRecomendada = entry.getKey();
            }
        }
        System.out.println("Compre mais ativos da classe '" + classeRecomendada + "'");
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

        HashMapAtivoPorCodigoAtivo.put("BTC", bitcoinAtivo);
        HashMapAtivoPorCodigoAtivo.put("ETH", ethereumAtivo);
        HashMapAtivoPorCodigoAtivo.put("CDC", nftAtivo);
        HashMapAtivoPorCodigoAtivo.put("BRSTNCLF1RL5", selicAtivo);
    }
}