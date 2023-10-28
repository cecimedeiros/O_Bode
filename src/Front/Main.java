package Front;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Facade f = new Facade();
        Scanner sc = new Scanner(System.in);
        boolean parada = false;
        boolean parada2 = false;
        boolean parada3 = false;
        boolean parada4 = false;

        System.out.println("Bem vindo ao Bode!");

        do{

            System.out.println("Selecione como deseja acessar o sistema:\n1.Cliente\n2.Funcionário");
            int e = Integer.parseInt(sc.nextLine());

            if (e == 1){ //Cliente

                do {
                    System.out.println("Menu cliente");
                    System.out.println("1.Iniciar pedido\n2.Voltar");
                    int e2 = Integer.parseInt(sc.nextLine());

                    if (e2 == 1) { //Pedido

                        System.out.println("Informe o nome do cliente:");
                        String nome = sc.nextLine();
                        f.criaCliente(nome);

                        System.out.println(f.mostraCardapio());

                        System.out.println("Informe o código do item que deseja pedir:");
                        int codPedido = Integer.parseInt(sc.nextLine());
                        f.adicionaItemAoPedido(codPedido);

                        do {
                            System.out.println("Deseja adicionar mais itens?\n1.Sim\n2.Finalizar pedido\n3.Remoção de item\n4.Cancelar pedido");
                            int e3 = Integer.parseInt(sc.nextLine());

                            if (e3 == 1){
                                System.out.println("Informe o código do item que deseja pedir:");
                                codPedido = Integer.parseInt(sc.nextLine());
                                f.adicionaItemAoPedido(codPedido);

                            } else if (e3 == 2){
                                f.mostraPedidoFinal();
                                System.out.println("Confirmar finalização do pedido?\n1.sim\nQualquer outro numero para voltar");
                                int conf = Integer.parseInt(sc.nextLine());

                                if (conf == 1){
                                    f.finalizaMontagemPedido();
                                    System.out.println("Pedido finalizado com sucesso!");
                                    parada2 = true;
                                }

                            } else if (e3 == 3){
                                f.mostraItensNoPedido();
                                System.out.println("Informe o código do item que deseja remover:");
                                codPedido = Integer.parseInt(sc.nextLine());
                                System.out.println("Confirmar remoção do item?\n1.sim\nQualquer outro numero para voltar");
                                int conf = Integer.parseInt(sc.nextLine());

                                if (conf == 1){
                                    f.removeItemDoPedido(codPedido);
                                }

                            } else if (e3 == 4){
                                System.out.println("Confirmar cancelamento do pedido?\n1.sim\nQualquer outro numero para voltar");
                                int conf = Integer.parseInt(sc.nextLine());

                                if (conf == 1){
                                    f.cancelaPedido();
                                    System.out.println("Pedido cancelado com sucesso!");
                                    parada2 = true;
                                }

                            } else {
                                System.out.println("Opção inválida. Tente novamente!");
                            }

                        } while (!parada3);


                    } else if (e2 == 2) { //Voltar
                        parada2 = true;
                        System.out.println();
                    } else { //erro
                        System.out.println("Opção inválida. Tente novamente!");
                        System.out.println();
                    }
                } while (!parada2);

            } else if (e == 2){ //Funcionário

                System.out.println("Insira a senha de acesso:");
                int senha = Integer.parseInt(sc.nextLine());

                if (f.verificaSenha(senha)){

                    do {
                        System.out.println("Menu funcionário");
                        System.out.println("1.Realizar serviços\n2.Solicitar relatório\n3.Encerrar atendimento do dia\n4.Voltar");
                        int o = Integer.parseInt(sc.nextLine());

                        if (o == 1) {

                        } else if (o == 2) {

                        } else if (o == 3) {

                        } else if (o == 4) {
                            parada4 = true;
                        } else {
                            System.out.println("Opção inválida. Tente novamente!");
                            System.out.println();
                        }
                    } while (!parada4);

                } else {
                    System.out.println("Senha incorreta");
                }

            } else { //erro
                System.out.println("Opção inválida. Tente novamente!");
                System.out.println();
            }

        } while (!parada);

        //fechar o scanner no finally
        
    }
}