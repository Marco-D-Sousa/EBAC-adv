package ebac;

/**
 * A sequência de Fibonacci é uma sequência matemática onde cada número é a soma dos dois anteriores.
 * Os dois primeiros números da sequência são definidos como F(0) = 0 e F(1) = 1.
 * 
 * Exemplo da sequência Fibonacci: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, ...
 */
public class Fibonacci {
    
      /**
     * Calcula o enésimo número da sequência de Fibonacci utilizando recursão.
     * A sequência de Fibonacci é definida pela seguinte fórmula:
     * F(n) = F(n-1) + F(n-2) para n > 1, com os casos base:
     * F(0) = 0 e F(1) = 1.
     * 
     * @param n O índice do número de Fibonacci a ser calculado.
     * @return O número da sequência de Fibonacci correspondente ao índice fornecido.
     * @throws IllegalArgumentException Se o valor de n for negativo, pois Fibonacci não é definido para números negativos.
     */
    public static long fibo(int n) {
        if (n < 2) {
            return n;
        } else {
            return fibo(n - 1) + fibo(n - 2);
        }
       
    }

    /**
     * O método principal que imprime os primeiros 30 números da sequência de Fibonacci.
     * Este método é usado para testar o cálculo de Fibonacci para os primeiros 30 números.
     * 
     * @param args Parâmetros da linha de comando (não utilizados neste caso).
     */
    public static void main(String[] args) {
        
        for (int i = 0; i < 30; i++) {
            System.out.println("<"+ i +"> " + Fibonacci.fibo(i));
        }
    }
}
