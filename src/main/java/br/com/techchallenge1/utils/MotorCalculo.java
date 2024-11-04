package src.main.java.br.com.techchallenge1.utils;

public class MotorCalculo {
    public double calculaRentabilidadeAtivo(double valorMedioAportes, double valorPresente) {
        return ((valorPresente - valorMedioAportes) / valorMedioAportes) * 100;
    }
}
