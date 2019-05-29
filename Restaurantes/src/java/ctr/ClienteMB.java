/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctr;

import dao.Dao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import model.Cliente;
import model.Marca;
import util.FacesUtil;

/**
 *
 * @author enascimento
 */@ManagedBean
@SessionScoped
public class ClienteMB implements Serializable{
    private Cliente cliente;
    private Dao dao;
    private String senha;
    
    public ClienteMB() {
        cliente = new Cliente();
        dao = new Dao();
        
       } 
        public void gravar (ActionEvent evt)
    {
        try {
            dao.gravar(cliente);
            /*listarMarcaVeiculo = (List<Marca>) dao.buscarTodos(Marca.class);*/
            cliente = new Cliente();
             FacesUtil.addInfoMessage("Informação", "Cliente salvo com sucesso!");
        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Erro", "Entre em contato com suporte!");
            ex.printStackTrace();
        }
    
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Dao getDao() {
        return dao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
    
    
}
