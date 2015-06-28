/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
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
    private File archivoXML;
    private RegistroUsuarioXML registroUsuarioXML;

    public ManejadorUsuario() {
        archivoXML = new File("Usuario.xml");
        registroUsuarioXML = new RegistroUsuarioXML(archivoXML);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String agregarUsuario(ActionEvent e) throws JDOMException, IOException {
        registroUsuarioXML.addUser(this.usuario);
        return "index";
    }

    public String login(String contraseña, String correo) throws JDOMException, IOException {
        if (!"".equals(contraseña) && !"".equals(correo) && registroUsuarioXML.verificarUsuario(contraseña, correo, 1) != null) {
            if (contraseña.equals(((Usuario) registroUsuarioXML.verificarUsuario(contraseña, correo, 1)).getContraseña()) && correo.equals(((Usuario) registroUsuarioXML.verificarUsuario(contraseña, correo, 1)).getCorreo())) {
                return "loginUser";
            } else {
                if (usuario.getCorreo().equals("usuario@ucr.ac.cr") && usuario.getContraseña().equals("123")) {
                    return "loginAdmin";
                }
            }
        }
        return "";
    }//fin metodo

    public Usuario getInfoUsuario() {
        this.usuario = (Usuario) registroUsuarioXML.verificarUsuario(usuario.getContraseña(), usuario.getCorreo(), 1);
        return usuario;
    }

    public void modificarUsuario() {
        registroUsuarioXML.modificarUsuario(this.usuario);
    }

}
