/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras.arboles.avl;

import java.io.IOException;

import archivos.Escritura;
import pojos.Usuario;

/**
 *
 * @author G
 */
public class ArbolAVL {
    public static String avl_aux;
    private NodoAVL raiz;

    // Constructor
    public ArbolAVL() {
        raiz = null;
    }
    
    // Verifica si esta vacio el arbol.
    public boolean esVacio(){
        return raiz == null;
    }
    
    public void vaciar(){
        raiz = null;
    }
    
    // Inserta la info en los nodos.
    public void insertar(Usuario nuevo_usuario){
        raiz = insertar(nuevo_usuario, raiz);
    }
    
    // Obtiene la altura del nodo.
    private int altura(NodoAVL t){
        return t == null? -1 : t.altura;
    }
    
    // Maximo del nodo izq/der.
    private int max(int lhs, int rhs){
        return lhs > rhs ? lhs : rhs;
    }
    
    // Factor de equilibrio
    private int getFactorEquilibrio(NodoAVL t) {
    	if(t == null) {
            return 0;
    	}else {
            return altura(t.getIzquierda())-altura(t.getDerecha());
    	}
    }
    
    // Inserta info en nodos, recursivamente.
    private NodoAVL insertar(Usuario nuevo_usuario, NodoAVL t){
    	System.out.println(nuevo_usuario.getId());
    	System.out.println("Insertando");
        if(t == null){
            t = new NodoAVL(nuevo_usuario);
//            System.out.println("Arbol vacio, insertando en la raiz.");
        }else if(nuevo_usuario.getId().compareTo(t.getUsuario().getId()) < 0){
//        	System.out.println("Insertando en la izquierda.");
            t.setIzquierda(insertar(nuevo_usuario, t.getIzquierda()));
            if(getFactorEquilibrio(t) == 2){
                if(nuevo_usuario.getId().compareTo(t.getIzquierda().getUsuario().getId()) < 0){
                    // CASO 1: Izquierda-izquierda
                    t = rotarConHijoIzq(t);
                }else{
                    // CASO 2: Izquierda-derecha
                    t = rotarDobleConHijoIzq(t);
                }
            }
            
        }else if(nuevo_usuario.getId().compareTo(t.getUsuario().getId()) > 0){
//        	System.out.println("Insertando en la derecha.");
            t.setDerecha(insertar(nuevo_usuario, t.getDerecha()));
            if(getFactorEquilibrio(t) == 2){
                if(nuevo_usuario.getId().compareTo(t.getDerecha().getUsuario().getId()) > 0){
                    // CASO 3: Derecha-derecha
                    t = rotarConHijoDer(t);
                }else{
                    // CASO 4: Derecha-izquierda
                    t = rotarDobleConHijoDer(t);
                }
            }
            
        }else{
            ;   // Duplicado; no hace nada.
            System.out.println("Valor duplicado.");
        }
        t.setAltura(max(altura(t.getIzquierda()), altura(t.getDerecha())) + 1);
        return t;
    }
    
    // Rotacion del arbol binario con un hijo izquierdo.
    private NodoAVL rotarConHijoIzq(NodoAVL k2){
        NodoAVL k1 = k2.getIzquierda();
        k2.setIzquierda(k1.getDerecha());
        k1.setDerecha(k2);
        k2.setAltura(max(altura(k2.getIzquierda()), altura(k2.getDerecha()))+1);
        k1.setAltura(max(altura(k1.getIzquierda()), k2.getAltura())+1);
        
        return k1;
    }
    
    // Rotacion del arbol binario con un hijo derecho.
    private NodoAVL rotarConHijoDer(NodoAVL k1){
        NodoAVL k2 = k1.getDerecha();
        k1.setDerecha(k2.getIzquierda());
        k2.setIzquierda(k1);
        k1.setAltura(max(altura(k1.getIzquierda()), altura(k1.getDerecha()))+1);
        k2.setAltura(max(altura(k2.getDerecha()), k1.getAltura())+1);
        
        return k2;
    }
    
    // Doble rotacion de un nodo del arbo binario: Primero el hijo izquierdo
    // con su hijo derecho; luego el nodo k3 con su nuevo hijo izquierdo.
    private NodoAVL rotarDobleConHijoIzq(NodoAVL k3){
        k3.setIzquierda(rotarConHijoDer(k3.getIzquierda()));
        return rotarConHijoIzq(k3);
    }
    
