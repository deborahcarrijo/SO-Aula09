import java.util.Random;

public class Simulador {
    public static void main(String[] args) {

        AlgoritmoNRU algoritmoNRU = new AlgoritmoNRU();
        AlgoritmoFIFO algoritmoFIFO = new AlgoritmoFIFO();
        AlgoritmoFIFOSC algoritmoFIFOSC = new AlgoritmoFIFOSC();
        AlgoritmoRELOGIO algoritmoRELOGIO = new AlgoritmoRELOGIO();
        AlgoritmoWSCLOCK algoritmoWSCLOCK = new AlgoritmoWSCLOCK();

        MatrizSWAP matrizSWAP = new MatrizSWAP(100);
        MatrizRAM matrizRAM = new MatrizRAM(10, matrizSWAP);

        // Imprimindo as matrizes iniciais
        System.out.println("\nMatriz SWAP Inicial:");
        matrizSWAP.imprimirMatrizSWAP();
        System.out.println("\nMatriz RAM Inicial:");
        matrizRAM.imprimirMatrizRAM();

        // Para fazer as simulações de cada um dos algoritmos de substituição basta
        // alterar o algoritmo passado como parâmetro do método "executarInstrucao" na
        // linha 27.
        for (int i = 1; i < 1000; i++) {
            int numeroInstrucao = sortearNumero();

            matrizRAM.executarInstrucao(numeroInstrucao, matrizSWAP, algoritmoWSCLOCK);
            if (i % 10 == 0) {
                // Chama a segunda função a cada 10 iterações
                matrizRAM.zerarAcessos();
            }
        }
        // Imprimindo as matrizes finais
        System.out.println("\nMatriz SWAP Final:");
        matrizSWAP.imprimirMatrizSWAP();
        System.out.println("\nMatriz RAM Final:");
        matrizRAM.imprimirMatrizRAM();
    }

    public static int sortearNumero() {
        Random random = new Random();
        return random.nextInt(100) + 1; // Gera um número aleatório entre 1 e 100.
    }
}
