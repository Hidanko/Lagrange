/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lagrange;

/**
 *
 * @author 6248489
 */
public class Lagrange {

    double x[];
    double f[];
    double[] cima = {0};
    double[] baixo = {0};
    double lagrange;
    double[] matriz;

    public Lagrange(double[] x, double[] f, double lagrange) {
        this.x = x;
        this.f = f;
        this.lagrange = lagrange;
    }

    public double calculo() {
            System.out.println("X.length = "+x.length);
        for (double interacao : x) {
            cima[(int) interacao] = 1;
            baixo[(int)interacao] = 1;
            for (int i = 0; i < x.length; i++) {
                if (interacao != i) {
                    cima[(int) interacao] *= lagrange - x[i];
                    baixo[(int) interacao] *= x[(int) interacao] - x[i];
                }

            }
        }

        for (int i = 0; i < cima.length; i++) {
            matriz[i] = cima[i] / baixo[i];
        }

        return resposta(matriz);
    }

    private double resposta(double[] resultado) {

        double soma = 0;
        for (double d : resultado) {
            soma += f[(int)d] * ( resultado[(int) d] / x[(int) d]);
        }

        return soma;
    }

}
