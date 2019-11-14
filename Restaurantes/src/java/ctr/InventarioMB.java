/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctr;

import dao.Dao;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import model.Empresa;
import model.Inventario;
import model.Usuario;
import util.ArquivoUtil;
import util.FacesUtil;

/**
 *
 * @author Eder
 */
@ManagedBean
@ViewScoped
public class InventarioMB implements Serializable {

    private Dao dao;
    private Inventario inventario;
    private Empresa empresa;
    private List<Empresa> listaEmpresa = new ArrayList<Empresa>();
    private List<Inventario> listaInventario = new ArrayList<Inventario>();
    private Integer idEmpresa;
    private String nomeEmpresa;
    private List<SelectItem> selectEmpresa;

    public InventarioMB() {
        dao = (Dao) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("dao");
        novo();
    }

    public void novo() {
        inventario = new Inventario();
        inventario.setEmpresa(new Empresa());
        listaInventario = new ArrayList<Inventario>();
        listaEmpresa = new ArrayList<Empresa>();
        listaInventario = (List<Inventario>) dao.buscarInventarioPorOrdem();
    }

    public void gravar(ActionEvent evt) {
        try {
            inventario.toUpperCase();
            dao.gravar(inventario);
            FacesUtil.addInfoMessage("Informação", "Salvo com sucesso!");
            novo();
        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Erro", "Entre em contato com suporte!");
            ex.printStackTrace();
        }
    }

    public List<SelectItem> getSelectEmpresa() {
        selectEmpresa = new ArrayList<SelectItem>();
        listaEmpresa = new ArrayList<Empresa>();
        listaEmpresa = (List<Empresa>) dao.buscarRestaurantePorOrdem();
        selectEmpresa.add(new SelectItem(0, "Selecione"));
        for (Empresa c : listaEmpresa) {
            selectEmpresa.add(new SelectItem(c.getIdEmpresa(), c.getNomeRestaurante()));
        }
        return selectEmpresa;
    }
    public void alterar() {
        inventario = (Inventario) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("inventario");       
    }
    public void excluir(ActionEvent evt) {
        try {
            inventario = (Inventario) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("inventario"); 
            dao.remover(inventario);
            FacesUtil.addInfoMessage("Informação", "Excluir com sucesso!");
            novo();
        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Erro", "Entre em contato com suporte!");
            ex.printStackTrace();
        }
    }

    public Dao getDao() {
        return dao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public List<Inventario> getListaInventario() {
        return listaInventario;
    }

    public void setListaInventario(List<Inventario> listaInventario) {
        this.listaInventario = listaInventario;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setListaEmpresa(List<Empresa> listaEmpresa) {
        this.listaEmpresa = listaEmpresa;
    }

}
