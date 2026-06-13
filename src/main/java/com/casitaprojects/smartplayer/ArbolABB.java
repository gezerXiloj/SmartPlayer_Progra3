/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casitaprojects.smartplayer;

/**
 *
 * @author gezer
 */
public class ArbolABB {
private Nodo raiz;

    public ArbolABB() {
        this.raiz = null;
    }

    public void insertar(Cancion cancion) {
        raiz = insertarRec(raiz, cancion);
    }

    private Nodo insertarRec(Nodo raiz, Cancion cancion) {
        if (raiz == null) {
            return new Nodo(cancion);
        }
        // Ordenamos alfabéticamente por título
        if (cancion.getTitulo().compareToIgnoreCase(raiz.cancion.getTitulo()) < 0) {
            raiz.izquierdo = insertarRec(raiz.izquierdo, cancion);
        } else if (cancion.getTitulo().compareToIgnoreCase(raiz.cancion.getTitulo()) > 0) {
            raiz.derecho = insertarRec(raiz.derecho, cancion);
        }
        return raiz;
    }  
    
    public Nodo getRaiz() {
        return raiz;
    }
    
    // --- MÉTODO DE BÚSQUEDA ---
    public Cancion buscar(String titulo) {
        Nodo resultado = buscarRec(raiz, titulo);
        return (resultado != null) ? resultado.cancion : null;
    }

    private Nodo buscarRec(Nodo raiz, String titulo) {
        // Si la raíz es nula o si el título coincide (ignorando mayúsculas)
        if (raiz == null || raiz.cancion.getTitulo().equalsIgnoreCase(titulo)) {
            return raiz;
        }
        // Si el título buscado es alfabéticamente menor, buscamos en la izquierda
        if (titulo.compareToIgnoreCase(raiz.cancion.getTitulo()) < 0) {
            return buscarRec(raiz.izquierdo, titulo);
        }
        // Si es mayor, buscamos en la derecha
        return buscarRec(raiz.derecho, titulo);
    }
}
