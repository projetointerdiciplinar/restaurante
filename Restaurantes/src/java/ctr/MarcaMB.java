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
import model.Marca;
import util.FacesUtil;

/**
 *
 * @author enascimento
 */
@ManagedBean
@ViewScoped
public class MarcaMB implements Serializable {
    
    private Marca marca;
    private Dao dao = new Dao();
    List<Marca> listarMarca= new ArrayList<Marca>();

    public MarcaMB() {
        marca = new  Marca();
        listarMarca= new ArrayList<Marca>();
        listarMarca = (List<Marca>) dao.buscarTodos(Marca.class);
    }
    
    public void gravar (ActionEvent evt)
    {
        try {
            marca.toUpperCase();
            dao.gravar(marca);
            /*listarMarcaVeiculo = (List<Marca>) dao.buscarTodos(Marca.class);*/
            marca = new Marca();
             FacesUtil.addInfoMessage("Informação", "Marca salva com sucesso!");
        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Erro", "Entre em contato com suporte!");
            ex.printStackTrace();
        }
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Dao getDao() {
        return dao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    public List<Marca> getListarMarca() {
        return listarMarca;
    }

    public void setListarMarca(List<Marca> listarMarca) {
        this.listarMarca = listarMarca;
    }
    
}
