/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras.arboles.avl.subestructura.cola;

import estructuras.arboles.abb.subestructura.cola.*;
import archivos.Escritura;
import estructuras.arboles.abb.NodoABB;
import java.io.IOException;

/**
 *
 * @author g
 */
public class ColaAVL {
    NodoColaAVL inicio;
    int tamano;

    public ColaAVL() {
        this.inicio = null;
        this.tamano = 0;
    }
        
    public boolean esVacia(){
        return inicio == null;
    }

    public NodoColaAVL getInicio() {
        return inicio;
    }

    public int getTamano() {
        return tamano;
    }
    
    public void encolar(NodoColaAVL nuevo){
        if(buscar(nuevo.getUsuario().getId())){
            System.out.println("El ID: " + nuevo.getUsuario().getId() + ", ya existe en la cola.");
        }else{
            if(esVacia()){
                inicio = nuevo;
                System.out.println("Se ha agregado correctamente el id: " + nuevo.getUsuario().getId() + ", a la cola.");
                tamano++;
            }else{
                NodoColaAVL auxiliar = inicio;
                while(auxiliar != null){
                    if(auxiliar.getSiguiente() == null){
                        auxiliar.setSiguiente(nuevo);
                        nuevo.setSiguiente(null);
                        System.out.println("Se ha agregado correctamente el id: " + nuevo.getUsuario().getId() + ", a la cola.");
                    }
                    auxiliar = auxiliar.getSiguiente();
                }
                tamano++;
            }
        }
    }
    
    public NodoColaAVL desencolar(){
        if(esVacia()){
            System.out.println("No hay nada que desencolar.");
            return null;
        }else{
            NodoColaAVL eliminado;
            System.out.println("Se desencolo ID: " + inicio.getUsuario().getId());
            NodoColaAVL auxiliar = inicio.getSiguiente();
            eliminado = inicio;
            inicio.setSiguiente(null);
            inicio = auxiliar;
            
            tamano--;
            return eliminado;
        }        
    }
    
    public void mostrar(){
        if(esVacia()){
            System.out.println("Cola vacia.");
        }else{
            NodoColaAVL auxiliar = inicio;
            System.out.println("\nContenido en la cola.");
            while(auxiliar != null){
                System.out.print("\nID: " + inicio.getUsuario().getId() + "->");
                auxiliar = auxiliar.getSiguiente();
            }
        }
    }
    
    public boolean buscar(String id){
        boolean encontrado = false;
        NodoColaAVL auxiliar = inicio;
        while(auxiliar != null){
            if(id.equals(auxiliar.getUsuario().getId())){
                encontrado = true;
            }
            auxiliar = auxiliar.getSiguiente();
        }
        return encontrado;
    }
    
}
