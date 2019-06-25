/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras.listas.dobles.circular;

import estructuras.listas.simples.ListaSimple;

/**
 *
 * @author g
 */
public class NodoDC {
    private int id;
    private String nombre;
    private String apellido;
    private NodoDC siguiente;
    private NodoDC anterior;
    private ListaSimple capas;

    public NodoDC(int id, String nombre, String apellido, ListaSimple capas) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.capas = capas;
        this.siguiente = this.anterior = null;
    }    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public NodoDC getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDC siguiente) {
        this.siguiente = siguiente;
    }

    public NodoDC getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDC anterior) {
        this.anterior = anterior;
    }

    public ListaSimple getCapas() {
        return capas;
    }

    public void setCapas(ListaSimple capas) {
        this.capas = capas;
    }

}
