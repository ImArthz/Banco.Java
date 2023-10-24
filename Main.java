import java.awt.Desktop;
import java.net.URI;
import java.time.LocalDate;
import java.time.DateTimeException;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco("Banco Exemplo", "Rua Exemplo, 123", "123-456-7890");
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("====== Menu do Banco ======");
            System.out.println("1. Criar Conta");
            System.out.println("2. Fazer Depósito");
            System.out.println("3. Fazer Saque");
            System.out.println("4. Visualizar Extrato");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                // Se o usuário inserir uma entrada inválida (não é um número inteiro), lidamos com isso aqui.
                System.err.println("Erro: Entrada inválida. Digite um número inteiro válido.");
                scanner.next(); // Limpa o buffer do teclado
                opcao = -1; // Defina um valor padrão para opção inválida
            }

            switch (opcao) {
                case 1:
                    // Escolher o tipo de conta
                    System.out.println("Escolha o tipo de conta:");
                    System.out.println("1. Conta Corrente Comum");
                    System.out.println("2. Conta Corrente Premium");
                    System.out.println("3. Conta Poupança");
                    System.out.println("4. Conta de Investimento");
                    int tipoConta = 0; // Inicializamos com um valor padrão
                    while (true) {
                        try {
                            System.out.print("Escolha o tipo de conta (1 para Conta Corrente Comum, 2 para Conta Corrente Premium, 3 para Conta Poupança, 4 para Conta de Investimento): ");
                            tipoConta = scanner.nextInt();
                            
                            if (tipoConta >= 1 && tipoConta <= 4) {
                                break; // Sai do loop se o valor estiver no intervalo de 1 a 4
                            } else {
                                System.err.println("Opção inválida. Escolha um número de 1 a 4.");
                            }
                        } catch (InputMismatchException e) {
                            System.err.println("Erro: Insira um número inteiro válido (1 a 4) para escolher o tipo de conta.");
                            scanner.next(); // Limpa o buffer do teclado
                        }
                    }
                    String nomeTitular;
                    float saldoInicial;
                
                    scanner.nextLine(); // Limpa o buffer do teclado
                    while (true) {
                        System.out.print("Nome do titular da conta: ");
                        nomeTitular = scanner.nextLine();
                        if (!nomeTitular.isEmpty()) {
                            break;
                        } else {
                            System.err.println("Erro: O nome do titular não pode estar vazio. Tente novamente.");
                        }
                    }
                
                    while (true) {
                        try {
                            System.out.print("Saldo inicial da conta (formato XX.XX): ");
                            saldoInicial = scanner.nextFloat();
                            if (saldoInicial >= 0) {
                                break;
                            } else {
                                System.err.println("Erro: O saldo inicial deve ser maior ou igual a zero. Tente novamente.");
                            }
                        } catch (InputMismatchException e) {
                            System.err.println("Erro: Formato de saldo inicial inválido. Use o formato XX.XX. Tente novamente.");
                            scanner.next(); // Limpa o buffer do teclado
                        }
                    }
                
                    // Agora, com base na escolha do usuário, crie a instância apropriada
                    ContaBancaria novaConta = null;
                    switch (tipoConta) {
                        case 1:
                            novaConta = new ContaCorrenteComum(nomeTitular, banco.contas.size() + 1, saldoInicial);
                            break;
                        case 2:
                            novaConta = new ContaCorrentePremium(nomeTitular, banco.contas.size() + 1, saldoInicial, limiteCredito);
                            break;
                        case 3:
                            novaConta = new ContaPoupanca(nomeTitular, banco.contas.size() + 1, saldoInicial, diaAniversario);
                            break;
                        case 4:
                            novaConta = new ContaInvestimento(nomeTitular, banco.contas.size() + 1, saldoInicial, taxaRetornoAnual);
                            break;
                        default:
                            System.err.println("Opção de tipo de conta inválida.");
                            break;
                    }
                
                    if (novaConta != null) {
                        banco.criarConta(novaConta);
                    }
                    break;
                case 2:
                    // Fazer depósito
                    int numeroContaDeposito;
                    float valorDeposito;

                    while (true) {
                        try {
                            System.out.print("Número da conta para depósito: ");
                            numeroContaDeposito = scanner.nextInt();
                            if (banco.existeConta(numeroContaDeposito)) {
                                break;
                            } else {
                                System.err.println("Erro: Número de conta inválido. Tente novamente.");
                            }
                        } catch (InputMismatchException e) {
                            System.err.println("Erro: Número de conta inválido. Tente novamente.");
                            scanner.next(); // Limpa o buffer do teclado
                        }
                    }

                    while (true) {
                        try {
                            System.out.print("Valor do depósito (formato XX.XX): ");
                            valorDeposito = scanner.nextFloat();
                            if (valorDeposito >= 0) {
                                break;
                            } else {
                                System.err.println("Erro: Valor de depósito inválido. Deve ser maior ou igual a zero. Tente novamente.");
                            }
                        } catch (InputMismatchException e) {
                            System.err.println("Erro: Formato de valor de depósito inválido. Use o formato XX.XX. Tente novamente.");
                            scanner.next(); // Limpa o buffer do teclado
                        }
                    }

                    LocalDate dataDeposito;
                    while (true) {
                        try {
                            System.out.print("Digite o dia do saque (dd): ");
                            int dia = scanner.nextInt();
                            System.out.print("Digite o mês do saque (MM): ");
                            int mes = scanner.nextInt();
                            System.out.print("Digite o ano do saque (yyyy): ");
                            int ano = scanner.nextInt();
                            dataDeposito = LocalDate.of(ano, mes, dia);
                            break;
                        } catch (DateTimeException e) {
                            System.err.println("Erro: Data de saque inválida. Tente novamente.");
                        }
                    }
                    ContaBancaria contaDeposito = banco.encontrarConta(numeroContaDeposito);
                    if (contaDeposito != null) {
                    contaDeposito.deposito(dataDeposito, valorDeposito);
                    } else {
                        System.err.println("Erro: Conta não encontrada.");
                    }
                case 3:
                    // Fazer saque
                    int numeroContaSaque;
                    float valorSaque;

                    while (true) {
                        try {
                            System.out.print("Número da conta para saque: ");
                            numeroContaSaque = scanner.nextInt();
                            if (banco.existeConta(numeroContaSaque)) {
                                break;
                            } else {
                                System.err.println("Erro: Número de conta inválido. Tente novamente.");
                            }
                        } catch (InputMismatchException e) {
                            System.err.println("Erro: Número de conta inválido. Tente novamente.");
                            scanner.next(); // Limpa o buffer do teclado
                        }
                    }

                    while (true) {
                        try {
                            System.out.print("Valor do saque (formato XX.XX): ");
                            valorSaque = scanner.nextFloat();
                            if (valorSaque >= 0) {
                                break;
                            } else {
                                System.err.println("Erro: Valor de saque inválido. Deve ser maior ou igual a zero. Tente novamente.");
                            }
                        } catch (InputMismatchException e) {
                            System.err.println("Erro: Formato de valor de saque inválido. Use o formato XX.XX. Tente novamente.");
                            scanner.next(); // Limpa o buffer do teclado
                        }
                    }

                    LocalDate dataSaque;
                    while (true) {
                        try {
                            System.out.print("Digite o dia do saque (dd): ");
                            int dia = scanner.nextInt();
                            System.out.print("Digite o mês do saque (MM): ");
                            int mes = scanner.nextInt();
                            System.out.print("Digite o ano do saque (yyyy): ");
                            int ano = scanner.nextInt();
                            dataSaque = LocalDate.of(ano, mes, dia);
                            break;
                        } catch (DateTimeException e) {
                            System.err.println("Erro: Data de saque inválida. Tente novamente.");
                        }
                    }

                    ContaBancaria contaSaque = banco.encontrarConta(numeroContaSaque);
                    if (contaSaque != null) {
                        if (contaSaque.saque(dataSaque, valorSaque)) {
                            System.out.println("Saque efetuado com sucesso.");
                        }
                    } else {
                        System.err.println("Erro: Conta não encontrada.");
                    }
                case 4:
                    int numeroContaExtrato = -1;
                    while (true) {
                        try {
                            System.out.print("Número da conta: ");
                            numeroContaExtrato = scanner.nextInt();
                            if (numeroContaExtrato > 0) {
                                break;
                            } else {
                                System.err.println("Erro: Número de conta inválido. Deve ser um número inteiro positivo. Tente novamente.");
                            }
                        } catch (InputMismatchException e) {
                            System.err.println("Erro: Número de conta inválido. Digite um número inteiro válido.");
                            scanner.next(); // Limpa o buffer do teclado
                        }
                    }
                
                    banco.visualizarExtrato(numeroContaExtrato);
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.err.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    String urlRepositorio = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
            try {
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(new URI(urlRepositorio));
                } else {
                    System.err.println("A abertura de URLs não é suportada neste ambiente.");
                }
            } catch (Exception e) {
                System.err.println("Erro ao abrir o navegador: " + e.getMessage());
            }
    }
}