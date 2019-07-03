/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras.arboles.avl.subestructura.cola;

import estructuras.arboles.abb.subestructura.cola.*;
import estructuras.arboles.abb.NodoABB;
import pojos.Usuario;

/**
 *
 * @author g
 */
public class NodoColaAVL {
    Usuario usuario;
    private NodoColaAVL siguiente;

    public NodoColaAVL(Usuario usuario) {
        this.usuario = usuario;
        this.siguiente = null;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public NodoColaAVL getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoColaAVL siguiente) {
        this.siguiente = siguiente;
    }
    
    
}
