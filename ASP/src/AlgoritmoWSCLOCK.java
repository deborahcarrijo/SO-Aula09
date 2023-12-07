import java.util.Random;

public class AlgoritmoWSCLOCK implements SubstituicaoPagina {

    private int ponteiroRelogio = 0;

    @Override
    public void executarSubstituicao(MatrizRAM matrizRAM, MatrizSWAP matrizSWAP, int instrucao) {
        int[] paginaNova = matrizSWAP.copiarLinha(instrucao - 1);

        Random random = new Random();
        int EP = random.nextInt(9899) + 100; // Gera um número aleatório entre 100 e 9999

        boolean substituicaoRealizada = false;

        // Percorre toda a matrizRAM
        for (int i = 0; i < matrizRAM.getNumLinhas(); i++) {
            int[] paginaSubstituida = matrizRAM.getLinha(ponteiroRelogio);

            if (paginaSubstituida[3] == 0) {
                // Bit de referência R = 0

                if (EP > paginaSubstituida[5]) {
                    // EP > T, verifica o bit de modificação M
                    if (paginaSubstituida[4] == 0) {
                        // Página limpa, carrega com a nova página
                        matrizRAM.setLinha(ponteiroRelogio, paginaNova);
                        ponteiroRelogio = (ponteiroRelogio + 1) % matrizRAM.getNumLinhas();
                        substituicaoRealizada = true;
                        break; // Páginas percorridas, substituição feita
                    } else {
                        // Página suja, solicita a escrita para o SWAP
                        matrizSWAP.setLinha(paginaSubstituida[0], paginaSubstituida);
                        matrizSWAP.zerarModificado(paginaSubstituida[0]);
                    }
                } else {
                    // EP <= T, a página faz parte do conjunto de trabalho, avança o ponteiro
                    ponteiroRelogio = (ponteiroRelogio + 1) % matrizRAM.getNumLinhas();
                }
            } else {
                // Bit de referência R = 1, desmarca e move o ponteiro
                matrizRAM.zerarAcesso(ponteiroRelogio);
                ponteiroRelogio = (ponteiroRelogio + 1) % matrizRAM.getNumLinhas();
            }
        }

        // Se nenhuma substituição foi feita durante a busca, procure por uma moldura
        // com M=0
        if (!substituicaoRealizada) {
            for (int i = 0; i < matrizRAM.getNumLinhas(); i++) {
                int[] paginaSubstituida = matrizRAM.getLinha(ponteiroRelogio);

                if (paginaSubstituida[4] == 0) {
                    // Página com M=0, substitui com a nova página
                    matrizRAM.setLinha(ponteiroRelogio, paginaNova);
                    ponteiroRelogio = (ponteiroRelogio + 1) % matrizRAM.getNumLinhas();
                    break; // Substituição feita
                } else {
                    // Página com M=1, move o ponteiro
                    ponteiroRelogio = (ponteiroRelogio + 1) % matrizRAM.getNumLinhas();
                }
            }
        }
    }
}
