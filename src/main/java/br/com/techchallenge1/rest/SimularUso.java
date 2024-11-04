package src.main.java.br.com.techchallenge1.rest;

import java.util.HashMap;

import src.main.java.br.com.techchallenge1.model.AporteAtivo;
import src.main.java.br.com.techchallenge1.model.Ativo;
import src.main.java.br.com.techchallenge1.model.CategoriaAtivo;
import src.main.java.br.com.techchallenge1.model.ClasseAtivo;
import src.main.java.br.com.techchallenge1.model.ClasseAtivoInvestidor;
import src.main.java.br.com.techchallenge1.model.Investidor;
import src.main.java.br.com.techchallenge1.model.ParceiroExterno;

public class SimularUso {
    public static HashMap<String, ClasseAtivo> HashMapClasseAtivo = new HashMap<>();
    
    public static void main(String[] args) {
        configuracoesAdministrador();

        // Cadastrar usuário
        Investidor investidor = new Investidor("Nome Teste", "email@teste.com.br", "teste", "senha");

        // Usuário: Classe Ativo -> Seleciona classe e define meta
        ClasseAtivo classeAtivoCripto = HashMapClasseAtivo.get("Criptoativos");
        float metaDeAlocacaoCripto = 10;
        ClasseAtivo classeAtivoRendaFixa = HashMapClasseAtivo.get("Renda Fixa");
        float metaDeAlocacaoRendaFixa = 90;

        // Sistema: Classe Ativo -> Vincula classe de ativo e uma meta ao investidor
        ClasseAtivoInvestidor classeAtivoInvestidorCripto = new ClasseAtivoInvestidor(metaDeAlocacaoCripto, classeAtivoCripto);
        ClasseAtivoInvestidor classeAtivoInvestidorRendaFixa = new ClasseAtivoInvestidor(metaDeAlocacaoRendaFixa, classeAtivoRendaFixa);
        investidor.adicionarClasseAtivoInvestidor(classeAtivoInvestidorCripto);
        investidor.adicionarClasseAtivoInvestidor(classeAtivoInvestidorRendaFixa);

        // Usuário: Categoria Ativo -> Criar categoria de ativo
        CategoriaAtivo categoriaAtivoA = new CategoriaAtivo("Moedas seguras", "Moedas já estabelecidas e com bom histórico");
        CategoriaAtivo categoriaAtivoB = new CategoriaAtivo("NFT alto risco", "Projetos novos NFT");
        CategoriaAtivo categoriaAtivoC = new CategoriaAtivo("Tesouro Direto", "Renda fixa seguro");

        // Usuário: Definir classe de ativo da categoria -> MUDANÇA: Classe está da categoria em vez dos ativos
        classeAtivoInvestidorCripto.adicionarCategoriaAtivo(categoriaAtivoA);
        classeAtivoInvestidorCripto.adicionarCategoriaAtivo(categoriaAtivoB);
        classeAtivoInvestidorRendaFixa.adicionarCategoriaAtivo(categoriaAtivoC);

        // Usuário: Ativo -> Preencher ativos -> MUDANÇA: Primeiro preencheu classe da categoria, depois os ativos
        // DÚVIDA: Ativo precisa ser pré definido no sistema? O cliente só seleciona? Porque o sistema precisa de alguma forma ver o valor do ativo.
        Ativo bitcoin = new Ativo("Bitcoin", "Bitcoin");
        Ativo ethereum = new Ativo("Ethereum", "Ethereum");
        Ativo nft = new Ativo("CapivaraDeChapeu", "NFTs capivaraDeChapeu");
        Ativo selic = new Ativo("Selic", "Tesouro Selic");

        // Usuário: Preencher quantidades de posse dos ativos
        AporteAtivo aporteBitcoin = new AporteAtivo(0.5); 
        AporteAtivo aporteEthereum = new AporteAtivo(0.2); 
        AporteAtivo aporteNft = new AporteAtivo(100); 
        AporteAtivo aporteSelic = new AporteAtivo(1); 

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

        // System.out.println(investidor.getHashMapClasseAtivoInvestidor().get("Criptoativos").getHashMapCategoriaAtivo().get("Moedas seguras").getHashMapAtivos());

    }
                    
    // Configurações iniciais pré definidas do sistema. O investidor não participa desse processo. 
    public static void configuracoesAdministrador() {
        ParceiroExterno parceiroRendaFixa = new ParceiroExterno("Parceiro Renda Fixa", "url1");
        ParceiroExterno parceiroCripto = new ParceiroExterno("Parceiro Cripto", "url2");
        ParceiroExterno parceiroBolsa = new ParceiroExterno("Parceiro Bolsa", "url3");

        ClasseAtivo ativosRendaFixa = new ClasseAtivo("Renda Fixa", "Investimentos renda fixa", parceiroRendaFixa);
        ClasseAtivo ativosCripto = new ClasseAtivo("Criptoativos", "Investimentos criptoativos", parceiroCripto);
        ClasseAtivo ativosAcoes = new ClasseAtivo("Ações", "Investimentos Ações", parceiroBolsa);

        HashMapClasseAtivo.put(ativosRendaFixa.getNomeClasseAtivo(), ativosRendaFixa);
        HashMapClasseAtivo.put(ativosCripto.getNomeClasseAtivo(), ativosCripto);
        HashMapClasseAtivo.put(ativosAcoes.getNomeClasseAtivo(), ativosAcoes);
    }
}