import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlgoritmoNRU implements SubstituicaoPagina {

    @Override
    public void executarSubstituicao(MatrizRAM matrizRAM, MatrizSWAP matrizSWAP, int instrucao) {
        // Criar listas para cada classe de página
        List<int[]> classe00 = new ArrayList<>();
        List<int[]> classe01 = new ArrayList<>();
        List<int[]> classe10 = new ArrayList<>();
        List<int[]> classe11 = new ArrayList<>();

        // Preencher as listas com páginas de cada classe
        for (int i = 0; i < matrizRAM.getNumLinhas(); i++) {

            int[] pagina = matrizRAM.copiarLinha(i);
            int r = pagina[3];
            int m = pagina[4];

            if (r == 0 && m == 0) {
                classe00.add(pagina);
            } else if (r == 0 && m == 1) {
                classe01.add(pagina);
            } else if (r == 1 && m == 0) {
                classe10.add(pagina);
            } else if (r == 1 && m == 1) {
                classe11.add(pagina);
            }
        }

        // Escolher uma classe com menor prioridade
        List<int[]> classeEscolhida = escolherClasseComMenorPrioridade(classe00, classe01, classe10, classe11);

        if (!classeEscolhida.isEmpty()) {
            // Escolher aleatoriamente uma página da classe escolhida
            int[] paginaSubstituida = classeEscolhida.get(new Random().nextInt(classeEscolhida.size()));
            System.out.println("Página escolhida para ser substituída: " + paginaSubstituida[0]);

            int[] paginaNova = matrizSWAP.copiarLinha(instrucao - 1);
            // Substituir a página na matrizRAM
            for (int i = 0; i < matrizRAM.getNumLinhas(); i++) {
                if (matrizRAM.getLinha(i)[0] == paginaSubstituida[0]) {

                    if (classeEscolhida == classe01 || classeEscolhida == classe11) {
                        // System.out.println("Modificar coluna M");
                        // matrizSWAP.imprimirMatrizSWAP();
                        matrizSWAP.setLinha(paginaSubstituida[0], matrizRAM.copiarLinha(i));
                        matrizSWAP.zerarAcesso(paginaSubstituida[0]);
                        matrizSWAP.zerarModificado(paginaSubstituida[0]);
                    }
                    matrizRAM.setLinha(i, paginaNova);
                }
            }
        }
    }

    private List<int[]> escolherClasseComMenorPrioridade(List<int[]> classe00, List<int[]> classe01,
            List<int[]> classe10, List<int[]> classe11) {
        if (!classe00.isEmpty()) {
            return classe00;
        } else if (!classe01.isEmpty()) {
            return classe01;
        } else if (!classe10.isEmpty()) {
            return classe10;
        } else {
            return classe11;
        }
    }
}
