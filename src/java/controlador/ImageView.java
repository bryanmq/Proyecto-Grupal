/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

 
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import modelo.RegistroCitaXML;
 
@ManagedBean
public class ImageView {
     
    private List<String> images;
    private RegistroCitaXML registroCita;
     
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        for (int i = 0; i <= 3; i++) {
            images.add("image"+i+".jpg");
        }
    }
 
    public List<String> getImages() {
        return images;
    }
}
