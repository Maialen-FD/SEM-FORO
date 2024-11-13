/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HILOS;

public class HiloSemaforo implements Runnable {

    private String estado; // "rojo", "amarillo", "verde"

    public HiloSemaforo() {
        estado = "rojo"; // Estado inicial del semáforo
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public void run() {
        while (true) {
            try {
                switch (estado) {
                    case "rojo":
                        Thread.sleep(5000); // Rojo durante 5 segundos
                        estado = "verde";
                        break;
                    case "verde":
                        Thread.sleep(5000); // Verde durante 5 segundos
                        estado = "amarillo";
                        break;
                    case "amarillo":
                        Thread.sleep(2000); // Amarillo durante 2 segundos
                        estado = "rojo";
                        break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
