/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casitaprojects.smartplayer;

/**
 *
 * @author gezer
 */
public class Playlist {
    
    private String nombre;
    private ListaDobleCircular canciones; // Cada playlist tiene su propia cola de reproducción

    public Playlist(String nombre) {
        this.nombre = nombre;
        this.canciones = new ListaDobleCircular(); 
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ListaDobleCircular getCanciones() {
        return canciones;
    }
    
    // Método para agregar una canción a esta playlist específica
    public void agregarCancion(Cancion cancion) {
        // Aprovechamos el método que ya tienes, enviando una lista de 1 elemento
        java.util.List<Cancion> unicaCancion = new java.util.ArrayList<>();
        unicaCancion.add(cancion);
        this.canciones.cargarLista(unicaCancion, cancion);
    }
    
}
