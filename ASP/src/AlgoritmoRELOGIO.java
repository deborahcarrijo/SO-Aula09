public class AlgoritmoRELOGIO implements SubstituicaoPagina {

    private int ponteiroRelogio = 0;

    @Override
    public void executarSubstituicao(MatrizRAM matrizRAM, MatrizSWAP matrizSWAP, int instrucao) {
        int[] paginaNova = matrizSWAP.copiarLinha(instrucao - 1);

        while (true) {
            int[] paginaSubstituida = matrizRAM.getLinha(ponteiroRelogio);

            if (paginaSubstituida[3] == 0) {
                // Substitui a página se o bit de referência for 0
                if (paginaSubstituida[4] == 1) {
                    matrizSWAP.setLinha(paginaSubstituida[0], paginaSubstituida);
                    matrizSWAP.zerarModificado(paginaSubstituida[0]);
                }

                matrizRAM.setLinha(ponteiroRelogio, paginaNova);
                ponteiroRelogio = (ponteiroRelogio + 1) % matrizRAM.getNumLinhas();
                break;
            } else {
                // Se o bit de referência estiver marcado, desmarca e move o ponteiro
                matrizRAM.zerarAcesso(ponteiroRelogio);
                ponteiroRelogio = (ponteiroRelogio + 1) % matrizRAM.getNumLinhas();
            }
        }
    }
}