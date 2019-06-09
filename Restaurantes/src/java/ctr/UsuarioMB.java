/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctr;

import dao.Dao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Usuario;

/**
 *
 * @author Eder
 */
@ManagedBean
@ViewScoped
public class UsuarioMB implements Serializable{
    private Dao dao;
    private Usuario usuario;
    private List<Usuario> listaUsuario = new ArrayList<Usuario>();
}
