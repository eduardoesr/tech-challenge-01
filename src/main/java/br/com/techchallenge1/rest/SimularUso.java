package src.main.java.br.com.techchallenge1.rest;

import java.time.LocalDateTime;
import java.util.HashMap;

import src.main.java.br.com.techchallenge1.model.AporteAtivoInvestidor;
import src.main.java.br.com.techchallenge1.model.Ativo;
import src.main.java.br.com.techchallenge1.model.CategoriaAtivoInvestidor;
import src.main.java.br.com.techchallenge1.model.ClasseAtivo;
import src.main.java.br.com.techchallenge1.model.ClasseAtivoInvestidor;
import src.main.java.br.com.techchallenge1.model.Investidor;
import src.main.java.br.com.techchallenge1.model.ParceiroExterno;

public class SimularUso {
    public static HashMap<String, ClasseAtivo> hashMapClasseAtivoPorNomeClasse = new HashMap<>(); // Obter classe pelo nome da classe
    public static HashMap<String, ClasseAtivo> HashMapClasseAtivoPorCodigoAtivo = new HashMap<>(); // Obter classe pelo código do ativo
    
    public static void main(String[] args) {
        configuracoesAdministrador();

        // Cadastrar usuário
        Investidor investidor = new Investidor("Nome Teste", "email@teste.com.br", "teste", "senha");




        // Contexto 1

        // Usuário: Classe Ativo -> Seleciona classe e define meta
        ClasseAtivo classeAtivoCripto = hashMapClasseAtivoPorNomeClasse.get("Criptoativos");
        float metaDeAlocacaoCripto = 10;
        ClasseAtivo classeAtivoRendaFixa = hashMapClasseAtivoPorNomeClasse.get("Renda Fixa");
        float metaDeAlocacaoRendaFixa = 90;

        // Sistema: Classe Ativo -> Vincula classe de ativo e uma meta ao investidor
        ClasseAtivoInvestidor classeAtivoInvestidorCripto = new ClasseAtivoInvestidor(metaDeAlocacaoCripto, classeAtivoCripto);
        ClasseAtivoInvestidor classeAtivoInvestidorRendaFixa = new ClasseAtivoInvestidor(metaDeAlocacaoRendaFixa, classeAtivoRendaFixa);
        investidor.adicionarClasseAtivoInvestidor(classeAtivoInvestidorCripto);
        investidor.adicionarClasseAtivoInvestidor(classeAtivoInvestidorRendaFixa);

        // Usuário: Categoria Ativo -> Criar categoria de ativo
        CategoriaAtivoInvestidor categoriaAtivoA = new CategoriaAtivoInvestidor("Moedas seguras", "Moedas já estabelecidas e com bom histórico");
        CategoriaAtivoInvestidor categoriaAtivoB = new CategoriaAtivoInvestidor("NFT alto risco", "Projetos novos NFT");
        CategoriaAtivoInvestidor categoriaAtivoC = new CategoriaAtivoInvestidor("Tesouro Direto", "Renda fixa seguro");

        // Usuário: Definir classe de ativo da categoria -> MUDANÇA: Classe está da categoria em vez dos ativos
        classeAtivoInvestidorCripto.adicionarCategoriaAtivoInvestidor(categoriaAtivoA);
        classeAtivoInvestidorCripto.adicionarCategoriaAtivoInvestidor(categoriaAtivoB);
        classeAtivoInvestidorRendaFixa.adicionarCategoriaAtivoInvestidor(categoriaAtivoC);

        // Usuário: Ativo -> Preencher ativos -> MUDANÇA: Primeiro preencheu classe da categoria, depois os ativos
        // DÚVIDA: Ativo precisa ser pré definido no sistema? O cliente só seleciona? Porque o sistema precisa de alguma forma ver o valor do ativo.
        Ativo bitcoin = new Ativo("Bitcoin", "Bitcoin", "BTC");
        Ativo ethereum = new Ativo("Ethereum", "Ethereum", "ETH");
        Ativo nft = new Ativo("CapivaraDeChapeu", "NFTs capivaraDeChapeu", "CDC");
        Ativo selic = new Ativo("Selic", "Tesouro Selic", "POSFIXADO");

        // Usuário: Preencher quantidades de posse dos ativos
        AporteAtivoInvestidor aporteBitcoin = new AporteAtivoInvestidor(0.5, 60000, LocalDateTime.now()); 
        AporteAtivoInvestidor aporteEthereum = new AporteAtivoInvestidor(0.2, 14000, LocalDateTime.now()); 
        AporteAtivoInvestidor aporteNft = new AporteAtivoInvestidor(100, 500, LocalDateTime.now()); 
        AporteAtivoInvestidor aporteSelic = new AporteAtivoInvestidor(1, 25000, LocalDateTime.now()); 

        // Sistema: Associar aporte aos ativos
        bitcoin.adicionarAporteAtivo(aporteBitcoin);
        ethereum.adicionarAporteAtivo(aporteEthereum);
        nft.adicionarAporteAtivo(aporteNft);
        selic.adicionarAporteAtivo(aporteSelic);

        // Usuário: Preencher categoria dos ativos
        categoriaAtivoA.adicionarAtivo(bitcoin);
        categoriaAtivoA.adicionarAtivo(ethereum);
        categoriaAtivoB.adicionarAtivo(nft);
        categoriaAtivoC.adicionarAtivo(selic);



        // Contexto 2




        // Sistema: Buscar ativos do investidor
        HashMap<String, ClasseAtivoInvestidor> ativosInvestidor = investidor.getHashMapClasseAtivoInvestidor();



        // System.out.println(investidor.getHashMapClasseAtivoInvestidor().get("Criptoativos").getHashMapCategoriaAtivo().get("Moedas seguras").getHashMapAtivos());

    }
                    
    // Configurações iniciais pré definidas do sistema. O investidor não participa desse processo. 
    public static void configuracoesAdministrador() {
        ParceiroExterno parceiroRendaFixa = new ParceiroExterno("Parceiro Renda Fixa", "url1");
        ParceiroExterno parceiroCripto = new ParceiroExterno("Parceiro Cripto", "url2");
        ParceiroExterno parceiroBolsa = new ParceiroExterno("Parceiro Bolsa", "url3");

        ClasseAtivo ativosRendaFixa = new ClasseAtivo("Renda Fixa", "Investimentos renda fixa", parceiroRendaFixa);
        ClasseAtivo ativosCripto = new ClasseAtivo("Criptoativos", "Investimentos criptoativos", parceiroCripto);
        ClasseAtivo ativosAcoes = new ClasseAtivo("B3", "Investimentos Ações", parceiroBolsa);

        hashMapClasseAtivoPorNomeClasse.put(ativosRendaFixa.getNomeClasseAtivo(), ativosRendaFixa);
        hashMapClasseAtivoPorNomeClasse.put(ativosCripto.getNomeClasseAtivo(), ativosCripto);
        hashMapClasseAtivoPorNomeClasse.put(ativosAcoes.getNomeClasseAtivo(), ativosAcoes);

        // HashMapClasseAtivoPorCodigoAtivo.put("BTC", ativosCripto)
        // HashMapClasseAtivoPorCodigoAtivo.put("ETH")
        // HashMapClasseAtivoPorCodigoAtivo.put("CDC")
        // HashMapClasseAtivoPorCodigoAtivo.put("BTC")
    }
}