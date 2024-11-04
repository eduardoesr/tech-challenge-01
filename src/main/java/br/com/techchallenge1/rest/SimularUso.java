package src.main.java.br.com.techchallenge1.rest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import src.main.java.br.com.techchallenge1.model.AporteAtivoInvestidor;
import src.main.java.br.com.techchallenge1.model.Ativo;
import src.main.java.br.com.techchallenge1.model.AtivoInvestidor;
import src.main.java.br.com.techchallenge1.model.CategoriaAtivoInvestidor;
import src.main.java.br.com.techchallenge1.model.ClasseAtivo;
import src.main.java.br.com.techchallenge1.model.ClasseAtivoInvestidor;
import src.main.java.br.com.techchallenge1.model.Investidor;
import src.main.java.br.com.techchallenge1.model.ParceiroExterno;

public class SimularUso {
    public static HashMap<String, ClasseAtivo> hashMapClasseAtivoPorNomeClasse = new HashMap<>(); // Obter classe pelo nome da classe
    public static HashMap<String, ClasseAtivo> hashMapClasseAtivoPorCodigoAtivo = new HashMap<>(); // Obter classe pelo código do ativo
    public static HashMap<String, Ativo> HashMapAtivoPorCodigoAtivo = new HashMap<>(); // Obter ativo pelo código do ativo
    
    public static void main(String[] args) {
        configuracoesAdministrador();

        // Cadastrar usuário
        Investidor investidor = new Investidor("Nome Teste", "email@teste.com.br", "teste", "senha");

        // Contexto 1

        // Usuário: Classe Ativo -> Seleciona classe e define meta
        // Sistema: Classe Ativo -> Vincula classe de ativo e uma meta ao investidor
        ClasseAtivoInvestidor classeAtivoInvestidorCripto = new ClasseAtivoInvestidor(10, hashMapClasseAtivoPorNomeClasse.get("Criptoativos"));
        ClasseAtivoInvestidor classeAtivoInvestidorRendaFixa = new ClasseAtivoInvestidor(70, hashMapClasseAtivoPorNomeClasse.get("Renda Fixa"));
        ClasseAtivoInvestidor classeAtivoInvestidorBolsa = new ClasseAtivoInvestidor(20, hashMapClasseAtivoPorNomeClasse.get("Ações"));
        
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
                new AporteAtivoInvestidor(0.5, 200000, LocalDateTime.now())
            )
        );
        categoriaAtivoB.adicionarAtivo(
            new AtivoInvestidor(
                HashMapAtivoPorCodigoAtivo.get("ETH"),
                new AporteAtivoInvestidor(0.2, 14000, LocalDateTime.now())
            )
        );
        categoriaAtivoC.adicionarAtivo(
            new AtivoInvestidor(
                HashMapAtivoPorCodigoAtivo.get("CDC"),
                new AporteAtivoInvestidor(1, 500, LocalDateTime.now())
            )
        );
        categoriaAtivoD.adicionarAtivo(
            new AtivoInvestidor(
                HashMapAtivoPorCodigoAtivo.get("BRSTNCLF1RL5"),
                new AporteAtivoInvestidor(1, 15489.85, LocalDateTime.now())
            )
        );

        // Contexto 2

        ArrayList<AtivoInvestidor> listaAtivosInvestidor = investidor.obterAtivosInvestidor();
        for (AtivoInvestidor ativoInvestidor : listaAtivosInvestidor) {
            double valorMedioAportes = ativoInvestidor.obterMediaDeAportes();
            double valorAtualAtivo = hashMapClasseAtivoPorCodigoAtivo.get(ativoInvestidor.getAtivo().getCodigoAtivo()).getParceiroExterno().obterCotacao(ativoInvestidor.getAtivo().getCodigoAtivo());
        };

    }
                    
    // Configurações iniciais pré definidas do sistema. O investidor não participa desse processo. 
    public static void configuracoesAdministrador() {
        ParceiroExterno parceiroRendaFixa = new ParceiroExterno("Parceiro Renda Fixa", "url1");
        ParceiroExterno parceiroCripto = new ParceiroExterno("Parceiro Cripto", "url2");
        ParceiroExterno parceiroBolsa = new ParceiroExterno("Parceiro Bolsa", "url3");

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
        Ativo bitcoinAtivo = new Ativo("Bitcoin", "BTC");
        Ativo ethereumAtivo = new Ativo("Ethereum",  "ETH");
        Ativo nftAtivo = new Ativo("CapivaraDeChapeu", "CDC");
        Ativo selicAtivo = new Ativo("Tesouro Selic 2027",  "BRSTNCLF1RL5");

        HashMapAtivoPorCodigoAtivo.put("BTC", bitcoinAtivo);
        HashMapAtivoPorCodigoAtivo.put("ETH", ethereumAtivo);
        HashMapAtivoPorCodigoAtivo.put("CDC", nftAtivo);
        HashMapAtivoPorCodigoAtivo.put("BRSTNCLF1RL5", selicAtivo);
    }
}