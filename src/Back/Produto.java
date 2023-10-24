package Back;

import java.util.Objects;

public class Produto {

    private String nome;
    private Double preco;
    private Integer qt;

    public Produto(String nome, Double preco, Integer qt){
        this.nome = nome;
        this.preco = preco;
        this.qt = qt;
    }

    public String getNome(){
        return nome;
    }

    public Double getPreco(){
        return preco;
    }

    public Integer getQt(){
        return qt;
    }

}
