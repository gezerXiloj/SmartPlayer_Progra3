/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casitaprojects.smartplayer;

/**
 *
 * @author gezer
 */
public class ArbolAVL {
private Nodo raiz;

    public ArbolAVL() { this.raiz = null; }

    private int altura(Nodo N) {
        if (N == null) return 0;
        return N.altura;
    }

    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    private Nodo rotacionDerecha(Nodo y) {
        Nodo x = y.izquierdo;
        Nodo T2 = x.derecho;
        x.derecho = y;
        y.izquierdo = T2;
        y.altura = max(altura(y.izquierdo), altura(y.derecho)) + 1;
        x.altura = max(altura(x.izquierdo), altura(x.derecho)) + 1;
        return x;
    }

    private Nodo rotacionIzquierda(Nodo x) {
        Nodo y = x.derecho;
        Nodo T2 = y.izquierdo;
        y.izquierdo = x;
        x.derecho = T2;
        x.altura = max(altura(x.izquierdo), altura(x.derecho)) + 1;
        y.altura = max(altura(y.izquierdo), altura(y.derecho)) + 1;
        return y;
    }

    private int getBalance(Nodo N) {
        if (N == null) return 0;
        return altura(N.izquierdo) - altura(N.derecho);
    }

    public void insertar(Cancion cancion) {
        raiz = insertarRec(raiz, cancion);
    }

    private Nodo insertarRec(Nodo nodo, Cancion cancion) {
        if (nodo == null) return (new Nodo(cancion));

        if (cancion.getTitulo().compareToIgnoreCase(nodo.cancion.getTitulo()) < 0)
            nodo.izquierdo = insertarRec(nodo.izquierdo, cancion);
        else if (cancion.getTitulo().compareToIgnoreCase(nodo.cancion.getTitulo()) > 0)
            nodo.derecho = insertarRec(nodo.derecho, cancion);
        else
            return nodo; // No se permiten duplicados

        nodo.altura = 1 + max(altura(nodo.izquierdo), altura(nodo.derecho));
        int balance = getBalance(nodo);

        // Casos de rotación
        if (balance > 1 && cancion.getTitulo().compareToIgnoreCase(nodo.izquierdo.cancion.getTitulo()) < 0)
            return rotacionDerecha(nodo);
        if (balance < -1 && cancion.getTitulo().compareToIgnoreCase(nodo.derecho.cancion.getTitulo()) > 0)
            return rotacionIzquierda(nodo);
        if (balance > 1 && cancion.getTitulo().compareToIgnoreCase(nodo.izquierdo.cancion.getTitulo()) > 0) {
            nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
            return rotacionDerecha(nodo);
        }
        if (balance < -1 && cancion.getTitulo().compareToIgnoreCase(nodo.derecho.cancion.getTitulo()) < 0) {
            nodo.derecho = rotacionDerecha(nodo.derecho);
            return rotacionIzquierda(nodo);
        }
        return nodo;
    } 
    
    public Nodo getRaiz() {
        return raiz;
    }
}
