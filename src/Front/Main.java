package Front;

import Back.ProdutoDaLoja;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<ProdutoDaLoja> lista;
        lista = Arrays.stream(ProdutoDaLoja.values()).toList();
        System.out.println(lista);


        List <ProdutoDaLoja> ordenada = new ArrayList<>();

        ProdutoDaLoja.AGUA.aumentaQt();
        ProdutoDaLoja.AGUA.aumentaQt();
        ProdutoDaLoja.AGUA.aumentaQt();
        ProdutoDaLoja.CHOCOLATE_COM_AGUA.aumentaQt();
        ProdutoDaLoja.CHOCOLATE_COM_AGUA.aumentaQt();

        int maior = 0;
        for (int i = 0; i < lista.size(); i++) {
            ProdutoDaLoja p = lista.get(i);
            if (p.getQt() > maior){
                maior = p.getQt();
                //seguindo essa logica
                ordenada.add(p);
            }
        }

        System.out.println(ordenada);

    }
}