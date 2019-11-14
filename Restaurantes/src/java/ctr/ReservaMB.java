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
import javax.faces.model.SelectItem;
import model.Empresa;
import model.Horario;
import model.Inventario;
import model.Reserva;

/**
 *
 * @author Eder
 */
@ManagedBean
@ViewScoped
public class ReservaMB implements Serializable {

    private Dao dao;
    private Reserva reserva;
    private Empresa empresa;
    private Horario horario;
    private boolean cafe, almoco, jantar, selecionouCafe, SelecionouAlmoco, selecionouJantar;

    private List<Empresa> listaEmpresa = new ArrayList<Empresa>();
    private List<Reserva> listaReserva = new ArrayList<Reserva>();
    private List<Horario> listaHorario = new ArrayList<Horario>();
    private List<Horario> listaCafe = new ArrayList<Horario>();
    private List<Horario> listaAlmoco = new ArrayList<Horario>();
    private List<Horario> listaJantar = new ArrayList<Horario>();
    private List lista;
    private List<SelectItem> selectCafe;
    private List<SelectItem> selectAlmoco;
    private List<SelectItem> selectJantar;

    public ReservaMB() {
        dao = (Dao) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("dao");
        novo();
    }

    public void novo() {
        reserva = new Reserva();
        horario = new Horario();
        empresa = new Empresa();
        listaReserva = new ArrayList<Reserva>();
        listaEmpresa = new ArrayList<Empresa>();
        horarios();
    }

    public List<SelectItem> getSelectCafe() {
        selectCafe = new ArrayList<SelectItem>();
        listaEmpresa = new ArrayList<Empresa>();
        listaEmpresa = (List<Empresa>) dao.buscarRestaurantePorOrdem();
        selectCafe.add(new SelectItem(0, "Selecione"));
        for (Empresa c : listaEmpresa) {
            selectCafe.add(new SelectItem(c.getIdEmpresa(), c.getNomeRestaurante()));
        }

        return selectCafe;
    }

    public void horarios() {
        listaHorario = new ArrayList<Horario>();
        listaHorario = (List<Horario>) dao.buscarHorario();
        double iniCafe = 0, fimCafe = 0, iniAlmoco = 0, fimAlmoco = 0, iniJantar = 0, fimJantar = 0;
        for (Horario c : listaHorario) {
            if (c.getDescricao().equals("CAFE DA MANHA")) {
                iniCafe = Double.parseDouble(c.getHorarioInicio().replaceAll(":", "."));
                fimCafe = Double.parseDouble(c.getHorarioFim().replaceAll(":", "."));

            } else if (c.getDescricao().equals("ALMOÇO")) {
                iniAlmoco = Double.parseDouble(c.getHorarioInicio().replaceAll(":", "."));
                fimAlmoco = Double.parseDouble(c.getHorarioFim().replaceAll(":", "."));

            } else if (c.getDescricao().equals("JANTAR")) {
                iniJantar = Double.parseDouble(c.getHorarioInicio().replaceAll(":", "."));
                fimJantar = Double.parseDouble(c.getHorarioFim().replaceAll(":", "."));
            }
        }
        for (double i = iniCafe; i <= fimCafe; i++) {

        }
    }

    public List<SelectItem> getSelectAlmoco() {
        return selectAlmoco;
    }

    public List<SelectItem> getSelectJantar() {
        return selectJantar;
    }

    public Dao getDao() {
        return dao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Empresa> getListaEmpresa() {
        return listaEmpresa;
    }

    public void setListaEmpresa(List<Empresa> listaEmpresa) {
        this.listaEmpresa = listaEmpresa;
    }

    public List<Reserva> getListaReserva() {
        return listaReserva;
    }

    public void setListaReserva(List<Reserva> listaReserva) {
        this.listaReserva = listaReserva;
    }

    public boolean isCafe() {
        return cafe;
    }

    public void setCafe(boolean cafe) {
        this.cafe = cafe;
    }

    public boolean isAlmoco() {
        return almoco;
    }

    public void setAlmoco(boolean almoco) {
        this.almoco = almoco;
    }

    public boolean isJantar() {
        return jantar;
    }

    public void setJantar(boolean jantar) {
        this.jantar = jantar;
    }

    public boolean isSelecionouCafe() {
        return selecionouCafe;
    }

    public void setSelecionouCafe(boolean selecionouCafe) {
        this.selecionouCafe = selecionouCafe;
    }

    public boolean isSelecionouAlmoco() {
        return SelecionouAlmoco;
    }

    public void setSelecionouAlmoco(boolean SelecionouAlmoco) {
        this.SelecionouAlmoco = SelecionouAlmoco;
    }

    public boolean isSelecionouJantar() {
        return selecionouJantar;
    }

    public void setSelecionouJantar(boolean selecionouJantar) {
        this.selecionouJantar = selecionouJantar;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public List<Horario> getListaHorario() {
        return listaHorario;
    }

    public void setListaHorario(List<Horario> listaHorario) {
        this.listaHorario = listaHorario;
    }

    public List<Horario> getListaCafe() {
        return listaCafe;
    }

    public void setListaCafe(List<Horario> listaCafe) {
        this.listaCafe = listaCafe;
    }

    public List<Horario> getListaAlmoco() {
        return listaAlmoco;
    }

    public void setListaAlmoco(List<Horario> listaAlmoco) {
        this.listaAlmoco = listaAlmoco;
    }

    public List<Horario> getListaJantar() {
        return listaJantar;
    }

    public void setListaJantar(List<Horario> listaJantar) {
        this.listaJantar = listaJantar;
    }

    public List getLista() {
        return lista;
    }

    public void setLista(List lista) {
        this.lista = lista;
    }

}
