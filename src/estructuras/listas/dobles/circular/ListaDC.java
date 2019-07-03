/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras.listas.dobles.circular;

import archivos.Escritura;
import estructuras.arboles.abb.NodoABB;
import estructuras.listas.dobles.circular.NodoDC;
import estructuras.arboles.avl.subestructura.lista.simple.ListaSimpleImagenes;
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

    public NodoDC getInicio() {
        return inicio;
    }

    public NodoDC getFin() {
        return fin;
    }

    public int getTamano() {
        return tamano;
    }
        
    public void insertar(NodoDC nuevo){
        if(buscar(nuevo.getId())){
            System.out.println("Ya se ha ingresado un usuario con este ID.");
        }else{
            if(estaVacia()){
                inicio = fin = nuevo;
                nuevo.setSiguiente(nuevo);
                nuevo.setAnterior(nuevo);
                System.out.println("Lista vacia, se inserto correctamente el ID: " + nuevo.getId());
            }else if(nuevo.getId() <= inicio.getId()){
                insertarAlInicio(nuevo);
            }else if(nuevo.getId() >= fin.getId()){
                insertarAlFinal(nuevo);
            }else{
                insertarAlMedio(nuevo);
            }
            tamano++;
        }
    }
    
    protected void insertarAlInicio(NodoDC nuevo){
        nuevo.setAnterior(fin);
        fin.setSiguiente(nuevo);
        inicio.setAnterior(nuevo);
        nuevo.setSiguiente(inicio);
        inicio = nuevo;
        System.out.println("Se inserto correctamente el ID: " + nuevo.getId() + ", al inicio.");
    }
    
    protected void insertarAlFinal(NodoDC nuevo){
        fin.setSiguiente(nuevo);
        nuevo.setAnterior(fin);
        nuevo.setSiguiente(inicio);
        inicio.setAnterior(nuevo);
        fin = nuevo;
        System.out.println("Se inserto correctamente el ID: " + nuevo.getId() + ", al final.");
    }
    
    protected void insertarAlMedio(NodoDC nuevo){
        boolean ins = false;
        NodoDC temporal = inicio;
        NodoDC auxiliar = inicio.getSiguiente();
        while(auxiliar != null){
            if(nuevo.getId() >= temporal.getId() && nuevo.getId() <= auxiliar.getId()){
                temporal.setSiguiente(nuevo);
                nuevo.setAnterior(temporal);
                nuevo.setSiguiente(auxiliar);
                auxiliar.setAnterior(nuevo);
                ins = true;
                break;
            } else {
                temporal = auxiliar;
                auxiliar = auxiliar.getSiguiente();
            }
        }
        if(!ins){
            temporal.setSiguiente(nuevo);
            nuevo.setAnterior(temporal);
        }
        System.out.println("Se inserto correctamente el ID: " + nuevo.getId() + ", al medio.");
    }
    
    public boolean buscar(int id){
        if(estaVacia()){
            System.out.println("Lista vacia.");
            return false;
        } else {
            if(id == inicio.getId()){
                System.out.println( "Se ha encontrado el nodo: "
                                +   "\nID: " + inicio.getId());
                return true;
            }
            NodoDC auxiliar = inicio.getSiguiente();
            while(auxiliar != inicio){
                if(id == auxiliar.getId()){
                    System.out.println( "Se ha encontrado el nodo: "
                                +   "\nID: " + auxiliar.getId());
                    return true;
                }
                auxiliar = auxiliar.getSiguiente();
            }
        }
        return false;
    }
    
    public NodoDC buscarNodo(int id){
        NodoDC encontrado;
        if(estaVacia()){
            System.out.println("Lista vacia.");
            return null;
        } else {
            if(id == inicio.getId()){
                System.out.println( "Se ha encontrado el nodo: "
                                +   "\nID: " + inicio.getId());
                encontrado = inicio;
                return encontrado;
            }
            NodoDC auxiliar = inicio.getSiguiente();
            while(auxiliar != inicio){
                if(id == auxiliar.getId()){
                    System.out.println( "Se ha encontrado el nodo: "
                                +   "\nID: " + auxiliar.getId());
                    encontrado = auxiliar;
                    return encontrado;
                }
                auxiliar = auxiliar.getSiguiente();
            }
        }
        return null;
    }
    
    public void eliminar(int id){
        if(!buscar(id)){
            System.out.println("No existe este ID en la lista.");
        }else{
            // Solo hay un elemento
            if(id == inicio.getId() && id == fin.getId() && inicio == fin){
                 System.out.println( "Se ha eliminado el nodo: "
                                +   "\nID: " + inicio.getId()
                                +   ", la lista ha quedado vacia.");
                fin = inicio = null;
                tamano = 0;
                return;
            // Eliminacion al inicio
            }else if(id == inicio.getId()){
                System.out.println( "Se ha eliminado el nodo: "
                                +   "\nID: " + inicio.getId()
                                +   ", al inicio.");
                inicio = inicio.getSiguiente();
                inicio.setAnterior(fin);
                fin.setSiguiente(inicio);
                tamano--;
            // Eliminacion al final
            }else if(id == fin.getId()){
                System.out.println( "Se ha eliminado el nodo: "
                                +   "\nID: " + fin.getId()
                                +   ", al final.");
                fin = fin.getAnterior();
                fin.setSiguiente(inicio);
                inicio.setAnterior(fin);
                tamano--;
            // Eliminacion al medio
            }else{
                NodoDC auxiliar = inicio.getSiguiente();
                while(auxiliar != inicio){
                    if(id == auxiliar.getId()){
                        System.out.println( "Se ha eliminado el nodo: "
                               +   "\nID: " + auxiliar.getId()
                               +   ", al medio.");
                        auxiliar.getSiguiente().setAnterior(auxiliar.getAnterior());
                        auxiliar.getAnterior().setSiguiente(auxiliar.getSiguiente());
                        auxiliar.setSiguiente(null);
                        auxiliar.setAnterior(null);
                        return;
                    }
                    auxiliar = auxiliar.getSiguiente();
                }
                tamano--;
            }
        }
    }
    
    public void mostrar(){
        if(estaVacia()){
            System.out.println("Lista vacia.");
        } else {
            System.out.println("Se muestra los elementos en la lista:");
            System.out.print("ID: " + inicio.getId() + "<->");
            NodoDC auxiliar = inicio.getSiguiente();
            while(auxiliar.getSiguiente() != inicio){
                System.out.print( "ID: " + auxiliar.getId()
                                +   "<->");
                auxiliar = auxiliar.getSiguiente();
            }
            System.out.print( "ID: " + auxiliar.getId()
                        +   "\n");
        }
    }
    
    public void graficar() throws IOException, InterruptedException{
        System.out.println("Se muestra la grafica de los elementos en la lista:");
        String nombre = "lista_doble_circular";
        String dot_grafo_lista_doble_ciruclar = "";
        dot_grafo_lista_doble_ciruclar 	=  
        "digraph lista_doble_circular"
        +   "\n{"
            +   "\n\tgraph[color = \"indigo:hotpink2\", fontcolor = \"white\", fontname = serif, style = filled, label = \"Lista doble circular de imagenes\"];"
            + 	"\n\tnode[shape = tripleoctagon, style = filled, color = white, fillcolor = black, fontcolor = white, peripheries = 2];"
            + 	"\n\tedge[color = \"blue:white:grey\"];"
            + 	"\n"
//            + 	"\n\tsubgraph cluster_lista_circular"
//            + 	"\n\t{"
            + 	"\n"
                +   generarDot()
//            +	"\n\t}"
        +   "\n}";
        Escritura.escribirArchivoDot(nombre, dot_grafo_lista_doble_ciruclar);
        Escritura.generarImagenDot(nombre);
    }
    
    public String generarDot() throws IOException{
        String dot = "";
        if(!estaVacia()){
            NodoDC auxiliar_contenido = inicio;
            while(auxiliar_contenido.getSiguiente() != inicio){
                // -------------------  Contendio   -------------------
                // Actual
                dot += 	
                "\n\t\t" + auxiliar_contenido.getId()
                +   "[fillcolor = black, fontcolor = white, label = <"
                +   "<FONT POINT-SIZE = \"9\"> "
                +   "ID imagen: " + auxiliar_contenido.getId()
                +   "</FONT>"
                +   ">"
                +   "]";
                // Lista interna de capas, de la imagen
                if(auxiliar_contenido.getCapas() != null){
                    // Obteniendo el subgrafo
                    dot += "\n" + auxiliar_contenido.getCapas().
                    graficar("_imagen_" + auxiliar_contenido.getId() + "_", 
                    "Capas de la imagen " + auxiliar_contenido.getId());
                    // Creando enlace
                    dot += "\n\t\t" + auxiliar_contenido.getId() + "->"
                    + "_imagen_" + auxiliar_contenido.getId() + "_"
                    + auxiliar_contenido.getCapas().getInicio().getId() + "\n\n";
                }
                auxiliar_contenido = auxiliar_contenido.getSiguiente();
            }
            dot += 	
            "\n\t\t" + auxiliar_contenido.getId()
            + 	"[fillcolor = black, fontcolor = white, label = <"
            + 	"<FONT POINT-SIZE = \"9\"> "
            +   "ID imagen: " + auxiliar_contenido.getId()
            +   "</FONT>"
            +   ">"
            +   "]"
            +   "\n";
            
            // Lista interna de capas, de la imagen
            if(auxiliar_contenido.getCapas() != null){
                // Obteniendo el subgrafo
                dot +=  "\n" + auxiliar_contenido.getCapas().
                graficar("_imagen_" + auxiliar_contenido.getId() + "_", 
                "Capas de la imagen " + auxiliar_contenido.getId());
                // Creando enlace
                dot += "\n\t\t" + auxiliar_contenido.getId() + "->"
                + "_imagen_" + auxiliar_contenido.getId() + "_"
                + auxiliar_contenido.getCapas().getInicio().getId() + "\n\n";
            }
            
            dot += "\n\t\t{ rank = same ";
            NodoDC auxiliar_ranks = inicio;
            while(auxiliar_ranks.getSiguiente() != inicio) {
                // -------------------  Enlaces   	-------------------
            	dot += "\t\t" + auxiliar_ranks.getId() + ", " 
                    + 	auxiliar_ranks.getSiguiente().getId();
            	auxiliar_ranks = auxiliar_ranks.getSiguiente();
            }
            dot +=  " }"
                +   "\n";
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
            dot +=  "\n";
            // -------------------  Enlaces al final  	-------------------
//            dot += "\n\t\t" + inicio.getId() + "->" + inicio.getAnterior().getId();
//            dot += "\n\t\t" + fin.getId() + "->" + fin.getSiguiente().getId();
        }
        return dot;
    }
}
