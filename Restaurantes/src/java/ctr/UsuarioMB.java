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
import model.Usuario;

/**
 *
 * @author Eder
 */
@ManagedBean
@ViewScoped
public class UsuarioMB implements Serializable {

    private Dao dao;
    private Usuario usuario;
    private List<Usuario> listaUsuario = new ArrayList<Usuario>();

    //Variaveis
    private String nome, perfil, user;

    public UsuarioMB() {
        dao = (Dao) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("dao");
        novo();
    }

    public void novo() {
        usuarioLogado3();
        System.out.println("perfil" + getUser());
    }

    public void usuarioLogado3() {
        List<Object[]> results = dao.usuarioLogado3();
        if (results.isEmpty()) {
            setNome("Administrador");
        } else {
            for (Object[] result : results) {
                setNome((String) result[0]);
                setUser((String) result[1]);
                setPerfil((String) result[2]);
            }

        }
    }

    public Dao getDao() {
        return dao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
