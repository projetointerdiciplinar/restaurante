/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctr;

import dao.Dao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import model.Pessoa;
import model.Usuario;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;
import util.FacesUtil;

/**
 *
 * @author Eder
 */
@ManagedBean
@ViewScoped
public class ClienteMB implements Serializable {

    // Classe
    private Pessoa pessoa;
    private Dao dao;
    private Usuario usuario;

    // Lista
    private List<Pessoa> listaPessoa = new ArrayList<Pessoa>();

    // variaveis
    private String nome, telefone, celular, senha, confirmaSenha, sex;
    @CPF
    private String cpf;
    private String email;
    private Date data;
    private boolean sexo, situacao;

    public void ClienteMB() {

        //dao = (Dao) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("dao");
        novo();
    }

    public void novo() {
        pessoa = new Pessoa();
        usuario = new Usuario();
        listaPessoa = new ArrayList<Pessoa>();
        pessoa.setUsuario(new Usuario());
        dao = new Dao();
        setSexo(true);
    }

    public void gravarCliente(ActionEvent evt) {
        try {
            preSalvar();
            if (!isSituacao()) {
                dao.gravar(usuario);
                pessoa.setUsuario(usuario);
                dao.gravar(pessoa);
                limparClienteUsuario();
//            /*listarMarcaVeiculo = (List<Marca>) dao.buscarTodos(Marca.class);*/
//FacesContext.getCurrentInstance().getExternalContext().redirect(("/sistema/dashboard.xhtml"));
                novo();
                FacesUtil.addInfoMessage("Informação", "Cadastro realizado com sucesso!");
            }

        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Erro", "Entre em contato com suporte!");
            ex.printStackTrace();
        }
    }

    public void preSalvar() {
        pessoa = new Pessoa();
        usuario = new Usuario();
        dao = new Dao();

        //System.out.println("======" + getNome());
        if (getSenha() == null ? getConfirmaSenha() != null : !getSenha().equals(getConfirmaSenha())) {
            setSituacao(true);
            FacesUtil.addWarnMessage("Aviso", "Senhas não confere");
        } else {
            if (isSexo()) {
                setSex("MASCULINO");
            } else {
                setSex("FEMININO");
            }

            // setar usuario
            usuario.setPerfil("CLIENTE");
            usuario.setUsuario(getCpf());
            usuario.setSenha(getSenha());
            // setar Cliente
            pessoa.setNome(getNome());
            pessoa.setTelefone(getTelefone());
            pessoa.setEmail(getEmail());
            pessoa.setSexo(getSex());
            pessoa.setCelular(getCelular());
            pessoa.setDataNascimento(getData());
        }

    }

    public void limparClienteUsuario() {
        setCpf("");
        setConfirmaSenha("");
        setSenha("");
        setNome("");
        setEmail("");
        setTelefone("");
        setCelular("");
        setData(null);
        setSexo(sexo);
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

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

}
