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
    double[] cima;
    double[] baixo;
    double lagrange;
    double[] matriz;
    int size;
    
    public Lagrange(double[] x, double[] f, double lagrange, int size) {
        
        this.x = new double[size];
        this.f = new double[size];
        this.size = size;
        this.x = x;
        this.f = f;
        this.lagrange = lagrange;
    }

    public Lagrange() {
    }

    public double calculo() {
        cima = new double[size];
        baixo = new double[size];
        matriz = new double[size];
            System.out.println("X.length = "+x.length);
        for (int interacao = 0; interacao < x.length; interacao++) {
            cima[interacao] = 1.0;
            baixo[interacao] = 1.0;
            for (int i = 0; i < x.length; i++) {
                if (interacao != i) {
                    cima[interacao] *= lagrange - x[i];
                    baixo[interacao] *= x[(int) interacao] - x[i];
                }

            }
        }

        for (int i = 0; i < cima.length; i++) {
            matriz[i] = cima[i] / baixo[i];
            System.out.println("Matriz["+i+"] = "+matriz[i]);
        }

        return resposta(matriz);
    }

    public double resposta(double[] resultado) {

        double soma = 0;
        for (int i = 0; i < x.length; i++) {
            soma += f[i] *resultado[i];
            System.out.println(i+" - "+soma);
        }

        return soma;
    }

}
