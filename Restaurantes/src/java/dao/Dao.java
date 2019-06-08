package dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.Pessoa;
import model.Usuario;

public class Dao implements Serializable {

    private EntityManagerFactory emf;
    private EntityManager em;
    private String user;

    public Dao() {
        emf = Persistence.createEntityManagerFactory("testePU");
        em = emf.createEntityManager();
    }

    public void gravar(Object objeto) {
        em.getTransaction().begin();
        em.persist(objeto);
        em.getTransaction().commit();
    }

    public void alterar(Object objeto) {
        em.getTransaction().begin();
        em.merge(objeto);
        em.getTransaction().commit();
    }

    public void remover(Object objeto) {
        em.getTransaction().begin();
        em.remove(objeto);
        em.getTransaction().commit();
    }

    public void excluir(Object objeto) {
        em.getTransaction().begin();
        em.remove(em.contains(objeto) ? objeto : em.merge(objeto));
        em.getTransaction().commit();
    }

    public void flush() {
        em.flush();
    }

    public Object buscar(Object objeto, int id) {
        return em.find(objeto.getClass(), id);
    }

    public List<?> buscarTodos(Class classe) {
        return em.createQuery("From " + classe.getName()).getResultList();
    }
//----------------TI-----------------------

    //----------------Login----------------
    public Usuario buscarUsuarioSpring(String usuario) {
        Usuario user = null;
        try {
            user = (Usuario) em.createNativeQuery("Select * from usuario where usuario = '" + usuario + "'", Usuario.class).getSingleResult();
            System.out.println("===============" + user.getUsuario());
            System.out.println("===============" + user.getPerfil());
            setUser(user.getUsuario());
        } catch (Exception e) {
        }

        return user;

    }

    public Usuario usuarioLogado(String nome) {
        return (Usuario) em.createNativeQuery("Select * from USUARIO where usuario = '" + nome + "'", Usuario.class).getSingleResult();
    }

    public List<Usuario> usuarioLogado2() {
        return (List<Usuario>) em.createNativeQuery("SELECT * FROM USUARIO  where usuario = '" + getUser() + "'", Usuario.class).getResultList();
    }
    

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    //----------------PESSOA----------------
    public List<Object[]> buscarCliente() {
        TypedQuery<Object[]> query = (TypedQuery<Object[]>) em.createNativeQuery("select a.nome, a.cpf,a.email, a.telefone from pessoa a, usuario b where a.id_usuario = b.id_usuario and b.perfil = 'CLIENTE'");
        List<Object[]> results = query.getResultList();
        return results;
    }

    
}
