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
public class ListaLaterales {
    NodoLateral primero, ultimo;
    
    public ListaLaterales() {
        primero = ultimo = null;
    }
    
    public boolean esVacia(){
        return primero == null;
    }
    
    public void insertar(NodoLateral nuevo){
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
    
    public void insertarALFrente(NodoLateral nuevo){
        primero.setAnterior(nuevo);
        nuevo.setSiguiente(primero);
        primero = primero.getAnterior();
    }
    
    public void insertarAlFinal(NodoLateral nuevo){
        ultimo.setSiguiente(nuevo);
        nuevo.setAnterior(ultimo);
        ultimo = ultimo.getSiguiente();
    }
    
    public void insertarAlMedio(NodoLateral nuevo){
        NodoLateral temporal1, temporal2;
        temporal1 = primero;
        while(temporal1.getY() < nuevo.getY()){
            
            temporal1 = temporal1.getSiguiente();
        }
        temporal2 = temporal1.getAnterior();
        
        temporal2.setSiguiente(nuevo);
        temporal1.setAnterior(nuevo);
        nuevo.setSiguiente(temporal1);
        nuevo.setAnterior(temporal2);
    }
    
    public void mostrar(){
        if(esVacia()){
            System.out.println("Lista de horizontal vacia.");
        }else{
            NodoLateral temporal;
            temporal = primero;
            while (temporal != null) {
                System.out.println("Y: " + temporal.getY());
                temporal = temporal.getSiguiente();
            }
        }
    }
    
    public boolean existe(int y){
        if(esVacia()){
            return false;
        }else{
            NodoLateral temporal;
            temporal = primero;
            while(temporal != null){
                if(y == temporal.getY()){
                    System.out.println("Se encontro y: " + y);
                    return true;
                }else if(temporal.getSiguiente() == null){
                    System.out.println("No se encontro y: " + y);
                    return false;
                }
                temporal = temporal.getSiguiente();
            }
            return false;
        }
    }
    
    public NodoLateral buscar(int y){
        if(existe(y)){
            NodoLateral temporal;
            temporal = primero;
            while(temporal.getY() != y){
                
                temporal = temporal.getSiguiente();
            }
            return temporal;
        }else{
            return new NodoLateral(-1);
        }
    }
}
