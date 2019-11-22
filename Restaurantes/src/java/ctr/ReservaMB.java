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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import model.Empresa;
import model.Horario;
import model.Reserva;
import model.Usuario;
import util.FacesUtil;

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
    private Usuario usuario;
    private Horario horario;
    //private boolean cafe, almoco, jantar, selecionouCafe, SelecionouAlmoco, selecionouJantar;
    private String horario1, horario2;
    private boolean bloqHorario;
    private int idEmpresa, totPessoaDis;

    private List<Empresa> listaEmpresa = new ArrayList<Empresa>();
    private List<Reserva> listaReserva = new ArrayList<Reserva>();
    private List<Reserva> listaReservaCliente = new ArrayList<Reserva>();
    private List<Reserva> listaReservaClienteEmpresa = new ArrayList<Reserva>();

    public ReservaMB() {
        dao = (Dao) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("dao");
        novo();
    }

    public void novo() {
        reserva = new Reserva();
        horario = new Horario();
        empresa = new Empresa();
        usuario = new Usuario();
        listaReserva = new ArrayList<Reserva>();
        listaEmpresa = new ArrayList<Empresa>();
        listaEmpresa = (List<Empresa>) dao.buscarRestaurantePorOrdem2();
        //horarios();
        reservaCliente();
        reservaClienteEmpresa();

    }

    public void bloquearHorario() {
        Date data = new Date();
        SimpleDateFormat hora = new SimpleDateFormat("hh24");
        int h = Integer.parseInt(hora.format(data));
        System.out.println("hora: " + h);
        if (h > 15) {
            setBloqHorario(true);
        }

    }

    public Date getMaxAge() {
        Calendar currentDate = Calendar.getInstance();
        //logger.info("Max Age: " + currentDate.get(Calendar.MONTH) + "/" + currentDate.get(Calendar.DATE) + "/" + currentDate.get(Calendar.YEAR));
        //System.out.println("max: " + currentDate.getTime());
        return currentDate.getTime();
    }

    public Date getMinHora() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.HOUR_OF_DAY, -2);
        //logger.info("Min Age: " + currentDate.get(Calendar.MONTH) + "/" + currentDate.get(Calendar.DATE) + "/" + currentDate.get(Calendar.YEAR));
        //System.out.println("min: " + currentDate.getTime());
        return currentDate.getTime();
    }

    public Date getMinAge() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.DAY_OF_MONTH, +60);
        //logger.info("Min Age: " + currentDate.get(Calendar.MONTH) + "/" + currentDate.get(Calendar.DATE) + "/" + currentDate.get(Calendar.YEAR));
        //System.out.println("min: " + currentDate.getTime());
        return currentDate.getTime();
    }

    public void selecionarRestaurante() {
        empresa = (Empresa) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("empresa");
        qtdePessoa();
        bloquearHorario();
    }

    public void qtdePessoa() {
        List<Object[]> results = dao.qtdePessoa(empresa.getIdEmpresa());
        System.out.println("lista: " + results.size());
        if (results.size() == 0) {
            setTotPessoaDis(empresa.getQtdePessoas());
        } else {
            int total = 0, qtdeReservado = 0;
            for (Object[] result : results) {
                total = (int) result[0];
                qtdeReservado = (int) result[1];
            }
            setTotPessoaDis(total - qtdeReservado);
        }
    }

    public void gravar(ActionEvent evt) {
        try {
            System.out.println("empresa: " + empresa.getIdEmpresa());
            System.out.println("usuario: " + dao.getIdUser());
            if (reserva.getData() == null) {
                Date data = new Date();
                reserva.setData(data);
                System.out.println("data: " + reserva.getData());
            } else {
                System.out.println("data: " + reserva.getData());
            }
            reserva.setEmpresa(empresa);
            System.out.println("horario: " + getHorario2());
            if (isBloqHorario()) {
                setHorario1("1");
            }
            if (getHorario1().equals("1") && getHorario2().equals("1")) {
                FacesUtil.addWarnMessage("Informação", "Escolha um horario!");
            } else {
                if (getHorario1().equals("1") && !getHorario2().equals("1")) {
                    reserva.setHora(getHorario2());
                } else if (!getHorario1().equals("1") && getHorario2().equals("1")) {
                    reserva.setHora(getHorario1());
                } else {
                    reserva.setHora(getHorario1() + " - " + getHorario2());
                }
                reserva.setStatus("PENDENTE");
                usuario.setIdUsuario(dao.getIdUser());
                reserva.setUsuario(usuario);
                dao.gravar(reserva);
                FacesUtil.addInfoMessage("Informação", "Salvo com sucesso!");
                novo();
            }
        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Erro", "Entre em contato com suporte!");
            ex.printStackTrace();
        }
    }

    // LISTA HISTORICO RESEVA CLIENTE
    public void reservaCliente() {
        listaReservaCliente = new ArrayList<Reserva>();
        listaReservaCliente = (List<Reserva>) dao.reservaCliente();
    }

    public void reservaClienteEmpresa() {
        listaReservaClienteEmpresa = new ArrayList<Reserva>();
        List<Object[]> results = dao.reservaClienteEmpresa();

        Reserva r;
        Empresa e;
        Usuario u;
        for (Object[] result : results) {
            r = new Reserva();
            e = new Empresa();
            u = new Usuario();
            r.setNome((String) result[0]);
            r.setTelefone((String) result[1]);
            r.setEmail((String) result[2]);
            r.setNomeRestaurante((String) result[3]);
            r.setData((Date) result[4]);
            r.setHora((String) result[5]);
            r.setQtde((Integer) result[6]);
            r.setStatus((String) result[7]);
            r.setId((Integer) result[8]);
            e.setIdEmpresa((Integer) result[9]);
            u.setIdUsuario((Integer) result[10]);
            r.setEmpresa(e);
            r.setUsuario(u);
            listaReservaClienteEmpresa.add(r);
        }
    }

    public void cancelar() {
        reserva = (Reserva) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("reserva");
        //System.out.println("id: " + reserva.getId());
        try {
            reserva.setStatus("CANCELADO");
            dao.alterar(reserva);
            novo();
            FacesUtil.addInfoMessage("Informação", "Cancelado com sucesso!");
            //System.out.println(reserva.getStatus());
        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Erro", "Entre em contato com suporte!");
            ex.printStackTrace();
        }
        //System.out.println(reserva.getStatus());
    }

    public void confirmar() {
        reserva = (Reserva) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("reserva");
        //System.out.println("id: " + reserva.getId());

        try {
            reserva.setStatus("CONFIRMADO");
            dao.alterar(reserva);
            novo();
            FacesUtil.addInfoMessage("Informação", "Confirmado com sucesso!");
            //System.out.println(reserva.getStatus());
        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Erro", "Entre em contato com suporte!");
            ex.printStackTrace();
        }

    }

    // LISTA HISTORICO RESEVA EMPRESA
