/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MAIN;

import GUI.VentanaPrincipal;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        int cantidadAutos = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de autos:"));
        new VentanaPrincipal(cantidadAutos).setVisible(true);
    }
}
