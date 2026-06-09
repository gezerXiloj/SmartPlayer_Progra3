/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casitaprojects.smartplayer;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author gezer
 */
public class Playlist {
 private String nombre;
    private List<Cancion> canciones;

    public Playlist(String nombre) {
        this.nombre = nombre;
        this.canciones = new ArrayList<>();
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public List<Cancion> getCanciones() { return canciones; }

    public void agregarCancion(Cancion cancion) {
        // Validamos que no agregue la misma canción dos veces a la misma lista
        if (!canciones.contains(cancion)) {
            canciones.add(cancion);
    }
    }
}
