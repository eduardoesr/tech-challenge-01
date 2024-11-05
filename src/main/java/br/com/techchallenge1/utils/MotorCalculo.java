package src.main.java.br.com.techchallenge1.utils;

public class MotorCalculo {

    public static double calculaRentabilidadeAtivo(double valorMedioAportes, double valorPresente) {
        return Arredondar.duasCasas(((valorPresente - valorMedioAportes) / valorMedioAportes) * 100);
    }
}
