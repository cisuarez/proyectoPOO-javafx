/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

/**
 *
 * @author DANIEL
 */
public class HoraEntradaInvalida extends RuntimeException{

    public HoraEntradaInvalida() {
    }
    
    public HoraEntradaInvalida(String message) {
        super(message);
    }
    
}
