import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class ContaBancaria {
    protected String nome;
    protected int numero_da_conta;
    protected float saldo;
    protected List<Transacao> transacoes;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumConta() {
        return numero_da_conta;
    }

    public void setNumConta(int numero_da_conta) {
        this.numero_da_conta = numero_da_conta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public ContaBancaria(String nome, int numero_da_conta, float saldo) {
        this.nome = nome;
        this.numero_da_conta = numero_da_conta;
        this.saldo = saldo;
        this.transacoes = new ArrayList<>();
    }

    public void adicionarTransacao(LocalDate data, float valor, String descricao) {
        Transacao transacao = new Transacao(data, valor, descricao);
        transacoes.add(transacao);
    }

    public void deposito(LocalDate data, float valorDeposito) {
        try {
            if (valorDeposito <= 0) {
                throw new IllegalArgumentException("O valor do depósito deve ser maior que zero.");
            }
            this.saldo += valorDeposito;
            String descricao = "Depósito efetuado na conta " + numero_da_conta + " Titular: " + nome + " na data: " + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " no valor de R$" + valorDeposito;
            Transacao transacao = new Transacao(data, valorDeposito, descricao);
            transacoes.add(transacao);
            System.out.println("Depósito de R$" + valorDeposito + " realizado com sucesso na data: " + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
    public boolean Imprimi_transacao(){
        try {
        if (transacoes.isEmpty()) {
            System.out.println("Não há transações para imprimir.");
            return true; // Indica que a operação foi bem-sucedida
        }

        for (Transacao transacao : transacoes) {
            System.out.println("Data: " + transacao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Valor: R$" + transacao.getValor());
            System.out.println("Descrição: " + transacao.getDescricao());
            System.out.println("---------------------------------------");
        }

        return true; // Indica que a operação foi bem-sucedida
    } catch (Exception e) {
        System.err.println("Erro: " + e.getMessage());
        return false; // Indica que ocorreu um erro durante a operação
    }
        
    }
}