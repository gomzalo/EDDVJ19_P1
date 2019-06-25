/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras.matrices.dispersa;

/**
 *
 * @author g
 */
public class NodoOrtogonal {
    private NodoOrtogonal arriba, abajo, izquierda, derecha;
    private int x, y;
    private String color;

    public NodoOrtogonal(String color, int x, int y) {
        this.arriba = null;
        this.abajo = null;
        this.izquierda = null;
        this.derecha = null;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public NodoOrtogonal getArriba() {
        return arriba;
    }

    public void setArriba(NodoOrtogonal arriba) {
        this.arriba = arriba;
    }

    public NodoOrtogonal getAbajo() {
        return abajo;
    }

    public void setAbajo(NodoOrtogonal abajo) {
        this.abajo = abajo;
    }

    public NodoOrtogonal getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(NodoOrtogonal izquierda) {
        this.izquierda = izquierda;
    }

    public NodoOrtogonal getDerecha() {
        return derecha;
    }

    public void setDerecha(NodoOrtogonal derecha) {
        this.derecha = derecha;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
   
}
