import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Banco {
    public static void main(String[] args) {
        ArrayList<ContaBancaria> contas = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("==== Menu ====");
            System.out.println("1. Criar Conta Corrente Comum");
            System.out.println("2. Criar Conta Corrente Premium");
            System.out.println("3. Criar Conta Poupança");
            System.out.println("4. Criar Conta de Investimento");
            System.out.println("5. Realizar Depósito");
            System.out.println("6. Realizar Saque");
            System.out.println("7. Gerar Extrato");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");

            int escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (escolha) {
                case 1:
                    criarContaCorrenteComum(contas, scanner);
                    break;
                case 2:
                    criarContaCorrentePremium(contas, scanner);
                    break;
                case 3:
                    criarContaPoupanca(contas, scanner);
                    break;
                case 4:
                    criarContaInvestimento(contas, scanner);
                    break;
                case 5:
                    realizarDeposito(contas, scanner);
                    break;
                case 6:
                    realizarSaque(contas, scanner);
                    break;
                case 7:
                    gerarExtrato(contas, scanner);
                    break;
                case 8:
                    System.out.println("Encerrando o programa.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void criarContaCorrenteComum(ArrayList<ContaBancaria> contas, Scanner scanner) {
        System.out.print("Nome do titular: ");
        String nome = scanner.nextLine();
        System.out.print("Número da conta: ");
        int numeroConta = scanner.nextInt();
        System.out.print("Saldo inicial: ");
        float saldo = scanner.nextFloat();
        ContaCorrenteComum conta = new ContaCorrenteComum(nome, numeroConta, saldo);
        contas.add(conta);
        System.out.println("Conta Corrente Comum criada com sucesso.");
    }

    private static void criarContaCorrentePremium(ArrayList<ContaBancaria> contas, Scanner scanner) {
        System.out.print("Nome do titular: ");
        String nome = scanner.nextLine();
        System.out.print("Número da conta: ");
        int numeroConta = scanner.nextInt();
        System.out.print("Saldo inicial: ");
        float saldo = scanner.nextFloat();
        System.out.print("Limite de crédito: ");
        float limiteCredito = scanner.nextFloat();
        ContaCorrentePremium conta = new ContaCorrentePremium(nome, numeroConta, saldo, limiteCredito);
        contas.add(conta);
        System.out.println("Conta Corrente Premium criada com sucesso.");
    }

    private static void criarContaPoupanca(ArrayList<ContaBancaria> contas, Scanner scanner) {
        System.out.print("Nome do titular: ");
        String nome = scanner.nextLine();
        System.out.print("Número da conta: ");
        int numeroConta = scanner.nextInt();
        System.out.print("Saldo inicial: ");
        float saldo = scanner.nextFloat();
        System.out.print("Dia do aniversário (dd/MM/yyyy): ");
        String dataAniversarioStr = scanner.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataAniversario = LocalDate.parse(dataAniversarioStr, formatter);
        ContaPoupanca conta = new ContaPoupanca(nome, numeroConta, saldo, dataAniversario);
        contas.add(conta);
        System.out.println("Conta Poupança criada com sucesso.");
    }

    private static void criarContaInvestimento(ArrayList<ContaBancaria> contas, Scanner scanner) {
        System.out.print("Nome do titular: ");
        String nome = scanner.nextLine();
        System.out.print("Número da conta: ");
        int numeroConta = scanner.nextInt();
        System.out.print("Saldo inicial: ");
        float saldo = scanner.nextFloat();
        System.out.print("Taxa de retorno anual (%): ");
        float taxaRetornoAnual = scanner.nextFloat();
        ContaInvestimento conta = new ContaInvestimento(nome, numeroConta, saldo, taxaRetornoAnual);
        contas.add(conta);
        System.out.println("Conta de Investimento criada com sucesso.");
    }

    private static void realizarDeposito(ArrayList<ContaBancaria> contas, Scanner scanner) {
        System.out.print("Número da conta: ");
        int numeroConta = scanner.nextInt();
        System.out.print("Valor do depósito: ");
        float valorDeposito = scanner.nextFloat();
        LocalDate data = LocalDate.now();

        for (ContaBancaria conta : contas) {
            if (conta.getNumConta() == numeroConta) {
                conta.deposito(data, valorDeposito);
                System.out.println("Depósito realizado com sucesso.");
                return;
            }
        }

        System.out.println("Conta não encontrada.");
    }

    private static void realizarSaque(ArrayList<ContaBancaria> contas, Scanner scanner) {
    System.out.print("Número da conta: ");
    int numeroConta = scanner.nextInt();
    System.out.print("Valor do saque: ");
    float valorSaque = scanner.nextFloat();

    for (ContaBancaria conta : contas) {
        if (conta.getNumConta() == numeroConta) {
            if (conta instanceof ContaCorrenteComum) {
                ContaCorrenteComum ccComum = (ContaCorrenteComum) conta;
                if (ccComum.saque(valorSaque,numeroConta)) {
                    System.out.println("Saque realizado com sucesso.");
                } else {
                    System.out.println("Erro ao realizar o saque.");
                }
            } else if (conta instanceof ContaCorrentePremium) {
                ContaCorrentePremium ccPremium = (ContaCorrentePremium) conta;
                if (ccPremium.saque(valorSaque,numeroConta)) {
                    System.out.println("Saque realizado com sucesso.");
                } else {
                    System.out.println("Erro ao realizar o saque.");
                }
                
            } else if (conta instanceof ContaPoupanca) {
                ContaPoupanca ccPou = (ContaPoupanca) conta;
                if (ccPou.saque(valorSaque,numeroConta)) {
                    System.out.println("Saque realizado com sucesso.");
                } else {
                    System.out.println("Erro ao realizar o saque.");
                }
                
            } else if (conta instanceof ContaInvestimento) {
                ContaInvestimento ccInv = (ContaInvestimento) conta;
                if (ccInv.saque(valorSaque,numeroConta)) {
                    System.out.println("Saque realizado com sucesso.");
                } else {
                    System.out.println("Erro ao realizar o saque.");
                }
                
            }
            return;
        }
    }

    System.out.println("Conta não encontrada.");
}
    private static void gerarExtrato(ArrayList<ContaBancaria> contas, Scanner scanner) {
    System.out.print("Número da conta: ");
    int numeroConta = scanner.nextInt();

    for (ContaBancaria conta : contas) {
        if (conta.getNumConta() == numeroConta) {
            if (conta instanceof ContaCorrenteComum) {
                ContaCorrenteComum ccComum = (ContaCorrenteComum) conta;
                ccComum.gerarExtrato();
            } else if (conta instanceof ContaCorrentePremium) {
                ContaCorrentePremium ccPrem = (ContaCorrentePremium) conta;
                ccPrem.gerarExtrato();
            } else if (conta instanceof ContaPoupanca) {
                ContaPoupanca ccPou = (ContaPoupanca) conta;
                ccPou.gerarExtrato();
            } else if (conta instanceof ContaInvestimento) {
                ContaInvestimento ccInv = (ContaInvestimento) conta;
                ccInv.gerarExtrato();
            }
            return;
        }
    }

    System.out.println("Conta não encontrada.");
}

}
