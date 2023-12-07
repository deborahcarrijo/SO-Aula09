import java.util.Random;

public class MatrizSWAP {
    int[][] matrizSWAP;

    // Construtor da classe MatrizSWAP
    public MatrizSWAP(int numLinhas) {
        // Inicializando a matrizSWAP com o número especificado
        this.matrizSWAP = new int[numLinhas][6];

        // Preenchendo a matrizSWAP no momento da criação do objeto
        preencherMatrizSWAP();
    }

    // Método para preencher a matrizSWAP
    private void preencherMatrizSWAP() {
        Random random = new Random();

        for (int i = 0; i < matrizSWAP.length; i++) {
            // Cada página é um array de 6 posições
            matrizSWAP[i] = new int[6];

            // Preenchendo os atributos de cada página conforme as regras
            matrizSWAP[i][0] = i; // N: sequencial de 0 a 99
            matrizSWAP[i][1] = i + 1; // I: sequencial de 1 a 100
            matrizSWAP[i][2] = random.nextInt(50) + 1; // D: aleatório de 1 a 50
            matrizSWAP[i][3] = 0; // R: 0
            matrizSWAP[i][4] = 0; // M: 0
            matrizSWAP[i][5] = random.nextInt(9900) + 100; // T: aleatório de 100 a 9999
        }
    }

    // Método para imprimir a matrizSWAP
    public void imprimirMatrizSWAP() {
        // Cabeçalho
        System.out.printf("%-10s%-10s%-10s%-10s%-10s%-10s\n", "N", "I", "D", "R", "M", "T");

        // Impressão dos dados
        for (int[] linha : matrizSWAP) {
            for (int pagina : linha) {
                System.out.printf("%-10s", pagina);
            }
            System.out.println();
        }
    }

    public int getNumLinhas() {
        return this.matrizSWAP.length;
    }

    public int[] copiarLinha(int linha) {
        int[] arrayCopiado = new int[6];

        // Verificar se a linha escolhida está dentro dos limites da matriz
        if (linha >= 0 && linha < matrizSWAP.length) {
            // Copiar os dados da linha para o array
            System.arraycopy(matrizSWAP[linha], 0, arrayCopiado, 0, 6);
        } else {
            System.out.println("Linha inválida.");
        }

        return arrayCopiado;
    }

    public void setLinha(int linhaASubstituir, int[] novaLinha) {
        if (linhaASubstituir >= 0 && linhaASubstituir < matrizSWAP.length) {
            matrizSWAP[linhaASubstituir] = novaLinha;
        } else {
            System.out.println("Índice de linha inválido ou tamanho da nova linha diferente.");
        }
    }

    public void zerarAcesso(int linha) {
        if (matrizSWAP[linha][3] == 1) {
            matrizSWAP[linha][3] = 0;
        }
    }

    public void zerarModificado(int linha) {
        if (matrizSWAP[linha][4] == 1) {
            matrizSWAP[linha][4] = 0;
        }
    }
}
