/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author scarlet
 */
@ManagedBean
@RequestScoped
public class Especialidad {

    private List<String> arrayEspecialidades;
    private List<String> arrayHora;

    public Especialidad() {
        arrayEspecialidades = new ArrayList<>();
        arrayEspecialidades.add("Pediatría");
        arrayEspecialidades.add("Ginecología");        
        arrayEspecialidades.add("Odontología");
        arrayEspecialidades.add("Cirugia plastica");
        
        arrayHora = new ArrayList<>();
        arrayHora.add("10:30am");
        arrayHora.add("11:30am");        
        arrayHora.add("1:30pm");
        arrayHora.add("3:30pm");
    }

    public List<String> getArrayEspecialidades() {
        return arrayEspecialidades;
    }

    public void setArrayEspecialidades(List<String> arrayEspecialidades) {
        this.arrayEspecialidades = arrayEspecialidades;
    }

    public List<String> getArrayHora() {
        return arrayHora;
    }

    public void setArrayHora(List<String> arrayHora) {
        this.arrayHora = arrayHora;
    }
    
    
    
}
