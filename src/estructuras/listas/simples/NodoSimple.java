/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras.listas.simples;

/**
 *
 * @author g
 */
public class NodoSimple {
    private NodoSimple siguiente;
    private int dato;

    public NodoSimple() {
        this.siguiente = null;
        this.dato = 0;
    }
    
    public NodoSimple(int dato) {
        this.siguiente = null;
        this.dato = dato;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public NodoSimple getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoSimple siguiente) {
        this.siguiente = siguiente;
    }
       
}
