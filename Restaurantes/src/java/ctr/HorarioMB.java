/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctr;

import dao.Dao;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import model.Empresa;
import model.Horario;
import util.FacesUtil;

/**
 *
 * @author Eder
 */
@ManagedBean
@ViewScoped
public class HorarioMB implements Serializable {

    private Empresa empresa;
    private Horario horario;
    private Dao dao;

    private List<Empresa> listaEmpresa = new ArrayList<Empresa>();
    private List<Horario> listaHorario = new ArrayList<Horario>();
    
    private List<SelectItem> selectEmpresa;
    private Date horarioInic;
    private Date horarioFim;

    public HorarioMB() {
        dao = (Dao) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("dao");
        novo();
    }

    public void novo() {
        horario = new Horario();
        horario.setEmpresa(new Empresa());
        listaHorario = new ArrayList<Horario>();
        listaEmpresa = new ArrayList<Empresa>();
        listaHorario = (List<Horario>) dao.buscarHorarioPorOrdem();
        setHorarioFim(null);
        setHorarioInic(null);
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
    
    public void gravar(ActionEvent evt) {
        try {
            SimpleDateFormat hora = new SimpleDateFormat("HH:mm");
            //System.out.println("data: " + hora.format(getHorarioSelecionado()));
            horario.setHorarioInicio(hora.format(getHorarioInic()));
            horario.setHorarioFim(hora.format(getHorarioFim()));
            dao.gravar(horario);
            FacesUtil.addInfoMessage("Informação", "Salvo com sucesso!");
            novo();
        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Erro", "Entre em contato com suporte!");
            ex.printStackTrace();
        }
    }
     public void alterar() {
        horario = (Horario) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("hor");       
    }
    public void excluir(ActionEvent evt) {
        try {
            horario = (Horario) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("hor"); 
            dao.remover(horario);
            FacesUtil.addInfoMessage("Informação", "Excluir com sucesso!");
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

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
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

    public List<Horario> getListaHorario() {
        return listaHorario;
    }

    public void setListaHorario(List<Horario> listaHorario) {
        this.listaHorario = listaHorario;
    }

    public Date getHorarioInic() {
        return horarioInic;
    }

    public void setHorarioInic(Date horarioInic) {
        this.horarioInic = horarioInic;
    }

    public Date getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(Date horarioFim) {
        this.horarioFim = horarioFim;
    }

    
    
}
