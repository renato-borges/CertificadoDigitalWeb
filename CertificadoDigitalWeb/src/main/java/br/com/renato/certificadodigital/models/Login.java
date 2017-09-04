package br.com.renato.certificadodigital.models;

import java.io.Serializable;

/**
 *
 * @author Renato
 */
public class Login implements Serializable {

    private static final long serialVersionUID = -9022213966353091762L;

    private String mensagem;
    private String password;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
