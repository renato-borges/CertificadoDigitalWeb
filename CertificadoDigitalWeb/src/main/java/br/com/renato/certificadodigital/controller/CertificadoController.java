package br.com.renato.certificadodigital.controller;

import br.com.renato.certificadodigital.models.Certificados;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import com.sun.jersey.api.client.WebResource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;
import javax.ws.rs.core.MediaType;

/**
 * Controller responsavel por comunicar com a API.
 *
 * @author Renato
 */
@ManagedBean(name = "certificado")
@ViewScoped
public class CertificadoController extends AbstractController {

    private Certificados certificado = new Certificados();
    private List<Certificados> certificados = new ArrayList<>();

    private javax.servlet.http.Part certificadoDigital;

    private Boolean renderizarForm = true;
    private Boolean renderizarDetalhes = false;

    /**
     * Metodo salva o certificado
     */
    public void salvar() {
        certificado.setCertificado(certificadoDigital.getSubmittedFileName());
        WebResource webResource = client.resource(URL + "certificado");
        webResource.type(MediaType.APPLICATION_JSON).accept("application/json")
                .post(gson.toJson(certificado));

        this.voltar();
    }

    /**
     * Metodo que altera o certificado.
     */
    public void alterar() {
        WebResource webResource = client.resource(URL + "certificado");
        webResource.type("application/json").accept("application/json")
                .put(gson.toJson(certificado));
    }

    /**
     * Metodo que deleta o certificado.
     */
    public void deletar() {
        WebResource webResource = client.resource(URL + "certificado" + certificado.getId());
        webResource.delete();
        this.voltar();
    }

    /**
     * Recupera todos os certificados.
     *
     * @return
     */
    public List<Certificados> getAll() {
        if (certificados.isEmpty()) {
            WebResource webResource = client.resource(URL + "certificado");
            String json = webResource.get(String.class);
            certificados = gson.fromJson(json, new TypeToken<List<Certificados>>() {
            }.getType());
            System.out.println(certificados);
        }
        return certificados;
    }

    /**
     * Apresenta os detalhes do certificado selecionado e esconde a tabela
     *
     * @param certificado
     */
    public void mostrarDetalhes(Certificados certificado) {
        this.certificado = certificado;

        renderizarForm = false;
        renderizarDetalhes = true;
    }

    /**
     * Apresenta a tabela de certificados e esconde os detalhes do certificado
     * selecionado.
     */
    public void voltar() {
        certificado = new Certificados();
        renderizarForm = true;
        renderizarDetalhes = false;
        certificados = new ArrayList<>();
        this.getAll();
    }

    /**
     * Valida o tamnha do arquivo é se algum arquivo foi selecionado.
     * 
     * @param ctx
     * @param comp
     * @param value 
     */
    public void validateFile(FacesContext ctx, UIComponent comp, Object value) {
        List<FacesMessage> msgs = new ArrayList<>();
        Part file = (Part) value;
        if (file.getSize() > 1024) {
            msgs.add(new FacesMessage("Arquivo Muito Grande"));
        }
        if (!"text/plain".equals(file.getContentType())) {
            msgs.add(new FacesMessage("Nenhum arquivo selecionado."));
        }
        if (!msgs.isEmpty()) {
            throw new ValidatorException(msgs);
        }
    }

    public Certificados getCertificado() {
        return certificado;
    }

    public void setCertificado(Certificados certificado) {
        this.certificado = certificado;
    }

    public List<Certificados> getCertificados() {
        return certificados;
    }

    public void setCertificados(List<Certificados> certificados) {
        this.certificados = certificados;
    }

    public Part getCertificadoDigital() {
        return certificadoDigital;
    }

    public void setCertificadoDigital(Part certificadoDigital) {
        this.certificadoDigital = certificadoDigital;
    }

    public Boolean getRenderizarForm() {
        return renderizarForm;
    }

    public void setRenderizarForm(Boolean renderizarForm) {
        this.renderizarForm = renderizarForm;
    }

    public Boolean getRenderizarDetalhes() {
        return renderizarDetalhes;
    }

    public void setRenderizarDetalhes(Boolean renderizarDetalhes) {
        this.renderizarDetalhes = renderizarDetalhes;
    }

}
