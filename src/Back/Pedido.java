package Back;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Pedido{

    DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm");
    private ArrayList <ProdutoDaLoja> pedidos = new ArrayList<>();
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

    public void addBatata(){
        pedidos.add(ProdutoDaLoja.BATATA_FRITA);
        ProdutoDaLoja.BATATA_FRITA.aumentaQt();
    }

    public void addPastel(){
        pedidos.add(ProdutoDaLoja.PASTEL);
        ProdutoDaLoja.PASTEL.aumentaQt();
    }

    public void addChocolateAoLeite(){
        pedidos.add(ProdutoDaLoja.CHOCOLATE_AO_LEITE);
        ProdutoDaLoja.CHOCOLATE_AO_LEITE.aumentaQt();
    }

    public void addAgua(){
        pedidos.add(ProdutoDaLoja.AGUA);
        ProdutoDaLoja.AGUA.aumentaQt();
    }

    public void addRefrigerante(){
        pedidos.add(ProdutoDaLoja.REFRIGERANTE);
        ProdutoDaLoja.REFRIGERANTE.aumentaQt();
    }

    public void addPaoBola(){
        pedidos.add(ProdutoDaLoja.PAO_BOLA);
        ProdutoDaLoja.PAO_BOLA.aumentaQt();
    }

    public void addQueijo(){
        pedidos.add(ProdutoDaLoja.PORCAO_DE_QUEIJO);
        ProdutoDaLoja.PORCAO_DE_QUEIJO.aumentaQt();
    }

    public void addCarneHamburguer(){
        pedidos.add(ProdutoDaLoja.CARNE_HAMBURGUER);
        ProdutoDaLoja.CARNE_HAMBURGUER.aumentaQt();
    }

    public void addBatataQueijo(){
        pedidos.add(ProdutoDaLoja.BATATA_COM_QUEIJO);
        ProdutoDaLoja.BATATA_COM_QUEIJO.aumentaQt();
    }

    public void addHamburguer(){
        pedidos.add(ProdutoDaLoja.HAMBURGUER);
        ProdutoDaLoja.HAMBURGUER.aumentaQt();
    }

    public void addPastelComRefrigerante(){
        pedidos.add(ProdutoDaLoja.PASTEL_COM_REFRIGERANTE);
        ProdutoDaLoja.PASTEL_COM_REFRIGERANTE.aumentaQt();
    }

    public void addChocolateComAgua(){
        pedidos.add(ProdutoDaLoja.CHOCOLATE_COM_AGUA);
        ProdutoDaLoja.CHOCOLATE_COM_AGUA.aumentaQt();
    }

    public void cancelarPedido(){
        if (status == Status.AGUARDANDO_PREPARO){
            this.status = Status.CANCELADO_PELO_CLIENTE;
            this.dataFimPedido = LocalDateTime.now();
            this.dataFimPedidoStr = LocalDateTime.now().format(f);
        }
    } // lembrar de adc aos finalizadosGeral na facade

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

            ProdutoDaLoja p = pedidos.get(i);
            pb += p.getPreco();

        }

        return pb;
    }

    public Integer contaItens(){
        return pedidos.size() + 1;
    }

    public ProdutoDaLoja maisBarato(){
        ProdutoDaLoja mb = pedidos.get(0);

        for (int i = 0; i < pedidos.size(); i++) {
            ProdutoDaLoja p = pedidos.get(i);
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

            ProdutoDaLoja p = pedidos.get(i);
            str += (i+1) + ". " + p.getDescricao() + "\n";

        }

        return str;

    }

    public String pedidoFinal(){
        String str = "";

        for (int i = 0; i < pedidos.size(); i++) {

            ProdutoDaLoja p = pedidos.get(i);
            str += (i+1) + ". " + p.getDescricao() + "\n";

        }

        cupom.setDesconto(this); //possibiidades poderosas disso aqui não funcionar
        return "Pedido:\n" + str + "\nHorário da venda: " + dataInicioPedidoStr + "\nTotal a pagar R$ " + valor;
    }

    public long tempoPreparoMinutos(){
        Duration t = Duration.between(dataInicioPedido, dataFimPedido);
        return t.toMinutes();
    }

}
