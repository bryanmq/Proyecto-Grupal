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
import org.jdom2.Attribute;
import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filter;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author bryanmq
 */
public class RegistroUsuarioXML {

    private Element raiz;
    private Document documento;
    private String ruta;

    public RegistroUsuarioXML(File archivoXML) {
        this.ruta = archivoXML.getAbsolutePath();
        if (archivoXML.exists()) {
            abrirArchivo();
        } else {
            crearArchivo();
        }
    }

    public void crearArchivo() {
        raiz = new Element("usuarios");
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

    public void addUser(Usuario user) throws IOException {
        Element usuario = new Element("usuario");
        Element nombre = new Element("nombre");
        Attribute cedula = new Attribute("cedula", user.getCedula());
        Element telefono = new Element("telefono");
        Element fechaNacimiento = new Element("fechaNacimiento");
        Element sexo = new Element("sexo");
        Element direccion = new Element("direccion");
        Element correo = new Element("correo");
        Element password = new Element("password");

        nombre.addContent(user.getNombre());
        telefono.addContent(user.getTelefono());
        fechaNacimiento.addContent(user.getFechaNacimiento().toString());
        sexo.addContent(user.getSexo());
        direccion.addContent(user.getDireccion());
        correo.addContent(user.getCorreo());
        password.addContent(user.getContraseña());

        usuario.setAttribute(cedula);
        usuario.addContent(nombre);
        usuario.addContent(telefono);
        usuario.addContent(fechaNacimiento);
        usuario.addContent(sexo);
        usuario.addContent(direccion);
        usuario.addContent(correo);
        usuario.addContent(password);

        raiz.addContent(usuario);
        this.guardar();
    }

    public Usuario verificarUsuario(String contraseña, String correo) {
        List<Element> listaUsuarios = (List<Element>) raiz.getChildren();
        for (Element usuario : listaUsuarios) {
            if (usuario.getChildText("password").equals(contraseña) && usuario.getChildText("correo").equals(correo)) {
                Date date = new Date(usuario.getChildText("fechaNacimiento"));
                System.out.println(":DDDDDDDDDDDDDDDDDD");
                return new Usuario(
                        usuario.getChildText("nombre"),
                        usuario.getAttributeValue("cedula"),
                        usuario.getChildText("telefono"),
                        usuario.getChildText("sexo"),
                        usuario.getChildText("direccion"),
                        usuario.getChildText("correo"),
                        usuario.getChildText("password"),
                        date);
            }
        }
        return null;
    }

    public Object verificarUsuarioXCedula(String cedula, int index) {
        List<Element> listaUsuarios = (List<Element>) raiz.getChildren();
        for (Element usuario : listaUsuarios) {
            if (usuario.getAttributeValue("cedula").equals(cedula)) {
                if (index == 1) {
                    Date date = new Date(usuario.getChildText("fechaNacimiento"));
                    return new Usuario(
                            usuario.getChildText("nombre"),
                            usuario.getAttributeValue("cedula"),
                            usuario.getChildText("telefono"),
                            usuario.getChildText("sexo"),
                            usuario.getChildText("direccion"),
                            usuario.getChildText("correo"),
                            usuario.getChildText("password"),
                            date);
                } else {
                    return usuario;
                }
            }
        }
        return null;
    }

    public ArrayList<Usuario> getUsuario() throws ParseException {

        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        Usuario usuario;
        List<Element> listaE = (List<Element>) raiz.getChildren();

        for (Element listaE1 : listaE) {
            usuario = new Usuario();
            Date fechaNac = new Date(listaE1.getChildText("fechaNacimiento"));
            usuario.setNombre(listaE1.getChildText("nombre"));
            usuario.setCedula(listaE1.getAttributeValue("cedula"));
            usuario.setTelefono(listaE1.getChildText("telefono"));
            usuario.setFechaNacimiento(fechaNac);
            usuario.setSexo(listaE1.getChildText("sexo"));
            usuario.setCorreo(listaE1.getChildText("correo"));
            usuario.setDireccion(listaE1.getChildText("direccion"));
            listaUsuarios.add(usuario);
        }
        return listaUsuarios;
    }

    public void modificarUsuario(Usuario user) {
        Element usuarioEncontrado = (Element) this.verificarUsuarioXCedula(user.getCedula(), 0);
        usuarioEncontrado.getChild("nombre").setText(user.getNombre());
        usuarioEncontrado.getChild("telefono").setText(user.getTelefono());
        usuarioEncontrado.getChild("fechaNacimiento").setText(user.getFechaNacimiento().toString());
        usuarioEncontrado.getChild("sexo").setText(user.getSexo());
        usuarioEncontrado.getChild("direccion").setText(user.getDireccion());
        usuarioEncontrado.getChild("correo").setText(user.getCorreo());
        usuarioEncontrado.getChild("password").setText(user.getContraseña());
        guardar();
    }
    
    public void eliminarUsuario(Usuario usuario) throws IOException {
        raiz.removeContent((Element) verificarUsuarioXCedula(usuario.getCedula(), 0));
        guardar();
    }

}//Fin clase
