/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras.matrices.dispersa;

import archivos.Escritura;
import estructuras.arboles.abb.NodoABB;
import estructuras.matrices.dispersa.laterales.NodoLateral;
import estructuras.matrices.dispersa.laterales.ListaLaterales;
import estructuras.matrices.dispersa.cabeceras.NodoCabecera;
import estructuras.matrices.dispersa.cabeceras.ListaCabeceras;
import java.io.IOException;

/**
 *
 * @author g
 */
public class Matriz {
    public ListaCabeceras cabeceras;
    public ListaLaterales laterales;
    
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
        String ooo = "";
        int o = 0;
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                ooo = String.valueOf(o);
                insertar(i, j, ooo);
                o++;
            }
        }
    }
    
     public String graficar(String opcion, String id_capa) throws IOException{
        String nombre = "matriz";
        
        if(opcion.equals("grafo")) {
            String dot_grafo_matriz =  
            "digraph matriz_" + id_capa
            +   "\n{"
                +   "\n\tnode[shape = egg, style = filled, color = navyblue, fontcolor = white, peripheries = 2];"
                +   "\n\tedge[color = deeppink];"
                +   "\n"
                +   "\n\tsubgraph cluster_matriz_" + id_capa
                +   "\n\t{"
                +   "\n"
                +   "\n"
                    +   generarDot(id_capa)
                    +   "\n"
                    +   "\n\t\tcolor = lightcyan"
                    +   "\n\t\tfontcolor = steelblue4"
                    +   "\n\t\tfontname = serif"
                    +   "\n\t\tstyle = filled"
                    +   "\n\t\tlabel = \"Capa " + id_capa + "\""
                +   "\n\t}"
            +   "\n}";
        Escritura.escribirArchivoDot(nombre, dot_grafo_matriz);
        Escritura.generarImagenDot(nombre);
        dot_grafo_matriz = "";
        return "";
        }else if(opcion.equals("subgrafo")) {
            String dot_subgrafo_matriz =	
            "\n\tsubgraph cluster_matriz_" + id_capa
            +   "\n\t{"
            +   "\n\tnode[shape = egg, style = filled, color = navyblue, fontcolor = white, peripheries = 2];"
            +   "\n\tedge[color = deeppink];"
            +   "\n"
            +   "\n"
                +   generarDot(id_capa)
                +   "\n"
                +   "\n\t\tcolor = lightcyan"
                +   "\n\t\tfontcolor = steelblue4"
                +   "\n\t\tfontname = serif"
                +   "\n\t\tstyle = filled"
                +   "\n\t\tlabel = \"Capa " + id_capa + "\""
            +   "\n\t}";
            return dot_subgrafo_matriz;
        }
        
        return "";
    }
     
     private String generarDot(String id_capa){
         String dot = "";
         // Creando nodo pivote
        dot +=  
        "\n\t\t//Nodo pivote"
        + "\n\t\tXY" + id_capa
        +   "["
        +   "group = XY" + id_capa
        +   ", label = <"
        +   "<FONT POINT-SIZE = \"9\">"
        +   id_capa
        +   "</FONT>"
        +   ">"
        +   "]";
        // Creando nodos cabeceras
        if(!cabeceras.esVacia()){
            dot += "\n\t\t//Nodos cabeceras";
            NodoCabecera cabecera_auxiliar;
            cabecera_auxiliar = cabeceras.getPrimero();
            while(cabecera_auxiliar != null){
                dot +=  
                "\n\t\tX" + cabecera_auxiliar.getX() + id_capa
                +   "["
                +   "group = X" + cabecera_auxiliar.getX() + id_capa
                +   ", label = <"
                +   "<FONT POINT-SIZE = \"9\">"
                +   cabecera_auxiliar.getX()
                +   "</FONT>"
                +   ">"
                +   "]";
                cabecera_auxiliar = cabecera_auxiliar.getSiguiente();
            }
        }
        // Creando nodos laterales
        if(!laterales.esVacia()){
            dot += "\n\t\t//Nodos laterales";
            NodoLateral lateral_auxiliar;
            lateral_auxiliar = laterales.getPrimero();
            while(lateral_auxiliar != null){
                dot +=  
                "\n\t\tY" + lateral_auxiliar.getY() + id_capa
                +   "["
                +   "group = XY" + id_capa
                +   ", label = <"
                +   "<FONT POINT-SIZE = \"9\">"
                +   lateral_auxiliar.getY()
                +   "</FONT>"
                +   ">"
                +   "]";
                lateral_auxiliar = lateral_auxiliar.getSiguiente();
            }
        }
        
        // Creando rank = min, para cabeceras
            // Nodo pivote
        dot += "\n\t\t//rank min de cabeceras";
        dot += "\n\t\t{ rank = min XY" + id_capa;
            // Cabeceras
        if(!cabeceras.esVacia()){
            NodoCabecera cabecera_auxiliar;
            cabecera_auxiliar = cabeceras.getPrimero();
            while(cabecera_auxiliar != null){
                dot +=  
                " X" + cabecera_auxiliar.getX() + id_capa;
                cabecera_auxiliar = cabecera_auxiliar.getSiguiente();
            }
        }
        dot += " }";
        
        // Creando enlaces cabeceras
        dot += "\n\t\t//Enlaces cabeceras";
            // Nodo pivote
        dot += "\n\t\tXY" + id_capa;
            // Cabeceras
        if(!cabeceras.esVacia()){
            NodoCabecera cabecera_auxiliar;
            cabecera_auxiliar = cabeceras.getPrimero();
            dot += "->" + "X" + cabecera_auxiliar.getX() + id_capa;
            while(cabecera_auxiliar.getSiguiente() != null){
                dot +=  
                "\n\t\tX" + cabecera_auxiliar.getX() + id_capa
                + "->" +  "X" + cabecera_auxiliar.getSiguiente().getX() + id_capa;
                
                cabecera_auxiliar = cabecera_auxiliar.getSiguiente();
            }
        }
        // Creando enlaces laterales
        dot += "\n\t\t//Enlaces laterales";
        // Nodo pivote
        dot += "\n\t\tXY" + id_capa;
            // laterales
        if(!laterales.esVacia()){
            NodoLateral lateral_auxiliar;
            lateral_auxiliar = laterales.getPrimero();
            dot += "->" + "Y" + lateral_auxiliar.getY() + id_capa;
            while(lateral_auxiliar.getSiguiente() != null){
                dot +=  
                "\n\t\tY" + lateral_auxiliar.getY() + id_capa
                + "->" +  "Y" + lateral_auxiliar.getSiguiente().getY() + id_capa;
                lateral_auxiliar = lateral_auxiliar.getSiguiente();
            }
        }
        
        // Creando nodos internos
        dot += "\n\t\t//Nodos internos";
            // Cabeceras
        if(!cabeceras.esVacia()){
            NodoCabecera cabecera_auxiliar;
            cabecera_auxiliar = cabeceras.getPrimero();
            while(cabecera_auxiliar != null){
                // Accediendo a su lista (vertical) de columnas
                if(!cabecera_auxiliar.getColumnas().esVacia()){
                    NodoOrtogonal columna_auxiliar = cabecera_auxiliar.getColumnas().getPrimero();
                    while(columna_auxiliar != null){
                        dot +=  
                        "\n\t\tX" + columna_auxiliar.getX() + "Y" + columna_auxiliar.getY() + id_capa
                        +   "["
                        +   "group = X" + cabecera_auxiliar.getX() + id_capa
                        +   ", fillcolor = \"" + columna_auxiliar.getColor() + "\", "
                        +   "label = <"
                        +   "<FONT COLOR=\"WHITE\"> " + columna_auxiliar.getColor()
                        +   "</FONT>"
                        +   ">"
                        +   "]";
                        columna_auxiliar = columna_auxiliar.getAbajo();
                    }
                }
                cabecera_auxiliar = cabecera_auxiliar.getSiguiente();
            }
        }
            // Laterales
            // Suponiendo que se accedio a todos, con el recorrido anterior...
        //  Creando enlaces de nodos internos
            // Cabeceras
        dot += "\n\n\t\t//Enlaces de cabeceras y nodos internos";
        if(!cabeceras.esVacia()){
            NodoCabecera cabecera_auxiliar;
            cabecera_auxiliar = cabeceras.getPrimero();
            while(cabecera_auxiliar != null){
                // Accediendo a su lista interna (vertical) de columnas
                if(!cabecera_auxiliar.getColumnas().esVacia()){
                    NodoOrtogonal columna_auxiliar = cabecera_auxiliar.getColumnas().getPrimero();
                    // Enlazando cabecera con primer nodo interno de su lista de columnas
                    dot += 
                    "\n\t\tX" + cabecera_auxiliar.getX() + id_capa
                    + "->" + "X" + columna_auxiliar.getX() + "Y" + columna_auxiliar.getY() + id_capa;
                    // Enlaces internos hacia abajo
                    dot += "\n\t\t//Enlaces internos internos hacia abajo";
                    while(columna_auxiliar.getAbajo() != null){
                        dot +=  
                        "\n\t\tX" + columna_auxiliar.getX() + "Y" + columna_auxiliar.getY() + id_capa
                        + "->" + "X" + columna_auxiliar.getAbajo().getX() + "Y" + columna_auxiliar.getAbajo().getY() + id_capa;
                        columna_auxiliar = columna_auxiliar.getAbajo();
                    }
                    // Enlaces internos hacia arriba
                    dot += "\n\t\t//Enlaces internos internos hacia arriba";
                    NodoOrtogonal columna_auxiliar_arriba = cabecera_auxiliar.getColumnas().getUltimo();
                    while(columna_auxiliar_arriba.getArriba() != null){
                        dot +=  
                        "\n\t\tX" + columna_auxiliar_arriba.getX() + "Y" + columna_auxiliar_arriba.getY() + id_capa
                        + "->" + "X" + columna_auxiliar_arriba.getArriba().getX() + "Y" + columna_auxiliar_arriba.getArriba().getY() + id_capa;
                        columna_auxiliar_arriba = columna_auxiliar_arriba.getArriba();
                    }
                }
                cabecera_auxiliar = cabecera_auxiliar.getSiguiente();
            }
        }
            // Laterales
        dot += "\n\n\t\t//Enlaces de laterales y nodos internos";
        if(!laterales.esVacia()){
            NodoLateral lateral_auxiliar;
            lateral_auxiliar = laterales.getPrimero();
            while(lateral_auxiliar != null){
                // Accediendo a su lista interna (horizontal) de filas
                if(!lateral_auxiliar.getFilas().esVacia()){
                    NodoOrtogonal fila_auxiliar = lateral_auxiliar.getFilas().getPrimero();
                    // Enlazando lateral con primer nodo interno de su lista de filas
                    dot += 
                    "\n\t\tY" + lateral_auxiliar.getY() + id_capa
                    + "->" + "X" + fila_auxiliar.getX() + "Y" + fila_auxiliar.getY() + id_capa;
                    // Enlaces internos hacia la derecha
                    dot += "\n\t\t//Enlaces internos internos hacia la derecha";
                    while(fila_auxiliar.getDerecha() != null){
                        dot +=  
                        "\n\t\tX" + fila_auxiliar.getX() + "Y" + fila_auxiliar.getY() + id_capa
                        + "->" + "X" + fila_auxiliar.getDerecha().getX() + "Y" + fila_auxiliar.getDerecha().getY() + id_capa;
                        fila_auxiliar = fila_auxiliar.getDerecha();
                    }
                    // Enlaces internos hacia la izquierda
                    dot += "\n\t\t//Enlaces internos internos hacia la izquierda";
                    NodoOrtogonal fila_auxiliar_izquierda = lateral_auxiliar.getFilas().getUltimo();
                    while(fila_auxiliar_izquierda.getIzquierda()!= null){
                        dot +=  
                        "\n\t\tX" + fila_auxiliar_izquierda.getX() + "Y" + fila_auxiliar_izquierda.getY() + id_capa
                        + "->" + "X" + fila_auxiliar_izquierda.getIzquierda().getX() + "Y" + fila_auxiliar_izquierda.getIzquierda().getY() + id_capa;
                        fila_auxiliar_izquierda = fila_auxiliar_izquierda.getIzquierda();
                    }
                }
                lateral_auxiliar = lateral_auxiliar.getSiguiente();
            }
        }
        
        // Creando rank = same, para laterales y sus filas internas
        dot += "\n\t\t//rank same de laterales y sus nodos internos";
        if(!laterales.esVacia()){
            NodoLateral lateral_auxiliar;
            lateral_auxiliar = laterales.getPrimero();
            while(lateral_auxiliar != null){
                // Accediendo a su lista interna (horizontal) de filas
                if(!lateral_auxiliar.getFilas().esVacia()){
                    NodoOrtogonal fila_auxiliar = lateral_auxiliar.getFilas().getPrimero();
                    dot += 
                    "\n\t\t{ rank = same " 
                    + "Y" + lateral_auxiliar.getY() + id_capa
                    + " " + "X" + fila_auxiliar.getX() + "Y" + fila_auxiliar.getY() + id_capa;
                    while(fila_auxiliar.getDerecha() != null){
                        dot +=  
                        " X" + fila_auxiliar.getX() + "Y" + fila_auxiliar.getY() + id_capa
                        + " " + "X" + fila_auxiliar.getDerecha().getX() + "Y" + fila_auxiliar.getDerecha().getY() + id_capa;
                        fila_auxiliar = fila_auxiliar.getDerecha();
                    }
                    dot += " }";
                }
                lateral_auxiliar = lateral_auxiliar.getSiguiente();
            }
        }
        return dot;
     }
}