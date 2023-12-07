import java.util.ArrayList;
import java.util.List;

public class AlgoritmoFIFOSC implements SubstituicaoPagina {
    private List<int[]> filaPaginas = new ArrayList<>();

    @Override
    public void executarSubstituicao(MatrizRAM matrizRAM, MatrizSWAP matrizSWAP, int instrucao) {
        if (filaPaginas.isEmpty()) {
            for (int i = 0; i < matrizRAM.getNumLinhas(); i++) {
                int[] pagina = matrizRAM.copiarLinha(i);
                filaPaginas.add(pagina);
            }
        }

        int[] paginaSubstituida = matrizRAM.buscarPaginaPorNumero(filaPaginas.get(0)[0]);
        int[] paginaNova = matrizSWAP.copiarLinha(instrucao - 1);

        if (paginaSubstituida[3] == 0) {
            for (int i = 0; i < matrizRAM.getNumLinhas(); i++) {
                if (matrizRAM.getLinha(i)[0] == paginaSubstituida[0]) {
                    if (paginaSubstituida[4] == 1) {
                        matrizSWAP.setLinha(paginaSubstituida[0], paginaSubstituida);
                        matrizSWAP.zerarModificado(paginaSubstituida[0]);
                    }
                    matrizRAM.setLinha(i, paginaNova); // Adicionar a nova página à matrizRAM
                }
            }
            filaPaginas.add(paginaNova); // Adicionar a página à fila
            filaPaginas.remove(0); // Remover página mais antiga da fila
        } else {
            do {
                System.out.println("Página escolhida tem R = 1");
                // Dar uma nova chance à página
                int[] paginaSegundaChance = paginaSubstituida;
                paginaSegundaChance[3] = 0;

                filaPaginas.remove(0); // Remover página mais antiga da fila
                filaPaginas.add(paginaSegundaChance); // Atualizar a fila

                for (int i = 0; i < matrizRAM.getNumLinhas(); i++) {
                    if (matrizRAM.getLinha(i)[0] == paginaSegundaChance[0]) {
                        matrizRAM.zerarAcesso(i);
                    }
                }

                paginaSubstituida = matrizRAM.buscarPaginaPorNumero(filaPaginas.get(0)[0]);
            } while (paginaSubstituida[3] == 1);

            for (int i = 0; i < matrizRAM.getNumLinhas(); i++) {
                if (matrizRAM.getLinha(i)[0] == paginaSubstituida[0]) {
                    if (paginaSubstituida[4] == 1) {
                        matrizSWAP.setLinha(paginaSubstituida[0], paginaSubstituida);
                        matrizSWAP.zerarModificado(paginaSubstituida[0]);
                    }
                    matrizRAM.setLinha(i, paginaNova); // Adicionar a nova página à matrizRAM
                }
            }
            filaPaginas.add(paginaNova); // Adicionar a página à fila
            filaPaginas.remove(0); // Remover página mais antiga da fila
        }
    }
}
