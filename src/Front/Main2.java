package Front;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main2 {
    public static void main(String[] args) {

        //String caminhoDoArquivo = "C:\\Users\\Lori\\ArquivosTexto\\arq.txt";
        //caso eu quisesse acrescentar era só colocar essa variável no parâmetro do try + ,true

        String[] linhas = new String[] {"Bom dia", "Boa tarde", "Boa noite"};
        String caminhoDoArquivoCriar = "C:\\Users\\Lori\\Desktop\\teste.txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoDoArquivoCriar))){
            for (String linha: linhas) {
                bw.write(linha);
                bw.newLine();
                // bw, por padrão, não tem quebra de linha, por isso esse newLine
            }
        } catch (IOException e){
            System.out.println("Erro: " + e.getMessage());
        }

    }
}
