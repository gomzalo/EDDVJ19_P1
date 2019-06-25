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
    
    public ListaCabeceras() {
        primero = ultimo = null;
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
    }
    
    public void insertarAlFinal(NodoCabecera nuevo){
        ultimo.setSiguiente(nuevo);
        nuevo.setAnterior(ultimo);
        ultimo = ultimo.getSiguiente();
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
