/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras.arboles.abb;
import archivos.Escritura;
import java.io.IOException;

/**
 *
 * @author g
 */
public class ABB {
    protected NodoABB raiz;
    protected boolean esIzquierdo, esRaiz;
    String abb_aux;
    
    public ABB(){
        raiz = null;
        abb_aux = "";
    }
    
    public boolean esVacio(){
        return raiz == null;
    }
    
    public void insertar(int id){
        raiz = insertar(raiz, id);
    }
    
    private NodoABB insertar(NodoABB nodo, int id){
        if(esVacio()){
            nodo = new NodoABB(id);
            System.out.println("Nodo agregado a la raiz.");
        }else{
            if(id <= nodo.getId()){
                nodo.setIzquierdo(insertar(nodo.getIzquierdo(), id));
            }else{
                nodo.setDerecho(insertar(nodo.getDerecho(), id));
            }
        }
        return nodo;
    }
    
    public boolean buscar(int id){
        if(esVacio()){
            return false;
        }else{
            return buscar(raiz, id);
        }
    }
    
    private boolean buscar(NodoABB r, int id){
        boolean encontrado = false;
        
        while((r != null) && !encontrado){
            int id_r = r.getId();
            if(id < id_r){
                r = r.getIzquierdo();
            } else if(id > id_r){
                r = r.getDerecho();
            }else{
                encontrado = true;
                break;
            }
            encontrado = buscar(r, id);
        }
        
        return encontrado;
    }
    
    public NodoABB buscarNodo(int id){
        
        if(raiz == null){
            return null;
        }
        
        return buscarNodo(raiz, id);
    }
    
    private NodoABB buscarNodo(NodoABB r, int id){
        // Verificamos si el ID buscado esta en la raiz.
        if(id == raiz.getId()){
            esRaiz = true;
            return raiz;
        }
        
        // Si el ID es el hijo izquierdo.
        if(r.getIzquierdo() != null && id == r.getIzquierdo().getId()){
            esIzquierdo = true;
            esRaiz = false;
            return r;
        } 
        // Si el ID es el hijo derecho.
        else if(r.getDerecho() != null && id == r.getDerecho().getId()){
            esIzquierdo = false;
            esRaiz = false;
            return r;
        }        
        // Si el ID es mayor que el de "r".
        else if(r.getDerecho() != null && id > r.getId()){
            // Busca en el subarbol derecho de arriba.
            return buscarNodo(r.getDerecho(), id);
        }
        // Si el ID es menor que el de "r".
        else if(r.getIzquierdo() != null && id < r.getId()){
            // Busca en el subarbol izquierdo de arriba.
            return buscarNodo(r.getIzquierdo(), id);
        }
        
        // Si no es ninguno de los casos anteriores, entonces no existe el ID.
        return null;
    }
    
    public String graficar(String opcion) throws IOException{
        String nombre = "arbolAVL";
        
        if(opcion.equals("grafo")) {
            String dot_grafo_arbol_avl = "";
            dot_grafo_arbol_avl =  
            "digraph avl"
            +   "\n{"
                +   "\n\tnode[shape = egg, style = filled, color = navyblue, fontcolor = white, peripheries = 2];"
                +   "\n\tedge[color = deeppink];"
                +   "\n"
                +   "\n\tsubgraph cluster_abb"
                +   "\n\t{"
                +   "\n"
                    +   generarDot(raiz)
                    +   "\n\t\tcolor = lightcyan"
                    +   "\n\t\tfontcolor = steelblue4"
                    +   "\n\t\tfontname = serif"
                    +   "\n\t\tstyle = filled"
                    +   "\n\t\tlabel = Capas"
                +   "\n\t}"
            +   "\n}";
        Escritura.escribirArchivoDot(nombre, dot_grafo_arbol_avl);
        Escritura.generarImagenDot(nombre);
        abb_aux = "";
        dot_grafo_arbol_avl = "";
        return "";
        }else if(opcion.equals("subgrafo")) {
            String dot_subgrafo_arbol_avl = "";
            dot_subgrafo_arbol_avl 	=	
            "\n\tsubgraph cluster_abb"
            +   "\n\t{"
            +   "\n\tnode[shape = egg, style = filled, color = navyblue, fontcolor = white, peripheries = 2];"
            +   "\n\tedge[color = deeppink];"
            +   "\n"
            +   "\n"
                +   generarDot(raiz)
                +   "\n\t\tcolor = lightcyan"
                +   "\n\t\tfontcolor = steelblue4"
                +   "\n\t\tfontname = serif"
                +   "\n\t\tstyle = filled"
                +   "\n\t\tlabel = Capas"
            +   "\n\t}";
            return dot_subgrafo_arbol_avl;
        }
        
        return "";
    }
    
