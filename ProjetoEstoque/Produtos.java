/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NovoProjeto;

/**
 *
 * @author mateu
 */
public class Produtos {
    private String nome;
    private String unidade;
    private int quantidade;
    private int preco;
    
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the unidade
     */
    public String getUnidade() {
        return unidade;
    }

    /**
     * @param unidade the unidade to set
     */
    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    @Override
     public String toString (){
         return "Nome: " + nome +"\n" +
         "Unidade: " + unidade + "\n" +
         "Quantidade: " + quantidade + "\n" +
          "Preco R$:" + preco;
                  }
    /**
     * @return the preco
     */
    public int getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(int preco) {
        this.preco = preco;
    }

    void setDiminuirQuantidade(int quantidadeSaida) {
        this.quantidade -= quantidadeSaida;


    
    }

    void setAdicionarQuantidade(int quantidadeEntrada) {
        this.quantidade += quantidadeEntrada;
    }
}

