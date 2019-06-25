/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras.listas.dobles.circular;

import archivos.Escritura;
import estructuras.arboles.abb.NodoABB;
import estructuras.listas.dobles.circular.NodoDC;
import estructuras.listas.simples.ListaSimple;
import java.io.IOException;

/**
 *
 * @author g
 */
public class ListaDC {
    NodoDC inicio;
    NodoDC fin;
    int tamano = 0;
    
    
    public boolean estaVacia(){
        return inicio == null;
    }
    
    public void insertarAlFinal(int id, String nombre, String apellido){
        if(buscar(id)){
            System.out.println("Ya se ha ingresado un usuario con este ID.");
        }else{
            NodoDC nuevo = new NodoDC(id, nombre, apellido, null);
//            nuevo.setSiguiente(inicio);
            if(estaVacia()){
                inicio = fin = nuevo;
                inicio.setSiguiente(fin);
                inicio.setAnterior(fin);
                fin.setSiguiente(inicio);
                System.out.println("Se inserto correctamente el ID: " + id);
            }else{
//                NodoDC nuevo = new NodoDC(id, nombre, apellido);
                fin.setSiguiente(nuevo);
                nuevo.setAnterior(fin);
                nuevo.setSiguiente(inicio);
                inicio.setAnterior(nuevo);
                fin = nuevo;                
                System.out.println("Se inserto correctamente el ID: " + id);
            }
            tamano++;
        }
    }
    
    public boolean buscar(int id){
        if(estaVacia()){
            System.out.println("Lista vacia.");
            return false;
        } else {
            NodoDC auxiliar = inicio;
//            if(inicio == fin){
//                System.out.println( "Se ha encontrado el nodo: "
//                                +   "\nID: " + auxiliar.getId()
//                                +   "\nNombre: " +  auxiliar.getNombre()
//                                +   "\nApellido: " + auxiliar.getApellido()
//                                +   "<->");
//                    return true;
//            }
            while(auxiliar.getSiguiente() != inicio){
                if(id == auxiliar.getId() || id == fin.getId()){
                    System.out.println( "Se ha encontrado el nodo: "
                                +   "\nID: " + auxiliar.getId()
                                +   "\nNombre: " +  auxiliar.getNombre()
                                +   "\nApellido: " + auxiliar.getApellido()
                                +   "<->");
                    return true;
                }
                auxiliar = auxiliar.getSiguiente();
            }
        }
        return false;
    }
    
    public void eliminar(int id){
        if(!buscar(id)){
            System.out.println("No existe este ID en la lista.");
        }else{
            // Solo hay un elemento
            if(id == inicio.getId() && id == fin.getId() && inicio == fin){
                 System.out.println( "Se ha eliminado el nodo: "
                                +   "\nID: " + inicio.getId()
                                +   "\nNombre: " +  inicio.getNombre()
                                +   "\nApellido: " + inicio.getApellido()
                                +   "<->");
                fin = inicio = null;
            // Eliminacion al frente
            }else if(id == inicio.getId()){
                System.out.println( "Se ha eliminado el nodo: "
                                +   "\nID: " + inicio.getId()
                                +   "\nNombre: " +  inicio.getNombre()
                                +   "\nApellido: " + inicio.getApellido()
                                +   "<->");
                inicio = inicio.getSiguiente();
                inicio.setAnterior(fin);
                fin.setSiguiente(inicio);
            }else if(id == fin.getId()){
                System.out.println( "Se ha eliminado el nodo: "
                                +   "\nID: " + fin.getId()
                                +   "\nNombre: " +  fin.getNombre()
                                +   "\nApellido: " + fin.getApellido()
                                +   "<->");
                fin = fin.getAnterior();
                fin.setSiguiente(inicio);
                inicio.setAnterior(fin);
            }else{
                NodoDC auxiliar = inicio;
                while(auxiliar != fin){
                    if(id == auxiliar.getId()){
                        System.out.println( "Se ha eliminado el nodo: "
                               +   "\nID: " + auxiliar.getId()
                               +   "\nNombre: " +  auxiliar.getNombre()
                               +   "\nApellido: " + auxiliar.getApellido()
                               +   "<->");
                        auxiliar.getSiguiente().setAnterior(auxiliar.getAnterior());
                        auxiliar.getAnterior().setSiguiente(auxiliar.getSiguiente());
                        auxiliar.setSiguiente(null);
                        auxiliar.setAnterior(null);
                        return;
                    }
                    auxiliar = auxiliar.getSiguiente();
                }
            }
        }
    }
    
