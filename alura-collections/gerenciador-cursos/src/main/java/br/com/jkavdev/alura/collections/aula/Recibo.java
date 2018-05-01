package br.com.jkavdev.alura.collections.aula;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Recibo implements Comparable<Recibo> {

    public static final String HORA_DIA = "HH:mm:ss dd/MM/yyyy";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(HORA_DIA);

    private LocalDateTime horaRecibo;
    private Integer valor;

    public Recibo(LocalDateTime horaRecibo, Integer valor) {
        Objects.requireNonNull(horaRecibo);
        Objects.requireNonNull(valor);
        this.horaRecibo = horaRecibo;
        this.valor = valor;
    }

    public LocalDateTime getHoraRecibo() {
        return horaRecibo;
    }

    public Integer getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "{" +
                "horario=" + horarioFormatado() +
                ", valor=" + valor +
                '}';
    }

    private String horarioFormatado() {
        String horario = horaRecibo.format(FORMATTER);
        String[] splited = horario.split(" ");
        return "às " + splited[0] + " de " + splited[1];
    }

    @Override
    public int compareTo(Recibo outro) {
        return getHoraRecibo().compareTo(outro.getHoraRecibo());
    }
}
