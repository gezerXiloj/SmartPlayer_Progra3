/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casitaprojects.smartplayer;

/**
 *
 * @author gezer
 */
public class Cancion {
    
    private String id; // Útil para búsquedas exactas
    private String titulo;
    private String artista;
    private String album;
    private String genero;
    private String duracion;
    private long tamanoBytes;
    private String rutaArchivo;
    private String rutaCaratula; // Para mostrar la imagen de la canción
    private String anio;

    public Cancion(String titulo, String artista, String album, String genero, String duracion, long tamanoBytes, String rutaArchivo, String anio) {
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.genero = genero;
        this.duracion = duracion;
        this.tamanoBytes = tamanoBytes;
        this.rutaArchivo = rutaArchivo;
        this.anio = anio;
        // Generamos un ID simple basado en el título y artista sin espacios
        this.id = (titulo + artista).replaceAll("\\s+", "").toLowerCase();
    }

    // Aquí van tus Getters y Setters (clic derecho -> Insert Code -> Getters and Setters)
    
    @Override
    public String toString() {
        return titulo + " - " + artista;
    }
}
