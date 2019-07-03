/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras.listas.dobles.circular;

import estructuras.arboles.avl.subestructura.lista.simple.ListaSimpleImagenes;
import estructuras.listas.dobles.circular.subestructura.lista.simple.ListaSimpleCapas;

/**
 *
 * @author g
 */
public class NodoDC {
    private int id; // ID de la imagen
    private NodoDC siguiente;
    private NodoDC anterior;
    private ListaSimpleCapas capas;

    public NodoDC(int id, ListaSimpleCapas capas) {
        this.id = id;
        this.capas = capas;
        this.siguiente = this.anterior = null;
    }    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public ListaSimpleCapas getCapas() {
        return capas;
    }

    public void setCapas(ListaSimpleCapas capas) {
        this.capas = capas;
    }

}
