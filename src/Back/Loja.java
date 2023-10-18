package Back;

import java.util.ArrayList;

public class Loja {

    private static Integer SENHA = 0000;
    private ArrayList <Pedido> pedidosFinalizados = new ArrayList<>();
    private ArrayList <Pedido> pedidosAguardandoPrep = new ArrayList<>();
    private ArrayList <Pedido> pedidosEmPrep = new ArrayList<>();

    public String mostraFinalizados(){
        String str = "";

        for (int i = 0; i < pedidosFinalizados.size(); i++) {

            Pedido p = pedidosFinalizados.get(i);
            str += "Pedido Nº " + i + 1 + "\nCliente: " + p.getCliente() + "\n" + p.toString() + "\n";

        }

        return str;
    }

    public String mostraAguardandoPrep(){
        String str = "";

        for (int i = 0; i < pedidosAguardandoPrep.size(); i++) {

            Pedido p = pedidosAguardandoPrep.get(i);
            str += "Prioridade: " + i + 1 + "\n" + p.toString() + "\n";

        }

        return str;
    }

    public String mostraEmPrep(){
        String str = "Ordem:\n";

        for (int i = 0; i < pedidosEmPrep.size(); i++) {

            Pedido p = pedidosEmPrep.get(i);
            str += i + 1 + "\n" + p.toString() + "\n";

        }

        return str;
    }

    public void preparar(int i){
        Pedido p = pedidosAguardandoPrep.get(i - 1);
        p.prepararPedido();
        pedidosAguardandoPrep.remove(p);
        pedidosEmPrep.add(p);
    }

    public void entregar(int i){
        Pedido p = pedidosEmPrep.get(i - 1);
        p.finalizaPedido();
        pedidosEmPrep.remove(p);
        pedidosFinalizados.add(p);
    }

    /*
    public long tempoMedio(){

    }
    */

    //a porra dos cancelados tem q ir pros finalizados *emoji de palhaço*

}
