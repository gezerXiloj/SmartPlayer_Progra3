/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casitaprojects.smartplayer;

/**
 *
 * @author gezer
 */
public class Cifrado {
    // Método de César +4 que mencionó el Ingeniero
    public static String encriptarCesar(String texto, int desplazamiento) {
        StringBuilder resultado = new StringBuilder();
        for (char caracter : texto.toCharArray()) {
            if (Character.isLetter(caracter)) {
                char base = Character.isLowerCase(caracter) ? 'a' : 'A';
                resultado.append((char) ((caracter - base + desplazamiento) % 26 + base));
            } else {
                resultado.append(caracter); // Si es un espacio o número, lo deja igual
            }
        }
        return resultado.toString();
    }
    
    public static String desencriptarCesar(String textoCifrado, int desplazamiento) {
        // Desencriptar es lo mismo pero restando el desplazamiento
        return encriptarCesar(textoCifrado, 26 - (desplazamiento % 26));
    }
}
