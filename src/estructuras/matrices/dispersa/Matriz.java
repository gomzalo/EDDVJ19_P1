/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras.matrices.dispersa;

import estructuras.matrices.dispersa.laterales.NodoLateral;
import estructuras.matrices.dispersa.laterales.ListaLaterales;
import estructuras.matrices.dispersa.cabeceras.NodoCabecera;
import estructuras.matrices.dispersa.cabeceras.ListaCabeceras;

/**
 *
 * @author g
 */
public class Matriz {
    ListaCabeceras cabeceras;
    ListaLaterales laterales;
    
    public Matriz(){
        cabeceras = new ListaCabeceras();
        laterales = new ListaLaterales();
    }
    
    public void insertar(int x, int y, String color){
        NodoOrtogonal nuevo = new NodoOrtogonal(color, x, y);
        if(!cabeceras.existe(x)){
            cabeceras.insertar(new NodoCabecera(x));
        }
        if(!laterales.existe(y)){
            laterales.insertar(new NodoLateral(y));
        }
        NodoCabecera cabecera_temporal;
        NodoLateral lateral_temporal;
        cabecera_temporal = cabeceras.buscar(x);
        lateral_temporal = laterales.buscar(y);
        
        cabecera_temporal.getColumnas().insertar(nuevo);
        lateral_temporal.getFilas().insertar(nuevo);
        System.out.println("Se agrego el color: " + color 
                + ", a las posiciones: " + x + ", " + y);
    }
    
    public void llenar(int x, int y){
        String oo = "";
        int o = 0;
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                oo = String.valueOf(o);
                insertar(i, j, oo);
                o++;
            }
        }
    }
}
