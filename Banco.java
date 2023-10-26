import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.text.Normalizer;


public class Banco {
    private static Set<Integer> numerosDeConta = new HashSet<>();
    
    public static void main(String[] args) {
        ArrayList<ContaBancaria> contas = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("==== Menu ====");
            System.out.println("1. Criar Conta Corrente Comum");
            System.out.println("2. Criar Conta Corrente Premium");
            System.out.println("3. Criar Conta Poupanca");
            System.out.println("4. Criar Conta de Investimento");
            System.out.println("5. Realizar Deposito");
            System.out.println("6. Realizar Saque");
            System.out.println("7. Gerar Extrato");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");

            int escolha = lerInteiro(scanner);
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
        String nome;
        do {
            System.out.print("Nome do titular (pelo menos um nome e um sobrenome): ");
            nome = scanner.nextLine();
        } while (!contemApenasLetras(nome) || nome.split(" ").length < 2);
        int numeroConta = obterNumeroContaUnico(scanner);
        float saldo;
    
        while (true) {
            System.out.print("Saldo inicial: ");
            saldo = lerFloat(scanner);
            if (saldo < 0) {
                System.err.println("Impossível criar conta com saldo menor que 0.");
            } else if (saldo < 30) {
                System.err.println("Deposite pelo menos 30 reais.");
            } else {
                break;
            }
        }
        
        LocalDate dataNascimento = lerDataNascimento(scanner);
    
        if (verificarIdade(dataNascimento, 18)) {
            ContaCorrenteComum conta = new ContaCorrenteComum(nome, numeroConta, saldo);
            contas.add(conta);
            System.out.println("Conta Corrente Comum criada com sucesso.");
        } else {
            System.out.println("O titular da conta deve ter pelo menos 18 anos de idade.");
            System.err.println("Nossas contas para menores de idade são exclusivamente do tipo poupança.");
        }
    }
    
    private static void criarContaCorrentePremium(ArrayList<ContaBancaria> contas, Scanner scanner) {
        String nome;
        do {
            System.out.print("Nome do titular (pelo menos um nome e um sobrenome): ");
            nome = scanner.nextLine();
        } while (!contemApenasLetras(nome) || nome.split(" ").length < 2);
        int numeroConta = obterNumeroContaUnico(scanner);
        float saldo;
        float limiteCredito;
    
        while (true) {
            System.out.print("Saldo inicial: ");
            saldo = lerFloat(scanner);
            if (saldo < 0) {
                System.err.println("Impossível criar conta com saldo menor que 0.");
            } else if (saldo < 30) {
                System.err.println("Deposite pelo menos 30 reais.");
            } else {
                break;
            }
        }
        while (true) {
            System.out.print("Limite de crédito: ");
            limiteCredito = lerFloat(scanner);
            if (limiteCredito < 0) {
                System.err.println("Impossível ter limite de crédito negativo.");
            } else if (limiteCredito < 100) {
                System.err.println("O limite de crédito mínimo é de 100 reais.");
            } else {
                break;
            }
        }
        LocalDate dataNascimento = lerDataNascimento(scanner);
    
        if (verificarIdade(dataNascimento, 18)) {
            ContaCorrentePremium conta = new ContaCorrentePremium(nome, numeroConta, saldo, limiteCredito);
            contas.add(conta);
            System.out.println("Conta Corrente Premium criada com sucesso.");
        } else {
            System.out.println("O titular da conta deve ter pelo menos 18 anos de idade.");
            System.err.println("Nossas contas para menores de idade são exclusivamente do tipo poupança.");
        }
    }
    
