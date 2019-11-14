package dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.Empresa;
import model.Horario;
import model.Inventario;
import model.Pessoa;
import model.Reserva;
import model.Usuario;

public class Dao implements Serializable {

    private EntityManagerFactory emf;
    private EntityManager em;
    private String user;
    private Integer idUser;

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
            setIdUser(user.getIdUsuario());
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

    public List<Object[]> usuarioLogado3() {
        TypedQuery<Object[]> query = (TypedQuery<Object[]>) em.createNativeQuery("select b.nome, a.usuario, a.perfil  from usuario a, pessoa b where a.id_usuario = b.id_usuario and a.usuario = '" + getUser() + "'");
        List<Object[]> results = query.getResultList();
        return results;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    //----------------EMPRESA----------------
    public List<Empresa> buscarRestaurantePorOrdem2() {
        return (List<Empresa>) em.createNativeQuery("SELECT * FROM empresa order by nome_restaurante", Empresa.class).getResultList();
    }

    public List<Empresa> buscarRestaurantePorOrdem() {
        return (List<Empresa>) em.createNativeQuery("SELECT * FROM empresa a WHERE a.id_usuario = (\n"
                + "SELECT b.id_usuario FROM usuario b \n"
                + "where b.id_usuario = a.id_usuario \n"
                + "and b.id_usuario = " + getIdUser() + ") order by a.nome_restaurante", Empresa.class).getResultList();
    }

    public List<Inventario> buscarInventarioPorOrdem() {
        return (List<Inventario>) em.createNativeQuery("select a.*,b.*from inventario a, empresa b, usuario c \n"
                + "where a.id_empresa = b.id_empresa \n"
                + "and b.id_usuario = c.id_usuario\n"
                + "and c.id_usuario =  " + getIdUser() + " order by a.DESCRICAO", Inventario.class).getResultList();
    }

    public List<Horario> buscarHorarioPorOrdem() {
        return (List<Horario>) em.createNativeQuery("select a.*,b.*from horario a, empresa b, usuario c \n"
                + "where a.id_empresa = b.id_empresa \n"
                + "and b.id_usuario = c.id_usuario\n"
                + "and c.id_usuario =  " + getIdUser(), Horario.class).getResultList();
    }

    //----------------PESSOA----------------
    public List<Object[]> buscarCliente() {
        TypedQuery<Object[]> query = (TypedQuery<Object[]>) em.createNativeQuery("select a.nome, b.usuario,a.email, a.telefone from pessoa a, usuario b where a.id_usuario = b.id_usuario and b.perfil = 'CLIENTE'");
        List<Object[]> results = query.getResultList();
        return results;
    }

    public List<Object[]> buscarEmpresa() {
        TypedQuery<Object[]> query = (TypedQuery<Object[]>) em.createNativeQuery("SELECT ID_EMPRESA, NOME_RESTAURANTE FROM EMPRESA  a WHERE a.id_usuario = (\n"
                + "SELECT b.id_usuario FROM usuario b \n"
                + "where b.id_usuario = a.id_usuario \n"
                + "and b.id_usuario = " + getIdUser() + ") order by a.nome_restaurante");
        List<Object[]> results = query.getResultList();
        return results;
    }

    //---------------- RESERVA ----------------
    public List<Horario> buscarHorario() {
        return (List<Horario>) em.createNativeQuery(" SELECT A.* FROM HORARIO A, EMPRESA B, USUARIO C\n"
                + "WHERE A.id_empresa = B.id_empresa\n"
                + "AND C.id_usuario = B.id_usuario\n"
                + "AND C.id_usuario = 1\n", Horario.class).getResultList();

    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

}
