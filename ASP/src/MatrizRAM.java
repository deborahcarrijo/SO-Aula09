import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatrizRAM {
    int[][] matrizRAM;

    public MatrizRAM(int numLinhas, MatrizSWAP matrizSWAP) {
        matrizRAM = new int[numLinhas][6];
        criarMatrizRAM(matrizSWAP);
    }

    private void criarMatrizRAM(MatrizSWAP matrizSWAP) {
        Random random = new Random();
        List<Integer> linhasDisponiveis = new ArrayList<>();

        // Preenche a lista com índices de linhas da matrizSWAP
        for (int i = 0; i < matrizSWAP.getNumLinhas(); i++) {
            linhasDisponiveis.add(i);
        }

        for (int i = 0; i < matrizRAM.length; i++) {
            // Verifica se ainda há linhas disponíveis
            if (linhasDisponiveis.isEmpty()) {
                // Não há mais linhas disponíveis, encerra o loop
                break;
            }

            // Sorteia um índice da lista de linhas disponíveis
            int indiceSorteado = random.nextInt(linhasDisponiveis.size());

            // Obtém o índice da linha na matrizSWAP
            int linhaSorteada = linhasDisponiveis.get(indiceSorteado);

            // Remove o índice sorteado da lista de linhas disponíveis
            linhasDisponiveis.remove(indiceSorteado);

            // Copia os valores da linha sorteada da matrizSWAP para a linha da matrizRAM
            matrizRAM[i] = matrizSWAP.copiarLinha(linhaSorteada);
        }
    }

    public void imprimirMatrizRAM() {
        // Cabeçalho
        System.out.printf("%-10s%-10s%-10s%-10s%-10s%-10s\n", "N", "I", "D", "R", "M", "T");

        // Impressão dos dados
        for (int[] linha : matrizRAM) {
            for (int pagina : linha) {
                System.out.printf("%-10s", pagina);
            }
            System.out.println();
        }
    }

    public void executarInstrucao(int instrucao, MatrizSWAP matrizSWAP, SubstituicaoPagina algoritmoSubstituicao) {
        // Buscar a página na matrizRAM
        int pagina = buscarPaginaPorInstrucao(instrucao);

        if (pagina != -1) {
            // 1) O bit de acesso R vai receber o valor 1.
            matrizRAM[pagina][3] = 1;

            // 2) A página terá 30% de chance de sofrer uma modificação.
            if (probabilidade(30)) {
                // 2.1) O campo Dado (D) será atualizado da seguinte maneira: D = D + 1;
                matrizRAM[pagina][2] += 1;

                // 2.2) O campo Modificado será atualizado: M = 1;
                matrizRAM[pagina][4] = 1;
            }
        } else {
            // Caso o número de instrução sorteado não esteja presente na MATRIZ RAM,
            // deverá ser utilizado um algoritmo de substituição de página para realizar a
            // substituição.
            algoritmoSubstituicao.executarSubstituicao(this, matrizSWAP, instrucao);
        }
    }

    public void zerarAcessos() {
        for (int i = 0; i < matrizRAM.length; i++) {
            if (matrizRAM[i][3] == 1) {
                matrizRAM[i][3] = 0;
            }
        }
    }

    public void zerarAcesso(int linha) {
        if (matrizRAM[linha][3] == 1) {
            matrizRAM[linha][3] = 0;
        }
    }

    private boolean probabilidade(int porcentagem) {
        Random random = new Random();
        int numeroAleatorio = random.nextInt(100) + 1; // Gera um número entre 1 e 100.

        return numeroAleatorio <= porcentagem;
    }

    public int buscarPaginaPorInstrucao(int instrucao) {
        for (int i = 0; i < matrizRAM.length; i++) {
            if (matrizRAM[i][1] == instrucao) {
                return i;
            }
        }
        return -1;
    }

    public int[] buscarPaginaPorNumero(int numero) {
        for (int[] pagina : matrizRAM) {
            if (pagina[0] == numero) {
                return pagina;
            }
        }
        return null;
    }

    public int getNumLinhas() {
        return this.matrizRAM.length;
    }

    public int getNumColunas() {
        return this.matrizRAM[0].length;
    }

    public int[] copiarLinha(int linha) {
        int[] arrayCopiado = new int[6];

        // Verificar se a linha escolhida está dentro dos limites da matriz
        if (linha >= 0 && linha < matrizRAM.length) {
            // Copiar os dados da linha para o array
            System.arraycopy(matrizRAM[linha], 0, arrayCopiado, 0, 6);
        } else {
            System.out.println("Linha inválida.");
        }
        return arrayCopiado;
    }

    public int[][] getMatrizRAM() {
        return matrizRAM;
    }

    public int[] getLinha(int linha) {
        if (linha >= 0 && linha < matrizRAM.length) {
            return matrizRAM[linha];
        } else {
            // Retornar algo apropriado se a linha especificada estiver fora do intervalo
            return null;
        }
    }

    public void setLinha(int linhaASubstituir, int[] novaLinha) {
        if (linhaASubstituir >= 0 && linhaASubstituir < matrizRAM.length) {
            matrizRAM[linhaASubstituir] = novaLinha;
        } else {
            System.out.println("Índice de linha inválido ou tamanho da nova linha diferente.");
        }
    }
}
