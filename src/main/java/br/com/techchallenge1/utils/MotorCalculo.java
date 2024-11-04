package src.main.java.br.com.techchallenge1.utils;

public class MotorCalculo {

    public static double calculaRentabilidadeAtivo(double valorMedioAportes, double valorPresente) {
        double rentabilidade = ((valorPresente - valorMedioAportes) / valorMedioAportes) * 100;
        return Math.round(rentabilidade * 100.0)/100.0;
    }
}
