import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    
    public boolean saqueinvestimento(float valorSaque, int numero_da_conta) {
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
    
    public void GerarExtratoInvestimento() {
        DecimalFormat df = new DecimalFormat("0.00"); // Formatação para duas casas decimais
        System.out.println("--------------------EXTRATO----------------------------");
        System.out.println("Numero da Conta: " + numero_da_conta);
        System.out.println("Nome do Titular: " + nome);
        System.out.println("Saldo: " + df.format(saldo));
        System.out.println("Taxa de Retorno Anual: " + taxaRetornoAnual + "%");
        System.out.println("--------------------TRANSAÇÕES----------------------------");
        Imprimi_transacao(); // Reutilize o método de impressão de transações da classe pai
        System.out.println("----------------------------------------------------------");
    }
}
