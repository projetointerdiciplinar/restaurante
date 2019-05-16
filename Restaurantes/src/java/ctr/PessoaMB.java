
package ctr;
import dao.Dao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import model.Pessoa;
import util.FacesUtil;


@ManagedBean
@ViewScoped
public class PessoaMB  implements Serializable{
    private Pessoa pessoa;
    private Dao dao = new Dao();
  
    
    public PessoaMB() {
        pessoa = new  Pessoa();

    }
    
   public void gravar (ActionEvent evt){
       
    try {
        dao.gravar(pessoa);
        /*listarMarcaVeiculo = (List<Marca>) dao.buscarTodos(Marca.class);*/
        pessoa = new Pessoa();
         FacesUtil.addInfoMessage("Informação", "Cadastro salvo com sucesso!");
    } catch (Exception ex) {
        FacesUtil.addErrorMessage("Erro", "Entre em contato com suporte!");
        }
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Dao getDao() {
        return dao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }


   
}

    
    