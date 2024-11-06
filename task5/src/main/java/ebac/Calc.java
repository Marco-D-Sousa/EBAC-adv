package ebac;

/**
 * A classe Calc oferece operações matemáticas básicas, como soma, subtração,
 * multiplicação e divisão.
 * Cada método da classe realiza uma operação aritmética entre dois números do
 * tipo {@code double}.
 * A operação de divisão lança uma exceção se houver tentativa de divisão por zero.
 */
public class Calc {

    /**
     * Realiza a soma de dois números.
     *
     * @param num1 O primeiro número a ser somado.
     * @param num2 O segundo número a ser somado.
     * @return O resultado da soma entre {@code num1} e {@code num2}.
     */
    public double soma(double num1, double num2) {
        return num1 + num2;
    }

    /**
     * Realiza a subtração entre dois números.
     *
     * @param num1 O número de onde será subtraído o valor de {@code num2}.
     * @param num2 O número a ser subtraído de {@code num1}.
     * @return O resultado da subtração entre {@code num1} e {@code num2}.
     */
    public double subtrai(double num1, double num2) {
        return num1 - num2;
    }

    /**
     * Realiza a multiplicação entre dois números.
     *
     * @param num1 O primeiro número a ser multiplicado.
     * @param num2 O segundo número a ser multiplicado.
     * @return O resultado da multiplicação entre {@code num1} e {@code num2}.
     */
    public double multiplica(double num1, double num2) {
        return num1 * num2;
    }

    /**
     * Realiza a divisão entre dois números. Caso o divisor seja zero,
     * uma exceção {@code IllegalArgumentException} será lançada.
     *
     * @param num1 O numerador da divisão.
     * @param num2 O denominador da divisão (não pode ser zero).
     * @return O resultado da divisão entre {@code num1} e {@code num2}.
     * @throws IllegalArgumentException Se {@code num2} for igual a zero.
     */
    public double divide(double num1, double num2) {
        if (num2 == 0) {
            throw new IllegalArgumentException("Divisao por 0 nao e permitida");
        }
        return num1 / num2;
    }

    /**
     * Método principal que testa as operações da classe {@code Calc}.
     * 
     * @param args Argumentos da linha de comando (não utilizados neste caso).
     */
    public static void main(String[] args) {
        Calc calc = new Calc();

        System.out.println("Soma: " + calc.soma(10, 12));
        System.out.println("Subtracao: " + calc.subtrai(10, 12));
        System.out.println("Multiplicacao: " + calc.multiplica(10, 12));
        System.out.println("Divisao: " + calc.divide(10, 12));
    }
}