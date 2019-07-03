/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras.arboles.abb.subestructura.cola;

import archivos.Escritura;
import estructuras.arboles.abb.NodoABB;
import java.io.IOException;

/**
 *
 * @author g
 */
public class ColaABB {
    NodoColaABB inicio;
    int tamano;

    public ColaABB() {
        this.inicio = null;
        this.tamano = 0;
    }
        
    public boolean esVacia(){
        return inicio == null;
    }

    public NodoColaABB getInicio() {
        return inicio;
    }

    public int getTamano() {
        return tamano;
    }
    
    public void encolar(NodoColaABB nuevo){
        if(buscar(nuevo.getCapa().getId())){
            System.out.println("El ID: " + nuevo.getCapa().getId() + ", ya existe en la cola.");
        }else{
            if(esVacia()){
                inicio = nuevo;
                System.out.println("Se ha agregado correctamente el id: " + nuevo.getCapa().getId() + ", a la cola.");
                tamano++;
            }else{
                NodoColaABB auxiliar = inicio;
                while(auxiliar != null){
                    if(auxiliar.getSiguiente() == null){
                        auxiliar.setSiguiente(nuevo);
                        nuevo.setSiguiente(null);
                        System.out.println("Se ha agregado correctamente el id: " + nuevo.getCapa().getId() + ", a la cola.");
                    }
                    auxiliar = auxiliar.getSiguiente();
                }
                tamano++;
            }
        }
    }
    
    public NodoColaABB desencolar(){
        if(esVacia()){
            System.out.println("No hay nada que desencolar.");
            return null;
        }else{
            NodoColaABB eliminado;
            System.out.println("Se desencolo ID: " + inicio.getCapa().getId());
            NodoColaABB auxiliar = inicio.getSiguiente();
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
            NodoColaABB auxiliar = inicio;
            System.out.println("\nContenido en la cola.");
            while(auxiliar != null){
                System.out.print("\nID: " + inicio.getCapa().getId() + "->");
                auxiliar = auxiliar.getSiguiente();
            }
        }
    }
    
    public boolean buscar(int id){
        boolean encontrado = false;
        NodoColaABB auxiliar = inicio;
        while(auxiliar != null){
            if(id == auxiliar.getCapa().getId()){
                encontrado = true;
            }
            auxiliar = auxiliar.getSiguiente();
        }
        return encontrado;
    }
    
}
