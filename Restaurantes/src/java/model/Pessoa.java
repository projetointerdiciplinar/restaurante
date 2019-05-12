
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;


@Entity
@Table (name = "pessoa")
public class Pessoa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pessoa", unique = true, nullable = false)
    private int idMarca;
    @Column(name = "descricao", nullable = false)
    private String descricao;
    
    @Column(name = "nome", nullable = false)
     private String nome;
    @Column(name = "cpf", nullable = false)
     private String cpf;
    @Column(name = "rg", nullable = false)
     private String rg;
    @Column(name = "endereco", nullable = false)
     private String endereco;
    @Column(name = "numero", nullable = false)
     private String numero;
    @Column(name = "bairro", nullable = false)
     private String bairro;
    @Column(name = "cidade", nullable = false)
     private String cidade;
    @Column(name = "cep", nullable = false)
     private String cep;
    @Column(name = "estado", nullable = false)
     private String estado;
    @Column(name = "telefone", nullable = false)
     private String telefone;
    @Column(name = "celular", nullable = false)
     private String celular;
    @Column(name = "senha", nullable = false)
     private String senha;
    @Column(name = "email", nullable = false)
     private String email;
    @Column(name = "produtoPorEmail", nullable = false)
     private String produtoPorEmail;
    @Column(name = "tipo", nullable = false)
     private String tipo;
     
     
     @Transient
     private String confirmaSenha;

     
     @Column(name = "data_nasc", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataNascimento;

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProdutoPorEmail() {
        return produtoPorEmail;
    }

    public void setProdutoPorEmail(String produtoPorEmail) {
        this.produtoPorEmail = produtoPorEmail;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

   
}
