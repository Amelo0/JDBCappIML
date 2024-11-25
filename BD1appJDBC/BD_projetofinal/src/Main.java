import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuarPrograma = true;

        while (continuarPrograma) {
            System.out.println("\n--- MENU INICIAL ---");
            System.out.println("Escolha a tabela para interagir:");
            System.out.println("1. Paciente");
            System.out.println("2. Médico");
            System.out.println("3. Consulta");
            System.out.println("4. Fechar programa");
            System.out.print("Escolha uma opção: ");

            int tabelaEscolhida = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (tabelaEscolhida) {
                case 1:
                    interagirComTabela(new PacienteManager(), scanner);
                    break;
                case 2:
//                    interagirComTabela(new MedicoManager(), scanner);
//                    break;
                    System.out.println("Interagir com Medico");
                case 3:
//                    interagirComTabela(new ConsultaManager(), scanner);
//                    break;
                    System.out.println("Interagir com consulta");
                case 4:
                    System.out.println("Encerrando o programa...");
                    continuarPrograma = false;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }

        scanner.close();
    }

    private static void interagirComTabela(Manager manager, Scanner scanner) {
        boolean continuarTabela = true;

        while (continuarTabela) {
            System.out.println("\n--- MENU DE OPERAÇÕES ---");
            System.out.println("1. Inserir");
            System.out.println("2. Listar");
            System.out.println("3. Atualizar");
            System.out.println("4. Deletar");
            System.out.println("5. Voltar ao menu inicial");
            System.out.print("Escolha uma operação: ");

            int operacao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (operacao) {
                case 1:
                    manager.inserir(scanner);
                    break;
                case 2:
                    manager.listar();
                    break;
                case 3:
                    manager.atualizar(scanner);
                    break;
                case 4:
                    manager.deletar(scanner);
                    break;
                case 5:
                    continuarTabela = false;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
}
