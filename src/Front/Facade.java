package Front;

import Back.Cliente;
import Back.Loja;
import Back.Pedido;

public class Facade {

    Loja l = new Loja();
    Pedido p;

    public void criaCliente(String nome){
        p = new Pedido(new Cliente(nome));
    }

    public String mostraCardapio(){
        return l.mostraCardapio();
    }

    public void adicionaItemAoPedido(int i){
        p.addItem(i);
    }

    public void cancelaPedido(){
        p.cancelarPedido(l);
    }

    public String mostraItensNoPedido(){
        return p.toString();
    }

    public void removeItemDoPedido(int i){
        p.removerItem(i);
    }

    public String mostraPedidoFinal(){
        return p.pedidoFinal();
    }

    public void finalizaMontagemPedido(){
        p.finalizaMontagemDoPedido(l);
    }

    public boolean verificaSenha(int senha){
        return l.verificaSenha(senha);
    }

}
