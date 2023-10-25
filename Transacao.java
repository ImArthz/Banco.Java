import java.time.LocalDate;

class Transacao {
    private LocalDate data;
    private float valor;
    private String descricao;

    public Transacao(LocalDate data, float valor, String descricao) {
        this.data = data;
        this.valor = valor;
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public float getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }
}