/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras.listas.dobles.circular.subestructura.lista.simple;

import estructuras.arboles.avl.subestructura.lista.simple.*;
import archivos.Escritura;
import java.io.IOException;

/**
 *
 * @author g
 */
public class ListaSimpleCapas {
    protected NodoSimpleCapas inicio;
    protected int tamano;

    public ListaSimpleCapas() {
        inicio = null;
        tamano = 0;
    }

    public NodoSimpleCapas getInicio() {
        return inicio;
    }
    
    public boolean esVacia(){
        return inicio == null;
    }
    
    public int getTamano(){
        return this.tamano;
    }
    
    public void agregarAlFinal(NodoSimpleCapas nuevo){
//        NodoSimpleCapas nuevo = new NodoSimpleCapas(dato);
        if(esVacia()){
            // Lista vacia, por lo que el inicio sera igual al nodo nuevo.
            inicio = nuevo;
        }else{
            // Se posiciona al inicio el nodo auxiliar.
            NodoSimpleCapas auxiliar = inicio;
            while(auxiliar.getSiguiente() != null){
                auxiliar = auxiliar.getSiguiente();
            }
            // Una vez alcanzado el final, se agrega el nuevo nodo.
            auxiliar.setSiguiente(nuevo);
        }
        tamano++;
    }
    
    public String graficar(String id, String titulo) throws IOException{
        String dot_grafo_lista_ordenada =
        "\n\t\tsubgraph cluster_lista_simple" + id
        +   "\n\t\t{"
        +   "\n\t\t\tgraph[color = \"darkturquoise:aliceblue\", fontcolor = \"black\", style = filled, fontsize = 7, label = \"" + titulo + "\"];"
        +   "\n\t\t\tnode[shape = tripleoctagon, style = filled, color = white, fillcolor = black, fontcolor = white, peripheries = 2];"
        +   "\n\t\t\tedge[color = \"purple:grey\"];"
        +   "\n"
            +   generarDot(id)
        +   "\n\t\t}"
        +   "\n";
        return dot_grafo_lista_ordenada;
    }
    
    public String generarDot(String id){
        String dot = "";
        if(!esVacia()){
            NodoSimpleCapas auxiliar_contenido = inicio;
            while(auxiliar_contenido != null){
                // -------------------  Contendio   -------------------
                // Actual
                dot += 	"\n\t\t\t" + id + auxiliar_contenido.getId()
                    + 	"[fillcolor = black, fontcolor = white, label = <"
                    +   "<FONT POINT-SIZE = \"9\">"
                    +   " ID capa: " + auxiliar_contenido.getId()
                    +   " </FONT>"
                    +   ">"
                    +   "]";
                
                auxiliar_contenido = auxiliar_contenido.getSiguiente();
            }
            dot += "\n";
            
            NodoSimpleCapas auxiliar_enlaces = inicio;
            while(auxiliar_enlaces.getSiguiente() != null) {
                // -------------------  Enlaces   	-------------------
            	dot += "\n\t\t\t" + id + auxiliar_enlaces.getId() + "->" 
                    + id + auxiliar_enlaces.getSiguiente().getId();
            	auxiliar_enlaces = auxiliar_enlaces.getSiguiente();
            }
        }
        return dot;
    }
    
}
