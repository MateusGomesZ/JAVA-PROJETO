/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NovoProjeto;

import java.util.ArrayList;
import java.util.Scanner;

public class AplicacaoProdutos {

    private Produtos produtosList[] = new Produtos[9999];
    private int posicaoAtual = 0;

    public static void main(String[] args) {
        

        AplicacaoProdutos app = new AplicacaoProdutos();
        app.tituloMenu();
        app.telaPrincipal();
    }

    private void telaPrincipal() {
        int opcao = 0;
        do {
            opcao = opcaoMenuPadrao();
            switch (opcao) {
                case 1:
                    menuCadastroProduto();
                    break;
                case 2:
                    menuMovimentacao();
                    break;
                case 3:
                    relatorioDeProdutos();
                    break;
                case 0:
                    System.out.println("Saindo do sistema");
                    break;
                default:
                    opcaoInvalida();
                    break;
            }
        } while (opcao != 0);
    }

    private void relatorioDeProdutos() {
        this.tituloMenu();
        System.out.println("RELATÓRIO");
        for (int i = 0; i < posicaoAtual; i++) {
            System.out.println("\n");
            System.out.println("Produtos: \n" +
                    "CÓDIGO: " + i + "\n" +
                    produtosList[i]);

        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n\n");
        System.out.println("APERTE QUALQUER LETRA + ENTER PARA CONTINUAR");
        scanner.next();
    }


    private void menuMovimentacao() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ALTERAÇAO DOS PRODUTOS");
        System.out.println("1 - ENTRADA\n" +
                "2 - SAÍDA\n" +
                "0 - RETORNAR\n" +
                "OPÇÃO  : \n");
        int opcaoMovimentacao = scanner.nextInt();
        switch (opcaoMovimentacao) {
            case 1:
                compraProdutos();
                break;
            case 2:
                saidaMovimentacao();
                break;
            case 0:
                System.out.println("Retornando para o menu");
                break;
            default:
                opcaoInvalida();
                break;
        }
    }

