/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author scarlet
 */
@ManagedBean
@RequestScoped
public class RegistroCitaXML {

    /**
     * Creates a new instance of RegistroCitaXML
     */
    public RegistroCitaXML() {
    }

    private Element raiz;
    private Document documento;
    private String ruta;

    public RegistroCitaXML(File archivoXML) {
        this.ruta = archivoXML.getAbsolutePath();
        if (archivoXML.exists()) {
            abrirArchivo();
        } else {
            crearArchivo();
        }
    }

    public void crearArchivo() {
        raiz = new Element("citas");
        documento = new Document(raiz);
        guardar();
    }

    public void guardar() {
        XMLOutputter xMLOutputter = new XMLOutputter();
        try {
            xMLOutputter.output(documento, new PrintWriter(ruta));
        } catch (IOException ex) {
            Logger.getLogger(RegistroUsuarioXML.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void abrirArchivo() {
        try {
            SAXBuilder sAXBuilder = new SAXBuilder();
            sAXBuilder.setIgnoringElementContentWhitespace(true);
            documento = sAXBuilder.build(ruta);
            raiz = documento.getRootElement();
        } catch (JDOMException | IOException ex) {
            System.out.println("error");
        }
    }

    public void addCita(Cita cita, Usuario usuario) throws IOException {
        Element eCita = new Element("cita");
        Element nombre = new Element("nombre");
        Element telefono = new Element("telefono");
        Element fechaNacimiento = new Element("fechaNacimiento");
        Element sexo = new Element("sexo");
        Element direccion = new Element("direccion");
        Element correo = new Element("correo");
        Element fechaDeCita = new Element("fechaDeCita");
        Element hora = new Element("hora");
        Element especialidad = new Element("especialidad");
        Attribute cedula = new Attribute("cedula", usuario.getCedula());

        nombre.addContent(usuario.getNombre());
        telefono.addContent(usuario.getTelefono());
        fechaNacimiento.addContent(usuario.getFechaNacimiento().toString());
        sexo.addContent(usuario.getSexo());
        direccion.addContent(usuario.getDireccion());
        correo.addContent(usuario.getCorreo());

        fechaDeCita.addContent(cita.getFecha().toString());
        hora.addContent(cita.getHora());
        especialidad.addContent(cita.getEspecialidad());

        eCita.setAttribute(cedula);
        eCita.addContent(nombre);
        eCita.addContent(telefono);
        eCita.addContent(fechaNacimiento);
        eCita.addContent(sexo);
        eCita.addContent(direccion);
        eCita.addContent(correo);
        eCita.addContent(fechaDeCita);
        eCita.addContent(hora);
        eCita.addContent(especialidad);

        raiz.addContent(eCita);
        this.guardar();
    }

    public ArrayList<Cita> getCitas(String correo) throws ParseException {
        ArrayList<Cita> listaUsuarios = new ArrayList<>();
        Cita cita;
        Usuario usuario;
        List<Element> listaE = (List<Element>) raiz.getChildren();

        for (Element listaE1 : listaE) {
            if (listaE1.getChildText("correo").equals(correo)) {
                usuario = new Usuario();
                Date fecha = new Date(listaE1.getChildText("fechaNacimiento"));
                Date fecha2 = new Date(listaE1.getChildText("fechaDeCita"));
                usuario.setCedula(listaE1.getAttributeValue("cedula"));
                usuario.setNombre(listaE1.getChildText("nombre"));
                usuario.setTelefono(listaE1.getChildText("telefono"));
                usuario.setFechaNacimiento(fecha);
                usuario.setSexo(listaE1.getChildText("sexo"));
                usuario.setCorreo(listaE1.getChildText("correo"));
                cita = new Cita();
                cita.setFecha(fecha2);
                cita.setEspecialidad(listaE1.getChildText("especialidad"));
                cita.setHora(listaE1.getChildText("hora"));
                cita.setUsuario(usuario);
                listaUsuarios.add(cita);
            }

        }
        return listaUsuarios;
    }

}