    public void mostrar(){
        if(estaVacia()){
            System.out.println("Lista vacia.");
        } else {
            System.out.println("Se muestra los elementos en la lista:");
            NodoDC auxiliar = inicio;
            while(auxiliar.getSiguiente() != inicio){
                System.out.println( "\nID: " + auxiliar.getId()
                                +   "\nNombre: " +  auxiliar.getNombre()
                                +   "\nApellido: " + auxiliar.getApellido()
                                +   "<->");
                auxiliar = auxiliar.getSiguiente();
            }
            System.out.println( "\nID: " + auxiliar.getId()
                        +   "\nNombre: " +  auxiliar.getNombre()
                        +   "\nApellido: " + auxiliar.getApellido()
                        +   "<->");
        }
    }
    
    public void graficar() throws IOException{
        System.out.println("Se muestra la grafica de los elementos en la lista:");
        String nombre = "lista_doble_circular";
        String dot_grafo_lista_doble_ciruclar = "";
        dot_grafo_lista_doble_ciruclar 	=  "digraph lista_doble_circular"
        + 	"\n{"
            + 	"\n\tnode[shape = tripleoctagon, style = filled, color = white, fillcolor = black, fontcolor = white, peripheries = 2];"
            + 	"\n\tedge[color = \"blue:white:grey\"];"
            + 	"\n"
            + 	"\n\tsubgraph cluster_lista_circular"
            + 	"\n\t{"
            + 	"\n"
                +	generarDot()
                + 	"\n\t\tcolor = \"indigo:hotpink2\""
                + 	"\n\t\tfontcolor = white"
                + 	"\n\t\tfontname = serif"
                + 	"\n\t\tstyle = filled"
                + 	"\n\t\tlabel = \"Lista doble circular\""
            +	"\n\t}"
        + 	"\n}";
        Escritura.escribirArchivoDot(nombre, dot_grafo_lista_doble_ciruclar);
        Escritura.generarImagenDot(nombre);
    }
    
    public String generarDot(){
        String dot = "";
        if(!estaVacia()){
            NodoDC auxiliar_contenido = inicio;
            while(auxiliar_contenido.getSiguiente() != inicio){
                // -------------------  Contendio   -------------------
                // Actual
                dot += 	"\n\t\t\t" + auxiliar_contenido.getId()
                    + 	"[fillcolor = black, fontcolor = white, label = "
                    +   "<ID: " + auxiliar_contenido.getId()
                    + 	" <BR /> "
                    + 	"<FONT POINT-SIZE = \"9\"> "
                    +   "Nombre: " + auxiliar_contenido.getNombre()
                    + 	" <BR /> "
                    +   "Apellido: " + auxiliar_contenido.getApellido()
                    +   "</FONT>>]";
                
                auxiliar_contenido = auxiliar_contenido.getSiguiente();
            }
            dot += 	"\n\t\t\t" + auxiliar_contenido.getId()
                    + 	"[fillcolor = black, fontcolor = white, label = "
                    +   "<ID: " + auxiliar_contenido.getId()
                    + 	" <BR /> "
                    + 	"<FONT POINT-SIZE = \"9\"> "
                    +   "Nombre: " + auxiliar_contenido.getNombre()
                    + 	" <BR /> "
                    +   "Apellido: " + auxiliar_contenido.getApellido()
                    +   "</FONT>>]";
            
            dot += "\n";
            dot += "\n\t\t{ rank = same";
            NodoDC auxiliar_ranks = inicio;
            while(auxiliar_ranks.getSiguiente() != inicio) {
                // -------------------  Enlaces   	-------------------
            	dot += "\t\t" + auxiliar_ranks.getId() + " " 
                    + 	auxiliar_ranks.getSiguiente().getId();
            	auxiliar_ranks = auxiliar_ranks.getSiguiente();
            }
            dot += 	" }"
                + 	"\n";
            NodoDC auxiliar_enlaces = inicio;
            while(auxiliar_enlaces.getSiguiente() != inicio) {
                // -------------------  Enlaces   	-------------------
            	dot += "\n\t\t" + auxiliar_enlaces.getId() + "->" 
                    + 	auxiliar_enlaces.getSiguiente().getId();                
            	auxiliar_enlaces = auxiliar_enlaces.getSiguiente();
            }
            NodoDC auxiliar_enlaces_anteriores = inicio;
            while(auxiliar_enlaces_anteriores.getSiguiente() != inicio) {
                // -------------------  Enlaces   	-------------------
            	dot += "\n\t\t" + auxiliar_enlaces_anteriores.getSiguiente().getId() + "->" 
                    + auxiliar_enlaces_anteriores.getId();                
            	auxiliar_enlaces_anteriores = auxiliar_enlaces_anteriores.getSiguiente();
            }
            
            // -------------------  Enlaces al final  	-------------------
            dot += "\n\t\t" + inicio.getId() + "->" + inicio.getAnterior().getId();
            dot += "\n\t\t" + fin.getId() + "->" + fin.getSiguiente().getId();
        }
        return dot;
    }
}
