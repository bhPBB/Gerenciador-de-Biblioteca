package Modelos;

import java.util.Comparator;

//Classe usada para comparar a coluna Status na tabela Emprestimos Ativos
public class ComparadorDias implements Comparator<String>{
    @Override
        public int compare(String linha1, String linha2) {
            boolean fechado1 = linha1.contains("Fechado");
            boolean fechado2 = linha2.contains("Fechado");

            if (fechado1 && !fechado2) {
                return 1;
            } else if (!fechado1 && fechado2) {
                return -1;
            }else if (fechado1 && fechado2){
                return 0;
            }
            
            int valor1 = Integer.parseInt(linha1.split(" ")[0]);
            int valor2 = Integer.parseInt(linha2.split(" ")[0]);

            String sinal1 = linha1.split(" ")[2];
            if (sinal1.contains("atrasado")) {
                valor1 *= -1;
            }
            
            String sinal2 = linha2.split(" ")[2];
            if (sinal2.contains("atrasado")) {
                valor2 *= -1;
            }

            return Integer.compare(valor1, valor2);
        }
}

