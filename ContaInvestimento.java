import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class ContaInvestimento extends ContaBancaria {
    protected float taxaRetornoAnual;

    public ContaInvestimento(String nome, int numero_da_conta, float saldo, float taxaRetornoAnual) {
        super(nome, numero_da_conta, saldo);
        this.taxaRetornoAnual = taxaRetornoAnual;
    }

    public float getTaxaRetornoAnual() {
        return taxaRetornoAnual;
    }

    public void setTaxaRetornoAnual(float taxaRetornoAnual) {
        this.taxaRetornoAnual = taxaRetornoAnual;
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

    public void GerarExtrato() {
        System.out.println("--------------------EXTRATO----------------------------");
        System.out.println("Numero da Conta: " + numero_da_conta);
        System.out.println("Nome do Titular: " + nome);
        System.out.println("Saldo: " + saldo);
        System.out.println("Taxa de Retorno Anual: " + taxaRetornoAnual + "%");
        Imprimi_transacao(); // Reutilize o método de impressão de transações da classe pai
    }
}
