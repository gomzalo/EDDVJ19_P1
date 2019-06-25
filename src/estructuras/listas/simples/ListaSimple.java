/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras.listas.simples;

import archivos.Escritura;
import java.io.IOException;

/**
 *
 * @author g
 */
public class ListaSimple {
    protected NodoSimple inicio;
    protected int tamano;

    public ListaSimple() {
        inicio = null;
        tamano = 0;
    }
    
    public boolean esVacia(){
        return inicio == null;
    }
    
    public int getTamano(){
        return this.tamano;
    }
    
    public void agregarAlFinal(int dato){
        NodoSimple nuevo = new NodoSimple(dato);
        if(esVacia()){
            // Lista vacia, por lo que el inicio sera igual al nodo nuevo.
            inicio = nuevo;
        }else{
            // Se posiciona al inicio el nodo auxiliar.
            NodoSimple auxiliar = inicio;
            while(auxiliar.getSiguiente() != null){
                auxiliar = auxiliar.getSiguiente();
            }
            // Una vez alcanzado el final, se agrega el nuevo nodo.
            auxiliar.setSiguiente(nuevo);
        }
        tamano++;
    }
    
    public String graficar(String id_cluster, String titulo) throws IOException{
        String dot_grafo_lista_ordenada =
             	"\n\tsubgraph cluster_lista_simple_" + id_cluster
            + 	"\n\t{"
            + 	"\n\tnode[shape = tripleoctagon, style = filled, color = white, fillcolor = black, fontcolor = white, peripheries = 2];"
            + 	"\n\tedge[color = \"purple:grey\"];"
            + 	"\n"
                +   generarDot()
                +   "\n\t\tcolor = \"indigo:hotpink2\""
                +   "\n\t\tfontcolor = white"
                +   "\n\t\tfontname = serif"
                +   "\n\t\tstyle = filled"
                +   "\n\t\tlabel = \"Lista" + titulo + " \""
            +	"\n\t}"
        +   "\n}";
        return dot_grafo_lista_ordenada;
    }
    
    public String generarDot(){
        String dot = "";
        if(!esVacia()){
            NodoSimple auxiliar_contenido = inicio;
            while(auxiliar_contenido != null){
                // -------------------  Contendio   -------------------
                // Actual
                dot += 	"\n\t\t\t" + auxiliar_contenido.getDato()
                    + 	"[fillcolor = black, fontcolor = white, label = "
                    +   "<ID: " + auxiliar_contenido.getDato()
                    +   ">"
                    +   "]";
                
                auxiliar_contenido = auxiliar_contenido.getSiguiente();
            }
            dot += "\n";
            
            NodoSimple auxiliar_enlaces = inicio;
            while(auxiliar_enlaces.getSiguiente() != null) {
                // -------------------  Enlaces   	-------------------
            	dot += "\n\t\t" + auxiliar_enlaces.getDato()+ "->" 
                    + 	auxiliar_enlaces.getSiguiente().getDato();
            	auxiliar_enlaces = auxiliar_enlaces.getSiguiente();
            }
        }
        return dot;
    }
    
}
