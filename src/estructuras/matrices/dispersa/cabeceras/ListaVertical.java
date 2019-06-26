/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras.matrices.dispersa.cabeceras;

import estructuras.matrices.dispersa.NodoOrtogonal;

/**
 *
 * @author g
 */
public class ListaVertical {
    NodoOrtogonal primero, ultimo;
    int tamano;

    public ListaVertical() {
        primero = ultimo = null;
        tamano = 0;
    }    

    public NodoOrtogonal getPrimero() {
        return primero;
    }

    public NodoOrtogonal getUltimo() {
        return ultimo;
    }
    
    public int getTamano() {
        return tamano;
    }
    
    public boolean esVacia(){
        return primero == null;
    }
    
    public void insertar(NodoOrtogonal nuevo){
        if(esVacia()){
            primero = ultimo = nuevo;
        }else{
            if(nuevo.getY() < primero.getY()){
                insertarALFrente(nuevo);
            }else if (nuevo.getY() > ultimo.getY()) {
                insertarAlFinal(nuevo);
            }else{
                insertarAlMedio(nuevo);
            }
        }
    }
    
    public void insertarALFrente(NodoOrtogonal nuevo){
        primero.setArriba(nuevo);
        nuevo.setAbajo(primero);
        primero = primero.getArriba();
        System.out.println("Se ha insertado al inicio en la lista vertical.");
        tamano++;
    }
    
    public void insertarAlFinal(NodoOrtogonal nuevo){
        ultimo.setAbajo(nuevo);
        nuevo.setArriba(ultimo);
        ultimo = ultimo.getAbajo();
        System.out.println("Se ha insertado al final en la lista vertical.");
        tamano++;
    }
    
    public void insertarAlMedio(NodoOrtogonal nuevo){
        NodoOrtogonal temporal1, temporal2;
        temporal1 = primero;
        while(temporal1.getY() < nuevo.getY()){
            
            temporal1 = temporal1.getAbajo();
        }
        temporal2 = temporal1.getArriba();
        temporal2.setAbajo(nuevo);
        temporal1.setArriba(nuevo);
        nuevo.setAbajo(temporal1);
        nuevo.setArriba(temporal2);
        System.out.println("Se ha insertado al medio en la lista vertical.");
        tamano++;
    }
    
    public void mostrar(){
        if(esVacia()){
            System.out.println("Lista de cabeceras vacia.");
        }else{
            NodoOrtogonal temporal;
            temporal = primero;
            while (temporal != null) {
                System.out.println("Y: " + temporal.getY());
                temporal = temporal.getAbajo();
            }
        }
    }
}