    // Doble rotacion de un nodo del arbo binario: Primero el hijo derecho
    // con su hijo izquierdo; luego el nodo k1 con su nuevo hijo derecho.
    private NodoAVL rotarDobleConHijoDer(NodoAVL k1){
        k1.setDerecha(rotarConHijoIzq(k1.getDerecha()));
        return rotarConHijoDer(k1);
    }
    
    // Devuelve el numero de nodos
    public int contarNodos(){
        return contarNodos(raiz);
    }
    
    // Contar nodos, recursivamente.
    private int contarNodos(NodoAVL r){
        if(r == null){
            return 0;
        }else{
            int l = 1;
            l += contarNodos(r.getIzquierda());
            l += contarNodos(r.getDerecha());
            return l;
        }
    }
    
    // Buscar un elemento.
    public boolean buscar(String codigo){
        return buscar(raiz, codigo);
    }
    
    // Buscar, recursivamente.
    private boolean buscar(NodoAVL r, String codigo){
        boolean encontrado = false;
        while((r != null) && ! encontrado)        {
            if(codigo.compareTo(r.getUsuario().getId()) < 0){
                r = r.getIzquierda();
            }else if(codigo.compareTo(r.getUsuario().getId()) > 0){
                r = r.getDerecha();
            }else{
                encontrado = true;
                break;
            }
            encontrado = buscar(r, codigo);
        }
        
        return encontrado;
    }
    
    // Eliminar nodo
    public void eliminar(String codigo) {
    	raiz = eliminar(codigo, raiz);
    }
    
    private NodoAVL eliminar(String codigo, NodoAVL t) {
    	if(t == null) {
    		return t;
    	}
    	if(codigo.compareTo(t.getUsuario().getId()) < 0) {
            t.setIzquierda(eliminar(codigo, t.getIzquierda()));
    	} else if(codigo.compareTo(t.getUsuario().getId()) > 0) {
            t.setDerecha(eliminar(codigo, t.getDerecha()));
    	}else {
            if(t.getIzquierda() == null || t.getDerecha() == null) {
                NodoAVL temp = null;
                if(temp == t.getIzquierda()) {
                    temp = t.getDerecha();
                }else {
                    temp = t.getIzquierda();
                }

                if(temp == null) {
                    temp = t;
                    t = null;
                }else {
                    t = temp;
                }
            }else {
                NodoAVL temp = sucesor(t.getDerecha());
                t.setUsuario(new Usuario(temp.getUsuario().getId()));
                t.setDerecha(eliminar(temp.getUsuario().getId(), t.getDerecha()));
            }
    	}
    	
    	if(t == null) {
    		return t;
    	}
    	
    	t.setAltura(max(altura(t.getIzquierda()), altura(t.getDerecha())));
    	int balance = getFactorEquilibrio(t);
    	
    	// CASO 1: Izquierda-izquierda
    	if(balance > 1 && getFactorEquilibrio(t.getIzquierda()) >= 0) {
            return rotarConHijoDer(t);
    	}
    	// CASO 2: Izquierda-derecha
    	if(balance > 1 && getFactorEquilibrio(t.getIzquierda()) < 0) {
            t.setIzquierda(rotarConHijoIzq(t.getIzquierda()));
            return rotarConHijoDer(t);
    	}
    	// CASO 3: Derecha-derecha
    	if(balance < -1 && getFactorEquilibrio(t.getDerecha()) <= 0) {
            return rotarConHijoIzq(t);
    	}
    	// CASO 4: Derecha-izquierda
    	if(balance < -1 && getFactorEquilibrio(t.getDerecha()) > 0) {
            t.setDerecha(rotarConHijoDer(t.getDerecha()));
            return rotarConHijoIzq(t);
    	}
    	
    	return t;
    }
    
    // Nodo sucesor
    private NodoAVL sucesor(NodoAVL t) {
    	NodoAVL aux = t;
    	while(aux.getIzquierda() != null) {
            aux = aux.getIzquierda();
    	}
    	return aux;
    }
    
    // Editar
    public void editar(String codigo_, Usuario usuario_nuevo) {
    	editar(codigo_, usuario_nuevo, raiz);
    }
    
    private void editar(String codigo_, Usuario usuario_nuevo, NodoAVL t) {
    	if(t != null) {
            if(t.getUsuario().getId().compareTo(codigo_) == 0) {
                t.setUsuario(null);
                t.setUsuario(usuario_nuevo);
                return;
            }
            editar(codigo_, usuario_nuevo, t.getIzquierda());
            editar(codigo_, usuario_nuevo, t.getDerecha());
    	}
    }

    boolean esRaiz, esIzquierdo;
	
