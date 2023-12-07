import java.util.*;

public class AlgoritmoFIFO implements SubstituicaoPagina {
    private List<int[]> filaPaginas = new ArrayList<>();

    @Override
    public void executarSubstituicao(MatrizRAM matrizRAM, MatrizSWAP matrizSWAP, int instrucao) {

        if (filaPaginas.isEmpty()) {
            for (int i = 0; i < matrizRAM.getNumLinhas(); i++) {
                int[] pagina = matrizRAM.copiarLinha(i);
                filaPaginas.add(pagina);
            }
        }

        int[] paginaSubstituida = filaPaginas.get(0);
        int[] paginaNova = matrizSWAP.copiarLinha(instrucao - 1);

        // Substituir a página na matrizRAM
        for (int i = 0; i < matrizRAM.getNumLinhas(); i++) {
            if (matrizRAM.getLinha(i)[0] == paginaSubstituida[0]) {
                if (paginaSubstituida[4] == 1) {
                    matrizSWAP.setLinha(paginaSubstituida[0], paginaSubstituida);
                    matrizSWAP.zerarAcesso(paginaSubstituida[0]);
                    matrizSWAP.zerarModificado(paginaSubstituida[0]);
                }
                matrizRAM.setLinha(i, paginaNova); // Adicionar a nova página à matrizRAM
            }
        }
        filaPaginas.add(paginaNova); // Adicionar a página à fila
        filaPaginas.remove(0); // Remover página mais antiga da fila
    }
}
