import java.util.Scanner;
import java.time.LocalDate;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;



class ContaCorrenteComum extends ContaBancaria {
    public ContaCorrenteComum(String nome, int numero_da_conta, float saldo) {
        super(nome, numero_da_conta, saldo);
    }

    public void GerarExtrato() {
    DecimalFormat df = new DecimalFormat("0.00"); // Formatação para duas casas decimais
        System.out.println("--------------------EXTRATO----------------------------");
        System.out.println("Numero da Conta : " + numero_da_conta );
        System.out.println("Nome do titular : "+ nome);
        System.out.println("Saldo : R$" + df.format(saldo)); // Formatando o saldo
        Imprimi_transacao(); // Reutilize o método de impressão de transações da classe pai
    }

    public boolean saque(float valorSaque) {
        try {
            if (valorSaque < 0) {
                System.err.println("Erro: O valor do saque deve ser maior que zero.");
                return false;
            }

            if (saldo - valorSaque <= 0) {
                System.err.println("Erro: Saldo insuficiente para o saque.");
                return false;
            }

            // Solicita a data do saque ao usuário
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite o dia do saque (dd): ");
            int dia = scanner.nextInt();
            System.out.print("Digite o mês do saque (MM): ");
            int mes = scanner.nextInt();
            System.out.print("Digite o ano do saque (yyyy): ");
            int ano = scanner.nextInt();
            scanner.close();

            boolean dataValida = true;
            try {
                LocalDate data = LocalDate.of(ano, mes, dia);
                String descricao = "Saque efetuado na conta " + numero_da_conta + " Titular: " + nome + " na data: " + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " no valor de R$" + valorSaque;
                Transacao transacao = new Transacao(data, -valorSaque, descricao);
                transacoes.add(transacao);
                System.out.println("Saque de R$" + valorSaque + " realizado com sucesso na data: " + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            } catch (Exception e) {
                System.err.println("Erro: Data de saque inválida.");
                dataValida = false;
            }

            return dataValida;
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
            return false;
        }
    }
}