    // Buscar usuario
    public Usuario buscarUsuario(String codigo) {
        Usuario user_temp = null;
        user_temp = buscarNodo(raiz, codigo).getUsuario();
        return user_temp;
    }
	
    // Buscar nodo
    public NodoAVL buscarNodo(String codigo) {
        return buscarNodo(raiz, codigo);
    }
	
	// Buscar nodo recursivo
    private NodoAVL buscarNodo(NodoAVL r, String codigo){
        NodoAVL aux = null;
        while(r != null){
            if(codigo.compareTo(r.getUsuario().getId()) < 0){
                r = r.getIzquierda();
                System.out.println("No se encontro nickname"+codigo);
            }else if(codigo.compareTo(r.getUsuario().getId()) > 0){
                r = r.getDerecha();
                System.out.println("No se encontro nickname"+codigo);
            }else{
                aux = r;
                System.out.println("Se encontro nickname"+aux.getUsuario().getId());
                break;
            }
            r = buscarNodo(r, codigo);
        }
        return aux;
    }
    
//    public Usuario verificarContraseña(String codigo, String contrasena){
////        System.out.println("Pass a verificar: "+contrasena);
////        System.out.println("Nodo encontrado: "+buscarNodo(raiz, nickname).getNickname());
////        System.out.println("Pass: "+buscarNodo(raiz, nickname).getContraseña());
//    	Usuario user_temp = null;
//        if(buscarNodo(raiz, codigo).getUsuario().getContrasena().equals(contrasena)){
//            user_temp = buscarNodo(raiz, codigo).getUsuario();
//            return user_temp;
//        }
//        return null;
//    }
    
    // Recorrer inorder.
    public void inorder(){
        inorder(raiz);
    }
        
