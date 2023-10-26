# Código de Contas Bancárias

Neste repositório, você encontrará um conjunto de classes em Java que modelam contas bancárias, um banco e um programa principal que interage com essas classes para criar contas, fazer depósitos, saques e visualizar extratos.

## Diagrama de Classes

Abaixo está o diagrama de classes do projeto:

![Diagrama de Classes]((https://github.com/ImArthz/Banco.Java/blob/main/DiagramaClasses.png)

## Estrutura do Código

- `ContaBancaria`: Classe base para representar uma conta bancária. Contém métodos para adicionar transações, realizar depósitos, sacar dinheiro e gerar extratos.

- `ContaCorrenteComum`: Classe que herda de `ContaBancaria` e representa uma conta corrente comum.

- `ContaCorrentePremium`: Classe que herda de `ContaBancaria` e representa uma conta corrente premium.

- `ContaPoupanca`: Classe que herda de `ContaBancaria` e representa uma conta poupança.

- `ContaInvestimento`: Classe que herda de `ContaBancaria` e representa uma conta de investimento.

- `Transacao`: Classe que representa uma transação bancária, incluindo a data, o valor e a descrição.

- `Banco`:  O programa principal que interage com o usuário para criar contas e realizar operações bancárias.


## Como Executar

1. Certifique-se de que você possui o Java JDK instalado em seu sistema.

2. Use o makefile fornecido para compilar o código. Execute o seguinte comando no terminal:

```bash
  make
```

3. Após a compilação bem-sucedida, execute o programa principal com o seguinte comando:

```bash
make run
```

4. Siga as instruções no programa para criar contas, fazer depósitos, saques e visualizar extratos.

## Observações

- O código foi escrito em Java e fornece uma estrutura básica para operações bancárias.

- Cada tipo de conta (Conta Corrente Comum, Conta Corrente Premium, Conta Poupança e Conta de Investimento) tem seu próprio comportamento e métodos específicos.

- Os extratos das contas exibem as transações registradas.

- Este é um exemplo simplificado e pode ser expandido para incluir mais recursos e validações.

Sinta-se à vontade para explorar e modificar o código de acordo com suas necessidades.

Para sugestões, melhorias ou correções, sinta-se à vontade para contribuir para este repositório.
## Dados

**Autor:** Arthur De Oliveira Mendonça 

**Redes Sociais:**

* [GitHub](https://github.com/ImArthz)
* [Twitter](https://twitter.com/Im_Arthz)

**Contato:**

* [WhatsApp](https://api.whatsapp.com/send?phone=37988528423)
* [Discord](https://discordapp.com/users/imarthz)
