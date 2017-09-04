package br.com.renato.certificadodigital.controller;

import br.com.renato.certificadodigital.models.Login;
import com.sun.jersey.api.client.WebResource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

/**
 * Controller responsavel por comunicar com a api de login
 *
 * @author Renato
 */
@ManagedBean(name = "mblogin")
@ViewScoped
public class LoginController extends AbstractController {

    private Login login = new Login();

    /**
     * Metodo realiza o login.
     *
     * @return
     */
    public String login() {
        WebResource webResource = client.resource(URL + "login");
        webResource.type(MediaType.APPLICATION_JSON).accept("application/json")
                .post(gson.toJson(login));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance()
                .getExternalContext().getResponse();
        response.addHeader("HMAC-Authentication", "Authentication");

        return "certificado/detalhes";
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

}
