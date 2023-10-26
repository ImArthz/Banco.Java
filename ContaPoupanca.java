import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;




class ContaPoupanca extends ContaBancaria {
    protected LocalDate diaAniversario;

    public ContaPoupanca(String nome, int numero_da_conta, float saldo, LocalDate diaAniversario) {
        super(nome, numero_da_conta, saldo);
        this.diaAniversario = diaAniversario;
    }

    public void setDiaAniversario(LocalDate diaAniversario) {
        this.diaAniversario = diaAniversario;
    }

    public LocalDate getDiaAniversario() {
        return diaAniversario;
    }
    
    public boolean saquePoupanca(float valorSaque, int numero_da_conta) {
        try {
            if (valorSaque < 0) {
                System.err.println("Erro: O valor do saque deve ser maior que zero.");
                return false;
            }

            if (saldo - valorSaque <= 0) {
                System.err.println("Erro: Saldo insuficiente para o saque.");
                return false;
            }

            // Obter a data/hora atual
            LocalDateTime dataHoraAtual = LocalDateTime.now();
            String descricao = "Saque efetuado na conta " + numero_da_conta + " Titular: " + nome + " na data e hora: " + dataHoraAtual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + " no valor de R$" + valorSaque;

            Transacao transacao = new Transacao(dataHoraAtual, -valorSaque, descricao);
            transacoes.add(transacao);
            System.out.println("Saque de R$" + valorSaque + " realizado com sucesso na data e hora: " + dataHoraAtual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

            return true;
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
            return false;
        }
}
    
    public void GerarExtratoPoupanca() {
        DecimalFormat df = new DecimalFormat("0.00"); // Formatação para duas casas decimais
        System.out.println("--------------------EXTRATO----------------------------");
        System.out.println("Número da Conta: " + numero_da_conta);
        System.out.println("Nome do titular: " + nome);
        System.out.println("Saldo : R$" + df.format(saldo)); // Formatando o saldo
        System.out.println("Dia do Aniversário: " + diaAniversario.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        Imprimi_transacao(); // Reutilize o método de impressão de transações da classe pai
    }
}