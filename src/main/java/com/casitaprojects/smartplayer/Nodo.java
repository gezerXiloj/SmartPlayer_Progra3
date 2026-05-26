/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casitaprojects.smartplayer;

/**
 *
 * @author gezer
 */
public class Nodo {
    public Cancion cancion;
    
    // Punteros para Árboles (ABB y AVL)
    public Nodo izquierdo;
    public Nodo derecho;
    public int altura; // Clave para el balanceo del AVL
    
    // Punteros para Listas (Simples, Dobles, Circulares)
    public Nodo siguiente;
    public Nodo anterior;

    public Nodo(Cancion cancion) {
        this.cancion = cancion;
        this.izquierdo = null;
        this.derecho = null;
        this.siguiente = null;
        this.anterior = null;
        this.altura = 1; // Para el AVL, la altura inicial siempre es 1
    }
}
