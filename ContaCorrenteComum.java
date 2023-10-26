import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;




class ContaCorrenteComum extends ContaBancaria {
    public ContaCorrenteComum(String nome, int numero_da_conta, float saldo) {
        super(nome, numero_da_conta, saldo);
    }
    
    public void GerarExtratocomum() {
    DecimalFormat df = new DecimalFormat("0.00"); // Formatação para duas casas decimais
        System.out.println("--------------------EXTRATO----------------------------");
        System.out.println("Numero da Conta : " + numero_da_conta );
        System.out.println("Nome do titular : "+ nome);
        System.out.println("Saldo : R$" + df.format(saldo)); // Formatando o saldo
        System.out.println("--------------------TRANSAÇÕES----------------------------");
        Imprimi_transacao(); // Reutilize o método de impressão de transações da classe pai
        System.out.println("----------------------------------------------------------");

    }
    
    
    public boolean saquecomum(float valorSaque, int numero_da_conta) {
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
            String descricao = "Saque efetuado na conta " + numero_da_conta + " Titular: " + nome + " na data: " + dataHoraAtual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + " no valor de R$" + valorSaque;

            Transacao transacao = new Transacao(dataHoraAtual, -valorSaque, descricao);
            transacoes.add(transacao);
            System.out.println("Saque de R$" + valorSaque + " realizado com sucesso na data: " + dataHoraAtual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

            return true; // Não é mais necessário verificar a validade da data, pois agora é a data/hora atual.
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
            return false;
        }
}
}