/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras.matrices.dispersa.laterales;

import estructuras.matrices.dispersa.NodoOrtogonal;

/**
 *
 * @author g
 */
public class ListaHorizontal {
    NodoOrtogonal primero, ultimo;

    public ListaHorizontal() {
        primero = ultimo = null;
    }
    
    public boolean esVacia(){
        return primero == null;
    }
    
    public void insertar(NodoOrtogonal nuevo){
        if(esVacia()){
            primero = ultimo = nuevo;
        }else{
            if(nuevo.getX() < primero.getX()){
                insertarALFrente(nuevo);
            }else if (nuevo.getX() > ultimo.getX()) {
                insertarAlFinal(nuevo);
            }else{
                insertarAlMedio(nuevo);
            }
        }
    }
    
    public void insertarALFrente(NodoOrtogonal nuevo){
        primero.setIzquierda(nuevo);
        nuevo.setDerecha(primero);
        primero = primero.getIzquierda();
    }
    
    public void insertarAlFinal(NodoOrtogonal nuevo){
        ultimo.setDerecha(nuevo);
        nuevo.setIzquierda(ultimo);
        ultimo = ultimo.getDerecha();
    }
    
    public void insertarAlMedio(NodoOrtogonal nuevo){
        NodoOrtogonal temporal1, temporal2;
        temporal1 = primero;
        while(temporal1.getX() < nuevo.getX()){
            
            temporal1 = temporal1.getDerecha();
        }
        temporal2 = temporal1.getIzquierda();
        temporal2.setDerecha(nuevo);
        temporal1.setIzquierda(nuevo);
        nuevo.setDerecha(temporal1);
        nuevo.setIzquierda(temporal2);
    }
    
    public void mostrar(){
        if(esVacia()){
            System.out.println("Lista de horizontal vacia.");
        }else{
            NodoOrtogonal temporal;
            temporal = primero;
            while (temporal != null) {
                System.out.println("X: " + temporal.getX());
                temporal = temporal.getDerecha();
            }
        }
    }
}
