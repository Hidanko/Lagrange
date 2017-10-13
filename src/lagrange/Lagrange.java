/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lagrange;

import view.Tabela;

/**
 *
 * @author 6248489
 */
public class Lagrange {

    double x[];
    double f[];
    double[] cima;
    double[] baixo;
    double valor;
    double[] matriz;
    double resultadoPratico = 0;
    double resultadoNormal = 0;
    int size;
    Tabela tabela;

    public Lagrange(double[] x, double[] f, double valor, int size) {

        this.x = new double[size];
        this.f = new double[size];
        this.size = size;
        this.x = x;
        this.f = f;
        this.valor = valor;
    }

    public Lagrange() {
    }

    public void calculo() {
        cima = new double[size];
        baixo = new double[size];
        matriz = new double[size];
        System.out.println("X.length = " + x.length);
        for (int interacao = 0; interacao < x.length; interacao++) {
            cima[interacao] = 1.0;
            baixo[interacao] = 1.0;
            for (int i = 0; i < x.length; i++) {
                if (interacao != i) {
                    cima[interacao] *= valor - x[i];
                    baixo[interacao] *= x[(int) interacao] - x[i];
                }

            }
        }

        for (int i = 0; i < cima.length; i++) {
            matriz[i] = cima[i] / baixo[i];
            
        }
        resultadoNormal = resposta(matriz);
        tabela.imprimirResultados(resultadoNormal, resultadoPratico);
    }

    public double resposta(double[] resultado) {

        double soma = 0;
        for (int i = 0; i < x.length; i++) {
            soma += f[i] * resultado[i];
            
        }
        esquemaPratico();
        return soma;
    }

    @SuppressWarnings("empty-statement")
    public void esquemaPratico() {
        tabela = new Tabela();

        for (int i = 0; i < size; i++) {
            String linha = " ";
             double mult = 1;
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    linha = (linha+"(" + valor + " - " + x[j] + " )  \t");
                    mult *= (valor - x[j]);
                } else {
                    linha = (linha+"(" + x[i] + " - " + x[j] + " )  \t");
                    mult *= (x[i] - x[j]);
                }
            }
            System.out.println(linha);
            resultadoPratico += (f[i] / mult);
            String envio[] = {String.valueOf(i + 1), linha, String.valueOf(mult), String.valueOf(f[i]), String.valueOf(f[i] / mult)};
            tabela.incrementarLinha(envio, i);
        }

        tabela.setVisible(true);
    }
}
