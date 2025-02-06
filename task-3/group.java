import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class group {

    public static List<List<Integer>> agrupar(int[] numeros, int n) {
        
        Arrays.sort(numeros);

        List<List<Integer>> grupos = new ArrayList<>();
        List<Integer> grupoAtual = new ArrayList<>();

        gerarGrupos(numeros, n, 0, grupoAtual, grupos);

        return grupos;
    }

    private static void gerarGrupos(int[] numeros, int n, int inicio, List<Integer> grupoAtual, List<List<Integer>> grupos) {

        if (grupoAtual.size() == n) {
            grupos.add(new ArrayList<>(grupoAtual));
            return;
        }

        for (int i = inicio; i < numeros.length; i++) {
            grupoAtual.add(numeros[i]);
            gerarGrupos(numeros, n, i + 1, grupoAtual, grupos);
            grupoAtual.remove(grupoAtual.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] numeros = {1, 2, 3, 4, 5};
        int n = 5;

        List<List<Integer>> grupos = agrupar(numeros, n);

        System.out.println("Grupos de " + n + ":");
        for (List<Integer> grupo : grupos) {
            System.out.println(grupo);
        }
    }
}