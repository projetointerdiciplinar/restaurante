package dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


public class Dao implements Serializable {

    private EntityManagerFactory emf;
    private EntityManager em;

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

    public void flush() {
        em.flush();
    }

    public Object buscar(Object objeto, int id) {
        return em.find(objeto.getClass(), id);
    }

    public List<?> buscarTodos(Class classe) {
        return em.createQuery("From " + classe.getName()).getResultList();
    }


    //----------------Colaborador----------------
    public List<Object[]> buscarColab() {
        TypedQuery<Object[]> query = (TypedQuery<Object[]>) em.createNativeQuery("select nome_colab, cd_colab from v_colab where dt_demis is null");
        List<Object[]> results = query.getResultList();
        return results;
    }

    public List<Object[]> buscarColaboradores(BigDecimal mat) {
        TypedQuery<Object[]> query = (TypedQuery<Object[]>) em.createNativeQuery("select colab_id, cd_colab, nome_colab from USINAS.v_colab where dt_demis is null and cd_colab = " + mat);
        List<Object[]> results = query.getResultList();
        return results;
    }



    //----------------Fornecedor----------------
    public List<Object[]> buscarFornecedor(String nome) {
        TypedQuery<Object[]> query = (TypedQuery<Object[]>) em.createNativeQuery("SELECT NOME, NOME_FANT, CORR_ID FROM CORR WHERE NOME like '%" + nome + "%'");
        List<Object[]> results = query.getResultList();
        return results;
    }

    //----------------Nota Fiscal----------------
    public List<Object[]> buscarNF() {
        TypedQuery<Object[]> query = (TypedQuery<Object[]>) em.createNativeQuery("SELECT A.NRO,a.SERIE,a.DT_EMISS,a.RAZAO_SOCIAL,a.NRO_NFE,b.CD_PROD,b.DESCR_PROD FROM NF_ENT a, ITNF_ENT b WHERE a.NFENT_ID = b.NFENT_ID AND b.CD_PROD  in ('54578','54579','54160','54164') order by a.DT_EMISS");
        //TypedQuery<Object[]> query = (TypedQuery<Object[]>) em.createNativeQuery("SELECT A.NRO,a.SERIE,a.DT_EMISS,a.RAZAO_SOCIAL,a.NRO_NFE,b.CD_PROD,b.DESCR_PROD FROM NF_ENT a, ITNF_ENT b WHERE a.NFENT_ID = b.NFENT_ID AND b.CD_PROD  in ('54578','54579','54160','54164') and a.nro = 575842 and a.DT_EMISS = '27/04/2012' order by a.DT_EMISS");
        List<Object[]> results = query.getResultList();
        return results;
    }

    public List<Object[]> buscarNfPorCodigo(int nf, String data) {
        TypedQuery<Object[]> query = (TypedQuery<Object[]>) em.createNativeQuery("SELECT A.NRO,a.SERIE,a.DT_EMISS,a.RAZAO_SOCIAL,a.NRO_NFE,b.CD_PROD,b.DESCR_PROD FROM NF_ENT a, ITNF_ENT b WHERE a.NFENT_ID = b.NFENT_ID AND b.CD_PROD  in ('54578','54579','54160','54164') and a.nro = " + nf + " and a.DT_EMISS  = '" + data + "' order by a.DT_EMISS");
        List<Object[]> results = query.getResultList();
        return results;
    }

    //----------------ip----------------
    public List<Object[]> buscarColaboradoresId(BigDecimal id) {
        TypedQuery<Object[]> query = (TypedQuery<Object[]>) em.createNativeQuery("select colab_id, cd_colab, nome_colab from v_colab where dt_demis is null and colab_id = " + id);
        List<Object[]> results = query.getResultList();
        return results;
        //public List<Object[]> buscarColaboradores(BigDecimal mat) usa esse metodo tbm
    }

    //----------------ComputadorSoftwareMB----------------
    public List<Object[]> buscarSoftRelacionadoMicro(String key) {
        TypedQuery<Object[]> query = (TypedQuery<Object[]>) em.createNativeQuery("SELECT\n"
                + "  CO.DESCRICAO MICRO ,\n"
                + "  SO.DESCRICAO,\n"
                + "  SO.KEY\n"
                + "FROM\n"
                + "  SOFTWARE SO ,\n"
                + "  COMPUTADOR CO ,\n"
                + "  COMPUTADOR_SOFTWARE CS\n"
                + "WHERE\n"
                + "  SO.ID_SOFTWARE     = CS.ID_SOFTWARE\n"
                + "AND CS.ID_COMPUTADOR = CO.ID_COMPUTADOR\n"
                + "AND SO.KEY           = '" + key + "'");
        List<Object[]> results = query.getResultList();
        return results;
    }

 


 






}
