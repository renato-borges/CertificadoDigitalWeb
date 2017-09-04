package br.com.renato.certificadodigital.controller;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;

/**
 * Controller abstrata que contem os objtos em comum.
 *
 * @author Renato
 */
public abstract class AbstractController {

    public String URL = "http://localhost:8080/RestEJB1/api/";

    public Gson gson = new Gson();
    public Client client = Client.create();
}