    private String generarDot(NodoABB r){
        if(r != null)
        {
            generarDot(r.getIzquierdo());
            // ................ Inicia codigo para graficar ................
            if(r.getIzquierdo() != null && r.getDerecho() != null)
            {
                if(r.getIzquierdo() != null)
                {
                    // Datos del nodo
                    abb_aux += 	
                    "\t\t" + r.getId()
                    +	"[label = "
                    +   "< ID: " + r.getId()
                    +   ">"
                    +   "]"
                    +	"\n";

                    abb_aux += 	
                    "\t\t" + r.getIzquierdo().getId()
                    +	"[label = "
                    +   "< ID: " + r.getIzquierdo().getId()
                    +   ">"
                    +   "]"
                    +	"\n";

                    // Enlaces a los nodos
                    abb_aux += 	
                    "\t\t" + r.getId() + "->" + r.getIzquierdo().getId()
                    +   "\n";
                }
                if(r.getDerecho() != null)
                {
                    // Datos del nodo
                    abb_aux += 	
                    "\t\t" + r.getId()
                    +	"[label = "
                    +   "< ID: " + r.getId()
                    +   ">"
                    +   "]"
                    +	"\n";

                    abb_aux +=
                    "\t\t" + r.getDerecho().getId()
                    +	"[label = "
                    +   "< ID: " + r.getDerecho().getId()
                    +   ">"
                    +   "]"
                    + 	"\n";

                    // Enlaces a los nodos
                    abb_aux += 	
                    "\t\t" + r.getId() + "->" + r.getDerecho().getId()
                    +   "\n";
                }
            }
            else
            {
            	// Datos del nodo
            	abb_aux += 	
                "\t\t" + r.getId()
                +   "[label = "
                +   "< ID: " + r.getId()
                +   ">"
                +   "]"
                +   "\n";

            	// Enlaces a los nodos
                abb_aux += "\t\t" + r.getId();
                
                if(r.getIzquierdo() != null && r.getDerecho() == null)
                {
                    // Enlaces a los nodos
                    abb_aux += 	
                    "->" + r.getIzquierdo().getId()
                    +   "\n";
                    
                    // Datos del nodo
                    abb_aux +=
                    "\t\t" + r.getIzquierdo().getId()
                    +	"[label = "
                    +   "< codigo: " + r.getIzquierdo().getId()
                    +   ">"
                    +   "]"
                    +	"\n";
                }
                if(r.getIzquierdo() == null && r.getDerecho() != null)
                {
                    // Enlaces a los nodos
                    abb_aux += 	
                    "->" + r.getDerecho().getId()
                    + 	"\n";

                    // Datos del nodo
                    abb_aux += 	
                    "\t\t" + r.getDerecho().getId()
                    +	"[label = "
                    +   "< ID: " + r.getDerecho().getId()
                    +   ">"
                    +   "]"
                    +	"\n";
                }
            }
            
            abb_aux += "\n";
            // ................ Finaliza codigo para graficar ................
            generarDot(r.getDerecho());
        }
        return abb_aux;
    }
    
}
