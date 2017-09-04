package br.com.renato.certificadodigital.models;

import java.io.Serializable;


/**
 * Classe de entidade do certificado.
 *
 * @author Renato
 */
public class Certificados implements Serializable {

    private static final long serialVersionUID = -3049409289512929057L;

    private Integer id;

    private String nome;

    private String certificado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCertificado() {
        return certificado;
    }

    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }

}