    private void inorder(NodoAVL r){
        if(r != null){
            inorder(r.getIzquierda());
            System.out.print(r.getUsuario().getId()+", ");
            inorder(r.getDerecha());
        }
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
                +   "\n\tsubgraph cluster_avl"
                +   "\n\t{"
                +   "\n"
                    +   generarDot(raiz)
                    +   "\n\t\tcolor = lightcyan"
                    +   "\n\t\tfontcolor = steelblue4"
                    +   "\n\t\tfontname = serif"
                    +   "\n\t\tstyle = filled"
                    +   "\n\t\tlabel = Usuarios"
                +   "\n\t}"
            +   "\n}";
            Escritura.escribirArchivoDot(nombre, dot_grafo_arbol_avl);
            Escritura.generarImagenDot(nombre);
            avl_aux = "";
            dot_grafo_arbol_avl = "";
            return "";
        }else if(opcion.equals("subgrafo")) {
            String dot_subgrafo_arbol_avl = "";
            dot_subgrafo_arbol_avl =
            "\n\tsubgraph cluster_avl"
            + 	"\n\t{"
            + 	"\n\tnode[shape = egg, style = filled, color = navyblue, fontcolor = white, peripheries = 2];"
            +   "\n\tedge[color = deeppink];"
            + 	"\n"
            + 	"\n"
                +   generarDot(raiz)
                +   "\n\t\tcolor = lightcyan"
                +   "\n\t\tfontcolor = steelblue4"
                +   "\n\t\tfontname = serif"
                +   "\n\t\tstyle = filled"
                +   "\n\t\tlabel = Usuarios"
            + 	"\n\t}";
            return dot_subgrafo_arbol_avl;
        }
        return "";
    }
    
    private String generarDot(NodoAVL r) throws IOException{
        if(r != null)
        {
            generarDot(r.getIzquierda());
            // ................ Inicia codigo para graficar ................
            if(r.getIzquierda() != null && r.getDerecha() != null)
            {
                if(r.getIzquierda() != null)
                {
                    // Datos del nodo
                    avl_aux += 	
                    "\t\t" + r.getUsuario().getId()
                    +	"[label = < ID: " + r.getUsuario().getId();
//                    +   " <BR /> "
//                    +	"<FONT POINT-SIZE = \"10\"> nombre: " + r.getUsuario().getNombre()
//                    +	" <BR /> "
//                    +	"contrasena: " + r.getUsuario().getContrasena() 
//                    +   " </FONT>"
                    if(r.getUsuario().getImagenes() != null){
                        avl_aux += r.getUsuario().getImagenes().
                            graficar("_usuario_" + r.getUsuario().getId(), "Imagenes de usuario " + r.getUsuario().getId());
                    }
                    avl_aux +=   ">"
                    +   "]"
                    +	"\n";

                    avl_aux += 	
                    "\t\t" + r.getIzquierda().getUsuario().getId()
                    +	"[label = "
                    +   "< ID: " + r.getIzquierda().getUsuario().getId()
//                    +	" <BR /> "
//                    +	"<FONT POINT-SIZE = \"10\"> nombre: " + r.getIzquierda().getUsuario().getNombre()
//                    +	" <BR /> "
//                    +	"contrasena: " + r.getIzquierda().getUsuario().getContrasena()
//                    +   " </FONT>"
                    +   ">"
                    +   "]"
                    +	"\n";

                    // Enlaces a los nodos
                    avl_aux += 	
                    "\t\t" + r.getUsuario().getId() + "->" + r.getIzquierda().getUsuario().getId()
                    +   "\n";
                }
                if(r.getDerecha() != null)
                {
                    // Datos del nodo
                    avl_aux += 	
                    "\t\t" + r.getUsuario().getId()
                    +	"[label = "
                    +   "< ID: " + r.getUsuario().getId()
//                    +	" <BR /> "
//                    +	"<FONT POINT-SIZE = \"10\"> nombre: " + r.getUsuario().getNombre()
//                    +	" <BR /> "
//                    +	"contrasena: " + r.getUsuario().getContrasena()
//                    +   " </FONT>"
                    +   ">"
                    +   "]"
                    +	"\n";

                    avl_aux +=
                    "\t\t" + r.getDerecha().getUsuario().getId()
                    +	"[label = "
                    +   "< ID: " + r.getDerecha().getUsuario().getId()
//                    +	" <BR /> "
//                    +	"<FONT POINT-SIZE = \"10\"> nombre: " + r.getDerecha().getUsuario().getNombre()
//                    +	" <BR /> "
//                    +	"contrasena: " + r.getDerecha().getUsuario().getContrasena()
//                    +   " </FONT>"
                    +   ">"
                    +   "]"
                    + 	"\n";

                    // Enlaces a los nodos
                    avl_aux += 	
                    "\t\t" + r.getUsuario().getId() + "->" + r.getDerecha().getUsuario().getId() 
                    +   "\n";
                }
            }
            else
            {
            	// Datos del nodo
            	avl_aux += 	
                "\t\t" + r.getUsuario().getId()
                +   "[label = "
                +   "< ID: " + r.getUsuario().getId()
//                +   " <BR /> "
//                +   "<FONT POINT-SIZE=\"10\"> nombre: " + r.getUsuario().getNombre()
//                +   " <BR /> "
//                +   "contrasena: " + r.getUsuario().getContrasena()
//                +   " </FONT>"
                +   ">"
                +   "]"
                +   "\n";

            	// Enlaces a los nodos
                avl_aux += "\t\t" + r.getUsuario().getId();
                
                if(r.getIzquierda() != null && r.getDerecha() == null)
                {
                    // Enlaces a los nodos
                    avl_aux += 	
                    "->" + r.getIzquierda().getUsuario().getId() 
                    +   "\n";
                    
                    // Datos del nodo
                    avl_aux +=
                    "\t\t" + r.getIzquierda().getUsuario().getId()
                    +	"[label = "
                    +   "< ID: " + r.getIzquierda().getUsuario().getId()
//                    +	" <BR /> "
//                    +	"<FONT POINT-SIZE = \"10\"> nombre: " + r.getIzquierda().getUsuario().getNombre()
//                    +	" <BR /> "
//                    +	"contrasena: " + r.getIzquierda().getUsuario().getContrasena()
//                    +   " </FONT>"
                    +   ">"
                    +   "]"
                    +	"\n";
                }
                if(r.getIzquierda() == null && r.getDerecha() != null)
                {
                    // Enlaces a los nodos
                    avl_aux += 	
                    "->" + r.getDerecha().getUsuario().getId()
                    + 	"\n";

                    // Datos del nodo
                    avl_aux += 	
                    "\t\t" + r.getDerecha().getUsuario().getId()
                    +	"[label = "
                    +   "< ID: " + r.getDerecha().getUsuario().getId()
//                    +	" <BR /> "
//                    +	"<FONT POINT-SIZE = \"10\"> nombre: " + r.getDerecha().getUsuario().getNombre()
//                    +	" <BR /> "
//                    +	"contrasena: " + r.getDerecha().getUsuario().getContrasena()
//                    +   " </FONT>"
                    +   ">"
                    +   "]"
                    +	"\n";
                }
            }
            
            avl_aux += "\n";
            // ................ Finaliza codigo para graficar ................
            generarDot(r.getDerecha());
        }
        return avl_aux;
    }
}