//    public List<SelectItem> getSelectCafe() {
//        selectCafe = new ArrayList<SelectItem>();
//        listaEmpresa = new ArrayList<Empresa>();
//        listaEmpresa = (List<Empresa>) dao.buscarRestaurantePorOrdem();
//        selectCafe.add(new SelectItem(0, "Selecione"));
//        for (Empresa c : listaEmpresa) {
//            selectCafe.add(new SelectItem(c.getIdEmpresa(), c.getNomeRestaurante()));
//        }
//
//        return selectCafe;
//    }
//
//    public void horarios() {
//        listaHorario = new ArrayList<Horario>();
//        listaHorario = (List<Horario>) dao.buscarHorario();
//        double iniCafe = 0, fimCafe = 0, iniAlmoco = 0, fimAlmoco = 0, iniJantar = 0, fimJantar = 0;
//        for (Horario c : listaHorario) {
//            if (c.getDescricao().equals("CAFE DA MANHA")) {
//                iniCafe = Double.parseDouble(c.getHorarioInicio().replaceAll(":", "."));
//                fimCafe = Double.parseDouble(c.getHorarioFim().replaceAll(":", "."));
//
//            } else if (c.getDescricao().equals("ALMOÇO")) {
//                iniAlmoco = Double.parseDouble(c.getHorarioInicio().replaceAll(":", "."));
//                fimAlmoco = Double.parseDouble(c.getHorarioFim().replaceAll(":", "."));
//
//            } else if (c.getDescricao().equals("JANTAR")) {
//                iniJantar = Double.parseDouble(c.getHorarioInicio().replaceAll(":", "."));
//                fimJantar = Double.parseDouble(c.getHorarioFim().replaceAll(":", "."));
//            }
//        }
//        for (double i = iniCafe; i <= fimCafe; i++) {
//
//        }
//    }
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

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public String getHorario1() {
        return horario1;
    }

    public void setHorario1(String horario1) {
        this.horario1 = horario1;
    }

    public String getHorario2() {
        return horario2;
    }

    public void setHorario2(String horario2) {
        this.horario2 = horario2;
    }

    public boolean isBloqHorario() {
        return bloqHorario;
    }

    public void setBloqHorario(boolean bloqHorario) {
        this.bloqHorario = bloqHorario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getTotPessoaDis() {
        return totPessoaDis;
    }

    public void setTotPessoaDis(int totPessoaDis) {
        this.totPessoaDis = totPessoaDis;
    }

    public List<Reserva> getListaReservaCliente() {
        return listaReservaCliente;
    }

    public void setListaReservaCliente(List<Reserva> listaReservaCliente) {
        this.listaReservaCliente = listaReservaCliente;
    }

    public List<Reserva> getListaReservaClienteEmpresa() {
        return listaReservaClienteEmpresa;
    }

    public void setListaReservaClienteEmpresa(List<Reserva> listaReservaClienteEmpresa) {
        this.listaReservaClienteEmpresa = listaReservaClienteEmpresa;
    }

}
