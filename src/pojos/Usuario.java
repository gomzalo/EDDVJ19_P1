/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import estructuras.listas.simples.ListaSimple;

/**
 *
 * @author g
 */
public class Usuario {
    private String id;
    private ListaSimple imagenes;

    public Usuario(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ListaSimple getImagenes() {
        return imagenes;
    }

    public void setImagenes(ListaSimple imagenes) {
        this.imagenes = imagenes;
    }
    
    
}