    private static void criarContaPoupanca(ArrayList<ContaBancaria> contas, Scanner scanner) {
        String nome;
        do {
            System.out.print("Nome do titular (pelo menos um nome e um sobrenome): ");
            nome = scanner.nextLine();
        } while (!contemApenasLetras(nome) || nome.split(" ").length < 2);
        int numeroConta = obterNumeroContaUnico(scanner);
        float saldo;
    
        while (true) {
            System.out.print("Saldo inicial: ");
            saldo = lerFloat(scanner);
            if (saldo < 0) {
                System.err.println("Impossível criar conta com saldo menor que 0.");
            } else if (saldo < 30) {
                System.err.println("Deposite pelo menos 30 reais.");
            } else {
                break;
            }
        }
        System.out.print("Dia do aniversário (dd/MM/yyyy): ");
        String dataAniversarioStr = scanner.next();
        LocalDate dataAniversario = LocalDate.parse(dataAniversarioStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    
        if (verificarIdade(dataAniversario, 6)) {
            ContaPoupanca conta = new ContaPoupanca(nome, numeroConta, saldo, dataAniversario);
            contas.add(conta);
            System.out.println("Conta Poupança criada com sucesso.");
        } else {
            System.out.println("O titular da conta deve ter pelo menos 6 anos de idade.");
        }
    }
    
    private static void criarContaInvestimento(ArrayList<ContaBancaria> contas, Scanner scanner) {
        String nome;
        do {
            System.out.print("Nome do titular (pelo menos um nome e um sobrenome): ");
            nome = scanner.nextLine();
        } while (!contemApenasLetras(nome) || nome.split(" ").length < 2);
        int numeroConta = obterNumeroContaUnico(scanner);
        float saldo;
    
        while (true) {
            System.out.print("Saldo inicial: ");
            saldo = lerFloat(scanner);
            if (saldo < 0) {
                System.err.println("Impossível criar conta com saldo menor que 0.");
            } else if (saldo < 30) {
                System.err.println("Deposite pelo menos 30 reais.");
            } else {
                break;
            }
        }
    
        LocalDate dataNascimento = lerDataNascimento(scanner);
        
        if (verificarIdade(dataNascimento, 18)) {
            System.out.print("Taxa de retorno anual (%): ");
            float taxaRetornoAnual = lerFloat(scanner);
            ContaInvestimento conta = new ContaInvestimento(nome, numeroConta, saldo, taxaRetornoAnual);
            contas.add(conta);
            System.out.println("Conta de Investimento criada com sucesso.");
        } else {
            System.out.println("O titular da conta deve ter pelo menos 18 anos de idade.");
            System.err.println("Nossas contas para menores de idade são exclusivamente do tipo poupança.");
        }
    }
    
        private static LocalDate lerDataNascimento(Scanner scanner) {
            while (true) {
                System.out.print("Data de nascimento (dd/MM/yyyy): ");
                String dataNascimentoStr = scanner.next();

                try {
                    LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    // Verificar se a data de nascimento é válida (por exemplo, não no futuro)
                    LocalDate dataAtual = LocalDate.now();
                    if (dataNascimento.isBefore(dataAtual)) {
                        scanner.nextLine(); // Consumir a quebra de linha
                        return dataNascimento;
                    } else {
                        System.out.println("A data de nascimento não pode ser no futuro. Tente novamente.");
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Formato de data inválido. Tente novamente no formato dd/MM/yyyy.");
                    scanner.nextLine(); // Consumir a quebra de linha
                }
            }
        }
    
    private static boolean verificarIdade(LocalDate dataNascimento, int idadeMinima) {
        LocalDate dataAtual = LocalDate.now();
        return dataNascimento.plusYears(idadeMinima).isBefore(dataAtual);
    }
    private static void realizarDeposito(ArrayList<ContaBancaria> contas, Scanner scanner) {
        System.out.print("Número da conta: ");
        int numeroConta = lerInteiro(scanner);
        float valorDeposito;
    
        System.out.print("Valor do depósito: ");
        valorDeposito = lerFloat(scanner);
    
        if (valorDeposito < 0) {
            System.err.println("Por favor, insira um valor válido.");
            return;
        }
    
        System.out.println("Deseja confirmar? (sim/nao)");
        String opcao = scanner.nextLine().toLowerCase();
        scanner.nextLine(); // Consumir a quebra de linha
    
        if (opcao.equals("sim") || opcao.equals("si") || opcao.equals("s")) {
            for (ContaBancaria conta : contas) {
                if (conta.getNumConta() == numeroConta) {
                    conta.deposito(valorDeposito);
                    System.out.println("Depósito realizado com sucesso.");
                    return;
                }
            }
            System.out.println("Conta não encontrada.");
        } else if (opcao.equals("nao") || opcao.equals("n") ||opcao.equals("não")) {
            System.out.println("Operação de depósito cancelada.");
        } else {
            System.err.println("Opção inválida. Por favor, insira 'sim' ou 'nao'.");
        }
    }
    

    private static void realizarSaque(ArrayList<ContaBancaria> contas, Scanner scanner) {
        System.out.print("Número da conta: ");
        int numeroConta = lerInteiro(scanner);
        float valorSaque;
    
        System.out.print("Valor do saque: ");
        valorSaque = lerFloat(scanner);
    
        if (valorSaque < 0) {
            System.err.println("Por favor, insira um valor válido.");
            return;
        }
    
        System.out.println("Deseja confirmar? (sim/nao)");
        String opcao = scanner.nextLine().toLowerCase();
        scanner.nextLine(); // Consumir a quebra de linha
        
        if (opcao.equals("sim") || opcao.equals("si") || opcao.equals("s")) {
            for (ContaBancaria conta : contas) {
                if (conta.getNumConta() == numeroConta) {
                    if (conta instanceof ContaCorrenteComum) {
                        ContaCorrenteComum ccComum = (ContaCorrenteComum) conta;
                        if (ccComum.saquecomum(valorSaque, numeroConta)) {
                            System.out.println("Saque realizado com sucesso.");
                        } else {
                            System.out.println("Erro ao realizar o saque.");
                        }
                    } else if (conta instanceof ContaCorrentePremium) {
                        ContaCorrentePremium ccPremium = (ContaCorrentePremium) conta;
                        if (ccPremium.saquePremium(valorSaque, numeroConta)) {
                            System.out.println("Saque realizado com sucesso.");
                        } else {
                            System.out.println("Erro ao realizar o saque.");
                        }
                    } else if (conta instanceof ContaPoupanca) {
                        ContaPoupanca ccPou = (ContaPoupanca) conta;
                        if (ccPou.saquePoupanca(valorSaque, numeroConta)) {
                            System.out.println("Saque realizado com sucesso.");
                        } else {
                            System.out.println("Erro ao realizar o saque.");
                        }
                    } else if (conta instanceof ContaInvestimento) {
                        ContaInvestimento ccInv = (ContaInvestimento) conta;
                        if (ccInv.saqueinvestimento(valorSaque, numeroConta)) {
                            System.out.println("Saque realizado com sucesso.");
                        } else {
                            System.out.println("Erro ao realizar o saque.");
                        }
                    }
                    return;
                }
            }
            System.out.println("Conta não encontrada.");
        } else if (opcao.equals("nao") || opcao.equals("n") || opcao.equals("não")) {
            System.out.println("Operação de saque cancelada.");
        } else {
            System.err.println("Opção inválida. Por favor, insira 'sim' ou 'nao'.");
        }
    }

    private static void gerarExtrato(ArrayList<ContaBancaria> contas, Scanner scanner) {
        System.out.print("Número da conta: ");
        int numeroConta = lerInteiro(scanner);

        for (ContaBancaria conta : contas) {
            if (conta.getNumConta() == numeroConta) {
                if (conta instanceof ContaCorrenteComum) {
                    ContaCorrenteComum ccComum = (ContaCorrenteComum) conta;
                    ccComum.GerarExtratocomum();
                } else if (conta instanceof ContaCorrentePremium) {
                    ContaCorrentePremium ccPrem = (ContaCorrentePremium) conta;
                    ccPrem.GerarExtratoPremium();
                } else if (conta instanceof ContaPoupanca) {
                    ContaPoupanca ccPou = (ContaPoupanca) conta;
                    ccPou.GerarExtratoPoupanca();
                } else if (conta instanceof ContaInvestimento) {
                    ContaInvestimento ccInv = (ContaInvestimento) conta;
                    ccInv.GerarExtratoInvestimento();
                }
                return;
            }
        }

        System.out.println("Conta não encontrada.");
    }

    private static int obterNumeroContaUnico(Scanner scanner) {
        int numeroConta;
        do {
            System.out.print("Número da conta: ");
            numeroConta = lerInteiro(scanner);
            if (!numerosDeConta.contains(numeroConta)) {
                numerosDeConta.add(numeroConta);
                return numeroConta;
            } else {
                System.out.println("Número de conta já existe. Escolha outro número.");
            }
        } while (true);
    }
    private static int lerInteiro(Scanner scanner) {
        int valor = 0;
        boolean inputValido = false;
    
        while (!inputValido) {
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                inputValido = true;
            } else {
                System.out.println("Digite um número inteiro válido.");
                scanner.nextLine(); // Consumir a entrada inválida
            }
        }
    
        return valor;
    }
    
    private static float lerFloat(Scanner scanner) {
        float valor = 0;
        boolean inputValido = false;
    
        while (!inputValido) {
            if (scanner.hasNextFloat()) {
                valor = scanner.nextFloat();
                inputValido = true;
            } else {
                System.out.println("Digite um número de ponto flutuante válido.");
                scanner.nextLine(); // Consumir a entrada inválida
            }
        }
    
        return valor;
    }
    private static boolean contemApenasLetras(String str) {
        // Remover acentuação da string
        String semAcentos = Normalizer.normalize(str, Normalizer.Form.NFD)
                                .replaceAll("[^\\p{ASCII}]", "");

        // Verificar se a string sem acentos contém apenas letras e espaços
        if (semAcentos.matches("^[\\p{L} ]+$")) {
            return true;
        } else {
            System.out.println("Nome inválido. Digite um nome que contenha apenas letras (inclusive letras acentuadas e 'ç').");
            return false;
        }
    }
}
