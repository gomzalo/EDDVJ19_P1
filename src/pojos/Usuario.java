/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import estructuras.arboles.avl.subestructura.lista.simple.ListaSimpleImagenes;

/**
 *
 * @author g
 */
public class Usuario {
    private String id;
    private ListaSimpleImagenes imagenes;

    public Usuario(String id) {
        this.id = id;
        this.imagenes = null;
    }

    public Usuario(String id, ListaSimpleImagenes imagenes) {
        this.id = id;
        this.imagenes = imagenes;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ListaSimpleImagenes getImagenes() {
        return imagenes;
    }

    public void setImagenes(ListaSimpleImagenes imagenes) {
        this.imagenes = imagenes;
    }
    
    
}
