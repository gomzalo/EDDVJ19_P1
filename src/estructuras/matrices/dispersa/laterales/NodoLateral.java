/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras.matrices.dispersa.laterales;

/**
 *
 * @author g
 */
public class NodoLateral {
    private int y;
    private NodoLateral siguiente;
    private NodoLateral anterior;
    private ListaHorizontal filas;
    
    public NodoLateral(int y) {
        this.y = y;
        filas = new ListaHorizontal();
        siguiente = anterior = null;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public NodoLateral getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLateral siguiente) {
        this.siguiente = siguiente;
    }

    public NodoLateral getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoLateral anterior) {
        this.anterior = anterior;
    }

    public ListaHorizontal getFilas() {
        return filas;
    }

    public void setFilas(ListaHorizontal filas) {
        this.filas = filas;
    }
    
    
    
}
