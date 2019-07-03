/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras.listas.dobles.circular.subestructura.lista.simple;

import estructuras.arboles.avl.subestructura.lista.simple.*;
import estructuras.matrices.dispersa.Matriz;

/**
 *
 * @author g
 */
public class NodoSimpleCapas {
    private NodoSimpleCapas siguiente;
    private int id;
    private Matriz capa_imagen;

    public NodoSimpleCapas() {
        this.siguiente = null;
        this.id = 0;
    }
    
    public NodoSimpleCapas(int dato) {
        this.siguiente = null;
        this.id = dato;
    }

    public NodoSimpleCapas(int id, Matriz capa) {
        this.siguiente = null;
        this.id = id;
        this.capa_imagen = capa;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NodoSimpleCapas getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoSimpleCapas siguiente) {
        this.siguiente = siguiente;
    }

    public Matriz getCapa_imagen() {
        return capa_imagen;
    }

    public void setCapa_imagen(Matriz capa_imagen) {
        this.capa_imagen = capa_imagen;
    }
    
}
