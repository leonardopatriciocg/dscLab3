package br.ufpb.dsc.lab3.excecoes;

public class UsuarioInvalidoException extends IllegalArgumentException {

    public UsuarioInvalidoException(String s) {
        super(s);
    }

    public UsuarioInvalidoException() {
        super();
    }
}
