/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import modelo.Cita;
import modelo.RegistroCitaXML;
import modelo.RegistroUsuarioXML;
import org.jdom2.JDOMException;

/**
 *
 * @author scarlet
 */
@ManagedBean
@RequestScoped
public class ManejadorCita {

    @ManagedProperty(value = "#{cita}")

    private Cita cita;
    private File archivoXML;
    private RegistroCitaXML registroCita;
    private RegistroUsuarioXML registroUsuarioXML;

    public ManejadorCita() {
        archivoXML = new File("Citas.xml");
        registroCita = new RegistroCitaXML(archivoXML);
        archivoXML = new File("Usuario.xml");
        registroUsuarioXML = new RegistroUsuarioXML(archivoXML);
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public String agregarCita(String correo, String contraseña) throws JDOMException, IOException, ParseException {
        String message="";
        message=registroCita.addCita(this.cita, registroUsuarioXML.verificarUsuario(contraseña, correo));
        return message;
    }
    
    public ArrayList<String> getArrayString(String correo){
        return registroCita.getArrayDeCitas(correo);
    }

    public ArrayList<Cita> getListaCitas(String correo) throws ParseException, JDOMException, IOException {
        ArrayList<Cita> listaUser = registroCita.getCitasEspecificas(correo);
        return listaUser;
    }
    
    public ArrayList<Cita> getListaCitasGeneral() throws ParseException, JDOMException, IOException {
        ArrayList<Cita> listaUser = registroCita.getCitas();
        return listaUser;
    }
    
    public String cancelarCita(String correo){
        try {
            registroCita.eliminarCita(correo);
        } catch (IOException ex) {
            Logger.getLogger(ManejadorCita.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ManejadorCita.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "loginUser";
    }

}
