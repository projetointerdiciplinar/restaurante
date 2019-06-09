/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctr;

import dao.Dao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import model.Empresa;
import model.Usuario;
import org.primefaces.model.UploadedFile;
import util.FacesUtil;

/**
 *
 * @author enascimento
 */
@ManagedBean
@ViewScoped
public class EmpresaMB implements Serializable{
    private Empresa empresa;
    private Usuario usuario;
    private Dao dao;
    private UploadedFile uploadedFile;
    private List<Empresa> listaEmpresa = new ArrayList<Empresa>();
    private List<Usuario> listaUsuario = new ArrayList<Usuario>();
    
    private String cnpj, user;
    private Integer idUser;
    
    public EmpresaMB(){
        dao = (Dao) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("dao");
        novo();
    }
    public void novo(){
        empresa      = new Empresa();
        usuario      = new Usuario();
        listaEmpresa = new ArrayList<Empresa>();
        listaUsuario = new ArrayList<Usuario>();
        listaUsuario = (List<Usuario>) dao.usuarioLogado2();
        listaEmpresa = (List<Empresa>) dao.buscarTodos(Empresa.class);
        System.out.println("===========" + listaUsuario.get(0).getUsuario());
        setUser(listaUsuario.get(0).getUsuario());
        setIdUser(listaUsuario.get(0).getIdUsuario());
    }
    
    public void gravar (ActionEvent evt)
    {
        try {
            usuario.setIdUsuario(getIdUser());
            empresa.setUsuario(usuario);
            dao.gravar(empresa);
            /*listarMarcaVeiculo = (List<Marca>) dao.buscarTodos(Marca.class);*/
            empresa = new Empresa();
             FacesUtil.addInfoMessage("Informação", "Cadastro salvo com sucesso!");
             novo();
        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Erro", "Entre em contato com suporte!");
            ex.printStackTrace();
        }
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Dao getDao() {
        return dao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    public List<Empresa> getListaEmpresa() {
        return listaEmpresa;
    }

    public void setListaEmpresa(List<Empresa> listaEmpresa) {
        this.listaEmpresa = listaEmpresa;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
    
    
}
