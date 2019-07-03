/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras.arboles.abb.subestructura.cola;

import estructuras.arboles.abb.NodoABB;

/**
 *
 * @author g
 */
public class NodoColaABB {
    NodoABB capa;
    private NodoColaABB siguiente;

    public NodoColaABB(NodoABB capa) {
        this.capa = capa;
        this.siguiente = null;
    }
    
    public NodoABB getCapa() {
        return capa;
    }

    public void setCapa(NodoABB capa) {
        this.capa = capa;
    }
    
    public NodoColaABB getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoColaABB siguiente) {
        this.siguiente = siguiente;
    }
    
    
}
