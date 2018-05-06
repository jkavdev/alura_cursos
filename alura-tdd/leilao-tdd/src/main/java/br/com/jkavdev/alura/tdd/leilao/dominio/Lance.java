package br.com.jkavdev.alura.tdd.leilao.dominio;

public class Lance {

    private Usuario usuario;
    private double valor;

    public Lance(Usuario usuario, double valor) {
        this.usuario = usuario;
        this.valor = valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public double getValor() {
        return valor;
    }

    public boolean isUsuariosIguais(Usuario outro){
        return usuario.equals(outro);
    }

    @Override
    public String toString() {
        return "{" +
                "usuario=" + usuario +
                ", valor=" + valor +
                '}';
    }
}
