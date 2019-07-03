/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivos;

import estructuras.arboles.avl.subestructura.lista.simple.ListaSimpleImagenes;
import estructuras.arboles.avl.subestructura.lista.simple.NodoSimpleImagenes;
import estructuras.listas.dobles.circular.NodoDC;
import estructuras.listas.dobles.circular.subestructura.lista.simple.ListaSimpleCapas;
import estructuras.listas.dobles.circular.subestructura.lista.simple.NodoSimpleCapas;
import estructuras.matrices.dispersa.Matriz;
import interfaz.VentanaPrincipal;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import pojos.Usuario;

/**
 *
 * @author g
 */
public class Lectura {
        
    public static void leerCapas(String ruta) throws FileNotFoundException, IOException, InterruptedException{
        Matriz capa_auxiliar = null;
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(ruta);
            sc = new Scanner(inputStream, "UTF-8");
            String id_capa_aux = "0";
// ..................... Contenido archivo ..................... 
            while (sc.hasNextLine()) {
                /*
                id_capa {
                   x, y, color;
                }
                */
                String linea = sc.nextLine();
                String x = "0";
                String y = "0";
                String color = "";
                if(linea.contains("{")){
                    // id_capa {
                    capa_auxiliar = new Matriz();
                    id_capa_aux = linea.replace("{","");
                    // id_capa 
                    id_capa_aux = id_capa_aux.replace("\\s+","");
                    // id_capa
                    id_capa_aux = id_capa_aux.trim();
                    System.out.println("ID capa: " + id_capa_aux);
                }
                if(linea.contains(";")){
                        String[] coordenada_auxiliar = linea.split(",");
                        x = coordenada_auxiliar[0];
                        x = x.replace("\\s+", "");
                        x = x.trim();
                        y = coordenada_auxiliar[1];
                        y = y.replace("\\s+", "");
                        y = y.trim();
                        color = coordenada_auxiliar[2];
                        color = color.replace(";", "");
                        color = color.trim();
                        capa_auxiliar.insertar(Integer.parseInt(y), Integer.parseInt(x), color);
//                        System.out.println("X: " + x + ", Y: " + y + ", Color: " + color);
                }
                if(linea.contains("}")){
                    VentanaPrincipal.arbol_binario_capas.insertar(Integer.parseInt(id_capa_aux), capa_auxiliar);
//                    capa_auxiliar.graficar("imagen", "grafo", id_capa_aux);
//                    VentanaPrincipal.arbol_binario_capas.buscarNodo(Integer.parseInt(id_capa_aux)).getCapa().graficar("imagen", "grafo", id_capa_aux);
                    id_capa_aux = "0";
//                    break;
                }
            }
// ..................... Fin contenido archivo ..................... 
//            VentanaPrincipal.arbol_binario_capas.graficar("grafo");
            
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }
    }
    
    public static void leerImagenes(String ruta) throws FileNotFoundException, IOException, InterruptedException{
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(ruta);
            sc = new Scanner(inputStream, "UTF-8");
// ..................... Contenido archivo ..................... 
            ListaSimpleCapas lista_capas_auxiliar;
            while (sc.hasNextLine()) {
                lista_capas_auxiliar = new ListaSimpleCapas();
                String linea = sc.nextLine();
                linea = linea.replace("\\s+", "");
                linea = linea.trim();
                // id_imagen { id_capa_i, id_capa_i+1... }
                String[] id_contenido = linea.split("\\{");
                // id_imagen
                String id_imagen = "0";
                id_imagen = id_contenido[0];
                // id_capa_i, id_capa_i+1... }
                String contenido = id_contenido[1];
                contenido = contenido.replace("}", "");
                // id_capa_i, id_capa_i+1... 
                String[] contenido_array = contenido.split(",");
                for (String string : contenido_array) {
                    String capa_id = "0";
                    capa_id = string;
                    capa_id = capa_id.replace("\\s+", "");
                    capa_id = capa_id.trim();
//                    System.out.println(capa_id);
                    
                    if(!VentanaPrincipal.arbol_binario_capas.esVacio()){
                        Matriz capa_auxiliar = VentanaPrincipal.arbol_binario_capas.buscarNodo(Integer.parseInt(capa_id)).getCapa();
                        lista_capas_auxiliar.agregarAlFinal(new NodoSimpleCapas(Integer.parseInt(capa_id), capa_auxiliar));
                    }else{
                        JOptionPane.showMessageDialog(null, "No se han cargado las capas", 
                        "Atencion", JOptionPane.WARNING_MESSAGE);
//                        lista_capas_auxiliar.agregarAlFinal(new NodoSimpleCapas(Integer.parseInt(capa_id)));
                    }
                }
                VentanaPrincipal.lista_doble_imagenes.insertar(new NodoDC(Integer.parseInt(id_imagen), lista_capas_auxiliar));
//                System.out.println(linea);
            }
//            VentanaPrincipal.lista_doble_imagenes.graficar();
// ..................... Fin contenido archivo ..................... 
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }
    }
    
    public static void leerUsuarios(String ruta) throws FileNotFoundException, IOException, InterruptedException{
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(ruta);
            sc = new Scanner(inputStream, "UTF-8");
// ..................... Contenido archivo ..................... 
            ListaSimpleImagenes lista_imagenes_auxiliar;
            while (sc.hasNextLine()) {
                lista_imagenes_auxiliar = new ListaSimpleImagenes();
                String linea = sc.nextLine();
//                System.out.println(linea);
                linea = linea.replace("\\s+", "");
                linea = linea.trim();
                // id_usuario : id_imagen_i, id_imagen_i+1... ;
                String[] id_contenido = linea.split(":");
                // id_usuario
                String id_usuario = "0";
                id_usuario = id_contenido[0];
                // id_imagen_i, id_imagen_i+1... ;
                String contenido = id_contenido[1];
                contenido = contenido.replace(";", "");
                // id_imagen_i, id_imagen_i+1...
                String[] contenido_array = contenido.split(",");
                for (String string : contenido_array) {
                    String imagen_id = "0";
                    imagen_id = string;
                    if(!imagen_id.isEmpty()){
                        imagen_id = imagen_id.replace("\\s+", "");
                        imagen_id = imagen_id.trim();
                        System.out.println("Imagen ID: " + imagen_id);
                        if(!VentanaPrincipal.lista_doble_imagenes.estaVacia()){
                            NodoDC imagen_auxiliar = VentanaPrincipal.lista_doble_imagenes.buscarNodo(Integer.parseInt(imagen_id));
                            lista_imagenes_auxiliar.agregarAlFinal(new NodoSimpleImagenes(Integer.parseInt(imagen_id), imagen_auxiliar));
                        }else{
                            JOptionPane.showMessageDialog(null, "No se han cargado las imagenes", 
                            "Atencion", JOptionPane.WARNING_MESSAGE);
//                            lista_imagenes_auxiliar.agregarAlFinal(new NodoSimpleCapas(Integer.parseInt(capa_id)));
                        }
                    }
                }
                if(!lista_imagenes_auxiliar.esVacia()){
                    VentanaPrincipal.avl_usuarios.insertar(new Usuario(id_usuario, lista_imagenes_auxiliar));
                    System.out.println("Se agrego con su lista de imagenes.");
                }else{
                    VentanaPrincipal.avl_usuarios.insertar(new Usuario(id_usuario, null));
//                    JOptionPane.showMessageDialog(null, "No se han cargado las imagenes", 
//                    "Atencion", JOptionPane.WARNING_MESSAGE);
                    System.out.println("No se han cargado las imagenes");
                }
            }
//            VentanaPrincipal.avl_usuarios.graficar("grafo");
// ..................... Fin contenido archivo ..................... 
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }
    }
}
