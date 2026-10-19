import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] nomes = {"Coca-Cola", "Coca-Cola Zero", "Kuat", "Sprite", "Fanta"};
        double[] precos = {2.50, 2.40, 2.30, 2.25, 2.20};

        while (true) {
            System.out.println("\n=== MÁQUINA DE REFRIGERANTE ===");

            for (int i = 0; i < nomes.length; i++) {
                System.out.printf("%d - %s: R$ %.2f%n", i + 1, nomes[i], precos[i]);
            }

            String opcao = "";

            while (!opcao.equals("1")) {
                System.out.print("\nDigite 1 para colocar uma moeda e tomar um refrigerante: ");
                opcao = sc.nextLine();
            }

            ArrayList<Double> moedas = new ArrayList<>();
            double saldo = 0;
            boolean entregue = false;

            while (!entregue) {
                String outraMoeda = "sim";

                while (outraMoeda.equalsIgnoreCase("sim")) {
                    System.out.print("Valor da moeda: R$ ");
                    double valor = Double.parseDouble(sc.nextLine().replace(",", "."));
                    moedas.add(valor);

                    saldo = 0;
                    for (double moeda : moedas) {
                        saldo += moeda;
                    }

                    System.out.printf("Saldo total: R$ %.2f%n", saldo);
                    System.out.print("Deseja digitar outra moeda? (sim/não): ");
                    outraMoeda = sc.nextLine();
                }

                int indice = -1;

                while (indice == -1) {
                    System.out.print("Qual refrigerante deseja? (digite o número): ");
                    String escolha = sc.nextLine();

                    for (int i = 0; i < nomes.length; i++) {
                        if (escolha.equals(String.valueOf(i + 1))) {
                            indice = i;
                            break;
                        }
                    }

                    if (indice == -1) {
                        System.out.println("Opção inválida, tente novamente.");
                    }
                }

                if (saldo >= precos[indice]) {
                    System.out.println(nomes[indice].toUpperCase() + " ENTREGUE!");

                    double troco = Math.round((saldo - precos[indice]) * 100) / 100.0;

                    if (troco > 0) {
                        System.out.printf("R$ %.2f DE TROCO ENTREGUE%n", troco);
                    }

                    entregue = true;
                } else {
                    System.out.println("Saldo insuficiente.");

                    String decisao = "";

                    while (!decisao.equals("1") && !decisao.equals("2")) {
                        System.out.print("1 - Inserir mais moedas | 2 - Troco de volta: ");
                        decisao = sc.nextLine();
                    }

                    if (decisao.equals("2")) {
                        System.out.printf("R$ %.2f DEVOLVIDO%n", saldo);
                        entregue = true;
                    }
                }
            }
        }
    }
}