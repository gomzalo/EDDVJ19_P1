/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivos;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author g
 */
public class Escritura {
    public static String direccion_imagen_recientemente_generada;
    static PrintWriter pw = null;
    
    public static void escribirArchivoDot(String nombre, String contenido){
        System.out.println("Escribiendo dot.");
        String ruta = "/home/g/Escritorio/" + nombre + ".dot";
        
        try {
            pw = new PrintWriter(new FileWriter(ruta));
            pw.println(contenido);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(null != pw){
                    pw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    
    public static void generarImagenDot(String nombre) throws IOException, InterruptedException, InterruptedException, InterruptedException{
        System.out.println("Generando imagen, a partir de dot.");
        String direccionDot = "dot";
        String paramT = "-Tjpeg";
        String direccionArchivo = "/home/g/Escritorio/" + nombre + ".dot";
        String paramO = "-o";
        String direccionImagen = "/home/g/Escritorio/" + nombre + ".jpeg";
        direccion_imagen_recientemente_generada = direccionArchivo;
        String[] cmd = new String[5];
        cmd[0] = direccionDot;
        cmd[1] = paramT;
        cmd[2] = direccionArchivo;
        cmd[3] = paramO;
        cmd[4] = direccionImagen;
        
        Runtime rt = Runtime.getRuntime();
        rt.exec(cmd);
        // Abrir imagen
        try {
            Thread.sleep(2000);
            Desktop dt = Desktop.getDesktop();
            dt.open(new File(direccionImagen));
        } catch (InterruptedException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void abrirImagen(String direccionImagen) throws IOException{
// Abrir imagen
        try {
            Thread.sleep(2000);
            Desktop dt = Desktop.getDesktop();
            dt.open(new File(direccionImagen));
        } catch (InterruptedException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
