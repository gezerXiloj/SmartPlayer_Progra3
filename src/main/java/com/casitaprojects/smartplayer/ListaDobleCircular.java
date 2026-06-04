/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casitaprojects.smartplayer;

import java.util.List;
import java.util.Random;

/**
 *
 * @author gezer
 */
public class ListaDobleCircular {
    private Nodo cabeza;
    private Nodo nodoActual; // La canción que está sonando ahorita
    private int tamaño;

    public ListaDobleCircular() {
        this.cabeza = null;
        this.nodoActual = null;
        this.tamaño = 0;
    }

    // Método para cargar TODA la tabla de un solo golpe
    public void cargarLista(List<Cancion> canciones, Cancion cancionInicial) {
        cabeza = null;
        tamaño = 0;
        
        for (Cancion c : canciones) {
            insertar(c);
        }
        
        // Buscamos cuál fue la que el usuario le dio doble clic para que sea la actual
        Nodo temp = cabeza;
        if (temp != null) {
            do {
                if (temp.cancion.getId().equals(cancionInicial.getId())) {
                    nodoActual = temp;
                    break;
                }
                temp = temp.siguiente;
            } while (temp != cabeza);
        }
    }

    private void insertar(Cancion cancion) {
        Nodo nuevo = new Nodo(cancion);
        if (cabeza == null) {
            cabeza = nuevo;
            cabeza.siguiente = cabeza;
            cabeza.anterior = cabeza;
        } else {
            Nodo ultimo = cabeza.anterior;
            nuevo.siguiente = cabeza;
            nuevo.anterior = ultimo;
            cabeza.anterior = nuevo;
            ultimo.siguiente = nuevo;
        }
        tamaño++;
    }

    // Navegación
    public Cancion obtenerSiguiente(boolean modoAleatorio) {
        if (cabeza == null) return null;
        
        if (modoAleatorio) {
            // Magia del Shuffle: Saltamos N veces aleatoriamente
            Random rand = new Random();
            int saltos = rand.nextInt(tamaño);
            for (int i = 0; i < saltos; i++) {
                nodoActual = nodoActual.siguiente;
            }
        } else {
            nodoActual = nodoActual.siguiente; // Avanza normal (al llegar al final, da la vuelta por ser circular)
        }
        return nodoActual.cancion;
    }

    public Cancion obtenerAnterior() {
        if (cabeza == null) return null;
        nodoActual = nodoActual.anterior; // Retrocede normal
        return nodoActual.cancion;
    }
    
    public Cancion getCancionActual() {
        return nodoActual != null ? nodoActual.cancion : null;
    }
}
