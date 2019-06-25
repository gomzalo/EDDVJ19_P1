/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras.arboles.avl;

import pojos.Usuario;

/**
 *
 * @author G
 */
public class NodoAVL {
    NodoAVL izquierda, derecha;
    int altura;
    Usuario usuario;

    public NodoAVL(Usuario usuario) {
        izquierda = null;
        derecha = null;
        altura = 0;
        this.usuario = usuario;
    }

    public NodoAVL() {
        izquierda = null;
        derecha = null;
        altura = 0;
        usuario = null;
    }

    public NodoAVL getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(NodoAVL izquierda) {
        this.izquierda = izquierda;
    }

    public NodoAVL getDerecha() {
        return derecha;
    }

    public void setDerecha(NodoAVL derecha) {
        this.derecha = derecha;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public Usuario getUsuario() {
            return usuario;
    }

    public void setUsuario(Usuario usuario) {
            this.usuario = usuario;
    }
    
}
