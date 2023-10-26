import java.time.LocalDateTime;

class Transacao {
    private LocalDateTime dataHora;
    private float valor;
    private String descricao;

    public Transacao(LocalDateTime dataHora, float valor, String descricao) {
        this.dataHora = dataHora;
        this.valor = valor;
        this.descricao = descricao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public float getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }
}