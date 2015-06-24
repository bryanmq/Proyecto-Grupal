/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import modelo.Usuario;

/**
 *
 * @author bryanmq
 */
@ManagedBean
@RequestScoped
public class ManejadorUsuario {

    @ManagedProperty(value = "#{usuario}")
    private Usuario usuario;
    public ManejadorUsuario() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    
}
