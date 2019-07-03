/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras.matrices.dispersa.cabeceras;

/**
 *
 * @author g
 */
public class NodoCabecera {
    private int x;
    private NodoCabecera siguiente;
    private NodoCabecera anterior;
    private ListaVertical columnas;
    
    public NodoCabecera(int x) {
        this.x = x;
        columnas = new ListaVertical();
        siguiente = anterior = null;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public NodoCabecera getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoCabecera siguiente) {
        this.siguiente = siguiente;
    }

    public NodoCabecera getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoCabecera anterior) {
        this.anterior = anterior;
    }

    public ListaVertical getColumnas() {
        return columnas;
    }

    public void setColumnas(ListaVertical columnas) {
        this.columnas = columnas;
    }
    
    
        
}
