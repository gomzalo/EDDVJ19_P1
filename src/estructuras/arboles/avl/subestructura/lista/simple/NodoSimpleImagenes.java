/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras.arboles.avl.subestructura.lista.simple;

import estructuras.listas.dobles.circular.NodoDC;

/**
 *
 * @author g
 */
public class NodoSimpleImagenes {
    private NodoSimpleImagenes siguiente;
    private int id;
    private NodoDC imagen_usuario;

    public NodoSimpleImagenes() {
        this.siguiente = null;
        this.id = 0;
    }
    
    public NodoSimpleImagenes(int dato) {
        this.siguiente = null;
        this.id = dato;
    }

    public NodoSimpleImagenes(int id, NodoDC imagen) {
        this.siguiente = null;
        this.id = id;
        this.imagen_usuario = imagen;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NodoSimpleImagenes getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoSimpleImagenes siguiente) {
        this.siguiente = siguiente;
    }

    public NodoDC getImagen_usuario() {
        return imagen_usuario;
    }

    public void setImagen_usuario(NodoDC imagen_usuario) {
        this.imagen_usuario = imagen_usuario;
    }
    
}
