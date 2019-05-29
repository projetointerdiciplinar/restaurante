

package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table (name = "empresa")
public class Empresa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_empresa", unique = true, nullable = false)
    private int idMarca;
    @Column(name = "descricao", nullable = false)
    private String descricao;
    
    @Column(name = "nome", nullable = false)
     private String nome;
    @Column(name = "sobrenome", nullable = false)
     private String sobrenome;
    @Column(name = "email", nullable = false)
     private String email;
    @Column(name = "cpf", nullable = false)
     private String cpf;
    @Column(name = "cnpj", nullable = false)
     private String cnpj;
    @Column(name = "celular", nullable = false)
     private String celular;
    @Column(name = "rg", nullable = false)
     private String rg;
    @Column(name = "orgaoemissor", nullable = false)
     private String orgaoemissor;
    @Column(name = "razaosocial", nullable = false)
     private String razaosocial;
    @Column(name = "nomedorestaurante", nullable = false)
     private String nomedorestaurante;
    @Column(name = "telefone", nullable = false)
     private String telefone;
    @Column(name = "cep", nullable = false)
     private String cep;
    @Column(name = "estado", nullable = false)
     private String estado;
    @Column(name = "cidade", nullable = false)
     private String cidade;
    @Column(name = "endereco", nullable = false)
     private String endereco;
    @Column(name = "numero", nullable = false)
     private int numero;

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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getOrgaoemissor() {
        return orgaoemissor;
    }

    public void setOrgaoemissor(String orgaoemissor) {
        this.orgaoemissor = orgaoemissor;
    }

    public String getRazaosocial() {
        return razaosocial;
    }

    public void setRazaosocial(String razaosocial) {
        this.razaosocial = razaosocial;
    }

    public String getNomedorestaurante() {
        return nomedorestaurante;
    }

    public void setNomedorestaurante(String nomedorestaurante) {
        this.nomedorestaurante = nomedorestaurante;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
   

}
