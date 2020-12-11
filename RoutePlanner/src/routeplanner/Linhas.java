/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routeplanner;

/**
 *
 * @author esdra
 */
public class Linhas {
    private String linha;
    private String estacao;

    public Linhas(String linha, String estacao) {
        this.linha = linha;
        this.estacao = estacao;
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public String getEstacao() {
        return estacao;
    }

    public void setEstacao(String estacao) {
        this.estacao = estacao;
    }
}
