/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctr;

import com.google.gson.Gson;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Client;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
    private String api;

    public PessoaMB() throws IOException {
        dao = (Dao) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("dao");
        novo();
        //chamadaApi();
    }

    public void chamadaApi() throws IOException {

        try {

            String passarToken;
            URL url = new URL("http://localhost:8084/Api/rest/client/listar/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output, retorno = "";
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                retorno += output;
            }
            Gson gson = new Gson(); // Or use new GsonBuilder().create();
            List client = gson.fromJson(retorno, List.class); // deserializes json into target2

            System.out.println("lista: " + client);
            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public void cadastrarApi() {
        OkHttpClient client = new OkHttpClient();

        String json = "{\"name\":\"" + getApi() + "\",\"end\":\"rua2\",\"cep\":\"382003\"}";
        System.out.println("json.length: " + json);
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url("http://localhost:8084/Api/rest/client/cadastrar")
                .post(body)
                .addHeader("Content-Type", "application/json") // Específica o tipo de parametro em jSon
                .addHeader("Accept", "*/*") // aceita qualquer URL
                .addHeader("Cache-Control", "no-cache") //sempre vai pegar os dados atualizado
                .addHeader("Host", "localhost:8084") // Endereço
                .addHeader("Content-Length", String.valueOf(json.length())) //quantidade de carecter
                .addHeader("Connection", "keep-alive")
                .addHeader("cache-control", "no-cache")//sempre vai pegar os dados atualizado
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException ex) {
            Logger.getLogger(PessoaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (response.isSuccessful()) {
            System.out.println("sucesso");
        }
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
            System.out.println("============" + pessoa.getSenha());
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
                setCpf("");
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

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
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
