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

/**
 *
 * @author g
 */
public class Escritura {
    static PrintWriter pw = null;
    
    public static void escribirArchivoDot(String nombre, String contenido){
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
    
    public static void generarImagenDot(String nombre) throws IOException{
        String direccionDot = "dot";
        String paramT = "-Tpng";
        String direccionArchivo = "/home/g/Escritorio/" + nombre + ".dot";
        String paramO = "-o";
        String direccionImagen = "/home/g/Escritorio/" + nombre + ".png";
        
        String[] cmd = new String[5];
        cmd[0] = direccionDot;
        cmd[1] = paramT;
        cmd[2] = direccionArchivo;
        cmd[3] = paramO;
        cmd[4] = direccionImagen;
        
        Runtime rt = Runtime.getRuntime();
        rt.exec(cmd);
        
        // Abrir imagen
        Desktop dt = Desktop.getDesktop();
        dt.open(new File(direccionImagen));
    }
}
