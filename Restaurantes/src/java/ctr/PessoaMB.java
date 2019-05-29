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
import model.Pessoa;
import model.Usuario;
import util.FacesUtil;

/**
 *
 * @author Eder
 */
@ManagedBean
@ViewScoped
public class PessoaMB implements Serializable{
    
    // Classe
    private Pessoa pessoa;
    private Dao dao;
    private Usuario usuario;
    
    // Lista
    private List<Pessoa> listaPessoa = new ArrayList<Pessoa>();
    
    // variaveis
    private String nome;
    
    public void PessoaMB(){
        dao = new Dao();
    //dao = (Dao) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("dao");
    novo();
    }
    public void novo(){
        pessoa      = new Pessoa();
        usuario     = new Usuario();
        listaPessoa = new ArrayList<Pessoa>();
        pessoa.setUsuario(new Usuario());
    }

    public void gravar (ActionEvent evt)
    {
        try {
            System.out.println("======" + getNome());
//            pessoa.setNome(getNome());
//            //usuario.setNome(pessoa.getCpf());
//            //usuario.setSenha(pessoa.getSenha());
//            //usuario.setPerfil("CLIENTE");
//            dao.gravar(usuario);
//            //pessoa.setUsuario(new Usuario());
//            dao.gravar(pessoa);
//            /*listarMarcaVeiculo = (List<Marca>) dao.buscarTodos(Marca.class);*/
//            pessoa = new Pessoa();
             FacesUtil.addInfoMessage("Informação", "Cadastro realizado com sucesso!");
        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Erro", "Entre em contato com suporte!");
            ex.printStackTrace();
        }
    }
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getListaPessoa() {
        return listaPessoa;
    }

    public void setListaPessoa(List<Pessoa> listaPessoa) {
        this.listaPessoa = listaPessoa;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
    
}
