package Front;

import Back.ProdutoDaLoja;

public class Main2 {
    public static void main(String[] args) {

        Facade f = new Facade();
        System.out.println(f.mostraCardapio());
    }
}
