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
public class ListaCabeceras {
    NodoCabecera primero, ultimo;
    int tamano;
    
    public ListaCabeceras() {
        primero = ultimo = null;
        tamano = 0;
    }

    public NodoCabecera getPrimero() {
        return primero;
    }

    public NodoCabecera getUltimo() {
        return ultimo;
    }

    public int getTamano() {
        return tamano;
    }
    
    public boolean esVacia(){
        return primero == null;
    }
    
    public void insertar(NodoCabecera nuevo){
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
    
    public void insertarALFrente(NodoCabecera nuevo){
        primero.setAnterior(nuevo);
        nuevo.setSiguiente(primero);
        primero = primero.getAnterior();
        System.out.println("Se ha insertado al inicio en la lista de cabeceras.");
        tamano++;
    }
    
    public void insertarAlFinal(NodoCabecera nuevo){
        ultimo.setSiguiente(nuevo);
        nuevo.setAnterior(ultimo);
        ultimo = ultimo.getSiguiente();
        System.out.println("Se ha insertado al final en la lista de cabeceras.");
        tamano++;
    }
    
    public void insertarAlMedio(NodoCabecera nuevo){
        NodoCabecera temporal1, temporal2;
        temporal1 = primero;
        while(temporal1.getX() < nuevo.getX()){
            
            temporal1 = temporal1.getSiguiente();
        }
        temporal2 = temporal1.getAnterior();
        
        temporal2.setSiguiente(nuevo);
        temporal1.setAnterior(nuevo);
        nuevo.setSiguiente(temporal1);
        nuevo.setAnterior(temporal2);
        System.out.println("Se ha insertado al medio en la lista de cabeceras.");
        tamano++;
    }
    
    public void mostrar(){
        if(esVacia()){
            System.out.println("Lista de horizontal vacia.");
        }else{
            NodoCabecera temporal;
            temporal = primero;
            while (temporal != null) {
                System.out.println("X: " + temporal.getX());
                temporal = temporal.getSiguiente();
            }
        }
    }
    
    public boolean existe(int x){
        if(esVacia()){
            return false;
        }else{
            NodoCabecera temporal;
            temporal = primero;
            while(temporal != null){
                if(x == temporal.getX()){
                    System.out.println("Se encontro x: " + x);
                    return true;
                }else if(temporal.getSiguiente() == null){
                    System.out.println("No se encontro x: " + x);
                    return false;
                }
                temporal = temporal.getSiguiente();
            }
            return false;
        }
    }
    
    public NodoCabecera buscar(int x){
        if(existe(x)){
            NodoCabecera temporal;
            temporal = primero;
            while(temporal.getX() != x){
                
                temporal = temporal.getSiguiente();
            }
            return temporal;
        }else{
            return new NodoCabecera(-1);
        }
    }
}