    private void saidaMovimentacao() {
        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("CONSUMO DOS PRODUTOS");
            System.out.println("Nome do produto");
            String nomeProduto = scanner.nextLine();

            boolean controle = true;
            for (int i = 0; i < posicaoAtual; i++) {
                if (nomeProduto.equalsIgnoreCase(produtosList[i].getNome())) {
                    controle=false;
                    Produtos produtosMovimentacao = produtosList[i];
                    System.out.println("QTDE ATUAL : " + produtosMovimentacao.getQuantidade());
                    System.out.println("QTDE SAÍDA : ");
                    int quantidadeSaida = scanner.nextInt();
                    System.out.println("QTDE FINAL : " + (produtosMovimentacao.getQuantidade() - quantidadeSaida));
                    if (produtosMovimentacao.getQuantidade() < quantidadeSaida) {
                        System.out.println("Quantidade maior que no estoque, saída não é possível");
                        break;
                    }
                    escolha = confirmaOperacao();
                    if (escolha.equalsIgnoreCase("S")) {
                        produtosMovimentacao.setDiminuirQuantidade(quantidadeSaida);
                        produtosList[i] = produtosMovimentacao;
                    }
                    break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

    private void compraProdutos() {
        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("ADICIONAR PRODUTOS");
            System.out.println("Nome do produto");
            String nomeProduto = scanner.nextLine();
            Produtos  produtosMovimentacao;
            boolean controle=true;
            for (int i = 0; i < posicaoAtual; i++) {
                if (nomeProduto.equalsIgnoreCase(produtosList[i].getNome())) {
                    controle=false;
                    produtosMovimentacao = produtosList[i];
                    System.out.println("QTDE ATUAL : " + produtosMovimentacao.getQuantidade());
                    System.out.println("QTDE ENTRADA : ");
                    int quantidadeEntrada = scanner.nextInt();
                    System.out.println("QTDE FINAL : " + (produtosMovimentacao.getQuantidade() + quantidadeEntrada));
                    escolha = confirmaOperacao();
                    if (escolha.equalsIgnoreCase("S")) {
                        produtosMovimentacao.setAdicionarQuantidade(quantidadeEntrada);
                        produtosList[i] = produtosMovimentacao;
                    }
                    break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);

            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

    private void opcaoInvalida() {
        System.out.println("Opção inválida");
    }

    private int opcaoMenuPadrao() {
        int opcao;
        System.out.println("MENU PRINCIPAL\n" +
                "1 - CADASTRO DE PRODUTOS\n" +
                "2 - MOVIMENTAÇÃO\n" +
                "3 - RELATÓRIOS\n" +
                "0 - FINALIZAR\n" +
                "OPÇÃO  : _\n");
        opcao = getEscolhaMenu();
        return opcao;
    }

    private void menuCadastroProduto() {
        int opcao;
        System.out.println("1 - INCLUSÃO\n" +
                "2 - ALTERAÇÃO\n" +
                "3 - CONSULTA\n" +
                "4 - EXCLUSÃO\n" +
                "0 - RETORNAR\n");
        opcao = getEscolhaMenu();
        switch (opcao) {
            case 1:
                cadastrarProdutos();
                break;
            case 2:
                alterarProduto();
                break;
            case 3:
                consultarProduto();
                break;
            case 4:
                excluirProduto();
                break;
            default:
                opcaoInvalida();
                break;
        }
    }

    private void excluirProduto() {

        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            this.tituloMenu();
            System.out.println("EXCLUSÃO DE PRODUTOS");
            System.out.println("Informe o nome do produto para pesquisa");
            String nomeConsulta = scanner.nextLine();
            boolean controle = true;
            ArrayList <Produtos> arrayList = new ArrayList();

            for (int i = 0; i < posicaoAtual; i++) {
                scanner = new Scanner(System.in);

                if (nomeConsulta.equalsIgnoreCase(produtosList[i].getNome())) {
                    controle=false;
                    System.out.println(produtosList[i].toString());
                    System.out.println("CONFIRMAR EXCLUSÃO ( S/N ) ?");
                    escolha = scanner.next();
                    if (escolha.equalsIgnoreCase("S")) {
                        for (int j = i; j < posicaoAtual - 1; j++){
                            produtosList [j] = produtosList [j + i];
                            posicaoAtual--;
                        }
                     
                    }
                    break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

    private void consultarProduto() {

        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            this.tituloMenu();
            System.out.println("CONSULTA DE PRODUTOS");
            System.out.println("Informe o nome do produto para pesquisa");
            String nomeConsulta = scanner.nextLine();
            boolean controle=true;
            for (int i = 0; i < posicaoAtual; i++) {
                if (nomeConsulta.equalsIgnoreCase(produtosList[i].getNome())) {
                    controle=false;
                    System.out.println(produtosList[i].toString());
                    break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

    private void alterarProduto() {
        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            this.tituloMenu();
            System.out.println("REAJUSTE DO PRODUTO");
            System.out.println("Informe o nome do produto para alterar");
            String nomeConsulta = scanner.nextLine();
            boolean controle=true;
            for (int i = 0; i < posicaoAtual; i++) {

                if (nomeConsulta.equalsIgnoreCase(produtosList[i].getNome())) {
                    controle=false;
                    System.out.println("PRODUTO ENCONTRADO\n");
                    Produtos produtos = setDadosDoProduto();
                    escolha = confirmaOperacao();
                    if (escolha.equalsIgnoreCase("S")) {
                        produtosList[i] = produtos;
                    }
                    break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

    private void mensagemConsultaNaoEncontrada(boolean controle) {
        if (controle) {
            System.out.println("Produtos não encontrado");
        }
    }

    private void cadastrarProdutos() {
        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            this.tituloMenu();
            System.out.println("CADASTRO DE PRODUTOS");
            Produtos produtos = setDadosDoProduto();
            escolha = confirmaOperacao();
            if (escolha.equalsIgnoreCase("S")) {
                produtosList[posicaoAtual] = produtos;
                posicaoAtual++;
             
                
            }
            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

    private Produtos setDadosDoProduto() {
       
        String escolha;
       
        do {
        Scanner scanner = new Scanner(System.in);
     
        System.out.println("Informe o nome do produto"  );
         String nome = scanner.nextLine();
        
       
            boolean controle=true;
            for (int i = 0; i < posicaoAtual; i++) {
             
                if (nome.equalsIgnoreCase(produtosList[i].getNome())) {
                    controle=false;
                    System.out.println("PRODUTO JA CADASTRADO\n");
                    System.out.println("Cadastre um novo Produto");
                
                    return setDadosDoProduto();
          
                }
            }               
              
        System.out.println("Informe a unidade de medida");
        scanner = new Scanner(System.in);
        String unidade = scanner.nextLine();
        System.out.println("Preço do Produto: ");
        scanner = new Scanner (System.in);
        int preco = scanner.nextInt();
        System.out.println("Informe a quantidade");
        int quantidade = scanner.nextInt();
        
         
        
        Produtos produtos = new Produtos();
        produtos.setNome(nome);
        produtos.setUnidade(unidade);
        produtos.setQuantidade(quantidade);
        produtos.setPreco(preco);
        return produtos;
    
         } while (escolha.equalsIgnoreCase("S"));
    }
    
    private String getRepetirOperacao() {
        Scanner scanner = new Scanner(System.in);
        String escolha;
        System.out.println("REPETIR OPERAÇÃO ( S/N ) ? ");
        escolha = scanner.next();
        return escolha;
    }

    private String confirmaOperacao() {
        Scanner scanner = new Scanner(System.in);
        String escolha;
        System.out.println("CONFIRMA OPERAÇÃO ( S/N ) ?");
        escolha = scanner.next();
        return escolha;
    }

    private int getEscolhaMenu() {
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.next());
    }

    private void tituloMenu() {
        System.out.println("CONTROLE DE ESTOQUE.\n");
    }
}