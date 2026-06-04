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

    // --- GETTERS (Obligatorios para que los Árboles y Hash funcionen) ---
    public String getTitulo() { return titulo; }
    public String getArtista() { return artista; }
    public String getAlbum() { return album; }
    public String getGenero() { return genero; }
    public String getDuracion() { return duracion; }
    public long getTamanoBytes() { return tamanoBytes; }
    public String getRutaArchivo() { return rutaArchivo; }
    public String getAnio() { return anio; }
    public String getId() { return id; }
    
    @Override
    public String toString() {
        return titulo + " - " + artista;
    }
}
