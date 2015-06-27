/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import modelo.RegistroUsuarioXML;
import modelo.Usuario;
import org.jdom2.JDOMException;

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
    
     public String agregarUsuario(ActionEvent e) throws JDOMException, IOException {
        File archivoXML = new File("Usuario.xml");
        RegistroUsuarioXML registroUser = new RegistroUsuarioXML(archivoXML);
        registroUser.addUser(this.usuario);
        return "index";
    }
    
    
    
}
