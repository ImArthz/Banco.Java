import java.time.LocalDate;
import java.time.DateTimeException;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Banco {
    private String nome;
    private String endereco;
    private String telefone;
    private List<ContaBancaria> contas;
    public String getNome() {
        return nome;
    }

    // Getter para o endereço do banco
    public String getEndereco() {
        return endereco;
    }

    // Getter para o telefone do banco
    public String getTelefone() {
        return telefone;
    }

    public Banco(String nome, String endereco, String telefone) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.contas = new ArrayList<>();
    }

    public void criarConta(ContaBancaria conta) {
        contas.add(conta);
        System.out.println("Conta criada com sucesso! Número da conta: " + conta.getNumConta());
    }

    public boolean existeConta(int numeroConta) {
        for (ContaBancaria conta : contas) {
            if (conta.getNumConta() == numeroConta) {
                return true;
            }
        }
        return false;
    }

    public ContaBancaria encontrarConta(int numeroConta) {
        for (ContaBancaria conta : contas) {
            if (conta.getNumConta() == numeroConta) {
                return conta;
            }
        }
        return null; // Retorna nulo se a conta não for encontrada
    }

    public void fazerDeposito(int numeroConta, LocalDate data, float valorDeposito) {
        ContaBancaria conta = encontrarConta(numeroConta);
        if (conta != null) {
            conta.deposito(data, valorDeposito);
        } else {
            System.err.println("Erro: Conta não encontrada.");
        }
    }

    public void fazerSaque(int numeroConta, LocalDate data, float valorSaque) {
        ContaBancaria conta = encontrarConta(numeroConta);
        if (conta != null) {
            if (conta.saque(data, valorSaque)) {
                System.out.println("Saque efetuado com sucesso.");
            }
        } else {
            System.err.println("Erro: Conta não encontrada.");
        }
    }

    public void visualizarExtrato(int numeroConta) {
        ContaBancaria conta = encontrarConta(numeroConta);
        if (conta != null) {
            conta.GerarExtrato();
        } else {
            System.err.println("Erro: Conta não encontrada.");
        }
    }
}


