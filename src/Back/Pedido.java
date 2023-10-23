package Back;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Pedido{

    DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm");
    private ArrayList <Produto> pedidos = new ArrayList<>();
    private LocalDateTime dataInicioPedido;
    private String dataInicioPedidoStr;
    private LocalDateTime dataFimPedido;
    private String dataFimPedidoStr;
    private Cliente cliente;
    private Status status;
    private CupomIF cupom;
    private Double valor;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.dataInicioPedido = LocalDateTime.now();
        this.dataInicioPedidoStr = LocalDateTime.now().format(f);
        this.status = Status.AGUARDANDO_PREPARO;
    }

    public String getCliente(){
        return cliente.getNome();
    }

    public void setCupom(){
        if (contaItens() == 4){
            this.cupom = new CupomPague3ELeve4Bode();
        } else if (contaItens() >= 5){
            this.cupom = new CupomItemMaisBaratoGratis();
        } else {
            this.cupom = new CupomSemDesconto();
        }
    }

    public void setValor(Double valor){
        this.valor = valor;
    }

    public Double getValor(){
        cupom.setDesconto(this);
        return valor;
    }

    public void addSuco(){
        pedidos.add(new Produto("Suco de laranja da fruta", 4.0));
    }

    public void addSalFrutas(){
        pedidos.add(new Produto("Salada de frutas", 8.0));
    }

    public void addTorrada(){
        pedidos.add(new Produto("Torrada de pão de forma", 3.0));
    }

    public void addQueijo(){
        pedidos.add(new Produto("Porção de queijo", 10.0));
    }

    public void addBatata(){
        pedidos.add(new Produto("Batata Frita", 12.0));
    }

    public void addBatataSuco(){
        pedidos.add(new Produto("Batata frita com suco", 14.0));
    }

    public void addTorradaQueijoSuco(){
        pedidos.add(new Produto("Torrada com queijo e suco", 15.0));
    }

    public void addTorradaSuco(){
        pedidos.add(new Produto("Torrada com suco", 5.0));
    }

    public void addBatataQueijo(){
        pedidos.add(new Produto("Batata com queijo derretido", 20.0));
    }

    public void cancelarPedido(){
        if (status == Status.AGUARDANDO_PREPARO){
            this.status = Status.CANCELADO_PELO_CLIENTE;
            this.dataFimPedido = LocalDateTime.now();
            this.dataFimPedidoStr = LocalDateTime.now().format(f);
        }
    } // lembrar de adc aos finalizados na facade

    public void prepararPedido(){
        if (status == Status.AGUARDANDO_PREPARO){
            status = Status.EM_PREPARO;
        }
    } // lembrar de adc aos em andamento na facade

    public void finalizaPedido(){
        if (status == Status.EM_PREPARO){
            this.status = Status.ENTREGUE;
            this.dataFimPedido = LocalDateTime.now();
            this.dataFimPedidoStr = LocalDateTime.now().format(f);
        }
    } // lembrar de adc aos finalizados na facade

    public void removerItem(int i){
        pedidos.remove(i - 1);
    }

    public Double calculaPrecoBruto(){
        Double pb = 0.0;

        for (int i = 0; i < pedidos.size(); i++) {

            Produto p = pedidos.get(i);
            pb += p.getPreco();

        }

        return pb;
    }

    public Integer contaItens(){
        return pedidos.size() + 1;
    }

    public Produto maisBarato(){
        Produto mb = pedidos.get(0);

        for (int i = 0; i < pedidos.size(); i++) {
            Produto p = pedidos.get(i);
            if (p.getPreco() < mb.getPreco()){
                mb = p;
            }
        }

        return mb;
    }

    @Override
    public String toString(){
        String str = "";

        for (int i = 0; i < pedidos.size(); i++) {

            Produto p = pedidos.get(i);
            str += (i+1) + ". " + p.getNome() + "\n";

        }

        return str;

    }

    public String pedidoFinal(){
        String str = "";

        for (int i = 0; i < pedidos.size(); i++) {

            Produto p = pedidos.get(i);
            str += (i+1) + ". " + p.getNome() + "\n";

        }

        cupom.setDesconto(this); //possibiidades poderosas disso aqui não funcionar
        return "Pedido:\n" + str + "\nHorário da venda: " + dataInicioPedidoStr + "\nTotal a pagar R$ " + valor;
    }

    public long tempoPreparoMinutos(){
        Duration t = Duration.between(dataInicioPedido, dataFimPedido);
        return t.toMinutes();
    }

}
