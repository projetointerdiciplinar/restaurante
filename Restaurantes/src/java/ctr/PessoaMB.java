/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctr;

import dao.Dao;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import model.Pessoa;
import model.Usuario;
import org.hibernate.validator.constraints.br.CPF;
import util.FacesUtil;

/**
 *
 * @author Eder
 */
@ManagedBean
@ViewScoped
public class PessoaMB implements Serializable {

    // Classe
    private Pessoa pessoa;
    private Dao dao;
    private Usuario usuario;

    // Lista
    private List<Pessoa> listaPessoa = new ArrayList<Pessoa>();
    private List<Pessoa> listaCliente = new ArrayList<Pessoa>();


    // variaveis
    private String nome, telefone, celular, email, confirmaEmail, senha, confirmaSenha, sexo;
    @CPF
    private String cpf;
    private Date data;

    public PessoaMB() {
        dao = (Dao) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("dao");
        novo();
    }

    public void novo() {
        pessoa = new Pessoa();
        usuario = new Usuario();
        listaPessoa = new ArrayList<Pessoa>();
        listaCliente = new ArrayList<Pessoa>();
        buscarCliente();
        pessoa.setUsuario(new Usuario());
    }

    public void gravarDoEmpresa(ActionEvent evt) {
        try {
            System.out.println("============"+pessoa.getSenha());
            if (pessoa.getSenha() == null ? getConfirmaSenha() != null : !pessoa.getSenha().equals(getConfirmaSenha())) {
                FacesUtil.addWarnMessage("Aviso", "Senhas não confere");
            } else {
                pessoa.setCpf(getCpf());
                usuario.setUsuario(pessoa.getCpf());
                usuario.setSenha(pessoa.getSenha());
                usuario.setPerfil("EMPRESA");
                pessoa.setUsuario(usuario);
                dao.gravar(usuario);
                dao.gravar(pessoa);
                novo();
                FacesUtil.addInfoMessage("Informação", "Cadastro realizado com sucesso!");
            }

        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Erro", "Entre em contato com suporte!");
            ex.printStackTrace();
        }
    }
    public void buscarCliente() {
        List<Object[]> results = dao.buscarCliente();
        Pessoa pes;
        for (Object[] result : results) {
            pes = new Pessoa();
            pes.setNome((String) result[0]);
            pes.setCpf((String) result[1]);
            pes.setEmail((String) result[2]);
            pes.setTelefone((String) result[3]);
            getListaCliente().add(pes);
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmaEmail() {
        return confirmaEmail;
    }

    public void setConfirmaEmail(String confirmaEmail) {
        this.confirmaEmail = confirmaEmail;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<Pessoa> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Pessoa> listaCliente) {
        this.listaCliente = listaCliente;
    }

    
}
