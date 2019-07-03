/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras.arboles.abb;
import archivos.Escritura;
import estructuras.arboles.abb.subestructura.cola.ColaABB;
import estructuras.arboles.abb.subestructura.cola.NodoColaABB;
import estructuras.matrices.dispersa.Matriz;
import java.io.IOException;

/**
 *
 * @author g
 */
public class ABB {
    protected NodoABB raiz;
    protected boolean esIzquierdo, esRaiz;
    String abb_aux;
    ColaABB cola_auxiliar;
    ColaABB cola_recorrido_preorder;
    ColaABB cola_recorrido_inorder;
    ColaABB cola_recorrido_postorder;
    
    public ABB(){
        raiz = null;
        abb_aux = "";
        cola_auxiliar = null;
        cola_recorrido_preorder = null;
        cola_recorrido_inorder = null;
        cola_recorrido_postorder = null;
    }

    public ColaABB getCola_auxiliar() {
        return cola_auxiliar;
    }

    public ColaABB getCola_recorrido_inorder() {
        return cola_recorrido_inorder;
    }
    
    public boolean esVacio(){
        return raiz == null;
    }
    
    public void insertar(int id, Matriz capa){
       raiz = insertar(raiz, id, capa);
    }
    
    private NodoABB insertar(NodoABB nodo, int id, Matriz capa){
        if(nodo == null){
            nodo = new NodoABB(id, capa);
        }else{
            if(id <= nodo.getId()){
                nodo.setIzquierdo(insertar(nodo.getIzquierdo(), id, capa));
            }else{
                nodo.setDerecho(insertar(nodo.getDerecho(), id, capa));
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
    
    public void inorder(){
        inorder(raiz);
    }
    
    private void inorder(NodoABB nodo){
        if(nodo != null){
            inorder(nodo.getIzquierdo());
            System.out.print(nodo.getId() + ", ");
            inorder(nodo.getDerecho());
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
    
    NodoABB nodo_encontrado;
    public NodoABB buscarNodo(int id){
        return buscarNodoABB(raiz, id);
    }

    private NodoABB buscarNodoABB(NodoABB r, int id){
        if(null == r){
            return null;
        }
        if(r.getId() == id){
            System.out.println("Se encontro el ID: " + id);
            return r;
        } else if(id < r.getId()){
            return buscarNodoABB(r.getIzquierdo(), id);
        }else{
            return buscarNodoABB(r.getDerecho(), id);
        }
    }
    
    public ColaABB listar(){
        cola_auxiliar = new ColaABB();
        listar(raiz);
        return cola_auxiliar;
    }
    
    public ColaABB r_preorder(){
        cola_recorrido_preorder = new ColaABB();
        r_preorder(raiz);
        return cola_recorrido_preorder;
    }
    
    public ColaABB r_inorder(){
        cola_recorrido_inorder = new ColaABB();
        r_inorder(raiz);
        return cola_recorrido_inorder;
    }
    
    public ColaABB r_postorder(){
        cola_recorrido_postorder = new ColaABB();
        r_postorder(raiz);
        return cola_recorrido_postorder;
    }
    
    public ColaABB recorrido(String recorrido){
        
        switch(recorrido){
            case "preorder":
                cola_recorrido_preorder = new ColaABB();
                r_preorder(raiz);
                return cola_recorrido_preorder;
//                break;
            case "inorder":
                cola_recorrido_inorder = new ColaABB();
                r_inorder(raiz);
                return cola_recorrido_inorder;
//                break;
            case "postorder":
                cola_recorrido_postorder = new ColaABB();
                r_postorder(raiz);
                return cola_recorrido_postorder;
//                break;
            default:
                break;
        }
        return null;
    }
    
    private void r_preorder(NodoABB nodo){
        if(nodo == null){
            System.out.println("Arbol vacio.");
        }
        if(nodo != null){
            cola_recorrido_preorder.encolar(new NodoColaABB(new NodoABB(nodo.getId(), nodo.getCapa())));
            r_preorder(nodo.getIzquierdo());
            r_preorder(nodo.getDerecho());
        }
    }
    
    private void r_inorder(NodoABB nodo){
        if(nodo == null){
            System.out.println("Arbol vacio.");
        }
        if(nodo != null){
            r_inorder(nodo.getIzquierdo());
            cola_recorrido_inorder.encolar(new NodoColaABB(new NodoABB(nodo.getId(), nodo.getCapa())));
            r_inorder(nodo.getDerecho());
        }
    }
    
    private void r_postorder(NodoABB nodo){
        if(nodo == null){
            System.out.println("Arbol vacio.");
        }
        if(nodo != null){
            r_postorder(nodo.getIzquierdo());
            r_postorder(nodo.getDerecho());
            cola_recorrido_postorder.encolar(new NodoColaABB(new NodoABB(nodo.getId(), nodo.getCapa())));
        }
    }
    
    
    private void listar(NodoABB nodo){
        if(nodo == null){
            System.out.println("Arbol vacio.");
        }
        if(nodo != null){
            listar(nodo.getIzquierdo());
            cola_auxiliar.encolar(new NodoColaABB(new NodoABB(nodo.getId(), nodo.getCapa())));
            listar(nodo.getDerecho());
        }
    }
    
    public String graficar(String opcion) throws IOException, InterruptedException{
        String nombre = "arbolABB";
        
        if(opcion.equals("grafo")) {
            String dot_grafo_abb =  
            "digraph abb"
            +   "\n{"
                +   "\n\tgraph[color = \"lightcyan\", fontcolor = \"steelblue4\", fontname = serif, style = filled, label = \"Capas\"];"
                +   "\n\tnode[shape = egg, style = filled, color = navyblue, fontcolor = white, peripheries = 2];"
                +   "\n\tedge[color = deeppink];"
                +   "\n"
//                +   "\n\tsubgraph cluster_abb"
//                +   "\n\t{"
                +   "\n"
                    +   generarDot(raiz)
//                +   "\n\t}"
            +   "\n}";
        Escritura.escribirArchivoDot(nombre, dot_grafo_abb);
        Escritura.generarImagenDot(nombre);
        abb_aux = "";
        dot_grafo_abb = "";
        return "";
        }else if(opcion.equals("subgrafo")) {
            String dot_subgrafo_abb = 	
            "\n\tsubgraph cluster_abb"
            +   "\n\t{"
            +   "\n\tgraph[color = \"lightcyan\", fontcolor = \"steelblue4\", fontname = serif, style = filled, label = \"Capas\"];"
            +   "\n\tnode[shape = egg, style = filled, color = navyblue, fontcolor = white, peripheries = 2];"
            +   "\n\tedge[color = deeppink];"
            +   "\n"
            +   "\n"
                +   generarDot(raiz)
            +   "\n\t}";
            return dot_subgrafo_abb;
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
                    +	"[label = <"
                    +   "<FONT POINT-SIZE = \"9\">"
                    +   " ID: " + r.getId()
                    +   "</FONT>"
                    +   ">"
                    +   "]"
                    +	"\n";

                    abb_aux += 	
                    "\t\t" + r.getIzquierdo().getId()
                    +	"[label = <"
                    +   "<FONT POINT-SIZE = \"9\">"
                    +   " ID: " + r.getIzquierdo().getId()
                    +   "</FONT>"
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
                    +	"[label = <"
                    +   "<FONT POINT-SIZE = \"9\">"
                    +   " ID: " + r.getId()
                    +   "</FONT>"
                    +   ">"
                    +   "]"
                    +	"\n";

                    abb_aux +=
                    "\t\t" + r.getDerecho().getId()
                    +	"[label = <"
                    +   "<FONT POINT-SIZE = \"9\">"
                    +   " ID: " + r.getDerecho().getId()
                    +   "</FONT>"
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
                +   "[label = <"
                +   "<FONT POINT-SIZE = \"9\">"
                +   " ID: " + r.getId()
                +   "</FONT>"
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
                    +	"[label = <"
                    +   "<FONT POINT-SIZE = \"9\">"
                    +   " ID: " + r.getIzquierdo().getId()
                    +   "</FONT>"
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
                    +	"[label = <"
                    +   "<FONT POINT-SIZE = \"9\">"
                    +   " ID: " + r.getDerecho().getId()
                    +   "</FONT>"
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
